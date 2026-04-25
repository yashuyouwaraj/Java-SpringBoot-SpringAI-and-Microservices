package com.yashu.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.yashu.kafka.model.Course;

@Service
public class KafkaService 
{

	@Autowired
	private KafkaTemplate<String, Course> kafkaTemplate;
	
	
	public String sendMessage(Course course)
	{
		kafkaTemplate.send("yashu", "course", course);
		return "Course message sent to kafka server";
	}
	
	
}
