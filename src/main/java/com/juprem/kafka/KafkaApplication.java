package com.juprem.kafka;

import com.juprem.kafka.message.Publish;
import com.juprem.kafka.sampleModel.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Flow;

@SpringBootApplication
public class KafkaApplication {

	@Autowired
	public KafkaApplication(Publish publisher) {
//		publisher.sendMessage("Hello World");
		publisher.sendMessage(new Todo("Todo1", "My first Todo"));
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

}
