package com.hotelium.limbo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;

import com.hotelium.limbo.dto.request.RoomRequestDTO;
import com.hotelium.limbo.generic.GenericService;
import com.hotelium.limbo.mapper.RoomMapper;
import com.hotelium.limbo.model.Hotel;
import com.hotelium.limbo.model.Room;
import com.hotelium.limbo.repository.HotelRepository;
import com.hotelium.limbo.repository.RoomRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoomService extends GenericService<Room, Long, RoomRequestDTO> {
    public RoomService(RoomRepository repository, RoomMapper mapper) {
        super(repository, mapper, Room.class, RoomRequestDTO.class);
    }

    @Autowired
    private RoomRepository repository;

    @Autowired
    private HotelRepository hotelRepository;

    public Room createRoom(Long hotelId, Room room) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);

        if (hotel.isPresent()) {
            room.setHotel(hotel.get());
            return repository.save(room);
        } else {
            throw new EntityNotFoundException("Hotel not found");
        }
    }
}
