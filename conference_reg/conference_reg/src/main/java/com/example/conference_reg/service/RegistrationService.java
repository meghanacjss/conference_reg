package com.example.conference_reg.service;
import com.example.conference_reg.conversions.EntityToModel;
import com.example.conference_reg.conversions.ModelToEntity;
import com.example.conference_reg.entity.Attendee;
import com.example.conference_reg.entity.Event;
import com.example.conference_reg.entity.Payment;
import com.example.conference_reg.entity.Registration;
import com.example.conference_reg.model.RegistrationModel;
import com.example.conference_reg.repository.AttendeeRepository;
import com.example.conference_reg.repository.EventRepository;
import com.example.conference_reg.repository.PaymentRepository;
import com.example.conference_reg.repository.RegistrationRepository;
import com.example.conference_reg.service.serviceinterface.RegistrationInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class RegistrationService implements RegistrationInter {
@Autowired
private RegistrationRepository registrationRepository;
@Autowired
private PaymentRepository paymentRepository;
@Autowired
private AttendeeRepository attendeeRepository;
@Autowired
private EventRepository eventRepository;
    @Autowired
    private EntityToModel entityToModel;
    @Autowired
    private ModelToEntity modelToEntity;



    public RegistrationModel createRegistration(RegistrationModel registrationModel, Event event, Attendee attendee) {
        List<Registration> registrationsForEvent = registrationRepository.findByEvent(event);
        System.out.println(registrationModel.getRdate());
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate newRegistrationDate = LocalDate.parse(registrationModel.getRdate(),dateFormat);

        boolean timingAvailable = checkTimingAvailability(registrationsForEvent, newRegistrationDate.toString());

        if (timingAvailable) {
            throw new IllegalArgumentException("Registration is already booked.");
        }
        Registration registration = modelToEntity.convertToEntity5(registrationModel);
        registration.setEvent(event);
        registration.setAttendee(attendee);
        return entityToModel.convertToModel5(registrationRepository.save(registration));
    }
    private boolean checkTimingAvailability(List<Registration> registrations, String newRegistrationDate) {

        return registrations.stream().anyMatch(date -> date.getRdate().equals(newRegistrationDate));


    }

public List<RegistrationModel> getAllRegistrations() {
    return registrationRepository.findAll().stream()
            .sorted(Comparator.comparing(Registration::getRdate))
            .map(entityToModel::convertToModel5)
            .collect(Collectors.toList());
}

    public List<RegistrationModel>getRegistrationsByEvent(Event event) {
        List<Registration> registrations = registrationRepository.findByEvent(event);
        return registrations.stream()
                .map(entityToModel::convertToModel5)
                .collect(Collectors.toList());
    }
    public List<RegistrationModel> getRegistrationsByAttendee(Attendee attendee) {
        List<Registration> registrations = registrationRepository.findByAttendee(attendee);
        return registrations.stream()
                .map(entityToModel::convertToModel5)
                .collect(Collectors.toList());
    }
    public RegistrationModel getRegistrationById(int rid) {
        Registration registration = registrationRepository.findById(rid).orElse(null);
        if (registration != null) {
            return entityToModel.convertToModel5(registration);
        }
        return null;
    }
public RegistrationModel cancelRegistration(int rid) {
    Registration registrationEntity = registrationRepository.findById(rid).orElse(null);
    if (registrationEntity != null) {
        List<Payment> payments = paymentRepository.findByRegistration(registrationEntity);
        payments.forEach(payment -> {
            payment.setRegistration(null);
            paymentRepository.save(payment);
        });
        registrationRepository.delete(registrationEntity);
        return entityToModel.convertToModel5(registrationEntity);
    } else {
        throw new IllegalArgumentException("Registration not found for ID: " + rid);
    }
}

}
