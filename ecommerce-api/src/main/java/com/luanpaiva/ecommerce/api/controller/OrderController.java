package com.luanpaiva.ecommerce.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luanpaiva.ecommerce.api.model.Order;
import com.luanpaiva.ecommerce.api.model.Product;
import com.luanpaiva.ecommerce.api.model.Response;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.luanpaiva.ecommerce.util.Messages.YOUR_ORDER_IS_BEING_PROCESSED;
import static com.luanpaiva.ecommerce.util.Topic.TOPIC_ECOMMERCE_NEW_ORDER;

@Slf4j
@RestController
@RequestMapping("/v1/orders/new-order")
public class OrderController {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response sendMessage(@RequestBody @Valid Order order) throws JsonProcessingException {
        order.setOrderId(UUID.randomUUID().toString());
        order.setCreationDate(LocalDateTime.now());
        setTotalOrderAmount(order);
        sendMessageToNewOrderTopic(order);
        return new Response(YOUR_ORDER_IS_BEING_PROCESSED, order.getOrderId(), order.getCreationDate());
    }

    private void sendMessageToNewOrderTopic(Order order) throws JsonProcessingException {
        final var orderJson = objectMapper.writeValueAsString(order);
        kafkaTemplate.send(
                TOPIC_ECOMMERCE_NEW_ORDER,
                order.getOrderId(),
                orderJson
        );
        log.info("Message successfully sent to topic {}", TOPIC_ECOMMERCE_NEW_ORDER);
    }

    private void setTotalOrderAmount(Order order) {
        var amount = BigDecimal.ZERO;
        for (Product product : order.getProducts()) {
            amount = amount.add(product.getValue()
                    .multiply(new BigDecimal(product.getQuantity())));
        }
        order.setAmount(amount);
    }
}
