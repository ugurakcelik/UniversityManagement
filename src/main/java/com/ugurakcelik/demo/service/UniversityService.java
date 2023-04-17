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
        University university = new University(name);
        universityRepository.save(university);
        return university.toString();
    }

    public String getUni(long id){
        University university = fetchUniversityById(id);
       return university.toString();
    }

    public void setRector(String rector, Long universityId){

        University university = fetchUniversityById(universityId);
        university.setRector(rector);
        universityRepository.save(university);
    }
    public String getRector(Long universityId){
        University university = fetchUniversityById(universityId);
       return university.getRector();
    }
    @Transactional
    public Long enroll(String first, String last, Long universityId){

        University uTmp = fetchUniversityById(universityId);
        Long id = uTmp.enroll(first, last);
        universityRepository.save(uTmp);
        return id;
    }

    public String student(long id){
        Student sTmp = fetchStudentById(id);
        return sTmp.toString();
    }

    public Long activate(String title, String teacher, long uniId){
          University uTmp = fetchUniversityById(uniId);
          Long id = uTmp.activate(title, teacher);
          universityRepository.save(uTmp);
          return id;
    }

    public String course(long code){
        Course cTmp = fetchCourseById(code);
        return cTmp.toString();
    }

    public void register(Long studentID, Long courseCode){

        Course cTmp = fetchCourseById(courseCode);
        Student sTmp = fetchStudentById(studentID);
        University uTmp = fetchUniversityById(sTmp.getUniversityId());

        uTmp.register(sTmp.getId(), cTmp.getId());
        universityRepository.save(uTmp);
    }

    public String listAttendees(long courseCode){

        Course cTmp = fetchCourseById(courseCode);
        University uTmp = fetchUniversityById(cTmp.getUniversityId());
        return uTmp.listAttendees(cTmp.getId());
    }

    public String studyPlan(long studentID){
        Student sTmp = fetchStudentById(studentID);
        University uTmp = fetchUniversityById(sTmp.getUniversityId());
        return uTmp.studyPlan(sTmp.getId());
    }

    public void exam(long studentId, long courseID, float grade, long universityId) {

        University uTmp = fetchUniversityById(universityId);
        Course cTmp = fetchCourseById(courseID);
        Student sTmp = fetchStudentById(studentId);
        Attendee aTmp = fetchAttendeeById(studentId);

        uTmp.exam(sTmp.getId(), cTmp.getId(), grade);
        universityRepository.save(uTmp);
    }

    public String studentAvg(long studentId){

        Student sTmp = fetchStudentById(studentId);
        University uTmp = fetchUniversityById(sTmp.getUniversityId());

        return uTmp.studentAvg(sTmp.getId());
    }

    public String courseAvg(long courseID){

        Course cTmp = fetchCourseById(courseID);
        University uTmp = fetchUniversityById(cTmp.getUniversityId());

        return uTmp.courseAvg(cTmp.getId());
    }

    public String topThreeStudents(long universityId){
        University uTmp = fetchUniversityById(universityId);
        return uTmp.topThreeStudents();
    }

    public University fetchUniversityById(Long id) {
        return universityRepository.findById(id).orElseThrow(() -> new RuntimeException("University not found."));
    }
    public Student fetchStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found."));
    }
    public Course fetchCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found."));
    }
    public Attendee fetchAttendeeById(Long id) {
        return attendeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Attendee not found."));
    }
}
