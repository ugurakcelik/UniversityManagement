package com.ugurakcelik.demo.model.university;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "university_id")
    private Long universityId;

    public Student(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public Student(String first, String last, Long universityId) {
        this.first = first;
        this.last = last;
        this.universityId = universityId;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", universityId=" + universityId +
                '}';
    }
}

