package com.luanpaiva.ecommerce.neworder.domain.repository;

import com.luanpaiva.ecommerce.neworder.domain.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
