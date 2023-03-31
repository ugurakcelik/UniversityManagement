package com.ugurakcelik.demo.model.university;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "course_id")
    private long courseId;

    private Long studentId;

    private float grade;

    public Exam (Long studentId, float grade){
        this.studentId = studentId;
        this.grade = grade;
    }

}
