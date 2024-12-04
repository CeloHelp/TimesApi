package com.apilearn.TimesApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/nyt")
public class NYTController {
    @GetMapping(value="/test")
    public String testEndpoint() {
        return "API NYT Working!";
    }
}
