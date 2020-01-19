package com.example.seller.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.seller.dto.OrderDTO;

public interface OrderService {
	/** 创建订单**/
	OrderDTO create(OrderDTO orderDTO);
	/** 根据openid查询订单**/
	Page<OrderDTO> findList(String buyerOpenid,Pageable pageable);
	/** 查询单个订单 **/
	OrderDTO findOne(String orderId);
	/** 取消订单**/
	OrderDTO cancel(OrderDTO orderDTO);	
	/** 支付订单**/
	OrderDTO paid(OrderDTO orderDTO);
	/** 完结订单**/
	OrderDTO finish(OrderDTO orderDTO);
}
