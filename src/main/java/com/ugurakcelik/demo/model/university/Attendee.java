package com.ugurakcelik.demo.model.university;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Data
@ToString
public class Attendee implements Serializable {

    @Id
    @SequenceGenerator(name = "attendee_id_seq", sequenceName = "attendee_id_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "attendee_id_seq")
    private Long id = 1L;

    private long courseId;
    private Long studentId;
    private String title;
    private long universityId;

    public Attendee(long studentId, long courseId, String title, long universityId){
        this.studentId = studentId;
        this.courseId = courseId;
        this.title = title;
        this.universityId = universityId;
    }

}
