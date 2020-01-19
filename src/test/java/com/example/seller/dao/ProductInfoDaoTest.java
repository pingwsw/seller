package com.example.seller.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.seller.entity.ProductInfo;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoDaoTest {
	@Autowired
	ProductInfoDao dao;
	@Test
	public void testSave(){
		ProductInfo entity = new ProductInfo();
		entity.setProductId("789");
		entity.setCategoryType(2);
		entity.setProductIcon("http://xxxxx");
		entity.setProductName("牛肉饭");
		entity.setProductPrice(new BigDecimal(23));
		entity.setProductStatus(0);
		entity.setProductStock(20);
		dao.save(entity);
	}
	@Test
	public void testfindByProductStatus(){
		List<ProductInfo> list = dao.findByProductStatus(1);
		System.out.println(list);
	}
}
