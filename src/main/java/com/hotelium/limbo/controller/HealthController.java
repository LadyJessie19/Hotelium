package com.hotelium.limbo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/health")
@Tag(name = "Health", description = "Health Check")
public class HealthController {
    @GetMapping()
    public String health() {
        return "THE DATABASE REQUESTS ARE OK. NOICE JOB TO YOU!";
    }
}
