package com.example.Graduation.Project.user;

import com.example.Graduation.Project.college.College;
import com.example.Graduation.Project.college.CollegeRepository;
import com.example.Graduation.Project.role.Role;
import com.example.Graduation.Project.role.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private CollegeRepository collegeRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserRequest userRequest;
    private UserResponse userResponse;
    private Role role;
    private College college;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup test data
        role = new Role();
        role.setRoleId(1L);
        role.setRoleName("STUDENT");

        college = new College();
        college.setCollegeId(1L);
        college.setCollegeName("Engineering");

        user = new User();
        user.setUserId(1L);
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");
        user.setFullName("Test User");
        user.setPhone("1234567890");
        user.setRole(role);
        user.setCollege(college);

        userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password");
        userRequest.setFullName("Test User");
        userRequest.setPhone("1234567890");
        userRequest.setRoleId(1L);
        userRequest.setCollegeId(1L);

        userResponse = new UserResponse();
        userResponse.setUserId(1L);
        userResponse.setEmail("test@example.com");
        userResponse.setFullName("Test User");
        userResponse.setPhone("1234567890");
        userResponse.setRole(role);
        userResponse.setCollege(college);
    }

    @Test
    void testFindAll() {
        // Arrange
        List<User> users = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        // Act
        List<UserResponse> result = userService.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals(userResponse, result.get(0));
        verify(userRepository).findAll();
        verify(userMapper).toUserResponse(user);
    }

    @Test
    void testFindById() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        // Act
        UserResponse result = userService.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(userResponse, result);
        verify(userRepository).findById(1L);
        verify(userMapper).toUserResponse(user);
    }

    @Test
    void testFindByIdNotFound() {
        // Arrange
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.findById(99L);
        });
        assertEquals("User not found with id: 99", exception.getMessage());
        verify(userRepository).findById(99L);
    }

    @Test
    void testCreate() {
        // Arrange
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(collegeRepository.findById(1L)).thenReturn(Optional.of(college));
        when(userMapper.toUser(userRequest, role, college)).thenReturn(user);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        // Act
        UserResponse result = userService.create(userRequest);

        // Assert
        assertNotNull(result);
        assertEquals(userResponse, result);
        verify(roleRepository).findById(1L);
        verify(collegeRepository).findById(1L);
        verify(userMapper).toUser(userRequest, role, college);
        verify(passwordEncoder).encode("password");
        verify(userRepository).save(user);
        verify(userMapper).toUserResponse(user);
    }

    @Test
    void testCreateRoleNotFound() {
        // Arrange
        when(roleRepository.findById(99L)).thenReturn(Optional.empty());
        userRequest.setRoleId(99L);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.create(userRequest);
        });
        assertEquals("Role not found with id: 99", exception.getMessage());
        verify(roleRepository).findById(99L);
        verify(collegeRepository, never()).findById(any());
    }

    @Test
    void testCreateCollegeNotFound() {
        // Arrange
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(collegeRepository.findById(99L)).thenReturn(Optional.empty());
        userRequest.setCollegeId(99L);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.create(userRequest);
        });
        assertEquals("College not found with id: 99", exception.getMessage());
        verify(roleRepository).findById(1L);
        verify(collegeRepository).findById(99L);
    }

    @Test
    void testUpdate() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(collegeRepository.findById(1L)).thenReturn(Optional.of(college));
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        // Act
        UserResponse result = userService.update(1L, userRequest);

        // Assert
        assertNotNull(result);
        assertEquals(userResponse, result);
        verify(userRepository).findById(1L);
        verify(roleRepository).findById(1L);
        verify(collegeRepository).findById(1L);
        verify(userRepository).save(user);
        verify(userMapper).toUserResponse(user);
    }

    @Test
    void testUpdateUserNotFound() {
        // Arrange
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.update(99L, userRequest);
        });
        assertEquals("User not found with id: 99", exception.getMessage());
        verify(userRepository).findById(99L);
    }

    @Test
    void testDelete() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        userService.delete(1L);

        // Assert
        verify(userRepository).findById(1L);
        verify(userRepository).delete(user);
    }

    @Test
    void testDeleteUserNotFound() {
        // Arrange
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.delete(99L);
        });
        assertEquals("User not found with id: 99", exception.getMessage());
        verify(userRepository).findById(99L);
        verify(userRepository, never()).delete(any());
    }
}