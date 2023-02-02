package com.luanpaiva.ecommerce_sendemail.domain.model;

import java.io.Serial;
import java.io.Serializable;

public record Email(String emailTo, String subject, String text) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

}
