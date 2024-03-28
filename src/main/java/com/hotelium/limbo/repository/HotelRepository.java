package com.hotelium.limbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hotelium.limbo.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("SELECT h FROM Hotel h WHERE (:name IS NULL OR LOWER(h.name) LIKE LOWER(CONCAT('%', :name,'%'))) AND (:destination IS NULL OR h.address LIKE %:destination%)")
    List<Hotel> findHotels(@Param("name") String name, @Param("destination") String destination);
}
