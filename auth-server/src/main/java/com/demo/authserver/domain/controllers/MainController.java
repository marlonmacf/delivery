package com.demo.authserver.domain.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping(value = "/", produces = MediaType.ALL_VALUE)
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Microservices with Spring, Netflix and MongoDB");
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("Admin rule only.");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<String> user() {
        return ResponseEntity.ok("User rule only.");
    }
}