package com.example.Graduation.Project.status;

import org.springframework.stereotype.Component;

@Component
public class StatusMapper {
    public Status toStatus(StatusRequest statusRequest) {
        Status status = new Status();
        status.setStatusName(statusRequest.getStatusName());
        return status;
    }

    public StatusResponse toStatusResponse(Status status) {
        StatusResponse response = new StatusResponse();
        response.setStatusId(status.getStatusId());
        response.setStatusName(status.getStatusName());
        return response;
    }
}