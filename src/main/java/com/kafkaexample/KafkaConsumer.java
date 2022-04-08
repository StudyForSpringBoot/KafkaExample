package com.kafkaexample;

import com.kafkaexample.utils.mail.MailDto;
import com.kafkaexample.utils.mail.MailService;
import com.kafkaexample.utils.mail.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final MailService mailService;

    @KafkaListener(topics = "my_topic", groupId = "my_group_id")
    public void getMessage(String message) {
        System.out.println(message);

        /* send mail */
        String token = new TokenGenerator().generateNewToken();
        new Thread(() -> {
            try {
                mailService.sendEmail(MailDto.builder()
                        .address("guswns3371@gmail.com")
                        .title("kafka mail test")
                        .token("token=" + token + "\nmessage=" + message)
                        .build());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
