package com.hotelium.limbo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelium.limbo.dto.request.UserRequestDTO;
import com.hotelium.limbo.generic.GenericController;
import com.hotelium.limbo.model.User;
import com.hotelium.limbo.service.UserService;

@RestController
@RequestMapping(path = "/users")
// @SecurityRequirement(name = "Authorization")
public class UserController extends GenericController<User, Long ,UserRequestDTO> {
    public UserController(UserService service){
        super(service);
    }
}
