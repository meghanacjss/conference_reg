package com.example.conference_reg.controller;
import com.example.conference_reg.model.EventModel;
import com.example.conference_reg.service.serviceinterface.EventInter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/event")
public class EventController {
@Autowired
private EventInter eventService;
    @PostMapping("/addevent")
    public ResponseEntity<EventModel> addEvent(@Valid @RequestBody EventModel eventModel) {
        EventModel addedEvent = eventService.add(eventModel);
        if (addedEvent != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(addedEvent);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/getevents")
    public ResponseEntity<List<EventModel>> getAllEvents() {
        List<EventModel> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }


    @GetMapping("/geteventbyid/")
    public ResponseEntity<EventModel> getEventById(@RequestParam int eid) {
        try {
            EventModel event = eventService.getEventById(eid);
            return ResponseEntity.ok(event);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateevent")
    public ResponseEntity<EventModel> updateEvent(@RequestBody EventModel eventModel) {
        EventModel updatedEvent = eventService.updateEvent(eventModel);
        if (updatedEvent != null) {
            return ResponseEntity.ok(updatedEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
