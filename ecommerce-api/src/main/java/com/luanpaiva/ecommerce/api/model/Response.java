package com.luanpaiva.ecommerce.api.model;

import java.time.LocalDateTime;

public record Response(String message, String orderId, LocalDateTime creationDate) {

}
