package com.luanpaiva.ecommerce.api.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String description;
    @NotNull
    @Positive
    private BigDecimal value;
    @Min(1)
    @NotNull
    private Integer quantity;
}
