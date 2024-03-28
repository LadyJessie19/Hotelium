package com.hotelium.limbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hotelium.limbo.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.user.id = :userId")
    List<Booking> findByUserId(Long userId);

    @Query("SELECT b FROM Booking b WHERE b.user.id = :userId AND b.isCanceled = :isCanceled")
    List<Booking> findByUserIdAndIsCanceled(Long userId, Boolean isCanceled);
}
