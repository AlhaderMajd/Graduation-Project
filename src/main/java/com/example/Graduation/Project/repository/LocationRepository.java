package com.example.Graduation.Project.repository;

import com.example.Graduation.Project.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByCollege_CollegeId(Long collegeId);
}