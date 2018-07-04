package com.demo.delivery.infrastructure.security;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomerUserDetailsService implements UserDetailsService {

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Autowired
    private MongoClient mongoClient;

    @Override
    @SuppressWarnings("unchecked")
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        MongoDatabase database = mongoClient.getDatabase(dbName);
        MongoCollection<Document> collection = database.getCollection("users");
        Document document = collection.find(Filters.eq("_id", email)).first();

        if (document != null) {
            String password = document.getString("password");
            List<String> authorities = (List<String>) document.get("authorities");
            return new MongoUserDetails(email, password, authorities.toArray(new String[authorities.size()]));
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }
}