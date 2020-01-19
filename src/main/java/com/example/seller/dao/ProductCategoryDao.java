package com.example.seller.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.seller.entity.ProductCategory;

public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer> {
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
