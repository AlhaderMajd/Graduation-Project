package com.example.Graduation.Project.role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleResponse> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> findById(@PathVariable Long id) {
        RoleResponse roleResponse = roleService.findById(id);
        return ResponseEntity.ok(roleResponse);
    }

    @PostMapping
    public ResponseEntity<RoleResponse> create(@RequestBody RoleRequest roleRequest) {
        RoleResponse roleResponse = roleService.create(roleRequest);
        return ResponseEntity.ok(roleResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResponse> update(
            @PathVariable Long id,
            @RequestBody RoleRequest roleRequest
    ) {
        RoleResponse roleResponse = roleService.update(id, roleRequest);
        return ResponseEntity.ok(roleResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}