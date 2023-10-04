package com.hotelium.limbo.enums;

import lombok.Getter;

@Getter
public enum RoomTypeEnum {
    SINGLE("Single"),
    COUPLE("Couple"),
    FAMILY("Family"),
    PRESIDENT("President");
   
   private final String type;

    RoomTypeEnum(String type) {
        this.type = type;
    }
}
