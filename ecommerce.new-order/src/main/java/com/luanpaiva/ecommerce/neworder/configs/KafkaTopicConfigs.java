package com.luanpaiva.ecommerce.neworder.configs;

import com.luanpaiva.ecommerce.neworder.domain.model.Topic;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfigs {

    @Bean
    public NewTopic ecommerceNewOrder() {
        return TopicBuilder
                .name(Topic.TOPIC_ECOMMERCE_NEW_ORDER)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
