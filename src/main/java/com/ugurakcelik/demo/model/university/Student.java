package com.ugurakcelik.demo.model.university;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student implements Serializable {

    @Id
    @SequenceGenerator(name = "student_id_seq", sequenceName = "student_id_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
    private Long id = 1L;
    private String first;
    private String last;

    private long universityId;

    public Student(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public Student(String first, String last, long universityId){
        this.first = first;
        this.last = last;
        this.universityId = universityId;
    }

}

