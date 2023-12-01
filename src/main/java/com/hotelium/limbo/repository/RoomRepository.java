package com.hotelium.limbo.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hotelium.limbo.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT COUNT(b) FROM Booking b JOIN b.rooms r WHERE r.id = :roomId AND ((b.checkIn <= :checkIn AND b.checkOut >= :checkIn) OR (b.checkIn <= :checkOut AND b.checkOut >= :checkOut))")
    int countActiveBookingsForRoom(@Param("roomId") Long roomId, @Param("checkIn") Date checkIn,
            @Param("checkOut") Date checkOut);
}
