package com.kafkaexample;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaProducer producer;

    @PostMapping("/publish")
    public void writeMessageToTopic(@RequestParam("message") String message) {
        producer.writeMessage(message);
    }
}
