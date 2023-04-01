package com.ugurakcelik.demo.dao;

import com.ugurakcelik.demo.model.university.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
