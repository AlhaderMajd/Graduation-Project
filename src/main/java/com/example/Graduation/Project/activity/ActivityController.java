package com.example.Graduation.Project.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public List<ActivityResponse> findAll() {
        return activityService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityResponse> findById(@PathVariable Long id) {
        ActivityResponse response = activityService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ActivityResponse> create(@RequestBody ActivityRequest request) {
        ActivityResponse response = activityService.create(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityResponse> update(
            @PathVariable Long id,
            @RequestBody ActivityRequest request
    ) {
        ActivityResponse response = activityService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}