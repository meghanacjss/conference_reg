package com.example.conference_reg.service.serviceinterface;

import com.example.conference_reg.entity.Attendee;
import com.example.conference_reg.exception.UserNotFoundException;
import com.example.conference_reg.model.AttendeeModel;

import java.util.List;
import java.util.Optional;

public interface AttendeeInter {
    AttendeeModel createAttendee(Attendee attendee);

    List<AttendeeModel> getAllAttendees();

    Optional<AttendeeModel> getAttendeeById(int aid) throws UserNotFoundException;
}
