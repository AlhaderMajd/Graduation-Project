package com.example.Graduation.Project.activity;

import com.example.Graduation.Project.activityType.ActivityTypeRepository;
import com.example.Graduation.Project.location.LocationRepository;
import com.example.Graduation.Project.status.StatusRepository;
import com.example.Graduation.Project.user.UserMapper;
import com.example.Graduation.Project.user.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ActivityMapper {
    private final UserRepository userRepository;
    private final ActivityTypeRepository activityTypeRepository;
    private final LocationRepository locationRepository;
    private final StatusRepository statusRepository;
    private final UserMapper userMapper;

    public ActivityMapper(UserRepository userRepository,
                          ActivityTypeRepository activityTypeRepository,
                          LocationRepository locationRepository,
                          StatusRepository statusRepository,
                          UserMapper userMapper) {
        this.userRepository = userRepository;
        this.activityTypeRepository = activityTypeRepository;
        this.locationRepository = locationRepository;
        this.statusRepository = statusRepository;
        this.userMapper = userMapper;
    }

    public Activity toActivity(ActivityRequest request) {
        Activity activity = new Activity();

        activity.setRequester(userRepository.findById(request.getRequesterId()).orElseThrow());
        activity.setType(activityTypeRepository.findById(request.getTypeId()).orElseThrow());
        if (request.getSupervisorId() != null)
            activity.setSupervisor(userRepository.findById(request.getSupervisorId()).orElseThrow());

        activity.setLocation(locationRepository.findById(request.getLocationId()).orElseThrow());
        activity.setDescription(request.getDescription());
        activity.setObjectives(request.getObjectives());
        activity.setStartTime(request.getStartTime());
        activity.setEndTime(request.getEndTime());
        activity.setCreatedAt(LocalDateTime.now());

        // 🟢 New mappings
        if (request.getStatusId() != null)
            activity.setStatus(statusRepository.findById(request.getStatusId()).orElseThrow());

        if (request.getAssigneeId() != null)
            activity.setAssignee(userRepository.findById(request.getAssigneeId()).orElseThrow());

        activity.setComment(request.getComment());
        activity.setActionDate(LocalDateTime.now());

        return activity;
    }

    public ActivityResponse toActivityResponse(Activity activity) {
        ActivityResponse response = new ActivityResponse();
        response.setRequestId(activity.getRequestId());
        response.setRequester(userMapper.toUserResponse(activity.getRequester()));
        response.setType(activity.getType());
        if (activity.getSupervisor() != null)
            response.setSupervisor(userMapper.toUserResponse(activity.getSupervisor()));
        response.setLocation(activity.getLocation());
        response.setDescription(activity.getDescription());
        response.setObjectives(activity.getObjectives());
        response.setStartTime(activity.getStartTime());
        response.setEndTime(activity.getEndTime());
        response.setCreatedAt(activity.getCreatedAt());

        // 🟢 New fields
        response.setStatus(activity.getStatus());
        if (activity.getAssignee() != null)
            response.setAssignee(userMapper.toUserResponse(activity.getAssignee()));
        response.setActionDate(activity.getActionDate());
        response.setComment(activity.getComment());
        return response;
    }
}