package com.example.conference_reg.service;
import com.example.conference_reg.conversions.EntityToModel;
import com.example.conference_reg.conversions.ModelToEntity;
import com.example.conference_reg.entity.Attendee;
import com.example.conference_reg.exception.UserNotFoundException;
import com.example.conference_reg.model.AttendeeModel;
import com.example.conference_reg.repository.AttendeeRepository;
import com.example.conference_reg.service.serviceinterface.AttendeeInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendeeService implements AttendeeInter {

    @Autowired
    private AttendeeRepository attendeeRepository;
    @Autowired
    private EntityToModel entityToModel;
    @Autowired
    private ModelToEntity modelToEntity;

    public AttendeeModel createAttendee(Attendee attendee) {
        Attendee savedAttendee = attendeeRepository.save(attendee);
        return entityToModel.convertToModel1(savedAttendee);
    }

    public List<AttendeeModel> getAllAttendees() {
        List<Attendee> attendees = attendeeRepository.findAll();
        return attendees.stream()
                .map(entityToModel::convertToModel1)
                .sorted(Comparator.comparingInt(AttendeeModel::getAid))
                .collect(Collectors.toList());
    }

    public Optional<AttendeeModel> getAttendeeById(int aid) throws UserNotFoundException {
        Optional<Attendee> optionalAttendee = attendeeRepository.findById(aid);
        return optionalAttendee.map(entityToModel::convertToModel1);
    }


}
