package com.hotelium.limbo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hotelium.limbo.enums.RoomTypeEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "rooms")
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column
    private Long roomNumber;

    @Setter
    @Column
    private String description;

    @Setter
    @Column
    @Enumerated(EnumType.STRING)
    private RoomTypeEnum roomType;

    @Setter
    @Column
    private Long coastHour;

    @Setter
    @Column
    private Boolean availability;

    @CreationTimestamp
    @Column
    private Date createdAt;

    @UpdateTimestamp
    @Column
    private Date updatedAt;

    @Column(name = "createdBy")
    private String createdBy = "Jessie";

    @Column(name = "updatedBy")
    private String updatedBy = "Jessie";

    @Setter
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonBackReference
    private Hotel hotel;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    // @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany(mappedBy = "rooms", cascade = CascadeType.ALL)
    private Set<Booking> bookings = new HashSet<>();

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", description='" + description + '\'' +
                ", roomType=" + roomType +
                ", costHour=" + coastHour +
                ", availability=" + availability +
                ", hotel=" + hotel +
                ", bookings=" + bookings +
                '}';
    }
}
