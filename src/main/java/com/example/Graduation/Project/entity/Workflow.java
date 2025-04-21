package com.example.Graduation.Project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "worksflow")
@Data
public class Workflow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workflow_id")
    private Long workflowId;

    @ManyToOne
    @JoinColumn(name = "assignee_id", nullable = false)
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private Activity request;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @Column(name = "action_date")
    private LocalDateTime actionDate;

    @Column(name = "comment")
    private String comment;
}