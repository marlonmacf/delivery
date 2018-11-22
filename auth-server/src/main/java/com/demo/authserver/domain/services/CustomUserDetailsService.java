package com.demo.authserver.domain.services;

import java.util.ArrayList;
import java.util.List;

import com.demo.authserver.infrastructure.entities.MongoUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));

        MongoUser user = mongoTemplate.findOne(query, MongoUser.class);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}