package com.ugurakcelik.demo.model.university;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Exam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exam_id_seq")
    @SequenceGenerator(name = "exam_id_seq", sequenceName = "exam_id_seq", allocationSize = 1)
    private Long id = 1L;

    private long courseId;
    private Long studentId;
    private float grade;

    public Exam (Long studentId, float grade){
        this.studentId = studentId;
        this.grade = grade;
    }

    public Exam (long studentId, long courseId, float grade){
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

}
