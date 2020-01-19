package com.example.seller.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.seller.entity.ProductCategory;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryServiceTest {
	@Autowired
	ProductCategoryService service;
	@Test
	public void testFindOne() {
		ProductCategory entity = service.findOne(1);
		System.out.println(entity);
	}

	@Test
	public void testFindAll() {
		 List<ProductCategory> list = service.findAll();
		 System.out.println(list);
	}

	@Test
	public void testSave() {
		 ProductCategory productCategory = new ProductCategory();
		 productCategory.setCategoryName("小孩最爱");
		 productCategory.setCategoryType(10);
		 service.save(productCategory);
	}

	@Test
	public void testFindByCategoryTypeIn() {
		 List<ProductCategory> list = service.findByCategoryTypeIn(Arrays.asList(10,2));
		 System.out.println(list);
	}

}
