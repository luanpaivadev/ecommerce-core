package com.luanpaiva.ecommerce.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class Customer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    private String customerId;
    @NotBlank
    private String email;
}
