package com.example.seller.utils;

import java.util.List;

import com.example.seller.dto.OrderDTO;
import com.example.seller.dto.OrderForm;
import com.example.seller.entity.OrderDetail;
import com.example.seller.enums.ResultEnum;
import com.example.seller.exception.SellException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderForm2OrderDTOConvert {
	public static OrderDTO convert(OrderForm orderForm){
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerAddress(orderForm.getAddress());
		orderDTO.setBuyerName(orderForm.getName());
		orderDTO.setBuyerOpenid(orderForm.getOpenid());
		orderDTO.setBuyerPhone(orderForm.getPhone());
		Gson gson = new Gson();
		
		try{
			List<OrderDetail> fromJson = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
			orderDTO.setOrderDetailList(fromJson);
		}catch(Exception e){
			log.error("[购物车转换失败] orderForm={}",orderForm);
			throw new SellException(ResultEnum.PARAM_ERROR);
		}
		return orderDTO;
	}
}
