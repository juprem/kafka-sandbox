package com.juprem.kafka.message;

import com.juprem.kafka.sampleModel.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class Publish {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Object> kafkaObjectTemplate;

    @Autowired
    public Publish(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, Object>  kafkaObjectTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaObjectTemplate = kafkaObjectTemplate;
    }

    public void sendMessage(String msg) {
        kafkaTemplate.send("baeldung", msg);
    }

    public void sendMessage(Todo msg) {
        kafkaObjectTemplate.send("baeldung", msg.getTitle(), msg);
    }

    public void sendMessageWithReturn(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("baeldung", message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }
}
