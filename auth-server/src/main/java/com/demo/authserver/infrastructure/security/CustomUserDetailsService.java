package com.demo.authserver.infrastructure.security;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;

import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MongoDatabase mongoDatabase;

    @Override
    @SuppressWarnings({ "unchecked", "deprecation" })
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        final MongoCollection<Document> collection = mongoDatabase.getCollection("users");
        final Document document = collection.find(Filters.eq("_id", email)).first();

        if (document != null) {

            final String password = document.getString("password");
            final List<String> authorities = (List<String>) document.get("authorities");
            final String[] roles = authorities.toArray(new String[authorities.size()]);
            final UserBuilder users = User.withDefaultPasswordEncoder();

            return users.username(email).password(password).roles(roles).build();
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }
}
