package com.ugurakcelik.demo.model.university;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
    @SequenceGenerator(name = "student_id_seq", sequenceName = "student_id_seq", allocationSize = 1)
    private Long id = 1L;
    private String first;
    private String last;

    private long universityId;

    public Student(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public Student(String first, String last, Long id){
        this.first = first;
        this.last = last;
        this.id = id;
    }

    public Student(String first, String last, long universityId){
        this.first = first;
        this.last = last;
        this.universityId = universityId;
    }

}

