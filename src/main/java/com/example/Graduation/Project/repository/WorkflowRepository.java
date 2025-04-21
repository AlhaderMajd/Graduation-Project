package com.example.Graduation.Project.repository;

import com.example.Graduation.Project.entity.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkflowRepository extends JpaRepository<Workflow, Long> {
    List<Workflow> findByRequest_RequestId(Long requestId);
    List<Workflow> findByAssignee_UserId(Long userId);
}