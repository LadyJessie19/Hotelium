package com.hotelium.limbo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

    @GeneratedValue
    @Id
    private Long id;

    @Setter
    @Column
    private Date checkIn;

    @Setter
    @Column
    private Date checkOut;

    @Column
    @CreationTimestamp
    private Date createdAt;

    @Column
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "createdBy")
    private String createdBy = "Jessie";

    @Column(name = "updatedBy")
    private String updatedBy = "Jessie";

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    // @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "booking_rooms", joinColumns = @JoinColumn(name = "booking_id"), inverseJoinColumns = @JoinColumn(name = "room_id"))
    private Set<Room> rooms = new HashSet<>();
}