package com.ugurakcelik.demo.model;

import com.ugurakcelik.demo.model.university.Attendee;
import com.ugurakcelik.demo.model.university.Course;
import com.ugurakcelik.demo.model.university.Student;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Entity
@Table(name ="university")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class University implements Serializable {

    @Id
    @SequenceGenerator(name = "university_id_seq", sequenceName = "university_id_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "university_id_seq")
    private long id;
    @Transient
    private final int MAX_STUDENTS = 1000;
    @Transient
    private final int MAX_COURSES = 1000;
    @Transient
    private final int MAX_ATTENDEES = 100;
    @Transient
    private final int MAX_COURSES_PER_STUDENT = 25;
    private String name;
    private String rector;
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", referencedColumnName = "id")
    private List<Student> student;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", referencedColumnName = "id")
    private List<Course> course;


    public University(String name){

        logger.info("Creating university " + name);
        this.name = name;
        this.student = new ArrayList<>();
        this.course = new ArrayList<>();
    }

    public Long enroll(String first, String last){
        if(student.size() >= MAX_STUDENTS ) {
            throw new RuntimeException("Maximum number of students reached.");
        }
        Student tmp = new Student(first, last, id);
        logger.info("New student enrolled: " + tmp.getId() + ", " + tmp.getFirst() + " " + tmp.getLast());
        student.add(tmp);
        return tmp.getId();
    }

    public String student(long id){

        Student tmp = findStudentById(id);
        if(tmp == null) {
            throw new RuntimeException("Student not found.");
        }
        return tmp.toString();
    }

    public Long activate(String title, String teacher){

        if(course.size() >= MAX_COURSES) {
            throw new RuntimeException("Maximum number of courses reached.");
        }
        Course c = new Course(title, teacher, id);
        logger.info("New course activated: " + c.getId() + ", " + c.getTitle() + " " + c.getTeacher());
        course.add(c);
        return c.getId();
    }

    public String course(long code){

        Course tmp = findCourseById(code);
        if(tmp == null) {
            throw new RuntimeException("Course not found.");
        }
        return tmp.toString();
    }

    public void register(Long studentID, Long courseCode){

        Course cTmp = findCourseById(courseCode);
        Student sTmp = findStudentById(studentID);

        if(cTmp == null || sTmp == null ) {
            throw new RuntimeException("No course or student matched.");
        }
        if(cTmp.attendees().size() >= MAX_ATTENDEES) {
            throw new RuntimeException("Maximum number of attendees reached for " + cTmp.getTitle());
        }
        if (course.stream().filter(courses -> courses.attendees().contains(studentID)).count() >= MAX_COURSES_PER_STUDENT) {
            throw new RuntimeException("Student cannot attend no more than " +MAX_COURSES_PER_STUDENT + " distinct courses.");
        }

        logger.info("Student " + sTmp.getId() + " signed up for course " + cTmp.getId());
        cTmp.register(sTmp.getId());
    }

    public String listAttendees(long courseCode){

        StringBuilder str = new StringBuilder();
        Course c = findCourseById(courseCode);
        if(c == null) {
            throw new RuntimeException("Course not found.");
        }

        for (Attendee attende : c.attendees()) {
            str.append(findStudentById(attende.getStudentId()).toString()).append("\n");
        }
        return (str.length() == 0) ? "No attendees" : str.toString().trim();
    }

    public String studyPlan(long studentID){

        StringBuilder str = new StringBuilder();
        for (Course value : course) {

            if (value.attendees().contains(studentID)) {
                str.append(value).append("\n");
            }
        }
        return (str.length() == 0) ? "No courses" : str.toString().trim();
    }

    public void exam(long studentId, long courseID, float grade) {

        Course cTmp = findCourseById(courseID);
        Student sTmp = findStudentById(studentId);

        if(cTmp == null || sTmp == null){
            throw new RuntimeException("No course or student matched.");
        }

        logger.info("Student " + studentId + " took an exam in course " + courseID + " with grade " + grade);
        cTmp.exam(sTmp.getId(), grade);
    }
    public String studentAvg(long studentId) {
        double avg = studentAvgDouble(studentId);
        if (avg == 0) {
            return "Student " + studentId + " hasn't taken any exams";
        } else {
            return String.format("Student %d : %.1f", studentId, avg);
        }
    }

    public double studentAvgDouble(long studentId) {
        double sum = 0;
        double count = 0;
        for(Course courses : course) {
            if(courses.attendees().stream().filter(attendee -> attendee.getStudentId() == studentId).findFirst().isPresent()){
                Float grade = courses.getGradeByStudentId(studentId);
                if(grade > 0f) {
                    sum +=grade;
                    count++;
                }
            }
        }
        if (count == 0) {
            return 0;
        } else {
            return sum / count;
        }
    }
    public String courseAvg(long courseId) {

        Course c = findCourseById(courseId);

        return (c.getTitle() + " average : " + c.courseAvg());
    }
    public String topThreeStudents() {

        Collections.sort(student, (s1, s2) -> {
            double s1Score = studentAvgDouble(s1.getId());
            double s2Score = studentAvgDouble(s2.getId());
            return Double.compare(s2Score, s1Score);
        });

        int sample = (int) student.stream().filter(s -> studentAvgDouble(s.getId()) > 0).limit(3).count();

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

    public Student findStudentById(long id) {
        return student.stream().filter(students -> students.getId().equals(id)).findFirst().orElse(null);
    }

    public Course findCourseById(long id) {
        return course.stream().filter(courses -> courses.getId().equals(id)).findFirst().orElse(null);
    }
    private final static Logger logger = Logger.getLogger("University");

}

