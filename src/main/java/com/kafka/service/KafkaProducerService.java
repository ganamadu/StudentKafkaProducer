package com.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.kafka.core.Student;

@Service
public class KafkaProducerService {
	
	private static final Logger logger = 
            LoggerFactory.getLogger(KafkaProducerService.class);
	
	@Autowired
	private KafkaTemplate<String, Student> kafkaTemplate;
	
	@Value(value = "${stud.topic.name}")
	private String studTopicName;
	
	/*
	 * public void sendMessage(String message) {
	 * logger.info(String.format("Message sent -> %s", message));
	 * this.kafkaTemplate.send(AppConstants.TOPIC_NAME, message); }
	 */
	
	public void saveStudent(Student student) {
		
		ListenableFuture<SendResult<String, Student>> future = this.kafkaTemplate.send(studTopicName, student);
		future.addCallback(new ListenableFutureCallback<SendResult<String, Student>>() {

			@Override
			public void onSuccess(SendResult<String, Student> result) {
				logger.info("Student Created: "+student+ " with offset: "+result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error("Student Created: "+ student, ex);
				
			}
		});
		
	}

}
