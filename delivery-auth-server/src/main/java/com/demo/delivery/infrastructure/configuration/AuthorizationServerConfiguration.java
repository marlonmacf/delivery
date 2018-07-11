package com.demo.delivery.infrastructure.configuration;

import com.demo.delivery.domain.services.CustomApprovalStore;
import com.demo.delivery.domain.services.CustomAuthorizationCodeServices;
import com.demo.delivery.domain.services.CustomClientDetailsService;
import com.demo.delivery.domain.services.CustomTokenStore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Bean
    public CustomClientDetailsService clientDetailsService() {
        return new CustomClientDetailsService();
    }

    @Bean
    public CustomTokenStore tokenStore() {
        return new CustomTokenStore();
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new CustomAuthorizationCodeServices();
    }

    @Bean
    public CustomApprovalStore approvalStore() {
        return new CustomApprovalStore();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.approvalStore(approvalStore())
                .authorizationCodeServices(authorizationCodeServices())
                .tokenStore(tokenStore());
    }
}