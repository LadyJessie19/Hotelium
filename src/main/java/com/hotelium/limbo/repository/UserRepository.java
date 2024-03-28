package com.hotelium.limbo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.hotelium.limbo.model.User;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> getUserByLogin(String login);

    UserDetails findByLogin(String login);

    @Query("SELECT u FROM User u JOIN Booking b ON u.id = b.user.id WHERE (:isCanceled IS NULL OR b.isCanceled = :isCanceled)")
    List<User> findUsersByBookingStatus(@Param("isCanceled") Boolean isCanceled);
}