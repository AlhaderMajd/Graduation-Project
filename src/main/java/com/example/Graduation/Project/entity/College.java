package com.example.Graduation.Project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "colleges")
@Data
public class College {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "college_id")
    private Long collegeId;

    @Column(name = "college_name", nullable = false, unique = true)
    private String collegeName;
}