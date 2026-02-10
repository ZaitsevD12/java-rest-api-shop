package com.example.shopapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
@Table(name = "supplier")
@Data
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "phone_number")
    private String phoneNumber;
}