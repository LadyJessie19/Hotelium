package com.hotelium.limbo.dto.response;

import java.util.List;

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
public class HotelResponseDTO {
    private Long id;
    private String name;
    private List<RoomResponseDTO> rooms;
    private Integer numberOfRooms;
    private Integer numberOfGuests;
}
