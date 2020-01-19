package com.example.seller.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.internal.CriteriaImpl.OrderEntry;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.example.seller.dao.OrderDetailDao;
import com.example.seller.dao.OrderMasterDao;
import com.example.seller.dto.OrderDTO;
import com.example.seller.entity.OrderDetail;
import com.example.seller.entity.OrderMaster;
import com.example.seller.entity.ProductInfo;
import com.example.seller.enums.OrderStatusEnum;
import com.example.seller.enums.PayStatusEnum;
import com.example.seller.enums.ResultEnum;
import com.example.seller.exception.SellException;
import com.example.seller.service.OrderService;
import com.example.seller.service.ProductInfoService;
import com.example.seller.utils.KeyUtil;
import com.example.seller.utils.OrderMaster2OrderDTOConvert;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderMasterDao orderMasterDao;
	@Autowired
	OrderDetailDao orderDetailDao;
	@Autowired
	ProductInfoService productInfoService;
	@Transactional
	public OrderDTO create(OrderDTO orderDTO) {
		/** 计算金额、生成主键、保存子表 **/
		List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
		BigDecimal orderAmount = BigDecimal.ZERO;
		String orderId = KeyUtil.generateUniqueKey();
		for (OrderDetail orderDetail : orderDetailList) {
			ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
			if(productInfo == null){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			BigDecimal amount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()));
			orderAmount = orderAmount.add(amount);
			orderDetail.setDetailId(KeyUtil.generateUniqueKey());
			orderDetail.setOrderId(orderId);
			BeanUtils.copyProperties(productInfo, orderDetail);
			orderDetailDao.save(orderDetail);
		}
		/**保存主表**/
		OrderMaster orderMaster = new OrderMaster();
		orderDTO.setOrderId(orderId);
		orderDTO.setOrderAmount(orderAmount);
		orderDTO.setOrderStatus(0);
		orderDTO.setPayStatus(0);
		BeanUtils.copyProperties(orderDTO, orderMaster);
		orderMasterDao.save(orderMaster);
		/** 减库存 **/
		productInfoService.decreaseStock(orderDetailList);
		return orderDTO;
	}
	@Override
	public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
		Page<OrderMaster> pageBean = orderMasterDao.findByBuyerOpenid(buyerOpenid, pageable);
		List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConvert.convert(pageBean.getContent());
		PageImpl<OrderDTO> pageImpl = new PageImpl<>(orderDTOList, pageable, pageBean.getTotalElements());
		return pageImpl;
	}
	@Override
	public OrderDTO findOne(String orderId) {
		OrderMaster orderMaster = orderMasterDao.findOne(orderId);
		if(orderMaster == null){
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderMaster.getOrderId());
		if(CollectionUtils.isEmpty(orderDetailList)){
			throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
		}
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		orderDTO.setOrderDetailList(orderDetailList);
		return orderDTO;
	}
	@Transactional
	public OrderDTO cancel(OrderDTO orderDTO) {
		/** 判断订单状态**/
		if(OrderStatusEnum.NEW.getCode() != orderDTO.getOrderStatus()){
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		/** 更新订单状态 **/
		orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updateOrder = orderMasterDao.save(orderMaster);
		if(updateOrder == null || OrderStatusEnum.CANCEL.getCode() != updateOrder.getOrderStatus()){
			log.error("[取消订单] 订单更新失败,orderMaster={}",orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		/** 返还库存 **/
		productInfoService.increaseStock(orderDTO.getOrderDetailList());
		/** 如果已经支付需要退款 **/
		if(PayStatusEnum.SUCCESS.getCode() == orderMaster.getPayStatus()){
			//TODO 退款代码以后完善 
		}
		return orderDTO;
	}
	@Override
	@Transactional
	public OrderDTO paid(OrderDTO orderDTO) {
		/** 判断订单状态**/
		if(OrderStatusEnum.NEW.getCode() != orderDTO.getOrderStatus()){
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		if(PayStatusEnum.WAIT.getCode() != orderDTO.getPayStatus()){
			throw new SellException(ResultEnum.PAY_STATUS_ERROR);
		}
		/** 更新订单状态 **/
		orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updateOrder = orderMasterDao.save(orderMaster);
		if(updateOrder == null || PayStatusEnum.SUCCESS.getCode() != updateOrder.getPayStatus()){
			log.error("[支付完成回调] 订单更新失败,orderMaster={}",orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		return orderDTO;
	}
	@Transactional
	public OrderDTO finish(OrderDTO orderDTO) {
		/** 判断订单状态**/
		if(OrderStatusEnum.NEW.getCode() != orderDTO.getOrderStatus()){
			throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
		}
		/** 更新订单状态 **/
		orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updateOrder = orderMasterDao.save(orderMaster);
		if(updateOrder == null || OrderStatusEnum.FINISHED.getCode() != updateOrder.getOrderStatus()){
			log.error("[完结订单] 订单更新失败,orderMaster={}",orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
		}
		return orderDTO;
	}
	
	
	
}
