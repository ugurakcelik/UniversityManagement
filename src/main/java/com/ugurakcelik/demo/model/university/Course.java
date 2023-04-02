package com.ugurakcelik.demo.model.university;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Course {
    private String title;
    private String teacher;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private ArrayList<Long> attendees = new ArrayList<>();
    @Transient
    private ArrayList<Exam> grades= new ArrayList<>();

    private long universityId;

    public Course(String title, String teacher){
        this.title = title;
        this.teacher = teacher;
    }

    public Course(String title, String teacher, long universityId){
        this.title = title;
        this.teacher = teacher;
        this.universityId = universityId;
    }

    public void register(Long studentID) {
        if (attendees.size() >= 100) {
            throw new RuntimeException("Course is already full");
        }
        if (attendees.contains(studentID)) {
            throw new RuntimeException("Student is already registered for this course");
        }
        attendees.add(studentID);
    }

    public ArrayList<Long> attendees() {
        return this.attendees;
    }

    public ArrayList<Exam> grades() {
        return grades;
    }
    public void exam(long studentID, float grade) {

        if (!attendees.contains(studentID)) {
            throw new RuntimeException("Student is not registered for this course");
        }
        if (!getGradeByStudentId(studentID).equals(0)) {
            throw new RuntimeException("Student has already taken the exam for this course");
        }
        if (grade < 0 || grade > 30) {
            throw new RuntimeException("Grade must be between 0 and 30");
        }
        grades.add(new Exam(studentID, grade));
    }

    public String courseAvg() {

        double sum = 0;
        double count = 0;

        if (grades.isEmpty()) {
            return "No student has taken the exam in " + title;
        }
        for (Exam entry : grades) {
            sum += entry.getGrade();
            count++;
        }
        double avg = sum / count;
        return title + " : " + avg;
    }

    public Float getGradeByStudentId(long id) {

        Exam s = grades.stream().filter(exam -> exam.getStudentId().equals(id)).findFirst().orElse(null);
        return s == null ? 0f : s.getGrade();
    }

}
