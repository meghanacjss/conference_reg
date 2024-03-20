package com.example.conference_reg.repository;

import com.example.conference_reg.entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee,Integer> {
}

