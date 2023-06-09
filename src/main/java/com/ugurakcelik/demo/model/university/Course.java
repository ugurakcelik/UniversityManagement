package com.ugurakcelik.demo.model.university;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Course implements Serializable {
    @Id
    @SequenceGenerator(name = "course_id_seq", sequenceName = "course_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id_seq")
    private Long id;
    private String title;
    private String teacher;
    @Column(name = "university_id")
    private Long universityId;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private List<Attendee> attendees = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private List<Exam> grades= new ArrayList<>();

    public Course(String title, String teacher, Long universityId) {
        this.title = title;
        this.teacher = teacher;
        this.universityId = universityId;
    }

    public void register(Long studentID) {
        if (attendees.size() >= 100) {
            throw new RuntimeException("Course is already full");
        }
        if (findAttendeeById(studentID) != null) {
            throw new RuntimeException("Student is already registered for this course");
        }
        Attendee attendee = new Attendee(studentID, id, title, universityId);
        attendees.add(attendee);
    }

    public List<Attendee> attendees() {
        return this.attendees;
    }
    public void exam(long studentID, float grade) {

        if (findAttendeeById(studentID) == null) {
            throw new RuntimeException("Student is not registered for this course");
        }

        if (getGradeByStudentId(studentID) > 0f) {
            throw new RuntimeException("Student has already taken the exam for this course");
        }
        if (grade < 0 || grade > 30) {
            throw new RuntimeException("Grade must be between 0 and 30");
        }
        grades.add(new Exam(universityId, studentID, id, grade));
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

    public Attendee findAttendeeById(long id) {
        Attendee s = attendees.stream().filter(attendee -> attendee.getStudentId().equals(id)).findFirst().orElse(null);
        return s;
    }
}
