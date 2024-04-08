package com.hotelium.limbo.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public ResponseEntity<Page<T>> findAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<T> result = service.findAll(pageable);
        System.out.println(result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Generic OP - Create a new entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The entity was successfully created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<D> create(@RequestBody D requestDTO) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<D>> violations = validator.validate(requestDTO);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<D> violation : violations) {
                System.out.println("ERROR: " + violation.getMessage());
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        D entity = service.create(requestDTO);

        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Generic OP - Find entity by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The entity was successfully retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Optional.class))),
            @ApiResponse(responseCode = "404", description = "Entity not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> findById(@PathVariable ID id) {
        Optional<T> entity = service.findById(id);
        if (entity != null && entity.isPresent()) {
            return ResponseEntity.ok(entity.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Entidade com o ID " + id + " não encontrada.");
        }
    }

    @PutMapping("/{id}")
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
