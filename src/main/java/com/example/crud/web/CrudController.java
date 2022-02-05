package com.example.crud.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrudController {

    @GetMapping("/")
    public String hello() {

        return "Hello from docker";
    }
}
