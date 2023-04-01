package com.ugurakcelik.demo.model.university;

import com.ugurakcelik.demo.model.University;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

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

