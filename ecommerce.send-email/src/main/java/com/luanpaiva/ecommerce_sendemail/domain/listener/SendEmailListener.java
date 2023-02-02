package com.luanpaiva.ecommerce_sendemail.domain.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luanpaiva.ecommerce_sendemail.domain.model.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import static com.luanpaiva.ecommerce_sendemail.domain.model.Topic.TOPIC_ECOMMERCE_SEND_EMAIL;

@Slf4j
@Component
public class SendEmailListener {

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JavaMailSender emailSender;

    @KafkaListener(topics = TOPIC_ECOMMERCE_SEND_EMAIL)
    public void listen(String emailJson) throws JsonProcessingException {

        final var email = objectMapper.readValue(emailJson, Email.class);
        final var message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(email.emailTo());
        message.setSubject(email.subject());
        message.setText(email.text());
        emailSender.send(message);
        log.info("Email successfully sent");
    }
}
