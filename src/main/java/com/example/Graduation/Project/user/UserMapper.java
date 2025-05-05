package com.example.Graduation.Project.user;
import com.example.Graduation.Project.college.College;
import com.example.Graduation.Project.role.Role;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserRequest userRequest, Role role, College college) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setFullName(userRequest.getFullName());
        user.setPhone(userRequest.getPhone());
        user.setRole(role);
        user.setCollege(college);
        return user;
    }

    public UserResponse toUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFullName(user.getFullName());
        userResponse.setPhone(user.getPhone());
        userResponse.setRole(user.getRole());
        userResponse.setCollege(user.getCollege());
        return userResponse;
    }
}