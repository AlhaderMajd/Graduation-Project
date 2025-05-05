package com.example.Graduation.Project.location;
import com.example.Graduation.Project.college.College;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationResponse {
    private Long locationId;
    private String locationName;
    private College college;
}