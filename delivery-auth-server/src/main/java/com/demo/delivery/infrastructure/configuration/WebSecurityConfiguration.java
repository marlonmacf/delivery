package com.demo.delivery.infrastructure.configuration;

import com.demo.delivery.domain.services.CustomUserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/logout.do").permitAll()
                .antMatchers("/**").authenticated()
            .and()
                .formLogin()
                .loginProcessingUrl("/login.do")
                .usernameParameter("uid")
                .passwordParameter("pwd")
                .loginPage("/login")
            .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout.do"))
            .and()
                .userDetailsService(userDetailsService());
    }
}