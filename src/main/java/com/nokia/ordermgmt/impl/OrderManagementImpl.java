package com.nokia.ordermgmt.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.nokia.ordermgmt.exception.CustomException;
import com.nokia.ordermgmt.model.Inventory;
import com.nokia.ordermgmt.model.Invoice;
import com.nokia.ordermgmt.wrapper.OrderItemWrapper;

/**
 * @author Kishore Prakash
 *
 */
public interface OrderManagementImpl {

	/**
	 * @param inventory
	 * @return ResponseEntity
	 * @throws CustomException 
	 */
	public ResponseEntity addProductToInventory(Inventory inventory) throws CustomException;

	/**
	 * @return inventory List
	 * @throws CustomException 
	 */
	public List<Inventory> getAllProducts() throws CustomException;

	/**
	 * @param orderItems
	 * @return Invoice
	 * @throws CustomException 
	 */
	public Invoice placeOrderByProducts(OrderItemWrapper orderItems) throws CustomException;

	/**
	 * @param productName
	 * @param status
	 * @return ResponseEntity
	 * @throws CustomException 
	 */
	public ResponseEntity updateProductStatus(String productName, String status) throws CustomException;

	/**
	 * @param productName
	 * @return ResponseEntity
	 * @throws CustomException 
	 */
	public ResponseEntity deleteProduct(String productName) throws CustomException;

}
