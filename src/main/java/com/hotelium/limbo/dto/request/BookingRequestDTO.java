package com.hotelium.limbo.dto.request;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class BookingRequestDTO {
    @NotNull
    @NotEmpty
    @JsonProperty("checkIn")
    private Date checkIn;

    @JsonProperty("checkOut")
    @NotNull
    @NotEmpty
    private Date checkOut;

    @JsonProperty("userId")
    @NotNull
    @NotEmpty
    private Long userId;

    @JsonProperty("roomIds")
    @NotNull
    @NotEmpty
    private List<Long> roomIds;

    @Override
    public String toString() {
        return "BookingDTO{" +
                "checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", userId=" + userId +
                ", roomIds=" + roomIds +
                '}';
    }
}
