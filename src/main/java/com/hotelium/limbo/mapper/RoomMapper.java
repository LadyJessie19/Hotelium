package com.hotelium.limbo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hotelium.limbo.dto.request.RoomRequestDTO;
import com.hotelium.limbo.dto.response.RoomResponseDTO;
import com.hotelium.limbo.generic.GenericMapper;
import com.hotelium.limbo.model.Room;

@Component
public class RoomMapper extends GenericMapper<Room, RoomRequestDTO, RoomResponseDTO> {
    public RoomMapper(ModelMapper mapper) {
        super(mapper);
    }
}
