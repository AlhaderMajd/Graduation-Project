//package com.example.Graduation.Project.config;
//
//
//import com.example.Graduation.Project.entity.*;
//import com.example.Graduation.Project.repository.*;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//
//@Configuration
//public class DataInitializer {
//
//    @Bean
//    CommandLineRunner initDatabase(RoleRepository roleRepository,
//                                   CollegeRepository collegeRepository,
//                                   LocationRepository locationRepository,
//                                   ActivityTypeRepository activityTypeRepository,
//                                   StatusRepository statusRepository,
//                                   UserRepository userRepository) {
//        return args -> {
//            // Initialize roles
//            Role studentRole = new Role();
//            studentRole.setRoleName("Student");
//            Role staffRole = new Role();
//            staffRole.setRoleName("Staff Member");
//            Role professorRole = new Role();
//            professorRole.setRoleName("Supervising Professor");
//            Role deanRole = new Role();
//            deanRole.setRoleName("College Dean");
//            Role viceDeanRole = new Role();
//            viceDeanRole.setRoleName("College Vice Dean");
//            Role studentUnionRole = new Role();
//            studentUnionRole.setRoleName("Student Union");
//            Role divisionHeadRole = new Role();
//            divisionHeadRole.setRoleName("Student Union Division Head");
//            Role directorRole = new Role();
//            directorRole.setRoleName("Director of Student Entities Department");
//            Role viceDeanAffairsRole = new Role();
//            viceDeanAffairsRole.setRoleName("Vice Dean of Student Affairs");
//            Role deanAffairsRole = new Role();
//            deanAffairsRole.setRoleName("Dean of Student Affairs");
//
//            roleRepository.saveAll(Arrays.asList(
//                    studentRole, staffRole, professorRole, deanRole, viceDeanRole,
//                    studentUnionRole, divisionHeadRole, directorRole,
//                    viceDeanAffairsRole, deanAffairsRole
//            ));
//
//            // Initialize colleges
//            College engineering = new College();
//            engineering.setCollegeName("Engineering");
//            College arts = new College();
//            arts.setCollegeName("Arts");
//            College science = new College();
//            science.setCollegeName("Science");
//
//            collegeRepository.saveAll(Arrays.asList(engineering, arts, science));
//
//            // Initialize locations
//            Location engRoom1 = new Location();
//            engRoom1.setLocationName("Engineering Building - Room 101");
//            engRoom1.setCollege(engineering);
//            Location engRoom2 = new Location();
//            engRoom2.setLocationName("Engineering Building - Room 102");
//            engRoom2.setCollege(engineering);
//            Location artsHall = new Location();
//            artsHall.setLocationName("Arts Auditorium");
//            artsHall.setCollege(arts);
//            Location sciLab = new Location();
//            sciLab.setLocationName("Science Lab A");
//            sciLab.setCollege(science);
//            Location uniHall = new Location();
//            uniHall.setLocationName("University Main Hall");
//            uniHall.setCollege(null); // University-level location
//            locationRepository.saveAll(Arrays.asList(engRoom1, engRoom2, artsHall, sciLab, uniHall));
//
//            // Initialize activity types
//            ActivityType initiative = new ActivityType();
//            initiative.setTypeName("Initiative");
//            ActivityType lecture = new ActivityType();
//            lecture.setTypeName("Lecture");
//            ActivityType training = new ActivityType();
//            training.setTypeName("Training Course");
//            ActivityType workshop = new ActivityType();
//            workshop.setTypeName("Workshop");
//            ActivityType exhibition = new ActivityType();
//            exhibition.setTypeName("Exhibition");
//            ActivityType competition = new ActivityType();
//            competition.setTypeName("Competition");
//
//            activityTypeRepository.saveAll(Arrays.asList(
//                    initiative, lecture, training, workshop, exhibition, competition
//            ));
//
//            // Initialize statuses
//            Status pending = new Status();
//            pending.setStatusName("Pending");
//            Status approved = new Status();
//            approved.setStatusName("Approved");
//            Status rejected = new Status();
//            rejected.setStatusName("Rejected");
//
//            statusRepository.saveAll(Arrays.asList(pending, approved, rejected));
//
//            // Initialize some users
//            User student1 = new User();
//            student1.setEmail("student1@ju.edu.jo");
//            student1.setPassword("$2a$10$examplehashedpassword");
//            student1.setFullName("Student One");
//            student1.setPhone("0791234567");
//            student1.setRole(studentRole);
//            student1.setCollege(engineering);
//
//            User professor1 = new User();
//            professor1.setEmail("prof1@ju.edu.jo");
//            professor1.setPassword("$2a$10$examplehashedpassword");
//            professor1.setFullName("Professor One");
//            professor1.setPhone("0797654321");
//            professor1.setRole(professorRole);
//            professor1.setCollege(engineering);
//
//            User dean1 = new User();
//            dean1.setEmail("dean1@ju.edu.jo");
//            dean1.setPassword("$2a$10$examplehashedpassword");
//            dean1.setFullName("Dean One");
//            dean1.setPhone("0791111111");
//            dean1.setRole(deanRole);
//            dean1.setCollege(engineering);
//
//            userRepository.saveAll(Arrays.asList(student1, professor1, dean1));
//        };
//    }
//}