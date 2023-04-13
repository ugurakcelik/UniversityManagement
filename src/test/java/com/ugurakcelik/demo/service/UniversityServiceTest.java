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
    }

    @Test
    void getUni() {
        System.out.println(universityService.getUni(1l));
    }

    @Test
    void setRector() {
        universityService.setRector("Rector Hector", 1L);
    }

    @Test
    void getRector() {
        String r = universityService.getRector();
        System.out.println(r);
    }

    @Test
    @Order(2)
    void activate() {
        long a = universityService.activate("Macro Economics", "Paul Krugman", 1L);
        System.out.println(a);
    }

    @Test
    @Order(3)
    void enroll() {
        long a = universityService.enroll("Ugur", "Akcelik", 1L);
    }

    @Test
    @Order(4)
    void register() {
        universityService.register(1L, 1L);
    }

    @Test
    @Order(5)
    void exam() {
        universityService.exam(1L, 1L, 28L, 1L);
    }

    @Test
    @Order(6)
    void studentAvg() {
        String test = universityService.studentAvg(1L);
        System.out.println(test);
    }

    @Test
    void student() {
        String test = universityService.student(1L);
        System.out.println(test);
    }

    @Test
    void course(){
        String test = universityService.course(1L);
        System.out.println(test);
    }

    @Test
    void listAttendees(){
        String test = universityService.listAttendees(1L);
        System.out.println(test);
    }

    @Test
    void studyPlan(){
        String test = universityService.studyPlan(1L);
        System.out.println(test);
    }

    @Test
    void courseAvg(){
        String test = universityService.courseAvg(1L);
        System.out.println(test);
    }

    @Test
    void topThreeStudents(){
        String test = universityService.topThreeStudents(1L);
        System.out.println(test);
    }

}