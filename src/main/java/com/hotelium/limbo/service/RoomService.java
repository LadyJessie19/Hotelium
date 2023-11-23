package com.hotelium.limbo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelium.limbo.dto.request.RoomRequestDTO;
import com.hotelium.limbo.generic.GenericService;
import com.hotelium.limbo.mapper.RoomMapper;
import com.hotelium.limbo.model.Hotel;
import com.hotelium.limbo.model.Room;
import com.hotelium.limbo.repository.HotelRepository;
import com.hotelium.limbo.repository.RoomRepository;

@Service
public class RoomService extends GenericService<Room, Long, RoomRequestDTO> {
    public RoomService(RoomRepository repository, RoomMapper mapper) {
        super(repository, mapper, Room.class, RoomRequestDTO.class);
    }

    @Autowired
    private RoomRepository repository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomMapper mapper;

    public RoomRequestDTO createHam(RoomRequestDTO requestDTO) {

        Optional<Hotel> hotel = this.hotelRepository.findById(new Long(2));

        System.out.println("--------hotel--------" + hotel.get().toString());

        RoomRequestDTO room = new RoomRequestDTO();
        room.setHotel(hotel.get());

        System.out.println(room);

        return new RoomRequestDTO();
    }
}
