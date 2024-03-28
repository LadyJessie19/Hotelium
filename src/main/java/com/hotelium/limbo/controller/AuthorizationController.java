package com.hotelium.limbo.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelium.limbo.dto.request.AuthorizationRequestDTO;
import com.hotelium.limbo.dto.request.CreateUserDTO;
import com.hotelium.limbo.dto.response.TokenResponseDTO;
import com.hotelium.limbo.dto.response.UserResponseDTO;
import com.hotelium.limbo.enums.UserRoleEnum;
import com.hotelium.limbo.infra.security.TokenService;
import com.hotelium.limbo.model.User;
import com.hotelium.limbo.repository.UserRepository;
import com.hotelium.limbo.utils.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authorization", description = "Login and register new users routes")
public class AuthorizationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @Operation(summary = "Login user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully logged in", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<Response> login(@RequestBody @Valid AuthorizationRequestDTO loginRequest) {

        var user = new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword());

        var auth = manager.authenticate(user);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        TokenResponseDTO tokenResponse = new TokenResponseDTO(token);

        if (token == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.created(URI.create("/auth/login"))
                .body(new Response(201, tokenResponse, "User logged in successfully!"));
    }

    @PostMapping("/register")
    @Operation(summary = "Register new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully registered", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<Response> register(@RequestBody @Valid CreateUserDTO newUser) {

        String name = newUser.getName();
        String login = newUser.getLogin();
        String password = newUser.getPassword();
        UserRoleEnum role = newUser.getRole();

        UserDetails userExists = repository.findByLogin(login);

        if (userExists != null) {
            return ResponseEntity.badRequest().build();
        }

        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        repository.save(user);

        UserResponseDTO userResponse = new UserResponseDTO(user.getId(), user.getName(), user.getLogin(),
                user.getRole(), user.getCreatedAt(), user.getUpdatedAt(), user.getCreatedBy(), user.getUpdatedBy(),
                user.getBookings(), user.isEnabled(), UserRoleEnum.USER);

        return ResponseEntity.created(URI.create("/auth/register"))
                .body(new Response(201, userResponse, "User created sucessfully!"));
    }
}
