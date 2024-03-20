package com.example.conference_reg.service.serviceinterface;

import com.example.conference_reg.model.EventModel;

import java.util.List;

public interface EventInter {

    EventModel add(EventModel eventModel);

    List<EventModel> getAllEvents();

    EventModel updateEvent(EventModel eventModel);

    EventModel getEventById(int eid);

}
