package com.ugurakcelik.demo.service;

import com.ugurakcelik.demo.dao.CourseRepository;
import com.ugurakcelik.demo.dao.StudentRepository;
import com.ugurakcelik.demo.dao.UniversityRepository;
import com.ugurakcelik.demo.model.University;
import com.ugurakcelik.demo.model.university.Course;
import com.ugurakcelik.demo.model.university.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityService {
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    public String createUni(String name){
        University x= new University(name);
        universityRepository.save(x);
        return x.toString();
    }

    public String getUni(long id){
        University university = universityRepository.findById(id).get();
       return university.toString();
    }

    public void setRector(String rector, long id){

        University university = universityRepository.findById(id).get();
        university.setRector(rector);
        universityRepository.save(university);
    }
    public String getRector(){
        Long id = 1L;
        University university = universityRepository.findById(id).get();
       return university.getRector();
    }

    public long activate(String title, String teacher){
        Course c = new Course(title, teacher);
        courseRepository.save(c);
        return c.getId();
    }

    public long enroll(String first, String last, Long universityId){
        Student tmp = new Student(first, last, universityId);
        studentRepository.save(tmp);
        return tmp.getId();
    }
}
