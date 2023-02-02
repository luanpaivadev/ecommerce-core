package com.luanpaiva.ecommerce.neworder.domain.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luanpaiva.ecommerce.neworder.domain.model.Email;
import com.luanpaiva.ecommerce.neworder.domain.model.Order;
import com.luanpaiva.ecommerce.neworder.domain.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.luanpaiva.ecommerce.neworder.domain.model.Topic.TOPIC_ECOMMERCE_NEW_ORDER;
import static com.luanpaiva.ecommerce.neworder.domain.model.Topic.TOPIC_ECOMMERCE_PAYMENT;
import static com.luanpaiva.ecommerce.neworder.domain.model.Topic.TOPIC_ECOMMERCE_SEND_EMAIL;
import static java.text.MessageFormat.format;

@Slf4j
@Component
public class OrderListener {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = TOPIC_ECOMMERCE_NEW_ORDER)
    public void listen(String json) throws JsonProcessingException {
        Order order = saveOrder(json);
        sendMessageToEmailTopic(order);
        // TODO: sendMessageToPaymentTopic(order);
    }

    private Order saveOrder(String json) throws JsonProcessingException {
        Order order = objectMapper.readValue(json, Order.class);
        validPaymentCreditCard(order);
        order = orderService.save(order);
        log.info("Order successfully saved: {}", order);
        return order;
    }

    private void validPaymentCreditCard(Order order) {
        final var creditCard = order.getPaymentType().getCreditCard();
        if (Objects.nonNull(creditCard)) {
            var creditCardNumber = creditCard.getNumber();
            var creditCardNumberCript =
                    creditCardNumber.substring(0, 4)
                            .concat(" **** **** ")
                            .concat(creditCardNumber.substring(12, 16));
            creditCard.setNumber(creditCardNumberCript);
            order.getPaymentType().setCreditCard(creditCard);
        }
    }

    private void sendMessageToPaymentTopic(Order order) throws JsonProcessingException {
        final var paymentType = objectMapper.writeValueAsString(order.getPaymentType());
        kafkaTemplate.send(
                TOPIC_ECOMMERCE_PAYMENT,
                order.getOrderId(),
                paymentType
        );
        log.info("Message successfully sent to topic {}", TOPIC_ECOMMERCE_PAYMENT);
    }

    private void sendMessageToEmailTopic(Order order) throws JsonProcessingException {
        final var email = new Email(
                order.getCustomer().getEmail(),
                "Your purchase is being processed",
                format("Thanks for the purchase!\n\n" +
                        "Your order is being processed, follow the order number: {0}", order.getOrderId())
        );
        kafkaTemplate.send(
                TOPIC_ECOMMERCE_SEND_EMAIL,
                order.getOrderId(),
                objectMapper.writeValueAsString(email)
        );
        log.info("Message successfully sent to topic {}", TOPIC_ECOMMERCE_SEND_EMAIL);
    }
}
