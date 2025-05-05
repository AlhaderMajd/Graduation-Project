package com.example.Graduation.Project.role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public List<RoleResponse> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .collect(Collectors.toList());
    }

    public RoleResponse findById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        return roleMapper.toRoleResponse(role);
    }

    @Transactional
    public RoleResponse create(RoleRequest roleRequest) {
        Role role = roleMapper.toRole(roleRequest);
        Role savedRole = roleRepository.save(role);
        return roleMapper.toRoleResponse(savedRole);
    }

    @Transactional
    public RoleResponse update(Long id, RoleRequest roleRequest) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        existingRole.setRoleName(roleRequest.getRoleName());
        Role updatedRole = roleRepository.save(existingRole);

        return roleMapper.toRoleResponse(updatedRole);
    }

    @Transactional
    public void delete(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        roleRepository.delete(role);
    }
}