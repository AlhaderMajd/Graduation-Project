package com.example.Graduation.Project.college;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colleges")
public class CollegeController {
    private final CollegeService collegeService;

    @Autowired
    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @GetMapping
    public List<CollegeResponse> findAll() {
        return collegeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollegeResponse> findById(@PathVariable Long id) {
        CollegeResponse response = collegeService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CollegeResponse> create(@RequestBody CollegeRequest request) {
        CollegeResponse response = collegeService.create(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollegeResponse> update(
            @PathVariable Long id,
            @RequestBody CollegeRequest request
    ) {
        CollegeResponse response = collegeService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        collegeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}