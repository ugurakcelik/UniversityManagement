package com.ugurakcelik.demo.dao;

import com.ugurakcelik.demo.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {


}
