package com.luanpaiva.ecommerce.neworder.domain.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class PaymentType implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private CreditCard creditCard;

}
