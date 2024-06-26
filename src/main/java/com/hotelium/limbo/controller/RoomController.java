package com.hotelium.limbo.controller;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelium.limbo.dto.request.RoomRequestDTO;
import com.hotelium.limbo.dto.response.RoomResponseDTO;
import com.hotelium.limbo.generic.GenericController;
import com.hotelium.limbo.model.Room;
import com.hotelium.limbo.service.RoomService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@RestController
@RequestMapping(path = "/rooms")
@Tag(name = "Rooms", description = "Room management endpoints")
public class RoomController extends GenericController<Room, Long, RoomRequestDTO, RoomResponseDTO> {
    public RoomController(RoomService service) {
        super(service);
    }

    @Autowired
    private RoomService service;

    @Override
    @PostMapping
    @Operation(hidden = true)
    public ResponseEntity<RoomRequestDTO> create(@RequestBody RoomRequestDTO request) {
        return null;
    }

    @Override
    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/create/{hotelId}")
    @Operation(summary = "Create a new room for a hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Room created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Room.class))),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Hotel not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> createRoom(@PathVariable Long hotelId, @RequestBody RoomRequestDTO requestDTO) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<RoomRequestDTO>> violations = validator.validate(requestDTO);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<RoomRequestDTO> violation : violations) {
                System.out.println("ERROR: " + violation.getMessage());
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Room entity = service.createRoom(hotelId, requestDTO);

        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{id}")
    @Operation(summary = "Delete a room by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room deleted successfully", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Room not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        if (service.findById(id) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Entidade com o ID " + id + " não encontrada.");
        }

        Boolean deleted = service.delete(id);

        if (deleted) {
            return ResponseEntity.ok("Entidade com o ID " + id + " foi excluída com sucesso.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Entidade com o ID " + id + " não encontrada para o delete.");
        }
    }

    @GetMapping("/availability/{id}")
    @Operation(summary = "Check room availability")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room availability status", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "404", description = "Room not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public boolean checkAvailability(@PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkIn,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date checkOut) {
        return service.isRoomAvailable(id, checkIn, checkOut);
    }
}
