package com.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.constants.AppConstants;

@Service
public class KafkaConsumerService {
	
	private static final Logger logger = 
            LoggerFactory.getLogger(KafkaConsumerService.class);
	
	@KafkaListener(topics = AppConstants.TOPIC_NAME, groupId = AppConstants.GROUP_ID)
	public void consumeMessage(String message) {
		logger.info(String.format("Message recieved -> %s", message));
	}

}
