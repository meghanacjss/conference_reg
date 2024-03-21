package com.example.conference_reg.entity;

import com.example.conference_reg.validation.DateTypeValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.*;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Registration {
    @Id
    @GeneratedValue
    private int rid;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "eventId")
    private Event event;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "attendeeId")
    private Attendee attendee;
    @DateTypeValidation
    private String rdate;


}
