package com.luanpaiva.ecommerce.api.model;

import jakarta.validation.Valid;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class PaymentType implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Valid
    private CreditCard creditCard;

}
