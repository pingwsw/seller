package com.example.seller.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.seller.dao.ProductCategoryDao;
import com.example.seller.entity.ProductCategory;
import com.example.seller.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	ProductCategoryDao productCategoryDao;
	@Override
	public ProductCategory findOne(Integer id) {
		return productCategoryDao.findOne(id);
	}

	@Override
	public List<ProductCategory> findAll() {
		return productCategoryDao.findAll();
	}

	@Override
	public ProductCategory save(ProductCategory productCategory) {
		return productCategoryDao.save(productCategory);
	}

	@Override
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
		return productCategoryDao.findByCategoryTypeIn(categoryTypeList);
	}

}
