package com.example.seller.vo;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FoodVO {
	@JsonProperty("id")
	private String productId;
	@JsonProperty("name")
	private String productName;
	@JsonProperty("price")
	private BigDecimal productPrice;
	@JsonProperty("description")
	private String productDescription;
	@JsonProperty("icon")
	private String productIcon;

}
