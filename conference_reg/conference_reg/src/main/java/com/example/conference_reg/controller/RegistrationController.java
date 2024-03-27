package com.example.conference_reg.controller;

import com.example.conference_reg.entity.Attendee;
import com.example.conference_reg.entity.Event;
import com.example.conference_reg.exception.UserNotFoundException;
import com.example.conference_reg.model.RegistrationModel;
import com.example.conference_reg.service.serviceinterface.AttendeeInter;
import com.example.conference_reg.service.serviceinterface.EventInter;
import com.example.conference_reg.service.serviceinterface.RegistrationInter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationInter registrationService;

    @Autowired
    private EventInter eventService;

    @Autowired
    private AttendeeInter attendeeService;

    @PostMapping("/createall")
    public ResponseEntity<RegistrationModel> createRegistration(@Valid @RequestBody RegistrationModel registrationModel,
                                                                @RequestParam("eid") int eid,
                                                                @RequestParam("aid") int aid) throws UserNotFoundException {

        Event event = new Event();
        event.setEid(eid);
        Attendee attendee = new Attendee();
        attendee.setAid(aid);
        if (event == null || attendee == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        RegistrationModel createdRegistration = registrationService.createRegistration(registrationModel, event, attendee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRegistration);
    }


    @GetMapping("/getall")
    public ResponseEntity<List<RegistrationModel>> getAllRegistrations() {
        List<RegistrationModel> registrations = registrationService.getAllRegistrations();
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/byEvent/")
    public ResponseEntity<List<RegistrationModel>> getRegistrationsByEvent(@RequestParam("eid") int eid) {
        Event event = new Event();
        event.setEid(eid);

        if (event == null) {
            return ResponseEntity.notFound().build();
        }
        List<RegistrationModel> registrations = registrationService.getRegistrationsByEvent(event);
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/byAttendee/")
    public ResponseEntity<List<RegistrationModel>> getRegistrationsByAttendee(@RequestParam("aid") int aid) throws UserNotFoundException {
        Attendee attendee = new Attendee();
        attendee.setAid(aid);
    
        if (attendee == null) {
            return ResponseEntity.notFound().build();
        }
        List<RegistrationModel> registrations = registrationService.getRegistrationsByAttendee(attendee);
        return ResponseEntity.ok(registrations);
    }

    @DeleteMapping("/cancel/")
    public ResponseEntity<String> cancelRegistration(@RequestParam int rid) {
        try {
            RegistrationModel cancelledRegistration = registrationService.cancelRegistration(rid);
            return ResponseEntity.ok("Registration with ID " + rid + " cancelled successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to cancel registration: " + e.getMessage());
        }
    }
 @GetMapping("/mostRegistrations")
    public ResponseEntity<Event> getEventWithMostRegistrations() {
        Event eventWithMostRegistrations = registrationService.getEventWithMostRegistrations();
        if (eventWithMostRegistrations != null) {
            return ResponseEntity.ok(eventWithMostRegistrations);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get_rid/")
    public ResponseEntity<RegistrationModel> getRegistrationById(@RequestParam("rid") int rid) {
        try {
            RegistrationModel registration = registrationService.getRegistrationById(rid);
            return ResponseEntity.ok(registration);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }


    }
}
