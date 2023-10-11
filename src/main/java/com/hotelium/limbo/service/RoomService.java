package com.hotelium.limbo.service;

import org.springframework.stereotype.Service;

import com.hotelium.limbo.dto.request.RoomRequestDTO;
import com.hotelium.limbo.generic.GenericService;
import com.hotelium.limbo.mapper.RoomMapper;
import com.hotelium.limbo.model.Room;
import com.hotelium.limbo.repository.RoomRepository;

@Service
public class RoomService extends GenericService<Room, Long, RoomRequestDTO> {
    public RoomService(RoomRepository repository, RoomMapper mapper){
        super(repository, mapper, Room.class, RoomRequestDTO.class);
    }
}
