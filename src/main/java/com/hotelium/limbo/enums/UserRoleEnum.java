package com.hotelium.limbo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoleEnum {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;
}
