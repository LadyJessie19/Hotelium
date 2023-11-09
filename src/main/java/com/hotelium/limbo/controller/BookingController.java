package com.hotelium.limbo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
