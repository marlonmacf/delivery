package com.demo.delivery.domain.repository;

import java.util.List;

import com.demo.delivery.domain.model.Order;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository implements IOrderRepository {

    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public Order find(Integer id) {
        return null;
    }

    @Override
    public List<Order> find() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}