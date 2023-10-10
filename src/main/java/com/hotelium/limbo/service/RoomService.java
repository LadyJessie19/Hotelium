package com.hotelium.limbo.service;

import com.hotelium.limbo.dto.request.RoomRequestDTO;
import com.hotelium.limbo.generic.GenericService;
import com.hotelium.limbo.model.Room;
import com.hotelium.limbo.repository.RoomRepository;

public class RoomService extends GenericService<Room, Long, RoomRequestDTO> {
    public RoomService(RoomRepository repository){
        super(repository);
    }
}
