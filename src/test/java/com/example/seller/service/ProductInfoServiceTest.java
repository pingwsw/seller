package com.example.seller.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.seller.entity.ProductInfo;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoServiceTest {
	
	@Autowired
	ProductInfoService productInfoService;
	@Test
	public void testSave() {
		
	}

	@Test
	public void testFindOne() {
		ProductInfo entity = productInfoService.findOne("789");
		System.out.println(entity);
	}

	@Test
	public void testFindUpAll() {
		List<ProductInfo> list = productInfoService.findUpAll();
		System.out.println(list);
	}

	@Test
	public void testFindAll() {
		PageRequest pageRequest = new PageRequest(1, 2);
		Page<ProductInfo> pageBean = productInfoService.findAll(pageRequest);
		System.out.println(pageBean.getTotalPages());
		System.out.println(pageBean.getTotalElements());
		System.out.println(pageBean.getContent());
	}

}
