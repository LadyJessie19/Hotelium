package com.hotelium.limbo.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hotelium.limbo.enums.RoomTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Rooms")
public class Room  {
     
    @Setter
    @Column
    private Number roomNumber;

    @Setter
    @Column
    private String description;

    @Setter
    @Column
    private RoomTypeEnum roomType;

    @Setter
    @Column
    private String coastHour;

    @Setter
    @Column
    private String availability;

    @GeneratedValue
    @Id
    private Long id;

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
}
