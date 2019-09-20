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
public class Invoice {
	
	@PrimaryKey
	@ApiModelProperty(notes = "Invoice number generated")
	private @NotNull String invoiceNumber;
	@ApiModelProperty(notes = "Date of invoice generation")
	private @NotNull String orderDate;
	@ApiModelProperty(notes = "List of products ordered")
	private @NotNull List<String> productList;
	@ApiModelProperty(notes = "Total price of order placed")
	private @NotNull int totalPrice;
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public List<String> getProductList() {
		return productList;
	}
	public void setProductList(List<String> productList) {
		this.productList = productList;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
}
