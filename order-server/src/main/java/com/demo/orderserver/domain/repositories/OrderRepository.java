package com.demo.orderserver.domain.repositories;

import java.util.List;

import com.demo.orderserver.domain.components.IOrderAdapter;
import com.demo.orderserver.domain.models.Order;
import com.demo.orderserver.infrastructure.entities.OrderDocument;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository implements IOrderRepository {

    @Autowired
    private MongoDatabase mongoDatabase;

    @Autowired
    private IOrderAdapter orderAdapter;

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
        final MongoCollection<Document> collection = mongoDatabase.getCollection(OrderDocument.FieldName.ORDERS);
        return orderAdapter.toDomain(collection.find());
    }

    @Override
    public void delete(Integer id) {

    }
}
