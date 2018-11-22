package com.demo.restserver.domain.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @RequestMapping(value = "random")
    public Integer random() {
        return new SecureRandom().nextInt();
    }
}