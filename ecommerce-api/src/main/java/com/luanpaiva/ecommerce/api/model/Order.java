package com.luanpaiva.ecommerce.api.model;

import jakarta.validation.Valid;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String orderId;
    @Valid
    private Customer customer;
    private LocalDateTime creationDate;
    @Valid
    private List<Product> products;
    private BigDecimal amount;
    @Valid
    private PaymentType paymentType;
}
