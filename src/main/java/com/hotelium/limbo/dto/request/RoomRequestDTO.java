package com.hotelium.limbo.dto.request;

import com.hotelium.limbo.enums.RoomTypeEnum;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RoomRequestDTO {
    @NotEmpty
    @NotNull
    private Long roomNumber;

    @NotEmpty
    @NotNull
    private String description;

    @NotEmpty
    @NotNull
    private RoomTypeEnum roomType;

    @NotEmpty
    @NotNull
    private String coastHour;

    @NotEmpty
    @NotNull
    private Boolean availability;
}
