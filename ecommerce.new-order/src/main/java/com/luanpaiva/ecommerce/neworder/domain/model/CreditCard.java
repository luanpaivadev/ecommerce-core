package com.luanpaiva.ecommerce.neworder.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class CreditCard implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String flag;
    private String cardHolder;
    private String number;
    private String expirationDate;
    private String securityCode;
}
