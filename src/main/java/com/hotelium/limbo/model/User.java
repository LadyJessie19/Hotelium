package com.hotelium.limbo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Setter
    @Column
    private String name;

    @Setter
    @Column
    private String email;

    @Setter
    @Column
    private String login;

    @Setter
    @Column
    private String password;

    @Setter
    @Column
    private String profile;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Booking> bookings = new HashSet<>();
}
