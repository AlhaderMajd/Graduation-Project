package com.example.Graduation.Project.activity;

import com.example.Graduation.Project.activityType.ActivityType;
import com.example.Graduation.Project.location.Location;
import com.example.Graduation.Project.user.UserResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ActivityResponse {
    private Long requestId;
    private UserResponse requester;
    private ActivityType type;
    private UserResponse supervisor;
    private Location location;
    private String description;
    private String objectives;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
}