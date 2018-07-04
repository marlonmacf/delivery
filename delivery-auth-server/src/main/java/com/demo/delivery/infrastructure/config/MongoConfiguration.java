package com.demo.delivery.infrastructure.config;

import com.mongodb.MongoClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String dbHost;

    @Value("${spring.data.mongodb.port}")
    private String dbPort;

    @Bean
    public MongoClient createConnection() {
        return new MongoClient(dbHost + ":" + dbPort);
    }
}