package com.demo.authserver.infrastructure.configuration;

import com.demo.authserver.infrastructure.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
}