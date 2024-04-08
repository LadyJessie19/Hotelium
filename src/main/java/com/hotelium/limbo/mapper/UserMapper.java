package com.hotelium.limbo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hotelium.limbo.dto.request.CreateUserDTO;
import com.hotelium.limbo.generic.GenericMapper;
import com.hotelium.limbo.model.User;

@Component
public class UserMapper extends GenericMapper<User, CreateUserDTO> {
    public UserMapper(ModelMapper mapper) {
        super(mapper);
    }
}
