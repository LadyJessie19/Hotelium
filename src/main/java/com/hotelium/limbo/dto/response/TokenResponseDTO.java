package com.hotelium.limbo.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponseDTO {

    private String token;
    private String message;

    public TokenResponseDTO(String token) {
        this.token = token;
        this.message = "The token was successfully generated!";
    }
}
