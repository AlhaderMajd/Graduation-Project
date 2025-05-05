package com.example.Graduation.Project.activity;

import com.example.Graduation.Project.activityType.ActivityType;
import com.example.Graduation.Project.activityType.ActivityTypeRepository;
import com.example.Graduation.Project.location.Location;
import com.example.Graduation.Project.location.LocationRepository;
import com.example.Graduation.Project.user.User;
import com.example.Graduation.Project.user.UserMapper;
import com.example.Graduation.Project.user.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ActivityMapper {
    private final UserRepository userRepository;
    private final ActivityTypeRepository activityTypeRepository;
    private final LocationRepository locationRepository;
    private final UserMapper userMapper; // Add this

    public ActivityMapper(UserRepository userRepository,
                          ActivityTypeRepository activityTypeRepository,
                          LocationRepository locationRepository,
                          UserMapper userMapper) { // Add this parameter
        this.userRepository = userRepository;
        this.activityTypeRepository = activityTypeRepository;
        this.locationRepository = locationRepository;
        this.userMapper = userMapper;
    }

    public Activity toActivity(ActivityRequest request) {
        Activity activity = new Activity();

        User requester = userRepository.findById(request.getRequesterId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getRequesterId()));
        activity.setRequester(requester);

        ActivityType type = activityTypeRepository.findById(request.getTypeId())
                .orElseThrow(() -> new RuntimeException("ActivityType not found with id: " + request.getTypeId()));
        activity.setType(type);

        if (request.getSupervisorId() != null) {
            User supervisor = userRepository.findById(request.getSupervisorId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getSupervisorId()));
            activity.setSupervisor(supervisor);
        }

        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + request.getLocationId()));
        activity.setLocation(location);

        activity.setDescription(request.getDescription());
        activity.setObjectives(request.getObjectives());
        activity.setStartTime(request.getStartTime());
        activity.setEndTime(request.getEndTime());
        activity.setCreatedAt(LocalDateTime.now());

        return activity;
    }

    public ActivityResponse toActivityResponse(Activity activity) {
        ActivityResponse response = new ActivityResponse();
        response.setRequestId(activity.getRequestId());
        response.setRequester(userMapper.toUserResponse(activity.getRequester()));
        response.setType(activity.getType());

        if (activity.getSupervisor() != null) {
            response.setSupervisor(userMapper.toUserResponse(activity.getSupervisor()));
        }

        response.setLocation(activity.getLocation());
        response.setDescription(activity.getDescription());
        response.setObjectives(activity.getObjectives());
        response.setStartTime(activity.getStartTime());
        response.setEndTime(activity.getEndTime());
        response.setCreatedAt(activity.getCreatedAt());
        return response;
    }
}