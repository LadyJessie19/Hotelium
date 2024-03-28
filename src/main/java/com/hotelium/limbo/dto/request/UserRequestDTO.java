package com.hotelium.limbo.dto.request;

import com.hotelium.limbo.enums.UserRoleEnum;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    private String login;

    @NotEmpty
    @NotNull
    private String password;

    @NotEmpty
    @NotNull
    private UserRoleEnum role;
}
