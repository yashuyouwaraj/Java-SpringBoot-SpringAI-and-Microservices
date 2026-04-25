package com.yashu.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.yashu.kafka.model.Course;

@Service
public class KafkaService 
{

	private String message;
	
	@KafkaListener(topics= "yashu" , groupId = "yashu-group")
	public void consume(Course course)
	{
		message = course + " Got the data from kafka";
		System.out.println(message);
		
	}

	public String getMessage() {
		return message;
	}

	
	
	
	
	
}
