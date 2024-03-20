package com.example.conference_reg.repository;

import com.example.conference_reg.entity.Attendee;
import com.example.conference_reg.entity.Event;
import com.example.conference_reg.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration,Integer> {
    List<Registration> findByEvent(Event event);

    List<Registration> findByAttendee(Attendee attendee);
}
