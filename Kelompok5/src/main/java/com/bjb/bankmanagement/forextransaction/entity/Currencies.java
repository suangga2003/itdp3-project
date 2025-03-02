package com.bjb.bankmanagement.forextransaction.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "currencies")
public class Currencies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // Ganti dari String ke LocalDateTime

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Ganti dari String ke LocalDateTime
}
