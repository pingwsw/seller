package com.example.seller.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.seller.entity.OrderMaster;

public interface OrderMasterDao extends JpaRepository<OrderMaster, String> {
	public Page<OrderMaster> findByBuyerOpenid(String buyerOpenid,Pageable pageable);
}
