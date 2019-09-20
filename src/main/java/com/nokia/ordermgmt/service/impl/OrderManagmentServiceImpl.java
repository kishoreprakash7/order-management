package com.nokia.ordermgmt.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nokia.ordermgmt.exception.CustomException;
import com.nokia.ordermgmt.impl.OrderManagementImpl;
import com.nokia.ordermgmt.model.Inventory;
import com.nokia.ordermgmt.model.Invoice;
import com.nokia.ordermgmt.repository.InventoryRepository;
import com.nokia.ordermgmt.repository.InvoiceRepository;
import com.nokia.ordermgmt.wrapper.OrderItemWrapper;

/**
 * @author Kishore Prakash
 *
 */
@Service
public class OrderManagmentServiceImpl implements OrderManagementImpl {

	private final Logger LOGGER = LoggerFactory.getLogger(OrderManagmentServiceImpl.class);

	@Autowired
	InventoryRepository inventoryRepository;

	@Autowired
	InvoiceRepository invoiceRepository;

	/**
	 * Add products to inventory
	 * @throws CustomException 
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity addProductToInventory(Inventory inventory) throws CustomException {

		LOGGER.info("addProductToInventory invoked");

		if(inventory != null) {
			//sets default status to Available
			inventory.setStatus("Available");
			inventoryRepository.save(inventory);
			LOGGER.info("Product added successfully");
			return new ResponseEntity(HttpStatus.OK);
		} else {
			throw new CustomException("Please enter valid product");
		}
	}

	/**
	 * Fetch all products from inventory
	 * @throws CustomException 
	 */
	@Override
	public List<Inventory> getAllProducts() throws CustomException {

		LOGGER.info("getAllProducts invoked");
		List<Inventory> inventoryList = new ArrayList<Inventory>();
		Iterable<Inventory> result = inventoryRepository.findAll();

		if(result != null) {
			result.forEach(inventoryList::add);
			LOGGER.info("products fetched successfully");
			return inventoryList;
		} else {
			throw new CustomException("Products could not be fetched");
		}

	}

	/**
	 * Place order of items
	 * @throws CustomException 
	 */
	@Override
	public Invoice placeOrderByProducts(OrderItemWrapper orderItems) throws CustomException {

		LOGGER.info("placeOrderByProducts invoked");
		Invoice invoice = new Invoice();
		List<String> productList = new ArrayList<String>();

		int totalPrice = 0;

		List<String> orderList = orderItems.getOrderItems();
		
		for (String item : orderList) {
			Optional<Inventory> optionalInventory = inventoryRepository.findById(item);
			if(optionalInventory.isPresent()) {
				Inventory inventory = optionalInventory.get();
				if ("Available".equalsIgnoreCase(inventory.getStatus())) {
					totalPrice += inventory.getPrice();
					productList.add(inventory.getProductName());
				} else {
					LOGGER.error(inventory.getProductName()+" is not available. Hence removed from cart!");
				}
				
			} else {
				LOGGER.error("Invalid product entered");
				throw new CustomException("Please enter valid product");
			}
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
		Date date = new Date();
		invoice.setOrderDate(dateFormat.format(date));
		invoice.setProductList(productList);
		invoice.setTotalPrice(totalPrice);
		invoice.setInvoiceNumber(UUID.randomUUID().toString());

		invoiceRepository.save(invoice);
		LOGGER.info("Order placed and invoice generated successfully");
		
		return invoice;
	}

	/**
	 * Update product availability
	 * @throws CustomException 
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity updateProductStatus(String productName, String status) throws CustomException {

		LOGGER.info("updateProductStatus invoked");
		Optional<Inventory> optInventory = inventoryRepository.findById(productName);
		
		if(optInventory.isPresent()) {
			Inventory inventory = optInventory.get();
			inventory.setStatus(status);
			inventoryRepository.save(inventory);
			LOGGER.info("product status updated successfully");
			return new ResponseEntity(HttpStatus.OK);
		} else {
			throw new CustomException("Product not found");
		}
	}

	/**
	 * Delete product by name
	 * @throws CustomException 
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity deleteProduct(String productName) throws CustomException {

		LOGGER.info("deleteProduct invoked");
		Optional<Inventory> optInventory = inventoryRepository.findById(productName);

		if(optInventory.isPresent()) {
			inventoryRepository.deleteById(productName);
			LOGGER.info("product deleted successfully");
			return new ResponseEntity(HttpStatus.OK);
		} else {
			throw new CustomException("Product not found");
		}

	}

}
