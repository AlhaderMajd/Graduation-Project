package com.example.Graduation.Project.user;
import com.example.Graduation.Project.college.College;
import com.example.Graduation.Project.role.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponse {
    private Long userId;
    private String email;
    private String fullName;
    private String phone;
    private Role role;
    private College college;
}
