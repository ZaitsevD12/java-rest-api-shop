package com.example.shopapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "client")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private String clientSurname;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    public enum Gender {
        MALE, FEMALE, OTHER
    }
}