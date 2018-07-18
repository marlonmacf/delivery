package com.demo.delivery.domain.components;

import java.util.ArrayList;
import java.util.List;

import com.demo.delivery.domain.models.Order;
import com.demo.delivery.infrastructure.entities.OrderDocument;
import com.mongodb.client.FindIterable;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class OrderAdapter {

    public Order toDomain(Document document) {
        final ObjectId id = (ObjectId) document.get(OrderDocument.FieldName.ID);
        final String product = document.getString(OrderDocument.FieldName.PRODUCT);
        final Double price = document.getDouble(OrderDocument.FieldName.PRICE);
        return new Order(id.toString(), product, price);
    }

    public List<Order> toDomain(FindIterable<Document> documents) {
        final List<Order> orders = new ArrayList<>();
        for (Document document : documents) {
            orders.add(toDomain(document));
        }
        return orders;
    }
}