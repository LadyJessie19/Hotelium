package com.hotelium.limbo.dto.request;

import java.util.Date;

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
    private Date checkIn;
    
    @NotNull
    @NotEmpty
    private Date checkOut;
}
