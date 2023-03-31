package com.ugurakcelik.demo.dao;

import com.ugurakcelik.demo.model.university.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
