package com.hotelium.limbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hotelium.limbo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByLogin(String login);

    @Query("SELECT u FROM User u JOIN Booking b ON u.id = b.user.id WHERE (:isCanceled IS NULL OR b.isCanceled = :isCanceled)")
    List<User> findUsersByBookingStatus(@Param("isCanceled") Boolean isCanceled);
}