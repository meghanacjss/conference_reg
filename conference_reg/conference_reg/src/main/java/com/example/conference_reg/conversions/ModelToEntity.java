package com.example.conference_reg.conversions;

import com.example.conference_reg.entity.*;
import com.example.conference_reg.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class ModelToEntity {
@Autowired
    PasswordEncoder passwordEncoder;
    public Attendee attendeeModelToEntity(AttendeeModel attendeeModel) {

        Attendee attendee = new Attendee();
        attendee.setAid(attendeeModel.getAid());
        attendee.setAname(attendeeModel.getAname());
        attendee.setEmail(attendeeModel.getEmail());
        attendee.setAffiliation(attendeeModel.getAffiliation());
        return attendee;
    }
    public Event eventModelToEntity(EventModel eventModel) {
        Event event = new Event();
        event.setEid(eventModel.getEid());
        event.setEname(eventModel.getEname());
        event.setDate(eventModel.getDate());
        event.setVenue(eventModel.getVenue());
        return event;
    }
    public Owner ownerModelToEntity(OwnerModel ownerModel) {
        if (ownerModel == null) {
            return null;
        }
        Owner owner = new Owner();
        owner.setName(ownerModel.getName());
        owner.setRole(ownerModel.getRole());
        owner.setUsername(ownerModel.getUsername());
        owner.setPassword(ownerModel.getPassword());
        owner.setEmail(ownerModel.getEmail());
        return owner;
    }
    public Payment paymentModelToEntity(PaymentModel paymentModel) {
        Payment payment = new Payment();
        payment.setPid(paymentModel.getPid());
        payment.setAmount(paymentModel.getAmount());
        payment.setPaymentDate(paymentModel.getPaymentDate());
        payment.setRegistration(paymentModel.getRegistration());
        return payment;
    }
    public Registration registrationModelToEntity(RegistrationModel registrationModel) {
        Registration registration = new Registration();
        registration.setRid(registrationModel.getRid());
        registration.setRdate(registrationModel.getRdate());
        registration.setAttendee(registrationModel.getAttendee());
        registration.setEvent(registrationModel.getEvent());
        return registration;
    }
}
