package com.example.shopapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "images")
@Data
public class Image {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Lob
    @Column(columnDefinition = "bytea")
    @JdbcTypeCode(SqlTypes.LONGVARBINARY)
    private byte[] image;
}