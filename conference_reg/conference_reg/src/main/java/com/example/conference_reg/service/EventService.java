package com.example.conference_reg.service;

import com.example.conference_reg.conversions.EntityToModel;
import com.example.conference_reg.conversions.ModelToEntity;
import com.example.conference_reg.entity.Event;
import com.example.conference_reg.model.EventModel;
import com.example.conference_reg.repository.EventRepository;
import com.example.conference_reg.service.serviceinterface.EventInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
@Service
public class EventService implements EventInter {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EntityToModel entityToModel;
    @Autowired
    private ModelToEntity modelToEntity;

    public EventModel add(EventModel eventModel) {
        Event event = modelToEntity.convertToEntity2(eventModel);
        return entityToModel.convertToModel2(eventRepository.save(event));
    }

    public List<EventModel> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(entityToModel::convertToModel2)
                .sorted(Comparator.comparingInt(EventModel::getEid))
                .toList();
    }

    public EventModel updateEvent(EventModel eventModel) {
        Event event = modelToEntity.convertToEntity2(eventModel);
        Event updateEvent = eventRepository.findById(event.getEid()).orElse(null);
        if (updateEvent != null) {
            updateEvent.setEname(event.getEname());
            updateEvent.setDate(event.getDate());
            updateEvent.setVenue(event.getVenue());
            eventRepository.save(updateEvent);
            return entityToModel.convertToModel2(updateEvent);
        }
        return null;
    }
    public EventModel getEventById(int eid) {
        Event event = eventRepository.findById(eid).orElse(null);
        return entityToModel.convertToModel2(event);
    }

//    public EventModel convertToModel2(Event event) {
//        if (event == null) {
//            return null;
//        }
//        EventModel eventModel = new EventModel();
//        eventModel.setEid(event.getEid());
//        eventModel.setEname(event.getEname());
//        eventModel.setDate(event.getDate());
//        eventModel.setVenue(event.getVenue());
//        return eventModel;
//    }

//    public Event convertToEntity2(EventModel eventModel) {
//        if (eventModel == null) {
//            return null;
//        }
//        Event event = new Event();
//        event.setEid(eventModel.getEid());
//        event.setEname(eventModel.getEname());
//        event.setDate(eventModel.getDate());
//        event.setVenue(eventModel.getVenue());
//        return event;
//    }
}
