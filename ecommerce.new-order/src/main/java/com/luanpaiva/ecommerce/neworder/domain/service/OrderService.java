package com.luanpaiva.ecommerce.neworder.domain.service;

import com.luanpaiva.ecommerce.neworder.domain.model.Order;
import com.luanpaiva.ecommerce.neworder.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order save(final Order order) {
        return orderRepository.save(order);
    }
}
