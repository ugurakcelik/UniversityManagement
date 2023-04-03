package com.ugurakcelik.demo.model.university;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Attendees {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long courseId;

    private long studentId;

    private String title;


}
