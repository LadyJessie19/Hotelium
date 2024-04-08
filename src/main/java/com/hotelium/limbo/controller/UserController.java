package com.hotelium.limbo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelium.limbo.dto.request.CreateUserDTO;
import com.hotelium.limbo.generic.GenericController;
import com.hotelium.limbo.model.User;
import com.hotelium.limbo.service.UserService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/users")
@Tag(name = "Users", description = "Users management endpoints")
@SecurityRequirement(name = "Authorization")
public class UserController extends GenericController<User, String, CreateUserDTO> {
    public UserController(UserService service) {
        super(service);
    }

    @Autowired
    private UserService service;

    @GetMapping("/status")
    @Operation(summary = "Get all users by booking status, true = canceled, false = not canceled")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list of users was successfully retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public List<User> getUsers(@RequestParam(required = false) Boolean isCanceled) {
        return service.findUsersByBookingStatus(isCanceled);
    }
}
