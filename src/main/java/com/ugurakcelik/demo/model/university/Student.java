package com.ugurakcelik.demo.model.university;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first;
    private String last;
    private long universityId;

    public Student(String first, String last){
        this.first = first;
        this.last = last;
    }

    public Student(String first, String last, long universityId){
        this.first = first;
        this.last = last;
        this.universityId = universityId;
    }

}

