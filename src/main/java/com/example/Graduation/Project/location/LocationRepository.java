package com.example.Graduation.Project.location;

import com.example.Graduation.Project.college.College;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByCollege_CollegeId(Long collegeId);
    boolean existsByLocationNameAndCollege(String name, College college);
    Location findByLocationNameAndCollege(String locationName, College college);
}