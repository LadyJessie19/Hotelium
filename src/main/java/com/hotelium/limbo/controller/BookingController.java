package com.hotelium.limbo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hotelium.limbo.dto.request.BookingRequestDTO;
import com.hotelium.limbo.generic.GenericController;
import com.hotelium.limbo.model.Booking;
import com.hotelium.limbo.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping(path = "/bookings")
@Tag(name = "Bookings", description = "The bookings endpoints")
public class BookingController extends GenericController<Booking, Long, BookingRequestDTO> {
    public BookingController(BookingService service) {
        super(service);
    }

    @Autowired
    private BookingService service;

    @PostMapping("/user/{userId}")
    @Operation(summary = "Create a new booking for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The booking was successfully created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Booking.class))),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Booking createBooking(@PathVariable Long userId, @RequestBody BookingRequestDTO bookingDTO) {
        return service.createBookingTwo(userId, bookingDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a booking by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The booking was successfully deleted", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Booking not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public String deleteBooking(@PathVariable Long id) {
        service.deleteBooking(id);
        return "The booking was successfully deleted!";
    }

    @PutMapping("/cancel/{id}")
    @Operation(summary = "Cancel a booking by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The booking was successfully canceled", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Booking not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public String cancelBooking(@PathVariable Long id) {
        service.cancelBooking(id);
        return "The booking was successfully canceled!";
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get bookings by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved bookings", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Booking.class)))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public List<Booking> getBookingsByUser(@PathVariable Long userId,
            @RequestParam(required = false) Boolean isCanceled) {
        return service.findBookingsByUserAndStatus(userId, isCanceled);
    }

    @Override
    @Operation(hidden = true)
    public String delete(@PathVariable Long id) {
        return "";
    }
}
