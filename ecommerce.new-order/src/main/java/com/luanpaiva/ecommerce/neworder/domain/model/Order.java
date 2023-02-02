package com.luanpaiva.ecommerce.neworder.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String orderId;
    private Customer customer;
    private LocalDateTime creationDate;
    private List<Product> products;
    private BigDecimal amount;
    private PaymentType paymentType;
}
