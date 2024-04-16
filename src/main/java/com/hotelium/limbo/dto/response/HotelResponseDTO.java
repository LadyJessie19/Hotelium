package com.hotelium.limbo.dto.response;

import java.util.Date;
import java.util.List;

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
    private String description;
    private String address;
    private List<RoomResponseDTO> rooms;
    private Integer numberOfRooms;
    private Integer numberOfGuests;
    private Date createdAt;
    private Date updatedAt;
}
