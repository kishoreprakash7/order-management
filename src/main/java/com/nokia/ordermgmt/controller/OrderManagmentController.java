package com.nokia.ordermgmt.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nokia.ordermgmt.exception.CustomException;
import com.nokia.ordermgmt.model.Inventory;
import com.nokia.ordermgmt.model.Invoice;
import com.nokia.ordermgmt.service.impl.OrderManagmentServiceImpl;
import com.nokia.ordermgmt.wrapper.OrderItemWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Kishore Prakash
 *
 */
@RestController
@RequestMapping("/ordermgmt")
@Api(value="orderManagement", description="REST operations for Nokia Order Management Application")
public class OrderManagmentController {

	private final Logger LOGGER = LoggerFactory.getLogger(OrderManagmentController.class);

	@Autowired
	OrderManagmentServiceImpl serviceImpl;

	/**
	 * @param inventory
	 * @return ResponseEntity
	 * @throws CustomException 
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/addProducts")
	@ApiOperation(value = "Add product to inventory", response = ResponseEntity.class)
	public ResponseEntity addProduct(@RequestBody Inventory inventory) throws CustomException {

		LOGGER.info("controller for addProduct invoked"); 
		ResponseEntity response = serviceImpl.addProductToInventory(inventory);
		return response;
	}

	/**
	 * @return ResponseEntity
	 * @throws CustomException 
	 */
	@GetMapping("/getAllProducts")
	@ApiOperation(value = "Fetch all products from inventory", response = ResponseEntity.class)
	public ResponseEntity<List<Inventory>> getAllProducts() throws CustomException {

		LOGGER.info("controller for getAllProducts invoked");
		List<Inventory> inventoryList = new ArrayList<Inventory>();
		inventoryList = serviceImpl.getAllProducts();

		return new ResponseEntity<List<Inventory>>(inventoryList, HttpStatus.OK);
	}

	/**
	 * @param orderItems
	 * @return ResponseEntity
	 * @throws CustomException 
	 */
	@PostMapping("/placeOrder")
	@ApiOperation(value = "Place order of products", response = ResponseEntity.class)
	public ResponseEntity<Invoice> placeOrder(@RequestBody OrderItemWrapper orderItems) throws CustomException {

		LOGGER.info("controller for placeOrder invoked");
		Invoice invoice = new Invoice();
		invoice = serviceImpl.placeOrderByProducts(orderItems);

		return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
	}

	/**
	 * @param productName
	 * @param status
	 * @return ResponseEntity
	 * @throws CustomException 
	 */
	@SuppressWarnings("rawtypes")
	@PutMapping("/updateProductStatus")
	@ApiOperation(value = "Update product availability", response = ResponseEntity.class)
	public ResponseEntity updateProductStatus(@RequestParam("productName") String productName, 
			@RequestParam("status") String status) throws CustomException {

		LOGGER.info("controller for updateProductStatus invoked");
		ResponseEntity response = serviceImpl.updateProductStatus(productName, status);
		return response;

	}

	/**
	 * @param productName
	 * @return ResponseEntity
	 * @throws CustomException 
	 */
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/deleteProduct")
	@ApiOperation(value = "Delete product by name", response = ResponseEntity.class)
	public ResponseEntity deleteProduct(@RequestParam("productName") String productName) throws CustomException {

		LOGGER.info("controller for deleteProduct invoked");
		ResponseEntity response = serviceImpl.deleteProduct(productName);
		return response;
	}

}
