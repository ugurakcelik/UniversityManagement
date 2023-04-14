package com.ugurakcelik.demo.dao;

import com.ugurakcelik.demo.model.university.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findAllByUniversityId(Long universityId);
}
