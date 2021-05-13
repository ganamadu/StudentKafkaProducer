package com.kafka.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.kafka")
@EnableJpaRepositories("com.kafka")
@EntityScan("com.kafka")
public class StudentKafkaProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentKafkaProducerApplication.class, args);
	}

}
