package com.hotelium.limbo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationRequestDTO {
    @Email
    @NotEmpty
    private String login;

    @NotEmpty
    @Size(min = 8, max = 20)
    private String password;
}
