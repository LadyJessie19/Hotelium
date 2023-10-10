package com.hotelium.limbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelium.limbo.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
