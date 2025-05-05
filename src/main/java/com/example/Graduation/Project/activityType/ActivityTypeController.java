package com.example.Graduation.Project.activityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity-types")
public class ActivityTypeController {
    private final ActivityTypeService activityTypeService;

    @Autowired
    public ActivityTypeController(ActivityTypeService activityTypeService) {
        this.activityTypeService = activityTypeService;
    }

    @GetMapping
    public List<ActivityTypeResponse> findAll() {
        return activityTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityTypeResponse> findById(@PathVariable Long id) {
        ActivityTypeResponse response = activityTypeService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ActivityTypeResponse> create(@RequestBody ActivityTypeRequest request) {
        ActivityTypeResponse response = activityTypeService.create(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityTypeResponse> update(
            @PathVariable Long id,
            @RequestBody ActivityTypeRequest request
    ) {
        ActivityTypeResponse response = activityTypeService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activityTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}