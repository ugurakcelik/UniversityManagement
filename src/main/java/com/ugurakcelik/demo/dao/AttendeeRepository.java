package com.ugurakcelik.demo.dao;

import com.ugurakcelik.demo.model.university.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
}
