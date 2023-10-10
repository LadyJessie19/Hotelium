package com.hotelium.limbo.mapper;

import org.modelmapper.ModelMapper;

import com.hotelium.limbo.generic.GenericMapper;
import com.hotelium.limbo.model.Room;

public class RoomMapper extends GenericMapper<Room, Long> {
    public RoomMapper(ModelMapper mapper){
        super(mapper);
    }
}
