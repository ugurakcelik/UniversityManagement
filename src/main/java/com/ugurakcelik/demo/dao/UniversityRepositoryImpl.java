//package com.ugurakcelik.demo.dao;
//
//import com.ugurakcelik.demo.model.University;
//import org.springframework.stereotype.Repository;
//
//public class UniversityRepositoryImpl implements UniversityRepository {
//
//    University uni;
//
//    @Override
//    public void createUniversity(String name) {
//        uni = new University(name);
//    }
//
//    @Override
//    public int activateCourse(String title, String teacher) {
//        return 0;
//    }
//
//    @Override
//    public String getCourseInfo(int code) {
//        return null;
//    }
//
//    @Override
//    public String getCourseAvg(int courseId) {
//        return null;
//    }
//
//    @Override
//    public int enrolStudent(String first, String last) {
//        return 0;
//    }
//
//    @Override
//    public void registerExam(int studentId, int courseID, int grade) {
//
//    }
//
//    @Override
//    public String getUniName() {
//        return uni.getName();
//    }
//
//    @Override
//    public String getRector() {
//        return null;
//    }
//
//    @Override
//    public String listAttendees(int courseCode) {
//        return null;
//    }
//
//    @Override
//    public void registerStudent(int studentID, int courseCode) {
//
//    }
//
//    @Override
//    public void setRector(String firstName, String lastName) {
//
//    }
//
//    @Override
//    public String getStudentInfo(int id) {
//        return null;
//    }
//
//    @Override
//    public String getStudentAvg(int studentId) {
//        return null;
//    }
//
//    @Override
//    public String getStudyPlan(int studentID) {
//        return null;
//    }
//
//    @Override
//    public String listTopThreeStudents() {
//        return null;
//    }
//}
