package com.hotelium.limbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/create/{hotelId}")
    public ResponseEntity<Room> createRoom(@PathVariable Long hotelId, @RequestBody Room room) {
        Room createdRoom = service.createRoom(hotelId, room);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        service.deleteRoom(id);
        return new ResponseEntity<>("Room deleted successfully", HttpStatus.OK);
    }
}
