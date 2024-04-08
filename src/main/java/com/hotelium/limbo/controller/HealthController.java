package com.hotelium.limbo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/")
@Tag(name = "Home", description = "Health Check and home endpoint")
public class HealthController {
    @GetMapping("/health")
    @Operation(summary = "Health Check")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Health check was successful", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))) })
    public String health() {
        return "THE DATABASE REQUESTS ARE OK. NOICE JOB TO YOU!";
    }

    @GetMapping
    @Operation(summary = "Home")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Health check was successful", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))) })
    public String home() {
        return "Hi! This is the home page. How are you doing?";
    }
}
