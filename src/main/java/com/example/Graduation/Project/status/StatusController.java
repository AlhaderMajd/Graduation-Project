package com.example.Graduation.Project.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statuses")
public class StatusController {
    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public List<StatusResponse> findAll() {
        return statusService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusResponse> findById(@PathVariable Long id) {
        StatusResponse response = statusService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<StatusResponse> create(@RequestBody StatusRequest request) {
        StatusResponse response = statusService.create(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusResponse> update(
            @PathVariable Long id,
            @RequestBody StatusRequest request
    ) {
        StatusResponse response = statusService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        statusService.delete(id);
        return ResponseEntity.noContent().build();
    }
}