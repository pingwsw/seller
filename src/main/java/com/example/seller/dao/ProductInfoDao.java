package com.example.seller.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.seller.entity.ProductInfo;

public interface ProductInfoDao extends JpaRepository<ProductInfo, String> {
	List<ProductInfo> findByProductStatus(Integer productStatus);
}
