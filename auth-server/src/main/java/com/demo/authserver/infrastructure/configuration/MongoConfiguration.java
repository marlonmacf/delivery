package com.demo.authserver.infrastructure.configuration;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String dbHost;

    @Value("${spring.data.mongodb.port}")
    private String dbPort;

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(dbHost + ":" + dbPort);
    }

    @Bean
    public MongoDatabase mongoDatabase() {
        return mongoClient().getDatabase(dbName);
    }
}
