package com.example.Graduation.Project.repository;

import com.example.Graduation.Project.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByRequester_UserId(Long userId);
    List<Activity> findBySupervisor_UserId(Long userId);
}