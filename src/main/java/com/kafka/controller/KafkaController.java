package com.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.core.Student;
import com.kafka.service.KafkaProducerService;

@RestController
@RequestMapping("/kafkaprod/stud")
public class KafkaController {
	
	@Autowired
	private final KafkaProducerService kafkaProducerService;
	
	@Autowired
	public KafkaController(KafkaProducerService kafkaProducerService) {
		this.kafkaProducerService = kafkaProducerService;
	}
	
	/*
	 * @PostMapping(value = "/publish") public void
	 * sendMessageToKafkaTopic(@RequestParam("message") String message) {
	 * this.kafkaProducerService.sendMessage(message); }
	 */
	
	@PostMapping(value = "/createStudent")
	public void sendMessageToKafkaTopic(@RequestBody Student student) {
		
		this.kafkaProducerService.saveStudent(student);
	}

}
