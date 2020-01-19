package com.example.seller.service;

import java.util.List;

import com.example.seller.entity.ProductCategory;

public interface ProductCategoryService {
	public ProductCategory findOne(Integer id);
	public List<ProductCategory> findAll();
	public ProductCategory save(ProductCategory productCategory);
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
