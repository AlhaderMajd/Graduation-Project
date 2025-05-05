package com.example.Graduation.Project.role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public Role toRole(RoleRequest roleRequest) {
        Role role = new Role();
        role.setRoleName(roleRequest.getRoleName());
        return role;
    }

    public RoleResponse toRoleResponse(Role role) {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setRoleId(role.getRoleId());
        roleResponse.setRoleName(role.getRoleName());
        return roleResponse;
    }
}