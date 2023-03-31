package com.ugurakcelik.demo.model.university;

import lombok.*;

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

    public Student(String first, String last){
        this.first = first;
        this.last = last;
    }

/*    @Override
    public String toString() {
        return id + " " + first + " " + last;
    }*/

}

