package com.nokia.ordermgmt.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

/**
 * @author Kishore Prakash
 *
 */
@RestController
@RequestMapping("kafka")
public class KafkaController {

	private final Logger LOGGER = LoggerFactory.getLogger(KafkaController.class);

	private final KafkaProducer producer;

	public KafkaController(KafkaProducer producer) {

		this.producer = producer; 
	}

	/**
	 * Controller for kafka publication
	 * @param invoiceNumber
	 */
	@PostMapping("/publish/{invoiceNumber}") 
	@ApiOperation(value = "sends Kafka topic across", response = ResponseEntity.class)
	public void sendMessageToKafkaTopic(@PathVariable("invoiceNumber") final String invoiceNumber) {

		LOGGER.info("sendMessageToKafkaTopic invoked");
		this.producer.sendMessageFlag(invoiceNumber); 
	}

}
