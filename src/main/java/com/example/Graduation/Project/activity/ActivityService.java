package com.example.Graduation.Project.activity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    public ActivityService(ActivityRepository activityRepository,
                           ActivityMapper activityMapper) {
        this.activityRepository = activityRepository;
        this.activityMapper = activityMapper;
    }

    public List<ActivityResponse> findAll() {
        return activityRepository.findAll()
                .stream()
                .map(activityMapper::toActivityResponse)
                .collect(Collectors.toList());
    }

    public ActivityResponse findById(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));
        return activityMapper.toActivityResponse(activity);
    }

    @Transactional
    public ActivityResponse create(ActivityRequest request) {
        Activity activity = activityMapper.toActivity(request);
        Activity savedActivity = activityRepository.save(activity);
        return activityMapper.toActivityResponse(savedActivity);
    }

    @Transactional
    public ActivityResponse update(Long id, ActivityRequest request) {
        Activity existingActivity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));

        // Update all fields except createdAt
        Activity updatedActivity = activityMapper.toActivity(request);
        updatedActivity.setRequestId(id);
        updatedActivity.setCreatedAt(existingActivity.getCreatedAt());

        Activity savedActivity = activityRepository.save(updatedActivity);
        return activityMapper.toActivityResponse(savedActivity);
    }

    @Transactional
    public void delete(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));
        activityRepository.delete(activity);
    }
}