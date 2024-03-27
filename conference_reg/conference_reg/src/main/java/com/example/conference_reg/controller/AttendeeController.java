package com.example.conference_reg.controller;
import com.example.conference_reg.conversions.ModelToEntity;
import com.example.conference_reg.entity.Attendee;
import com.example.conference_reg.exception.UserNotFoundException;
import com.example.conference_reg.model.AttendeeModel;
import com.example.conference_reg.model.EventModel;
import com.example.conference_reg.service.serviceinterface.AttendeeInter;
import com.example.conference_reg.service.serviceinterface.EventInter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
public class AttendeeController {
    @Autowired
    private AttendeeInter attendeeService;
    @Autowired
    private EventInter eventService;
    @Autowired
    private ModelToEntity modelToEntity;
    @PostMapping("/createattendee/")
    public ResponseEntity<AttendeeModel> createAttendee(@Valid @RequestBody AttendeeModel attendeeModel,
                                                        @RequestParam int eid) {
        EventModel event = eventService.getEventById(eid);
        if (event == null) {
            return ResponseEntity.notFound().build();
        }
        Attendee attendee = modelToEntity.attendeeModelToEntity(attendeeModel);
        attendee.setEvent(modelToEntity.eventModelToEntity(event));
        AttendeeModel createdAttendee = attendeeService.createAttendee(attendee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAttendee);
    }
    @GetMapping("/allattendees")
    public ResponseEntity<List<AttendeeModel>> getAllAttendees() {
        List<AttendeeModel> attendees = attendeeService.getAllAttendees();
        return ResponseEntity.ok(attendees);
    }
    @GetMapping("/get/")
    public ResponseEntity<AttendeeModel> getAttendeeById(@RequestParam int aid) throws UserNotFoundException {
        try {
            Optional<AttendeeModel> attendee = attendeeService.getAttendeeById(aid);
            return attendee.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}



















