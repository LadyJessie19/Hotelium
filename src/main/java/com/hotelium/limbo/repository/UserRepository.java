package com.hotelium.limbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelium.limbo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {}