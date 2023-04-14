package com.ugurakcelik.demo.service;

import com.ugurakcelik.demo.dao.AttendeeRepository;
import com.ugurakcelik.demo.dao.CourseRepository;
import com.ugurakcelik.demo.dao.StudentRepository;
import com.ugurakcelik.demo.dao.UniversityRepository;
import com.ugurakcelik.demo.model.University;
import com.ugurakcelik.demo.model.university.Attendee;
import com.ugurakcelik.demo.model.university.Course;
import com.ugurakcelik.demo.model.university.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UniversityService {

    private final UniversityRepository universityRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final AttendeeRepository attendeeRepository;

    @Autowired
    public UniversityService(UniversityRepository universityRepository,
                     CourseRepository courseRepository,
                     StudentRepository studentRepository,
                     AttendeeRepository attendeeRepository) {
        this.universityRepository = universityRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.attendeeRepository = attendeeRepository;
    }

    public String createUni(String name){
        University uni= new University(name);
        universityRepository.save(uni);
        return uni.toString();
    }

    public String getUni(long id){
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found."));
       return university.toString();
    }

    public void setRector(String rector, long id){

        University university = universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found."));
        university.setRector(rector);
        universityRepository.save(university);
    }
    public String getRector(){
        Long id = 1L;
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found."));
       return university.getRector();
    }
    @Transactional
    public Long enroll(String first, String last, Long universityId){
        University uTmp = universityRepository.findById(universityId)
                .orElseThrow(() -> new RuntimeException("University not found."));

        Long id = uTmp.enroll(first, last);
        studentRepository.save(uTmp.getStudent().get(uTmp.getStudent().size()-1));
        universityRepository.save(uTmp);
        return id;
    }

    public String student(long id){
        Student sTmp = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found."));
        return sTmp.toString();
    }

    public Long activate(String title, String teacher, long uniId){
          University uTmp = universityRepository.findById(uniId)
                  .orElseThrow(() -> new RuntimeException("University not found."));

          Long id = uTmp.activate(title, teacher);
        universityRepository.save(uTmp);
            return id;
    }

    public String course(long code){
        Course cTmp = courseRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Course not found."));
        return cTmp.toString();
    }

    public void register(long studentID, long courseCode){

        Course cTmp = courseRepository.findById(courseCode)
                .orElseThrow(() -> new RuntimeException("Course not found."));
        Student sTmp = studentRepository.findById(studentID)
                .orElseThrow(() -> new RuntimeException("Student not found."));
        University uTmp = universityRepository.findById(cTmp.getUniversity().getId())
                .orElseThrow(() -> new RuntimeException("University not found."));

        uTmp.register(sTmp.getId(), cTmp.getId());
        universityRepository.save(uTmp);
    }

    public String listAttendees(long courseCode){
        Course cTmp = courseRepository.findById(courseCode)
                .orElseThrow(() -> new RuntimeException("Course not found."));
        University uTmp = universityRepository.findById(cTmp.getUniversity().getId())
                .orElseThrow(() -> new RuntimeException("University not found."));

        return uTmp.listAttendees(cTmp.getId());
    }

    public String studyPlan(long studentID){
        Student sTmp = studentRepository.findById(studentID)
                .orElseThrow(() -> new RuntimeException("Student not found."));
        University uTmp = universityRepository.findById(sTmp.getUniversity().getId())
                .orElseThrow(() -> new RuntimeException("University not found."));
        return uTmp.studyPlan(sTmp.getId());
    }

    public void exam(long studentId, long courseID, float grade, long universityId) {

        University uTmp = universityRepository.findById(universityId)
                .orElseThrow(() -> new RuntimeException("University not found."));
        Course cTmp = courseRepository.findById(courseID)
                .orElseThrow(() -> new RuntimeException("Course not found."));
        Student sTmp = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found."));
        Attendee aTmp = attendeeRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Attendee not found."));

        uTmp.exam(sTmp.getId(), cTmp.getId(), grade);
        universityRepository.save(uTmp);
    }

    public String studentAvg(long studentId){

        Student sTmp = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found."));
        University uTmp = universityRepository.findById(sTmp.getUniversity().getId())
                .orElseThrow(() -> new RuntimeException("University not found."));

        return uTmp.studentAvg(sTmp.getId());
    }

    public String courseAvg(long courseID){

        Course cTmp = courseRepository.findById(courseID)
                .orElseThrow(() -> new RuntimeException("Course not found."));
        University uTmp = universityRepository.findById(cTmp.getUniversity().getId())
                .orElseThrow(() -> new RuntimeException("University not found."));

        return uTmp.courseAvg(cTmp.getId());
    }

    public String topThreeStudents(long universityId){
        University uTmp = universityRepository.findById(universityId)
                .orElseThrow(() -> new RuntimeException("University not found."));
        return uTmp.topThreeStudents();
    }
}
