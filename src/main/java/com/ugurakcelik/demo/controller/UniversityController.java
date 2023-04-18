package com.ugurakcelik.demo.controller;

import com.ugurakcelik.demo.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class UniversityController {
    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @PostMapping("/university")
    public String createUniversity(@RequestParam String name) {
        return universityService.createUni(name);
    }

    @GetMapping("/university/{id}")
    public String getUniversity(@PathVariable long id) {
        return universityService.getUni(id);
    }

    @PutMapping("/university/{universityId}/rector")
    public void setRector(@RequestParam String rector, @PathVariable Long universityId) {
        universityService.setRector(rector, universityId);
    }

    @GetMapping("/university/{universityId}/rector")
    public String getRector(@PathVariable Long universityId) {
        return universityService.getRector(universityId);
    }

    @PostMapping("/university/{universityId}/enroll")
    public Long enrollStudent(@RequestParam String first, @RequestParam String last, @PathVariable Long universityId) {
        return universityService.enroll(first, last, universityId);
    }
    @GetMapping("/student/{id}")
    public String getStudent(@PathVariable long id) {
        return universityService.student(id);
    }

    @PostMapping("/university/{universityId}/course")
    public Long activateCourse(@RequestParam String title, @RequestParam String teacher, @PathVariable long universityId) {
        return universityService.activate(title, teacher, universityId);
    }

    @GetMapping("/course/{code}")
    public String getCourse(@PathVariable long code) {
        return universityService.course(code);
    }

    @PostMapping("/student/{studentId}/register/{courseCode}")
    public void registerStudentToCourse(@PathVariable Long studentId, @PathVariable Long courseCode) {
        universityService.register(studentId, courseCode);
    }

    @GetMapping("/course/{courseCode}/attendees")
    public String getCourseAttendees(@PathVariable long courseCode) {
        return universityService.listAttendees(courseCode);
    }

    @GetMapping("/student/{studentId}/studyPlan")
    public String getStudentStudyPlan(@PathVariable long studentId) {
        return universityService.studyPlan(studentId);
    }

    @PostMapping("/university/{id}/student/{studentId}/course/{courseID}/{grade}")
    public void addExamGrade(@PathVariable long universityId, @PathVariable long studentId, @PathVariable long courseID, @PathVariable float grade) {
        universityService.exam(studentId, courseID, grade, universityId);
    }

    @GetMapping("/student/{studentId}/average")
    public String getStudentAverage(@PathVariable long studentId) {
        return universityService.studentAvg(studentId);
    }

    @GetMapping("/course/{courseID}/average")
    public String getCourseAverage(@PathVariable long courseID) {
        return universityService.courseAvg(courseID);
    }

    @GetMapping("/university/{universityId}/topThreeStudents")
    public String getTopThreeStudents(@PathVariable long universityId) {
        return universityService.topThreeStudents(universityId);
    }
}
