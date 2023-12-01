package com.hotelium.limbo.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String uniqueCode;

    @Setter
    @Column
    private String creditCard;

    @Setter
    @Column
    private Boolean isCanceled = false;

    @Setter
    @Column
    private Date checkIn;

    @Setter
    @Column
    private Date checkOut;

    @Column
    private Date deadline;

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
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "booking_rooms", joinColumns = @JoinColumn(name = "booking_id"), inverseJoinColumns = @JoinColumn(name = "room_id"))
    private List<Room> rooms = new ArrayList<>();

    private void calculateDeadline() {
        Calendar forewardDate = Calendar.getInstance();
        forewardDate.setTime(this.createdAt);
        forewardDate.add(Calendar.DATE, 7);
        this.deadline = forewardDate.getTime();
    }

    private void generateUniqueCode() {
        this.uniqueCode = UUID.randomUUID().toString();
    }

    @PrePersist
    public void beforePersist() {
        this.createdAt = new Date();
        this.calculateDeadline();
        this.generateUniqueCode();
    }
}