package com.hotelium.limbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelium.limbo.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long userId);

    List<Booking> findByUserIdAndIsCanceled(Long userId, Boolean isCanceled);
}
