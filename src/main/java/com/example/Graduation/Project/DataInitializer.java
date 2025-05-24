//package com.example.Graduation.Project;
//
//import com.example.Graduation.Project.activity.Activity;
//import com.example.Graduation.Project.activity.ActivityRepository;
//import com.example.Graduation.Project.activityType.ActivityType;
//import com.example.Graduation.Project.activityType.ActivityTypeRepository;
//import com.example.Graduation.Project.college.College;
//import com.example.Graduation.Project.college.CollegeRepository;
//import com.example.Graduation.Project.location.Location;
//import com.example.Graduation.Project.location.LocationRepository;
//import com.example.Graduation.Project.role.Role;
//import com.example.Graduation.Project.role.RoleRepository;
//import com.example.Graduation.Project.status.Status;
//import com.example.Graduation.Project.status.StatusRepository;
//import com.example.Graduation.Project.user.User;
//import com.example.Graduation.Project.user.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//
//@Configuration
//public class DataInitializer {
//
//    @Bean
//    @Transactional
//    CommandLineRunner initDatabase(RoleRepository roleRepository,
//                                   CollegeRepository collegeRepository,
//                                   LocationRepository locationRepository,
//                                   ActivityTypeRepository activityTypeRepository,
//                                   StatusRepository statusRepository,
//                                   UserRepository userRepository,
//                                   ActivityRepository activityRepository,
//                                   PasswordEncoder passwordEncoder) {
//        return args -> {
//            // Clear all existing data first (in reverse order of dependencies)
//            activityRepository.deleteAll();
//            userRepository.deleteAll();
//            statusRepository.deleteAll();
//            activityTypeRepository.deleteAll();
//            roleRepository.deleteAll();
//            locationRepository.deleteAll();
//            collegeRepository.deleteAll();
//
//            // === الكليات ===
//            College university = new College();
//            university.setCollegeName("الجامعة");
//
//            College it = new College();
//            it.setCollegeName("كلية تكنولوجيا المعلومات");
//
//            collegeRepository.saveAll(Arrays.asList(university, it));
//
//            // === المواقع ===
//            Location itAuditorium = new Location();
//            itAuditorium.setLocationName("مسرح اللوزي");
//            itAuditorium.setCollege(it);
//
//            Location conferenceRoom = new Location();
//            conferenceRoom.setLocationName("قاعة المؤتمرات - الجامعة");
//            conferenceRoom.setCollege(university);
//
//            locationRepository.saveAll(Arrays.asList(itAuditorium, conferenceRoom));
//
//            // === الأدوار ===
//            Role studentRole = new Role();
//            studentRole.setRoleName("طالب");
//
//            Role staffRole = new Role();
//            staffRole.setRoleName("موظف");
//
//            Role professorRole = new Role();
//            professorRole.setRoleName("أستاذ مشرف");
//
//            Role viceDeanRole = new Role();
//            viceDeanRole.setRoleName("نائب عميد الكلية");
//
//            Role deanRole = new Role();
//            deanRole.setRoleName("عميد الكلية");
//
//            Role studentUnionRole = new Role();
//            studentUnionRole.setRoleName("الاتحاد الطلابي");
//
//            Role divisionHeadRole = new Role();
//            divisionHeadRole.setRoleName("رئيس شعبة الاتحاد الطلابي");
//
//            Role directorRole = new Role();
//            directorRole.setRoleName("مدير دائرة الهيئات الطلابية");
//
//            Role viceDeanAffairsRole = new Role();
//            viceDeanAffairsRole.setRoleName("نائب عميد شؤون الطلبة");
//
//            Role deanAffairsRole = new Role();
//            deanAffairsRole.setRoleName("عميد شؤون الطلبة");
//
//            roleRepository.saveAll(Arrays.asList(
//                    studentRole, staffRole, professorRole, viceDeanRole, deanRole,
//                    studentUnionRole, divisionHeadRole, directorRole,
//                    viceDeanAffairsRole, deanAffairsRole
//            ));
//
//            // === أنواع النشاطات ===
//            ActivityType initiative = new ActivityType();
//            initiative.setTypeName("مبادرة");
//
//            ActivityType lecture = new ActivityType();
//            lecture.setTypeName("محاضرة");
//
//            ActivityType training = new ActivityType();
//            training.setTypeName("دورة تدريبية");
//
//            ActivityType workshop = new ActivityType();
//            workshop.setTypeName("ورشة عمل");
//
//            ActivityType exhibition = new ActivityType();
//            exhibition.setTypeName("معرض");
//
//            ActivityType competition = new ActivityType();
//            competition.setTypeName("مسابقة");
//
//            activityTypeRepository.saveAll(Arrays.asList(
//                    initiative, lecture, training, workshop, exhibition, competition
//            ));
//
//            // === حالات الطلب ===
//            Status pending = new Status();
//            pending.setStatusName("قيد الانتظار");
//
//            Status approved = new Status();
//            approved.setStatusName("موافق عليه");
//
//            Status rejected = new Status();
//            rejected.setStatusName("مرفوض");
//
//            statusRepository.saveAll(Arrays.asList(pending, approved, rejected));
//
//            // === المستخدمين ===
//            // طالب
//            User student1 = new User();
//            student1.setEmail("student1@ju.edu.jo");
//            student1.setPassword(passwordEncoder.encode("student123"));
//            student1.setFullName("الطالب الأول");
//            student1.setPhone("0791234567");
//            student1.setRole(studentRole);
//            student1.setCollege(it);
//
//            // موظف
//            User staff1 = new User();
//            staff1.setEmail("staff1@ju.edu.jo");
//            staff1.setPassword(passwordEncoder.encode("staff123"));
//            staff1.setFullName("الموظف الأول");
//            staff1.setPhone("0790000001");
//            staff1.setRole(staffRole);
//            staff1.setCollege(university);
//
//            // أستاذ مشرف
//            User prof1 = new User();
//            prof1.setEmail("prof1@ju.edu.jo");
//            prof1.setPassword(passwordEncoder.encode("prof123"));
//            prof1.setFullName("الأستاذ المشرف الأول");
//            prof1.setPhone("0791111111");
//            prof1.setRole(professorRole);
//            prof1.setCollege(it);
//
//            // نائب عميد كلية تكنولوجيا المعلومات
//            User itViceDean = new User();
//            itViceDean.setEmail("it.vicedean@ju.edu.jo");
//            itViceDean.setPassword(passwordEncoder.encode("vicedean123"));
//            itViceDean.setFullName("نائب عميد كلية تكنولوجيا المعلومات");
//            itViceDean.setPhone("0793333333");
//            itViceDean.setRole(viceDeanRole);
//            itViceDean.setCollege(it);
//
//            // عميد كلية تكنولوجيا المعلومات
//            User itDean = new User();
//            itDean.setEmail("it.dean@ju.edu.jo");
//            itDean.setPassword(passwordEncoder.encode("dean123"));
//            itDean.setFullName("عميد كلية تكنولوجيا المعلومات");
//            itDean.setPhone("0792222222");
//            itDean.setRole(deanRole);
//            itDean.setCollege(it);
//
//            // الاتحاد الطلابي
//            User studentUnionUser = new User();
//            studentUnionUser.setEmail("student.union@ju.edu.jo");
//            studentUnionUser.setPassword(passwordEncoder.encode("union123"));
//            studentUnionUser.setFullName("الاتحاد الطلابي");
//            studentUnionUser.setPhone("0798888888");
//            studentUnionUser.setRole(studentUnionRole);
//            studentUnionUser.setCollege(university);
//
//            // رئيس شعبة الاتحاد الطلابي
//            User studentUnionHead = new User();
//            studentUnionHead.setEmail("union.head@ju.edu.jo");
//            studentUnionHead.setPassword(passwordEncoder.encode("head123"));
//            studentUnionHead.setFullName("رئيس شعبة الاتحاد الطلابي");
//            studentUnionHead.setPhone("0794444444");
//            studentUnionHead.setRole(divisionHeadRole);
//            studentUnionHead.setCollege(university);
//
//            // مدير دائرة الهيئات الطلابية
//            User director = new User();
//            director.setEmail("director@ju.edu.jo");
//            director.setPassword(passwordEncoder.encode("director123"));
//            director.setFullName("مدير دائرة الهيئات الطلابية");
//            director.setPhone("0795555555");
//            director.setRole(directorRole);
//            director.setCollege(university);
//
//            // نائب عميد شؤون الطلبة
//            User viceDeanAffairs = new User();
//            viceDeanAffairs.setEmail("vice.dean.affairs@ju.edu.jo");
//            viceDeanAffairs.setPassword(passwordEncoder.encode("viceaffairs123"));
//            viceDeanAffairs.setFullName("نائب عميد شؤون الطلبة");
//            viceDeanAffairs.setPhone("0796666666");
//            viceDeanAffairs.setRole(viceDeanAffairsRole);
//            viceDeanAffairs.setCollege(university);
//
//            // عميد شؤون الطلبة
//            User deanAffairs = new User();
//            deanAffairs.setEmail("dean.affairs@ju.edu.jo");
//            deanAffairs.setPassword(passwordEncoder.encode("affairs123"));
//            deanAffairs.setFullName("عميد شؤون الطلبة");
//            deanAffairs.setPhone("0797777777");
//            deanAffairs.setRole(deanAffairsRole);
//            deanAffairs.setCollege(university);
//
//            userRepository.saveAll(Arrays.asList(
//                    student1, staff1, prof1, itViceDean, itDean,
//                    studentUnionUser, studentUnionHead, director,
//                    viceDeanAffairs, deanAffairs
//            ));
//
//            // === إنشاء طلب نشاط كمثال ===
//            Activity activity = new Activity();
//            activity.setRequester(student1);
//            activity.setType(initiative);
//            activity.setSupervisor(prof1);
//            activity.setLocation(itAuditorium);
//            activity.setDescription("مبادرة لتعليم البرمجة للمبتدئين");
//            activity.setObjectives("تعزيز مهارات البرمجة لدى الطلاب الجدد");
//            activity.setStartTime(LocalDateTime.now().plusDays(7));
//            activity.setEndTime(LocalDateTime.now().plusDays(7).plusHours(3));
//            activity.setCreatedAt(LocalDateTime.now());
//
//            // Set initial status and assignee (replacing Workflow functionality)
//            activity.setStatus(pending);
//            activity.setAssignee(prof1);
//            activity.setActionDate(LocalDateTime.now());
//
//            activityRepository.save(activity);
//
//            // Create a second activity example with a different assignee
//            Activity activity2 = new Activity();
//            activity2.setRequester(student1);
//            activity2.setType(workshop);
//            activity2.setSupervisor(prof1);
//            activity2.setLocation(conferenceRoom);
//            activity2.setDescription("ورشة عمل حول تطوير تطبيقات الويب");
//            activity2.setObjectives("تعزيز مهارات تطوير الويب");
//            activity2.setStartTime(LocalDateTime.now().plusDays(14));
//            activity2.setEndTime(LocalDateTime.now().plusDays(14).plusHours(4));
//            activity2.setCreatedAt(LocalDateTime.now().minusDays(1));
//            activity2.setStatus(pending);
//            activity2.setAssignee(itViceDean);
//            activity2.setActionDate(LocalDateTime.now().minusDays(1));
//
//            activityRepository.save(activity2);
//
//            System.out.println("✅ تم إدخال البيانات الأولية بنجاح.");
//        };
//    }
//}
