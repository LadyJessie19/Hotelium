package com.hotelium.limbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelium.limbo.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    
}
