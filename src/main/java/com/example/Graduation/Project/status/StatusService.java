package com.example.Graduation.Project.status;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusService {
    private final StatusRepository statusRepository;
    private final StatusMapper statusMapper;

    public StatusService(StatusRepository statusRepository, StatusMapper statusMapper) {
        this.statusRepository = statusRepository;
        this.statusMapper = statusMapper;
    }

    public List<StatusResponse> findAll() {
        return statusRepository.findAll()
                .stream()
                .map(statusMapper::toStatusResponse)
                .collect(Collectors.toList());
    }

    public StatusResponse findById(Long id) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status not found with id: " + id));
        return statusMapper.toStatusResponse(status);
    }

    @Transactional
    public StatusResponse create(StatusRequest statusRequest) {
        Status status = statusMapper.toStatus(statusRequest);
        Status savedStatus = statusRepository.save(status);
        return statusMapper.toStatusResponse(savedStatus);
    }

    @Transactional
    public StatusResponse update(Long id, StatusRequest statusRequest) {
        Status existingStatus = statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status not found with id: " + id));

        existingStatus.setStatusName(statusRequest.getStatusName());
        Status updatedStatus = statusRepository.save(existingStatus);

        return statusMapper.toStatusResponse(updatedStatus);
    }

    @Transactional
    public void delete(Long id) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status not found with id: " + id));
        statusRepository.delete(status);
    }
}