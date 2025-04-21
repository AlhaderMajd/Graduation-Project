package com.example.Graduation.Project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "activity_types")
@Data
public class ActivityType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "type_name", nullable = false, unique = true)
    private String typeName;
}