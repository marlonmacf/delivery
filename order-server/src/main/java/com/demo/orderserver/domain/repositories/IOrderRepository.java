package com.demo.orderserver.domain.repositories;

import java.util.List;

import com.demo.orderserver.domain.models.Order;

public interface IOrderRepository {

    public Order save(Order order);

    public Order find(Integer id);

    public List<Order> find();

    public void delete(Integer id);
}
