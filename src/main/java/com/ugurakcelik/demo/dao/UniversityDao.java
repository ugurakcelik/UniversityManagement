package com.ugurakcelik.demo.dao;

public interface UniversityDao {

    void createUniversity(String name);
    int activateCourse(String title, String teacher);
    String getCourseInfo(int code);
    String getCourseAvg(int courseId);
    int enrolStudent(String first, String last);
    void registerExam(int studentId, int courseID, int grade);
    String getUniName();
    String getRector();
    String listAttendees(int courseCode);
    void registerStudent(int studentID, int courseCode);
    void setRector(String firstName, String lastName);
    String getStudentInfo(int id);
    String getStudentAvg(int studentId);
    String getStudyPlan(int studentID);
    String listTopThreeStudents();



}
