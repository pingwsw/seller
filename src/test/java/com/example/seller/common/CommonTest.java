package com.example.seller.common;

import org.junit.Test;

import com.example.seller.entity.OrderMaster;
import com.example.seller.enums.OrderStatusEnum;

public class CommonTest {
	@Test
	public void test01(){
		OrderMaster orderMaster = new OrderMaster();
		orderMaster.setOrderStatus(0);
		orderMaster.setPayStatus(0);
		System.out.println(OrderStatusEnum.FINISHED.getCode()!=orderMaster.getOrderStatus() );
	}
}	
