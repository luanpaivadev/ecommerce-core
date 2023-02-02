package com.luanpaiva.ecommerce.neworder.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@Document
public class Customer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String customerId;
    private String email;
}
