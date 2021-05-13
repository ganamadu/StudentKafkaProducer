package com.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.service.KafkaEmpService;

@RestController
@RequestMapping("/kafkaprod/emp")
public class KafkaEmpController {
	
	@Autowired
	KafkaEmpService kafkaEmpService;
	
	@GetMapping("/list")
	public String getEmpList() throws Exception {
		return kafkaEmpService.getEmpList();
	}
	
	@GetMapping("/empno/{empno}")
	public String getEmpByNo(@PathVariable Integer empno) throws Exception {
		return kafkaEmpService.getEmpNo(empno);
	}
	
	
	

}
