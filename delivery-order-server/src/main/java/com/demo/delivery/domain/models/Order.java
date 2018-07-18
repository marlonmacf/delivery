package com.demo.delivery.domain.models;

public class Order {

    private String id;

    private String product;

    private Double price;

    public Order(String id, String product, Double price) {
        this.id = id;
        this.product = product;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}