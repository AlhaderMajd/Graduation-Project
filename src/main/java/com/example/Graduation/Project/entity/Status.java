package com.example.Graduation.Project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "statuses")
@Data
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "status_name", nullable = false, unique = true)
    private String statusName;
}