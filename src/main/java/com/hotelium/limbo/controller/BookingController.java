package com.hotelium.limbo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hotelium.limbo.dto.request.BookingRequestDTO;
import com.hotelium.limbo.generic.GenericController;
import com.hotelium.limbo.model.Booking;
import com.hotelium.limbo.service.BookingService;

@RestController
@RequestMapping(path = "/bookings")
public class BookingController extends GenericController<Booking, Long, BookingRequestDTO> {
    public BookingController(BookingService service) {
        super(service);
    }

    @Autowired
    private BookingService service;

    @PostMapping("/user/{userId}")
    public Booking createBooking(@PathVariable Long userId, @RequestBody BookingRequestDTO bookingDTO) {
        return service.createBookingTwo(userId, bookingDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        service.deleteBooking(id);
        return "The booking was successfully deleted!";
    }

    @PutMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable Long id) {
        service.cancelBooking(id);
        return "The booking was successfully canceled!";
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUser(@PathVariable Long userId,
            @RequestParam(required = false) Boolean isCanceled) {
        return service.findBookingsByUserAndStatus(userId, isCanceled);
    }
}
