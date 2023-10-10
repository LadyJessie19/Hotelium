package com.hotelium.limbo.controller;

import com.hotelium.limbo.dto.request.RoomRequestDTO;
import com.hotelium.limbo.generic.GenericController;
import com.hotelium.limbo.model.Room;
import com.hotelium.limbo.service.RoomService;

public class RoomController extends GenericController<Room, Long, RoomRequestDTO> {
    public RoomController(RoomService service){
        super(service);
    }
}
