package com.hotelium.limbo.generic;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Optional;

public class GenericController<T, ID, D> {
    private final GenericService<T, ID, D> service;

    public GenericController(GenericService<T, ID, D> service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Generic OP - Get all entities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list of entities was successfully retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public List<T> findAll() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Generic OP - Create a new entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The entity was successfully created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public D create(@RequestBody D requestDTO) {
        return service.create(requestDTO);
    }

    @GetMapping("/dto/{id}")
    @Operation(summary = "Generic OP - Find entity by ID with DTO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The entity was successfully retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Optional.class))),
            @ApiResponse(responseCode = "404", description = "Entity not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Optional<D> findByIdWithDTO(@PathVariable ID id) {
        return service.findByIdWithDTO(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Generic OP - Find entity by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The entity was successfully retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Optional.class))),
            @ApiResponse(responseCode = "404", description = "Entity not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Optional<T> findById(@PathVariable ID id) {
        return service.findById(id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Generic OP - Update entity by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The entity was successfully updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Optional.class))),
            @ApiResponse(responseCode = "404", description = "Entity not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Optional<D> update(@PathVariable ID id, @RequestBody D body) {
        return service.update(id, body);
    }

    @Operation(summary = "Generic OP - Delete entity by ID")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The entity was successfully deleted", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Entity not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public String delete(@PathVariable ID id) {
        service.delete(id);
        return "The entity was successfully deleted";
    }
}
