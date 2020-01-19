package com.example.seller.service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.seller.dto.OrderDTO;
import com.example.seller.entity.OrderDetail;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderServiceTest {
	@Autowired
	OrderService orderService;
	

	@Test
	public void testCreate() {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerAddress("花果山");
		orderDTO.setBuyerName("大师兄");
		orderDTO.setBuyerOpenid("xxaabb");
		orderDTO.setBuyerPhone("13935941872");
		ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProductId("1234");
		orderDetail.setProductQuantity(2);
		orderDetailList.add(orderDetail);
		OrderDetail orderDetail2 = new OrderDetail();
		orderDetail2.setProductId("4567");
		orderDetail2.setProductQuantity(1);
		orderDetailList.add(orderDetail2);
		orderDTO.setOrderDetailList(orderDetailList);
		orderService.create(orderDTO);
	}

	@Test
	public void testFindList() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindOne() {
		String orderId = "7734541579401718349";
		OrderDTO orderDTO = orderService.findOne(orderId);
		log.info("orderDTO={}",orderDTO);
		
	}

	@Test
	public void testCancel() {
		OrderDTO orderDTO = orderService.findOne("7734541579401718349");
		orderService.cancel(orderDTO);
		log.info("[取消订单测试] orderDTO={}",orderDTO);
	}

	@Test
	public void testPaid() {
		fail("Not yet implemented");
	}

	@Test
	public void testFinish() {
		fail("Not yet implemented");
	}
	
}
