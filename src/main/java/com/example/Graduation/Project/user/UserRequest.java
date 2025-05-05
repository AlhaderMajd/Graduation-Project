package com.example.Graduation.Project.user;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private Long roleId;
    private Long collegeId;
}
