package com.hotelium.limbo.dto.request;

import com.hotelium.limbo.enums.UserRoleEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    private UserRoleEnum role = UserRoleEnum.USER;
}
