package com.example.Graduation.Project.status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByStatusName(String statusName);
    boolean existsByStatusName(String name);

}