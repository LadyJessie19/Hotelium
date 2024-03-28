package com.hotelium.limbo.dto.response;

import java.util.Date;
import java.util.Set;

import com.hotelium.limbo.enums.UserRoleEnum;
import com.hotelium.limbo.model.Booking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private String id;
    private String name;
    private String login;
    private String role;
    private String createdAt;
    private String updatedAt;
    private String createdBy;
    private String updatedBy;
    private String bookings;
    private Boolean enabled;
    private String authorities;

    public UserResponseDTO(String id2, String name2, String login2, UserRoleEnum role2, Date createdAt2,
            Date updatedAt2, String createdBy2, String updatedBy2, Set<Booking> bookings2, boolean enabled2,
            UserRoleEnum user) {

        this.id = id2;
        this.name = name2;
        this.login = login2;
        this.role = role2.toString();
        this.createdAt = createdAt2.toString();
        this.updatedAt = updatedAt2.toString();
        this.createdBy = createdBy2;
        this.updatedBy = updatedBy2;
        this.bookings = bookings2.toString();
        this.enabled = enabled2;
        this.authorities = user.toString();
    }
}
