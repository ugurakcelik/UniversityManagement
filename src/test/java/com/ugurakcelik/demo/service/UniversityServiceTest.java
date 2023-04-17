package com.ugurakcelik.demo.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UniversityServiceTest {
    private UniversityService universityService;

    @Autowired
    public UniversityServiceTest(UniversityService universityService){
        this.universityService = universityService;
    }

    @Test
    @Order(1)
    void createUni() {
        String u1 = universityService.createUni("Politecnico di Torino");
        String u2 = universityService.createUni("Unito");
    }

    @Test
    void getUni() {
        System.out.println(universityService.getUni(1L));
        System.out.println(universityService.getUni(2L));
    }

    @Test
    void setRector() {
        universityService.setRector("Rector Hector", 1L);
        universityService.setRector("Rector Hector", 2L);
    }

    @Test
    void getRector() {
        System.out.println(universityService.getRector(1L));
        System.out.println(universityService.getRector(2L));
    }

    @Test
    @Order(2)
    void activate() {
        universityService.activate("Macro Economics", "Paul Krugman", 1L);
        universityService.activate("Micro Economics", "Paul Krugman", 1L);

        universityService.activate("Macro Economics", "Paul Krugman", 2L);
        universityService.activate("Micro Economics", "Paul Krugman", 2L);
    }

    @Test
    @Order(3)
    void enroll() {
        universityService.enroll("Ugur", "Akcelik", 1L);
        universityService.enroll("John", "Doe", 1L);

        universityService.enroll("Ugur", "Akcelik", 2L);
        universityService.enroll("John", "Doe", 2L);
    }

    @Test
    @Order(4)
    void register() {
        universityService.register(1L, 1L);
        universityService.register(1L, 2L);
        universityService.register(2L, 1L);
        universityService.register(2L, 2L);
    }

    @Test
    @Order(5)
    void exam() {
        universityService.exam(1L, 1L, 28L, 1L);
        universityService.exam(1L, 2L, 28L, 1L);
        universityService.exam(2L, 1L, 28L, 1L);
        universityService.exam(2L, 2L, 28L, 1L);
    }

    @Test
    @Order(6)
    void studentAvg() {
        System.out.println(universityService.studentAvg(1L));
        System.out.println(universityService.studentAvg(2L));
    }

    @Test
    void student() {
        System.out.println(universityService.student(1L));
        System.out.println(universityService.student(2L));
    }

    @Test
    void course(){
        System.out.println(universityService.course(1L));
        System.out.println(universityService.course(2L));
    }

    @Test
    void listAttendees(){
        System.out.println(universityService.listAttendees(1L));
        System.out.println(universityService.listAttendees(2L));
    }

    @Test
    void studyPlan(){
        System.out.println(universityService.studyPlan(1L));
        System.out.println(universityService.studyPlan(2L));
    }

    @Test
    void courseAvg(){
        System.out.println(universityService.courseAvg(1L));
        System.out.println(universityService.courseAvg(2L));
    }

    @Test
    void topThreeStudents(){
        System.out.println(universityService.topThreeStudents(1L));
        System.out.println(universityService.topThreeStudents(2L));
    }

}