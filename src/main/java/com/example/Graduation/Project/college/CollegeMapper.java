package com.example.Graduation.Project.college;

import org.springframework.stereotype.Component;

@Component
public class CollegeMapper {
    public College toCollege(CollegeRequest collegeRequest) {
        College college = new College();
        college.setCollegeName(collegeRequest.getCollegeName());
        return college;
    }

    public CollegeResponse toCollegeResponse(College college) {
        CollegeResponse response = new CollegeResponse();
        response.setCollegeId(college.getCollegeId());
        response.setCollegeName(college.getCollegeName());
        return response;
    }
}