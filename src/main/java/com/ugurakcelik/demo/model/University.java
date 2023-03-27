package com.ugurakcelik.demo.model;

import com.ugurakcelik.demo.model.university.Course;
import com.ugurakcelik.demo.model.university.Rector;
import com.ugurakcelik.demo.model.university.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;

public class University {

    private final int MAX_STUDENTS = 1000;
    private final int MAX_COURSES = 1000;
    private final int MAX_ATTENDEES = 100;
    private final int MAX_COURSES_PER_STUDENT = 25;

    private static int courseId;
    private static int studentId;

    private String name;
    private Rector rector = new Rector();
    private ArrayList<Student> student = new ArrayList<>();
    private ArrayList<Course> course = new ArrayList<>();


    public University(String name){

        courseId = 10;
        studentId = 10000;

        logger.info("Creating university " + name);
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setRector(String first, String last){
        rector.setFirst(first);
        rector.setLast(last);
    }

    public String getRector(){
        return rector.toString();
    }

    public int enroll(String first, String last){
        if(student.size() >= MAX_STUDENTS ) {
            throw new RuntimeException("Maximum number of students reached.");
        }
        Student tmp = new Student(first, last, studentId++);
        logger.info("New student enrolled: " + tmp.getId() + ", " + tmp.getFirst() + " " + tmp.getLast());
        student.add(tmp);
        return tmp.getId();
    }

    public String student(int id){

        Student tmp = student.stream().filter(students -> students.getId().equals(id)).findFirst().orElse(null);
        if(tmp == null) {
            throw new RuntimeException("Student not found.");
        }
        return tmp.toString();
    }

    public int activate(String title, String teacher){

        if(course.size() >= MAX_COURSES) {
            throw new RuntimeException("Maximum number of courses reached.");
        }
        Course c = new Course(title, teacher, courseId++);
        logger.info("New course activated: " + c.getId() + ", " + c.getTitle() + " " + c.getTeacher());
        course.add(c);
        return c.getId();
    }

    public String course(int code){

        Course tmp = course.stream().filter(courses -> courses.getId().equals(code)).findFirst().orElse(null);
        if(tmp == null) {
            throw new RuntimeException("Course not found.");
        }
        return tmp.toString();
    }

    public void register(int studentID, int courseCode){

        Course cTmp = course.stream().filter(courses -> courses.getId().equals(courseCode)).findFirst().orElse(null);
        Student stTmp = student.stream().filter(students -> students.getId().equals(studentID)).findFirst().orElse(null);

        if(cTmp == null || stTmp == null ) {
            throw new RuntimeException("No course or student matched.");
        }
        if(cTmp.attendees().size() >= MAX_ATTENDEES) {
            throw new RuntimeException("Maximum number of attendees reached for " + cTmp.getTitle());
        }
        if (course.stream().filter(courses -> courses.attendees().contains(studentID)).count() >= MAX_COURSES_PER_STUDENT) {
            throw new RuntimeException("Student cannot attend no more than " +MAX_COURSES_PER_STUDENT + " distinct courses.");
        }

        logger.info("Student " + stTmp.getId() + " signed up for course " + cTmp.getId());
        cTmp.register(stTmp.getId());
    }

    public String listAttendees(int courseCode){

        StringBuilder str = new StringBuilder();
        Course c = course.stream().filter(courses -> courses.getId().equals(courseCode)).findFirst().orElse(null);
        for(int i=0; i < c.attendees().size(); i++) {
            int studentID = c.attendees().get(i);
            str.append(student.stream().filter(students -> students.getId().equals(studentID)).findFirst().orElse(null).toString()+ "\n");
        }

        return (str.length() == 0) ? "No attendees" : str.toString().trim();

    }

    public String studyPlan(int studentID){

        StringBuilder str = new StringBuilder();
        for(int i=0; i < course.size(); i++) {

            if(course.get(i).attendees().contains(studentID)) {
                str.append(course.get(i).toString() + "\n");
            }
        }
        return (str.length() == 0) ? "No courses" : str.toString().trim();
    }

    public void exam(int studentId, int courseID, int grade) {

        Course cTmp = course.stream().filter(courses -> courses.getId().equals(courseID)).findFirst().orElse(null);
        Student stTmp = student.stream().filter(students -> students.getId().equals(studentId)).findFirst().orElse(null);

        if(cTmp == null || stTmp == null){
            throw new RuntimeException("No course or student matched.");
        }

        logger.info("Student " + studentId + " took an exam in course " + courseID + " with grade " + grade);
        cTmp.exam(stTmp.getId(), grade);
    }
    public String studentAvg(int studentId) {
        double sum = 0;
        double count = 0;
        for(Course courses : course) {
            if(courses.attendees().contains(studentId) && courses.grades().containsKey(studentId)) {
                sum +=courses.grades().get(studentId);
                count++;
            }
        }
        if (count == 0) {
            return "Student " + studentId + " hasn't taken any exams";
        } else {
            double avg = (double) sum / count;
            return String.format("Student %d : %.1f", studentId, avg);
        }
    }

    public double studentAvgDouble(int studentId) {
        double sum = 0;
        double count = 0;
        for(Course courses : course) {
            if(courses.attendees().contains(studentId) && courses.grades().containsKey(studentId)) {
                sum +=courses.grades().get(studentId);
                count++;
            }
        }
        if (count == 0) {
            return 0;
        } else {
            double avg = sum / count;
            return avg;
        }
    }
    public String courseAvg(int courseId) {

        Course c = course.stream().filter(courses -> courses.getId().equals(courseId)).findFirst().orElse(null);

        return (c.getTitle() + " average : " + c.courseAvg());
    }
    public String topThreeStudents() {

        Collections.sort(student, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                double s1Score = studentAvgDouble(s1.getId());
                double s2Score = studentAvgDouble(s2.getId());
                return Double.compare(s2Score, s1Score);
            }
        });

        int sample = student.size() < 3 ? student.size() : 3 ;

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < sample; i++) {
            str.append(student.get(i).getFirst())
                    .append(" ")
                    .append(student.get(i).getLast())
                    .append(" : ")
                    .append(studentAvgDouble(student.get(i).getId()))
                    .append("\n");
        }
        return str.toString().trim();
    }
    private final static Logger logger = Logger.getLogger("University");

}

