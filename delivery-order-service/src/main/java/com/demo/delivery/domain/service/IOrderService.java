package com.demo.delivery.domain.service;

import java.util.List;

import com.demo.delivery.domain.model.Order;

public interface IOrderService {

    public Order save(Order order);

    public Order find(Integer id);

    public List<Order> find();

    public void delete(Integer id);
}