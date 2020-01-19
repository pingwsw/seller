package com.example.seller.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.example.seller.dto.OrderDTO;
import com.example.seller.entity.OrderMaster;

public class OrderMaster2OrderDTOConvert {
	public static OrderDTO convert(OrderMaster orderMaster){
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		return orderDTO;
	}
	
	public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
		ArrayList<OrderDTO> list = new ArrayList<OrderDTO>();
		for (OrderMaster orderMaster : orderMasterList) {
			list.add(convert(orderMaster));
		}
		return list;
	}
}
