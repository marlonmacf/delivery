package com.demo.delivery.infrastructure.entities;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = OrderDocument.FieldName.ORDERS)
public class OrderDocument implements Serializable {

    private static final long serialVersionUID = -3239845768821177031L;

    @Id
    private ObjectId id;

    @Field(OrderDocument.FieldName.PRODUCT)
    private String product;

    @Field(OrderDocument.FieldName.PRICE)
    private Double price;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public static class FieldName {

        public static final String ID = "_id";
        public static final String PRODUCT = "product";
        public static final String PRICE = "price";
        public static final String ORDERS = "orders";
    }
}