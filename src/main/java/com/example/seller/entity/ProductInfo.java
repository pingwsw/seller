package com.example.seller.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@Data
@DynamicUpdate
public class ProductInfo {
	@Id
	private String productId;
	private String productName;
	private BigDecimal productPrice;
	private Integer productStock;
	private String productDescription;
	private String productIcon;
	private Integer productStatus;
	private Integer categoryType;
	private Date createTime;
	private Date updateTime;
}
