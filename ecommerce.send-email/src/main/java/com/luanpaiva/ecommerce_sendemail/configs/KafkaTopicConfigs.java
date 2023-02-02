package com.luanpaiva.ecommerce_sendemail.configs;

import com.luanpaiva.ecommerce_sendemail.domain.model.Topic;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfigs {

    @Bean
    public NewTopic ecommerceSendEmail() {
        return TopicBuilder
                .name(Topic.TOPIC_ECOMMERCE_SEND_EMAIL)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
