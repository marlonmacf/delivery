package com.demo.orderserver.domain.components;

import java.util.List;

import com.demo.orderserver.domain.models.Order;
import com.mongodb.client.FindIterable;

import org.bson.Document;

public interface IOrderAdapter {

    public Order toDomain(Document document);

    public List<Order> toDomain(FindIterable<Document> documents);
}
