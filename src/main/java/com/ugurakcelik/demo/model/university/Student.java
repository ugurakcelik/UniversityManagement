package com.ugurakcelik.demo.model.university;

import com.ugurakcelik.demo.model.University;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Student implements Serializable {

    @Id
    @SequenceGenerator(name = "student_id_seq", sequenceName = "student_id_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
    private Long id;
    private String first;
    private String last;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    public Student(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public Student(String first, String last, University u) {
        this.first = first;
        this.last = last;
        this.university = u;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", universityId=" + university.getId() +
                '}';
    }
}

