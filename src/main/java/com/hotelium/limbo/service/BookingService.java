package com.hotelium.limbo.service;

import com.hotelium.limbo.dto.request.BookingRequestDTO;
import com.hotelium.limbo.generic.GenericService;
import com.hotelium.limbo.model.Booking;
import com.hotelium.limbo.repository.BookingRepository;
import com.hotelium.limbo.mapper.BookingMapper;

public class BookingService extends GenericService<Booking, Long, BookingRequestDTO>{
    public BookingService(BookingRepository repository, BookingMapper mapper){
        super(repository, mapper, Booking.class, BookingRequestDTO.class);
    }
}
