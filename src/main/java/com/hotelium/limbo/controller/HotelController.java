package com.hotelium.limbo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelium.limbo.dto.request.HotelRequestDTO;
import com.hotelium.limbo.generic.GenericController;
import com.hotelium.limbo.model.Hotel;
import com.hotelium.limbo.service.HotelService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/hotels")
@Tag(name = "Hotels", description = "Operations related to hotels")
public class HotelController extends GenericController<Hotel, Long, HotelRequestDTO> {
    public HotelController(HotelService hotelService) {
        super(hotelService);
    }

    @Autowired
    private HotelService service;

    @GetMapping("/search")
    public List<Hotel> searchHotels(@RequestParam(required = false) String name,
            @RequestParam(required = false) String destination) {
        return service.findHotels(name, destination);
    }
}
