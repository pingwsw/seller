package com.example.seller.dao;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.seller.entity.ProductCategory;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryDaoTest {
	@Autowired
	ProductCategoryDao dao;
	@Test
	public void testSave(){
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryName("女人最爱");
		productCategory.setCategoryType(3);
		productCategory = dao.save(productCategory);
		Assert.assertNotNull(productCategory);
	}
	@Test
	public void testDelete(){
		dao.delete(3);
	}
	@Test
	public void testFindOne(){
		ProductCategory one = dao.findOne(1);
		System.out.println(one);
	}
	@Test
	public void testUpdate(){
		ProductCategory one = new ProductCategory();
		one.setCategoryId(4);
		one.setCategoryName("老人最爱");
		one.setCategoryType(3);
		dao.save(one);
	}
	@Test
	public void findByCategoryTypeInTest(){
		List<ProductCategory> list = dao.findByCategoryTypeIn(Arrays.asList(1,2,3,4));
		System.out.println(list);
	}
}
