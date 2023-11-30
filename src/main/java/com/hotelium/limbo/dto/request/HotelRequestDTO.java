package com.hotelium.limbo.dto.request;

import java.util.List;

import com.hotelium.limbo.model.Room;

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
public class HotelRequestDTO {
    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    private String description;

    @NotEmpty
    @NotNull
    private String address;

    @NotEmpty
    @NotNull
    private Integer numberOfRooms;

    @NotEmpty
    @NotNull
    private Integer numberOfGuests;
}
