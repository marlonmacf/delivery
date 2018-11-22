package com.demo.authserver.infrastructure.configuration;

import com.demo.authserver.domain.services.CustomApprovalStore;
import com.demo.authserver.domain.services.CustomAuthorizationCodeServices;
import com.demo.authserver.domain.services.CustomClientDetailsService;
import com.demo.authserver.domain.services.CustomTokenStore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Bean
    public CustomApprovalStore customApprovalStore() {
        return new CustomApprovalStore();
    }

    @Bean
    public CustomAuthorizationCodeServices customAuthorizationCodeServices() {
        return new CustomAuthorizationCodeServices();
    }

    @Bean
    public CustomClientDetailsService customClientDetailsService() {
        return new CustomClientDetailsService();
    }

    @Bean
    public CustomTokenStore customTokenStore() {
        return new CustomTokenStore();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(customClientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            .approvalStore(customApprovalStore())
            .authorizationCodeServices(customAuthorizationCodeServices())
            .tokenStore(customTokenStore());
    }
}