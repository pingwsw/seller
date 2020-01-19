package com.example.seller.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.example.seller.entity.OrderDetail;
import com.example.seller.utils.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;


/**
 * @JsonInclude(JsonInclude.Include.NON_NULL)
 * 配置json转换时如果是null,不显示，
 * 这个注解针对某个类，也可以在配置文件中全局配置
 *
 */
@Data
public class OrderDTO {
	private String orderId;
	private String buyerName;
	private String buyerPhone;
	private String buyerAddress;
	private String buyerOpenid;
	private BigDecimal orderAmount;
	private Integer orderStatus;
	private Integer payStatus;
	@JsonSerialize(using=Date2LongSerializer.class)
	private Date createTime;
	@JsonSerialize(using=Date2LongSerializer.class)
	private Date updateTime;
	private List<OrderDetail> orderDetailList;
}
