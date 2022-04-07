package com.kafkaexample;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private static final String TOPIC = "my_topic";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void writeMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
