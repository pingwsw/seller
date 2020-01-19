package com.example.seller.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.seller.entity.OrderDetail;

public interface OrderDetailDao extends JpaRepository<OrderDetail, String> {
	public List<OrderDetail> findByOrderId(String orderId);
}
