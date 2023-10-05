package com.hotelium.limbo.service;

import org.springframework.stereotype.Service;

import com.hotelium.limbo.dto.request.UserRequestDTO;
import com.hotelium.limbo.generic.GenericService;
import com.hotelium.limbo.mapper.UserMapper;
import com.hotelium.limbo.model.User;
import com.hotelium.limbo.repository.UserRepository;

@Service
public class UserService extends GenericService<User, Long, UserRequestDTO> {
    public UserService(UserRepository repository, UserMapper mapper){
        super(repository, mapper, User.class, UserRequestDTO.class);
    }
}
