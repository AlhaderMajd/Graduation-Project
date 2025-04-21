package com.example.Graduation.Project.repository;

import com.example.Graduation.Project.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College, Long> {
    College findByCollegeName(String collegeName);
}