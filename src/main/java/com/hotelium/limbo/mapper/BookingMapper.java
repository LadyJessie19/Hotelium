package com.hotelium.limbo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hotelium.limbo.dto.request.BookingRequestDTO;
import com.hotelium.limbo.generic.GenericMapper;
import com.hotelium.limbo.model.Booking;

@Component
public class BookingMapper extends GenericMapper<Booking, BookingRequestDTO> {
    public BookingMapper(ModelMapper mapper) {
        super(mapper);
    }
}
