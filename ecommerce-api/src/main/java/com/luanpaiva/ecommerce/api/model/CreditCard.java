package com.luanpaiva.ecommerce.api.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class CreditCard implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank
    private final String flag;
    @NotBlank
    private final String cardHolder;
    @NotBlank
    private final String number;
    @NotBlank
    private final String expirationDate;
    @NotBlank
    private final String securityCode;
}
