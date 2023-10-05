package com.hotelium.limbo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class TestController {
    @GetMapping()
    public String testController(){
        return "THE DATABASE REQUESTS ARE OK. NOICE JOB TO YOU!";
    }
}
