package com.hotelium.limbo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelium.limbo.dto.request.HotelRequestDTO;
import com.hotelium.limbo.generic.GenericService;
import com.hotelium.limbo.mapper.HotelMapper;
import com.hotelium.limbo.model.Hotel;
import com.hotelium.limbo.repository.HotelRepository;

@Service
public class HotelService extends GenericService<Hotel, Long, HotelRequestDTO> {
    public HotelService(HotelRepository repository, HotelMapper mapper) {
        super(repository, mapper, Hotel.class, HotelRequestDTO.class);
    }

    @Autowired
    private HotelRepository repository;

    public List<Hotel> findAll(String search) {
        return repository.findByName(search);
    }
}
