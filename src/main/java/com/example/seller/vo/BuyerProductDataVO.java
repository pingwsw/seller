package com.example.seller.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BuyerProductDataVO {
	@JsonProperty("name")
	private String categoryName;
	@JsonProperty("type")
	private Integer categoryType;
	@JsonProperty("foods")
	private List<FoodVO> foods;
}
