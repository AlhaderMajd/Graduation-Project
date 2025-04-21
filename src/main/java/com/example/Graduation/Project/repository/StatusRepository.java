package com.example.Graduation.Project.repository;

import com.example.Graduation.Project.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByStatusName(String statusName);
}