package com.nokia.ordermgmt.model;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Kishore Prakash
 *
 */
@Table
public class Shipping {
	
	@PrimaryKey
	@ApiModelProperty(notes = "Shipping ID of order")
	private @NotNull String shippingID;
	@ApiModelProperty(notes = "Date of shipment")
	private @NotNull String orderDate;
	@ApiModelProperty(notes = "Products list to be shipped")
	private @NotNull List<String> productList;
	@ApiModelProperty(notes = "Invoice number of bill generation")
	private @NotNull String invoiceNumber;
	
	
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String date) {
		this.orderDate = date;
	}
	public List<String> getProductList() {
		return productList;
	}
	public void setProductList(List<String> productList) {
		this.productList = productList;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getShippingID() {
		return shippingID;
	}
	public void setShippingID(String shippingID) {
		this.shippingID = shippingID;
	}
}
