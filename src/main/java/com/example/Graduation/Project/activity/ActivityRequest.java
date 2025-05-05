package com.example.Graduation.Project.activity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ActivityRequest {
    private Long requesterId;
    private Long typeId;
    private Long supervisorId;
    private Long locationId;
    private String description;
    private String objectives;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}