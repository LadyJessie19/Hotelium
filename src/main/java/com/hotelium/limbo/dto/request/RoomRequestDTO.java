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
    private Long coastHour;

    @NotEmpty
    @NotNull
    private Boolean availability;

    @NotEmpty
    @NotNull
    private Hotel hotel;

    @Override
    public String toString() {
        return "RoomRequestDTO{" +
                "roomNumber=" + roomNumber +
                ", description='" + description + '\'' +
                ", roomType=" + roomType +
                ", costHour=" + coastHour +
                ", availability=" + availability +
                ", hotel=" + hotel +
                '}';
    }
}
