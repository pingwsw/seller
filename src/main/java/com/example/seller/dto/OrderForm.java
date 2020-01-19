package com.example.seller.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderForm {
	
	@NotBlank(message="姓名不能为空")
	private String name;
	@NotBlank(message="电话不能为空")
	private String phone;
	@NotBlank(message="地址不能为空")
	private String address;
	@NotBlank(message="openid不能为空")
	private String openid;
	@NotBlank(message="购物车不可以为空")
	private String items;
}
