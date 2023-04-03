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
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AttendeeRepository attendeeRepository;

    public String createUni(String name){
        University uni= new University(name);
        universityRepository.saveAndFlush(uni);
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
        universityRepository.saveAndFlush(university);
    }
    public String getRector(){
        Long id = 1L;
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found."));
       return university.getRector();
    }
    public long enroll(String first, String last, long universityId){
        University uTmp = universityRepository.findById(universityId)
                .orElseThrow(() -> new RuntimeException("University not found."));

        long id = uTmp.enroll(first, last);
        universityRepository.saveAndFlush(uTmp);
        return id;
    }

    public String student(long id){
        Student sTmp = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found."));
        return sTmp.toString();
    }

    public long activate(String title, String teacher, long uniId){
          University uTmp = universityRepository.findById(uniId)
                  .orElseThrow(() -> new RuntimeException("University not found."));

          long id = uTmp.activate(title, teacher);
        universityRepository.saveAndFlush(uTmp);
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
        University uTmp = universityRepository.findById(cTmp.getUniversityId())
                .orElseThrow(() -> new RuntimeException("University not found."));

        uTmp.register(sTmp.getId(), cTmp.getId());
        universityRepository.saveAndFlush(uTmp);
    }

    public String listAttendees(long courseCode){
        Course cTmp = courseRepository.findById(courseCode)
                .orElseThrow(() -> new RuntimeException("Course not found."));
        University uTmp = universityRepository.findById(cTmp.getUniversityId())
                .orElseThrow(() -> new RuntimeException("University not found."));

        return uTmp.listAttendees(cTmp.getId());
    }

    public String studyPlan(long studentID){
        Student sTmp = studentRepository.findById(studentID)
                .orElseThrow(() -> new RuntimeException("Student not found."));
        University uTmp = universityRepository.findById(sTmp.getUniversityId())
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
        universityRepository.saveAndFlush(uTmp);
    }

    public String studentAvg(long studentId){

        Student sTmp = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found."));
        University uTmp = universityRepository.findById(sTmp.getUniversityId())
                .orElseThrow(() -> new RuntimeException("University not found."));

        return uTmp.studentAvg(sTmp.getId());
    }

    public String courseAvg(long courseID){

        Course cTmp = courseRepository.findById(courseID)
                .orElseThrow(() -> new RuntimeException("Course not found."));
        University uTmp = universityRepository.findById(cTmp.getUniversityId())
                .orElseThrow(() -> new RuntimeException("University not found."));

        return uTmp.courseAvg(cTmp.getId());
    }

    public String topThreeStudents(long universityId){
        University uTmp = universityRepository.findById(universityId)
                .orElseThrow(() -> new RuntimeException("University not found."));
        return uTmp.topThreeStudents();
    }
}
