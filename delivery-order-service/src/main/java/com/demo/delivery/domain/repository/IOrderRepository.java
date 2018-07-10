package com.demo.delivery.domain.repository;

import java.util.List;

import com.demo.delivery.domain.model.Order;

public interface IOrderRepository {

    public Order save(Order order);

    public Order find(Integer id);

    public List<Order> find();

    public void delete(Integer id);
}