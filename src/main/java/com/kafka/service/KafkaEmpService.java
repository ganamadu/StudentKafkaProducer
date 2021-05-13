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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.dao.KafkaEmpDAO;

@Service
public class KafkaEmpService {

	private static final Logger logger = 
			LoggerFactory.getLogger(KafkaEmpService.class);

	@Autowired
	public KafkaTemplate<String, String> kafkaEmpTemplate;
	
	@Autowired
	KafkaEmpDAO kafkaEmpDAO;

	@Value(value = "${emp.topic.name}")
	private String empTopicName;


	public String  getEmpList() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(kafkaEmpDAO.findAll());
		empList(jsonString);
		return jsonString;
	}

	public String  getEmpNo(Integer empno) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(kafkaEmpDAO.findById(empno).get());
		empList(jsonString);
		return jsonString;
	}

	public void empList(String empResponseJson) {
		
		ListenableFuture<SendResult<String, String>> future = this.kafkaEmpTemplate.send(empTopicName, empResponseJson);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				logger.info("Employee Json String : "+empResponseJson+ " with offset: "+result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error("Employee Json String: "+ empResponseJson, ex);

			}
		});

	}


}
