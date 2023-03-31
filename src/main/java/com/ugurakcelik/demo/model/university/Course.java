package com.ugurakcelik.demo.model.university;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private List<Exam> grades= new ArrayList<>();

    public Course(String title, String teacher){
        this.title = title;
        this.teacher = teacher;
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

    public List<Exam> grades() {
        return grades;
    }
    public void exam(Long studentID, Integer grade) {
        if (!attendees.contains(studentID)) {
            throw new RuntimeException("Student is not registered for this course");
        }
        if (grades.contains(studentID)){
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

}
