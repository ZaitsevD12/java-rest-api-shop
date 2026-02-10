package com.example.shopapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
@Table(name = "address")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;
}