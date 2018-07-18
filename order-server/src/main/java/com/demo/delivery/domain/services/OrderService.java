package com.demo.delivery.domain.services;

import java.util.List;

import com.demo.delivery.domain.models.Order;
import com.demo.delivery.domain.repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order find(Integer id) {
        return orderRepository.find(id);
    }

    @Override
    public List<Order> find() {
        return orderRepository.find();
    }

    @Override
    public void delete(Integer id) {
        orderRepository.delete(id);
    }
}