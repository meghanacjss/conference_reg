package com.example.conference_reg.conversions;

import com.example.conference_reg.entity.*;
import com.example.conference_reg.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EntityToModel {
@Autowired
    PasswordEncoder passwordEncoder;
    public AttendeeModel attendeeEntityToModel(Attendee attendee) {
        AttendeeModel attendeeModel = new AttendeeModel();
        attendeeModel.setAid(attendee.getAid());
        attendeeModel.setAname(attendee.getAname());
        attendeeModel.setEmail(attendee.getEmail());
        attendeeModel.setAffiliation(attendee.getAffiliation());

        return attendeeModel;
    }

    public EventModel eventEntityToModel(Event event) {
        if (event == null) {
            return null;
        }
        EventModel eventModel = new EventModel();
        eventModel.setEid(event.getEid());
        eventModel.setEname(event.getEname());
        eventModel.setDate(event.getDate());
        eventModel.setVenue(event.getVenue());
        return eventModel;
    }
    public OwnerModel ownerEntityToModel(Owner owner) {
        if (owner == null) {
            return null;
        }
        OwnerModel ownerModel = new OwnerModel();
        ownerModel.setName(owner.getName());
        ownerModel.setRole(owner.getRole());
        ownerModel.setUsername(owner.getUsername());
        ownerModel.setPassword(owner.getPassword());
        ownerModel.setEmail(owner.getEmail());
        return ownerModel;
    }
    public PaymentModel paymentEntityToModel(Payment payment) {
        PaymentModel paymentModel = new PaymentModel();
        paymentModel.setPid(payment.getPid());
        paymentModel.setAmount(payment.getAmount());
        paymentModel.setPaymentDate(payment.getPaymentDate());
        paymentModel.setRegistration(payment.getRegistration());
        return paymentModel;
    }
    public RegistrationModel registrationEntityToModel(Registration registration) {
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setRid(registration.getRid());
        registrationModel.setRdate(registration.getRdate());
        registrationModel.setAttendee(registration.getAttendee());
        registrationModel.setEvent(registration.getEvent());
        return registrationModel;
    }
}
