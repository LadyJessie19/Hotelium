package com.hotelium.limbo.dto.request;

import com.hotelium.limbo.enums.RoomTypeEnum;
import com.hotelium.limbo.model.Hotel;

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
public class RoomRequestDTO {
    @NotNull
    private int roomNumber;

    @NotNull
    private String description;

    @NotNull
    private RoomTypeEnum roomType;

    @NotNull
    private int coastHour;

    @NotNull
    private Boolean availability;

    private Hotel hotel;

    @Override
    public String toString() {
        return "RoomRequestDTO{" +
                "roomNumber=" + roomNumber +
                ", description='" + description + '\'' +
                ", roomType=" + roomType +
                ", costHour=" + coastHour +
                ", availability=" + availability +
                '}';
    }
}
