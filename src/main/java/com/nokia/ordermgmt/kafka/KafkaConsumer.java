package com.nokia.ordermgmt.kafka;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.nokia.ordermgmt.exception.CustomException;
import com.nokia.ordermgmt.model.Invoice;
import com.nokia.ordermgmt.model.Shipping;
import com.nokia.ordermgmt.repository.InvoiceRepository;
import com.nokia.ordermgmt.repository.ShippingRepository;

/**
 * @author Kishore Prakash
 *
 */
@Service
public class KafkaConsumer {

	private final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

	@Autowired
	ShippingRepository shippingRepository;

	@Autowired
	InvoiceRepository invoiceRepository;

	/**
	 * Consumes the invoiceNumber to generate shipping details
	 * @param invoiceNumber
	 * @throws CustomException 
	 */
	@KafkaListener(topics = "shippingMessage", groupId = "group_Id") 
	public void	postShippingOrder(String invoiceNumber) throws CustomException 
	{
		LOGGER.info("Kafka consumer invoked");
		LOGGER.info("Message "+invoiceNumber+" consumed");

		Shipping shipping = new Shipping();
		Optional<Invoice> optInvoice = invoiceRepository.findById(invoiceNumber);
		if(optInvoice.isPresent()) {
			Invoice invoice = optInvoice.get();
			shipping.setShippingID(UUID.randomUUID().toString()); 
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
			Date date = new Date();
			shipping.setOrderDate(dateFormat.format(date));
			shipping.setProductList(invoice.getProductList());
			shipping.setInvoiceNumber(invoice.getInvoiceNumber());
		} else {
			throw new CustomException("Invoice number not found");
		}
		
		LOGGER.info("Shipping ID generated successfully");
		shippingRepository.save(shipping); 		
	}	
}
