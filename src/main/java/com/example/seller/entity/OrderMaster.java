package com.example.seller.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@Data
public class OrderMaster {
	@Id
	private String orderId;
	private String buyerName;
	private String buyerPhone;
	private String buyerAddress;
	private String buyerOpenid;
	private BigDecimal orderAmount;
	private Integer orderStatus;
	private Integer payStatus;
	private Date createTime;
	private Date updateTime; 
}
