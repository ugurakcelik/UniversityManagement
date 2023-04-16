package com.ugurakcelik.demo.model.university;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Exam implements Serializable {

    @Id
    @SequenceGenerator(name = "exam_id_seq", sequenceName = "exam_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exam_id_seq")
    private Long id;
    @Column(name = "course_id")
    private Long courseId;
    private Long studentId;
    private Float grade;

    public Exam (long studentId, long courseId, float grade){
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

}
