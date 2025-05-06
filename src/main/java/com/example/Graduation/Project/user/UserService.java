package com.example.Graduation.Project.user;

import com.example.Graduation.Project.college.College;
import com.example.Graduation.Project.college.CollegeRepository;
import com.example.Graduation.Project.role.Role;
import com.example.Graduation.Project.role.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final CollegeRepository collegeRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(
            UserRepository userRepository,
            UserMapper userMapper,
            RoleRepository roleRepository,
            CollegeRepository collegeRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.collegeRepository = collegeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userMapper.toUserResponse(user);
    }

    @Transactional
    public UserResponse create(UserRequest userRequest) {
        Role role = roleRepository.findById(userRequest.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + userRequest.getRoleId()));

        College college = collegeRepository.findById(userRequest.getCollegeId())
                .orElseThrow(() -> new RuntimeException("College not found with id: " + userRequest.getCollegeId()));

        User user = userMapper.toUser(userRequest, role, college);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword())); // Encode password

        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Transactional
    public UserResponse update(Long id, UserRequest userRequest) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        Role role = roleRepository.findById(userRequest.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + userRequest.getRoleId()));

        College college = collegeRepository.findById(userRequest.getCollegeId())
                .orElseThrow(() -> new RuntimeException("College not found with id: " + userRequest.getCollegeId()));

        existingUser.setEmail(userRequest.getEmail());
        existingUser.setPassword(userRequest.getPassword());
        existingUser.setFullName(userRequest.getFullName());
        existingUser.setPhone(userRequest.getPhone());
        existingUser.setRole(role);
        existingUser.setCollege(college);

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toUserResponse(updatedUser);
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }
}