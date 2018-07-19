package com.demo.orderserver.domain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping(value = "/", produces = MediaType.ALL_VALUE)
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> save() {
        return ResponseEntity.ok("Microservices with Spring, Netflix and MongoDB");
    }
}
