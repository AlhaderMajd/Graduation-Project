package com.example.Graduation.Project.workflow;

import com.example.Graduation.Project.activity.Activity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkflowRepository extends JpaRepository<Workflow, Long> {
    List<Workflow> findByRequest_RequestId(Long requestId);
    List<Workflow> findByAssignee_UserId(Long userId);
    boolean existsByAssigneeAndRequest(User assignee, Activity request);

}