package com.example.Graduation.Project.college;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollegeService {
    private final CollegeRepository collegeRepository;
    private final CollegeMapper collegeMapper;

    public CollegeService(CollegeRepository collegeRepository, CollegeMapper collegeMapper) {
        this.collegeRepository = collegeRepository;
        this.collegeMapper = collegeMapper;
    }

    public List<CollegeResponse> findAll() {
        return collegeRepository.findAll()
                .stream()
                .map(collegeMapper::toCollegeResponse)
                .collect(Collectors.toList());
    }

    public CollegeResponse findById(Long id) {
        College college = collegeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("College not found with id: " + id));
        return collegeMapper.toCollegeResponse(college);
    }

    @Transactional
    public CollegeResponse create(CollegeRequest collegeRequest) {
        College college = collegeMapper.toCollege(collegeRequest);
        College savedCollege = collegeRepository.save(college);
        return collegeMapper.toCollegeResponse(savedCollege);
    }

    @Transactional
    public CollegeResponse update(Long id, CollegeRequest collegeRequest) {
        College existingCollege = collegeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("College not found with id: " + id));

        existingCollege.setCollegeName(collegeRequest.getCollegeName());
        College updatedCollege = collegeRepository.save(existingCollege);

        return collegeMapper.toCollegeResponse(updatedCollege);
    }

    @Transactional
    public void delete(Long id) {
        College college = collegeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("College not found with id: " + id));
        collegeRepository.delete(college);
    }
}