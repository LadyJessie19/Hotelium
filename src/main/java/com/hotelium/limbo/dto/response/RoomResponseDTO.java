package com.hotelium.limbo.dto.response;

import com.hotelium.limbo.enums.RoomTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseDTO {
    private Long id;
    private Long hotelId;
    private Long roomNumber;
    private String description;
    private RoomTypeEnum roomType;
    private int coastHour;
    private Boolean availability;
}
