package com.ugurakcelik.demo.model.university;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Attendee implements Serializable {

    @Id
    @SequenceGenerator(name = "attendee_id_seq", sequenceName = "attendee_id_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "attendee_id_seq")
    private Long id = 1L;
    @Column(name = "course_id")
    private Long courseId;
    private Long studentId;
    private String title;
    @Column(name = "university_id")
    private Long universityId;

    public Attendee(Long studentId, Long courseId, String title, Long universityId){
        this.studentId = studentId;
        this.courseId = courseId;
        this.title = title;
        this.universityId = universityId;
    }

}
