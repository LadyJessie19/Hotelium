package com.hotelium.limbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelium.limbo.dto.request.RoomRequestDTO;
import com.hotelium.limbo.generic.GenericController;
import com.hotelium.limbo.model.Room;
import com.hotelium.limbo.service.RoomService;

@RestController
@RequestMapping(path = "/rooms")
public class RoomController extends GenericController<Room, Long, RoomRequestDTO> {
    public RoomController(RoomService service) {
        super(service);
    }

    @Autowired
    private RoomService service;

    @PostMapping("/create")
    public RoomRequestDTO createHamCon(@RequestBody RoomRequestDTO requestDTO) {
        return this.service.createHam(requestDTO);
    }
}
