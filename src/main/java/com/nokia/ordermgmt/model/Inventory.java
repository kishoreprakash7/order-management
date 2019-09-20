package com.nokia.ordermgmt.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Kishore Prakash
 * 
 */
@Table
public class Inventory {
	
	@PrimaryKey
	@ApiModelProperty(notes = "Name of the product")
	private @NotNull String productName;
	@ApiModelProperty(notes = "ID of the product")
	private @NotNull String productID;
	@ApiModelProperty(notes = "Price of the product")
	private @NotNull int price;
	@ApiModelProperty(notes = "Availability of the product")
	private String status;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
