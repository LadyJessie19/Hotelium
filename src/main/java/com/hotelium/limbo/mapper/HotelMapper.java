package com.hotelium.limbo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hotelium.limbo.dto.request.HotelRequestDTO;
import com.hotelium.limbo.dto.response.HotelResponseDTO;
import com.hotelium.limbo.generic.GenericMapper;
import com.hotelium.limbo.model.Hotel;

@Component
public class HotelMapper extends GenericMapper<Hotel, HotelRequestDTO, HotelResponseDTO> {
    public HotelMapper(ModelMapper mapper) {
        super(mapper);
    }
}
