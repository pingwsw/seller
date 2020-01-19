package com.example.seller.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;
import javax.print.attribute.HashAttributeSet;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.seller.dto.OrderDTO;
import com.example.seller.dto.OrderForm;
import com.example.seller.enums.ResultEnum;
import com.example.seller.exception.SellException;
import com.example.seller.service.OrderService;
import com.example.seller.utils.OrderForm2OrderDTOConvert;
import com.example.seller.utils.ResultVOUtil;
import com.example.seller.vo.ResultVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
	@Autowired
	OrderService orderService;
	@PostMapping("/create")
	public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
		}
		OrderDTO orderDTO = OrderForm2OrderDTOConvert.convert(orderForm);
		if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
			throw new SellException(ResultEnum.CART_EMPTY);
		}
		OrderDTO create = orderService.create(orderDTO);
		HashMap<String, String> hashMap = new HashMap<String,String>();
		hashMap.put("orderId", create.getOrderId());
		return ResultVOUtil.success(hashMap);
	}
	@RequestMapping("/list")
	public ResultVO<List<OrderDTO>> list(@RequestParam(defaultValue="0") Integer page,
										 @RequestParam(defaultValue="2") Integer size,
										 String openid){
		if(StringUtils.isEmpty(openid)){
			log.error("[查询订单列表] 没有参数openid");
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		PageRequest pageRequest = new PageRequest(page, size);
		Page<OrderDTO> orderList = orderService.findList(openid, pageRequest);
		return ResultVOUtil.success(orderList.getContent());
	}
	@GetMapping("/detail")
	public ResultVO<OrderDTO> detail(String openid,String orderId){
		if(StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)){
			log.error("[查询订单详情] 参数不全");
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		OrderDTO orderDTO = orderService.findOne(orderId);
		if(!orderDTO.getBuyerOpenid().equals(openid)){
			log.error("[查询订单详情] openid不一致,request.openid＝{},order.openid={}",openid,orderDTO.getBuyerOpenid());
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		return ResultVOUtil.success(orderDTO);
	}
	@PostMapping("/cancel")
	public ResultVO cancel(String openid,String orderId){
		if(StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)){
			log.error("[取消订单] 参数不全");
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		OrderDTO orderDTO = orderService.findOne(orderId);
		if(!orderDTO.getBuyerOpenid().equals(openid)){
			log.error("[取消订单] openid不一致,request.openid＝{},order.openid={}",openid,orderDTO.getBuyerOpenid());
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		OrderDTO cancel = orderService.cancel(orderDTO);
		return ResultVOUtil.success();
	}
}

