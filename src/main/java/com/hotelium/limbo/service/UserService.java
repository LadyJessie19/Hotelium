package com.hotelium.limbo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelium.limbo.dto.request.CreateUserDTO;
import com.hotelium.limbo.dto.response.UserResponseDTO;
import com.hotelium.limbo.generic.GenericService;
import com.hotelium.limbo.mapper.UserMapper;
import com.hotelium.limbo.model.User;
import com.hotelium.limbo.repository.UserRepository;

@Service
public class UserService extends GenericService<User, String, CreateUserDTO, UserResponseDTO> {

    public UserService(UserRepository repository, UserMapper mapper) {
        super(repository, mapper, User.class, CreateUserDTO.class, UserResponseDTO.class);
    }

    @Autowired
    private UserRepository repository;

    public List<User> findUsersByBookingStatus(Boolean isCanceled) {
        return repository.findUsersByBookingStatus(isCanceled);
    }
}
