package com.example.Graduation.Project.repository;

import com.example.Graduation.Project.entity.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {
    ActivityType findByTypeName(String typeName);
}