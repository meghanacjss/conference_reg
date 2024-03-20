package com.example.conference_reg.service.serviceinterface;

import com.example.conference_reg.entity.Attendee;
import com.example.conference_reg.entity.Event;
import com.example.conference_reg.model.RegistrationModel;

import java.util.List;

public interface RegistrationInter {

    RegistrationModel createRegistration(RegistrationModel registrationModel, Event event, Attendee attendee);

    List<RegistrationModel> getAllRegistrations();

    List<RegistrationModel> getRegistrationsByEvent(Event event);

    List<RegistrationModel> getRegistrationsByAttendee(Attendee attendee);

    RegistrationModel getRegistrationById(int rid);

    RegistrationModel cancelRegistration(int rid);
}
