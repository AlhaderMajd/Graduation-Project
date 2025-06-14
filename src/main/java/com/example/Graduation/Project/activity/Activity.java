package com.example.Graduation.Project.activity;

import com.example.Graduation.Project.activityType.ActivityType;
import com.example.Graduation.Project.location.Location;
import com.example.Graduation.Project.status.Status;
import com.example.Graduation.Project.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
@Data
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;

    @ManyToOne
    @JoinColumn(name = "requester_id", nullable = false)
    private User requester;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private ActivityType type;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private User supervisor;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "objectives")
    private String objectives;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // New fields from Workflow
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @Column(name = "action_date")
    private LocalDateTime actionDate;

    @Column(name = "comment")
    private String comment;
}