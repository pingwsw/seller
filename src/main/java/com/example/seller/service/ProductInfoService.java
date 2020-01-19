package com.example.seller.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.seller.entity.OrderDetail;
import com.example.seller.entity.ProductInfo;

public interface ProductInfoService {
	//新增或更新
	public ProductInfo save(ProductInfo productInfo);
	//根据 id 查询商品
	public ProductInfo findOne(String productId);
	//得到所有上线的商品 ，手机端用
	public List<ProductInfo> findUpAll();
	//查询所有商品，卖家管理端用
	public Page<ProductInfo> findAll(Pageable pageable);
	//加库存   
	public void decreaseStock(List<OrderDetail> orderDetailList);
	//减库存	
	public void increaseStock(List<OrderDetail> orderDetailList);
}
