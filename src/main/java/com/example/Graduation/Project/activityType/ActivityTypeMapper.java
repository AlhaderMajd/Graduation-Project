package com.example.Graduation.Project.activityType;

import org.springframework.stereotype.Component;

@Component
public class ActivityTypeMapper {
    public ActivityType toActivityType(ActivityTypeRequest request) {
        ActivityType activityType = new ActivityType();
        activityType.setTypeName(request.getTypeName());
        return activityType;
    }

    public ActivityTypeResponse toActivityTypeResponse(ActivityType activityType) {
        ActivityTypeResponse response = new ActivityTypeResponse();
        response.setTypeId(activityType.getTypeId());
        response.setTypeName(activityType.getTypeName());
        return response;
    }
}