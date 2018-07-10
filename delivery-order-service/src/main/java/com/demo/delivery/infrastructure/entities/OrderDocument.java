package com.demo.delivery.infrastructure.entities;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "order")
public class OrderDocument implements Serializable {

    private static final long serialVersionUID = -3239845768821177031L;

    @Id
    private ObjectId id;

    @Field("product")
    private String product;

    @Field("price")
    private Double price;
}