package com.example.Graduation.Project.activityType;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityTypeService {
    private final ActivityTypeRepository activityTypeRepository;
    private final ActivityTypeMapper activityTypeMapper;

    public ActivityTypeService(ActivityTypeRepository activityTypeRepository,
                               ActivityTypeMapper activityTypeMapper) {
        this.activityTypeRepository = activityTypeRepository;
        this.activityTypeMapper = activityTypeMapper;
    }

    public List<ActivityTypeResponse> findAll() {
        return activityTypeRepository.findAll()
                .stream()
                .map(activityTypeMapper::toActivityTypeResponse)
                .collect(Collectors.toList());
    }

    public ActivityTypeResponse findById(Long id) {
        ActivityType activityType = activityTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ActivityType not found with id: " + id));
        return activityTypeMapper.toActivityTypeResponse(activityType);
    }

    @Transactional
    public ActivityTypeResponse create(ActivityTypeRequest request) {
        ActivityType activityType = activityTypeMapper.toActivityType(request);
        ActivityType savedActivityType = activityTypeRepository.save(activityType);
        return activityTypeMapper.toActivityTypeResponse(savedActivityType);
    }

    @Transactional
    public ActivityTypeResponse update(Long id, ActivityTypeRequest request) {
        ActivityType existingActivityType = activityTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ActivityType not found with id: " + id));

        existingActivityType.setTypeName(request.getTypeName());
        ActivityType updatedActivityType = activityTypeRepository.save(existingActivityType);

        return activityTypeMapper.toActivityTypeResponse(updatedActivityType);
    }

    @Transactional
    public void delete(Long id) {
        ActivityType activityType = activityTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ActivityType not found with id: " + id));
        activityTypeRepository.delete(activityType);
    }
}