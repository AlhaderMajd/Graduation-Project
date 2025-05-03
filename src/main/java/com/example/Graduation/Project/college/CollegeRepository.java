package com.example.Graduation.Project.college;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College, Long> {
    College findByCollegeName(String collegeName);
    boolean existsByCollegeName(String name);
}