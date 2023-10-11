package com.hotelium.limbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelium.limbo.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {}
