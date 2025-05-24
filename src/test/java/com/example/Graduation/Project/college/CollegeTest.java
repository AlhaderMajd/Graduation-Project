package com.example.Graduation.Project.college;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CollegeTest {

    @Mock
    private CollegeRepository collegeRepository;

    @Mock
    private CollegeMapper collegeMapper;

    @InjectMocks
    private CollegeService collegeService;

    private College college;
    private CollegeRequest collegeRequest;
    private CollegeResponse collegeResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup test data
        college = new College();
        college.setCollegeId(1L);
        college.setCollegeName("Engineering");

        collegeRequest = new CollegeRequest();
        collegeRequest.setCollegeName("Engineering");

        collegeResponse = new CollegeResponse();
        collegeResponse.setCollegeId(1L);
        collegeResponse.setCollegeName("Engineering");
    }

    @Test
    void testFindAll() {
        // Arrange
        List<College> colleges = Arrays.asList(college);
        when(collegeRepository.findAll()).thenReturn(colleges);
        when(collegeMapper.toCollegeResponse(college)).thenReturn(collegeResponse);

        // Act
        List<CollegeResponse> result = collegeService.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals(collegeResponse, result.get(0));
        verify(collegeRepository).findAll();
        verify(collegeMapper).toCollegeResponse(college);
    }

    @Test
    void testFindById() {
        // Arrange
        when(collegeRepository.findById(1L)).thenReturn(Optional.of(college));
        when(collegeMapper.toCollegeResponse(college)).thenReturn(collegeResponse);

        // Act
        CollegeResponse result = collegeService.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(collegeResponse, result);
        verify(collegeRepository).findById(1L);
        verify(collegeMapper).toCollegeResponse(college);
    }

    @Test
    void testFindByIdNotFound() {
        // Arrange
        when(collegeRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            collegeService.findById(99L);
        });
        assertEquals("College not found with id: 99", exception.getMessage());
        verify(collegeRepository).findById(99L);
    }

    @Test
    void testCreate() {
        // Arrange
        when(collegeMapper.toCollege(collegeRequest)).thenReturn(college);
        when(collegeRepository.save(college)).thenReturn(college);
        when(collegeMapper.toCollegeResponse(college)).thenReturn(collegeResponse);

        // Act
        CollegeResponse result = collegeService.create(collegeRequest);

        // Assert
        assertNotNull(result);
        assertEquals(collegeResponse, result);
        verify(collegeMapper).toCollege(collegeRequest);
        verify(collegeRepository).save(college);
        verify(collegeMapper).toCollegeResponse(college);
    }

    @Test
    void testUpdate() {
        // Arrange
        when(collegeRepository.findById(1L)).thenReturn(Optional.of(college));
        when(collegeRepository.save(college)).thenReturn(college);
        when(collegeMapper.toCollegeResponse(college)).thenReturn(collegeResponse);

        // Act
        CollegeResponse result = collegeService.update(1L, collegeRequest);

        // Assert
        assertNotNull(result);
        assertEquals(collegeResponse, result);
        verify(collegeRepository).findById(1L);
        verify(collegeRepository).save(college);
        verify(collegeMapper).toCollegeResponse(college);
    }

    @Test
    void testUpdateCollegeNotFound() {
        // Arrange
        when(collegeRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            collegeService.update(99L, collegeRequest);
        });
        assertEquals("College not found with id: 99", exception.getMessage());
        verify(collegeRepository).findById(99L);
    }

    @Test
    void testDelete() {
        // Arrange
        when(collegeRepository.findById(1L)).thenReturn(Optional.of(college));

        // Act
        collegeService.delete(1L);

        // Assert
        verify(collegeRepository).findById(1L);
        verify(collegeRepository).delete(college);
    }

    @Test
    void testDeleteCollegeNotFound() {
        // Arrange
        when(collegeRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            collegeService.delete(99L);
        });
        assertEquals("College not found with id: 99", exception.getMessage());
        verify(collegeRepository).findById(99L);
        verify(collegeRepository, never()).delete(any());
    }
}