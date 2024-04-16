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
public class BookingResponseDTO {
    private Long id;
    private String creditCard;
    private Date checkIn;
    private Date checkOut;
    private Long userId;
    private List<Long> roomIds;
}
