package com.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {
	
	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;

	/*
	 * @Value(value = "${general.topic.name}") private String topicName;
	 */

	@Value(value = "${stud.topic.name}")
	private String studTopicName;
	
	@Value(value = "${emp.topic.name}")
	private String empTopicName;
	
	
	@Bean
	public NewTopic studentTopic() {
		return TopicBuilder.name(studTopicName)
			      .partitions(1)
			      .replicas(1)
			      .build();
	}
	
	@Bean
	public NewTopic empTopic() {
		return TopicBuilder.name(empTopicName)
			      .partitions(1)
			      .replicas(1)
			      .build();
	}

	

}
