package com.nokia.ordermgmt.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Kishore Prakash
 *
 */
@Service
public class KafkaProducer {

	private final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

	private static final String TOPIC = "shippingMessage";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	/**
	 * Sends kafka topic and invoice number
	 * @param invoiceNumber
	 */
	public void sendMessageFlag(String invoiceNumber) {

		LOGGER.info("kafkaProducer invoked");
		this.kafkaTemplate.send(TOPIC, invoiceNumber);
	}

}
