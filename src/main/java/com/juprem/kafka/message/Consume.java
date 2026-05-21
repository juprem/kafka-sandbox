package com.juprem.kafka.message;

import com.juprem.kafka.sampleModel.Todo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consume {

    @KafkaListener(topics = "baeldung", groupId = "foo")
    public void listenGroupFoo(ConsumerRecord<String, Todo> message) {
        if (message.value() instanceof Todo todo) {
            System.out.println("Got Todo: " + todo);
        }
    }
}
