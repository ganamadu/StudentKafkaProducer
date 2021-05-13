package com.kafka.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kafka.core.Employee;

@Repository
public interface KafkaEmpDAO extends CrudRepository<Employee, Serializable> {
	
	

}
