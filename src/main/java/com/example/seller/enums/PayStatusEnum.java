package com.example.seller.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
	WAIT(0,"等待支付"),
	SUCCESS(1,"支付成功");
	private Integer code;
	private String message;
	private PayStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
