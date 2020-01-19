package com.example.seller.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

/**
 *	jpa会直接把数据库中的下划线转驼峰命名
 * @author Administrator
 *	如果表名和类名不一致,@table(name="表名")
 */
@Entity
@Data
@DynamicUpdate
public class ProductCategory {
	@Id
	@GeneratedValue
	private Integer categoryId;
	private String categoryName;
	private Integer categoryType;
	private Date createTime;
	private Date updateTime;
}	