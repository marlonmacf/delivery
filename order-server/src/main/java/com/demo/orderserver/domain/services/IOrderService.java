package com.demo.orderserver.domain.services;

import java.util.List;

import com.demo.orderserver.domain.models.Order;

public interface IOrderService {

    public Order save(Order order);

    public Order find(Integer id);

    public List<Order> find();

    public void delete(Integer id);
}
