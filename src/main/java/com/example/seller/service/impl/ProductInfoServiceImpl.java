package com.example.seller.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.seller.dao.ProductInfoDao;
import com.example.seller.entity.OrderDetail;
import com.example.seller.entity.ProductInfo;
import com.example.seller.enums.ProductStatusEnum;
import com.example.seller.enums.ResultEnum;
import com.example.seller.exception.SellException;
import com.example.seller.service.ProductInfoService;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
	
	@Autowired
	ProductInfoDao productInfoDao;
	

	@Override
	public ProductInfo save(ProductInfo productInfo) {
		return productInfoDao.save(productInfo);
	}

	@Override
	public ProductInfo findOne(String productId) {
		return productInfoDao.findOne(productId);
	}

	@Override
	public Page<ProductInfo> findAll(Pageable pageable) {
		return productInfoDao.findAll(pageable);
	}

	@Override
	public List<ProductInfo> findUpAll() {
		return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
	}

	@Transactional
	public void decreaseStock(List<OrderDetail> orderDetailList) {
		for (OrderDetail orderDetail : orderDetailList) {
			ProductInfo productInfo = productInfoDao.findOne(orderDetail.getProductId());
			if(productInfo == null){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			Integer result = productInfo.getProductStock() - orderDetail.getProductQuantity();
			if(result < 0){
				throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
			}
			productInfo.setProductStock(result);
			productInfoDao.save(productInfo);
		}
	}

	@Transactional
	public void increaseStock(List<OrderDetail> orderDetailList) {
		for (OrderDetail orderDetail : orderDetailList) {
			ProductInfo productInfo = productInfoDao.findOne(orderDetail.getProductId());
			if(productInfo == null){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			Integer result = productInfo.getProductStock() + orderDetail.getProductQuantity();
			productInfo.setProductStock(result);
			productInfoDao.save(productInfo);
		}
		
	}

}
