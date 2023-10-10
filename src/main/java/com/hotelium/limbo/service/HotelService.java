package com.hotelium.limbo.service;

import org.springframework.stereotype.Service;

import com.hotelium.limbo.dto.request.HotelRequestDTO;
import com.hotelium.limbo.generic.GenericService;
import com.hotelium.limbo.mapper.HotelMapper;
import com.hotelium.limbo.model.Hotel;
import com.hotelium.limbo.repository.HotelRepository;

@Service
public class HotelService extends GenericService<Hotel, Long, HotelRequestDTO> {
    public HotelService(HotelRepository repository, HotelMapper mapper){
        super(repository, mapper, Hotel.class, HotelRequestDTO.class);
    }
}
