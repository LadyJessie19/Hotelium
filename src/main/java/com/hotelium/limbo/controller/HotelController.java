package com.hotelium.limbo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelium.limbo.dto.request.HotelRequestDTO;
import com.hotelium.limbo.generic.GenericController;
import com.hotelium.limbo.model.Hotel;
import com.hotelium.limbo.service.HotelService;

@RestController
@RequestMapping(path = "/hotels")
public class HotelController extends GenericController<Hotel, Long, HotelRequestDTO> {
    public HotelController(HotelService hotelService){
        super(hotelService);
    }
}
