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
        University x= new University(name);
        universityRepository.save(x);
        return x.toString();
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

    public long activate(String title, String teacher, long uniId){
          University uTmp = universityRepository.findById(uniId)
                  .orElseThrow(() -> new RuntimeException("University not found."));
           Course c = new Course(title, teacher, uniId);
              courseRepository.saveAndFlush(c);
            return c.getId();

    }

    public long enroll(String first, String last, long universityId){
        University uTmp = universityRepository.findById(universityId)
                .orElseThrow(() -> new RuntimeException("University not found."));

        Student sTmp = new Student(first, last, universityId);
        studentRepository.saveAndFlush(sTmp);
        return sTmp.getId();
    }

    public long register(long studentID, long courseCode){

        Course cTmp = courseRepository.findById(courseCode)
                .orElseThrow(() -> new RuntimeException("Course not found."));
        Student sTmp = studentRepository.findById(studentID)
                .orElseThrow(() -> new RuntimeException("Student not found."));
        University uTmp = universityRepository.findById(cTmp.getUniversityId())
                .orElseThrow(() -> new RuntimeException("University not found."));

        Attendee attendee = new Attendee(sTmp.getId(), cTmp.getId(), cTmp.getTitle(), uTmp.getId());
        cTmp.register(sTmp.getId());
        courseRepository.save(cTmp);
        return attendee.getId();
    }

    public void exam(long studentId, long courseID, float grade) {

        Course cTmp = courseRepository.findById(courseID)
                .orElseThrow(() -> new RuntimeException("Course not found."));
        Student sTmp = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found."));
        Attendee aTmp = attendeeRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Attendee not found."));


        cTmp.exam(studentId, grade);
        courseRepository.save(cTmp);
    }

    public double studentAvg(long studentId){

        Student sTmp = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found."));
        University uTmp = universityRepository.findById(sTmp.getUniversityId())
                .orElseThrow(() -> new RuntimeException("University not found."));

        return uTmp.studentAvgDouble(sTmp.getId());
    }
}
