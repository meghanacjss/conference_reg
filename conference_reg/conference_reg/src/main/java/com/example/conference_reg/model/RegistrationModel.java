package com.example.conference_reg.model;

import com.example.conference_reg.entity.Attendee;
import com.example.conference_reg.entity.Event;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegistrationModel {

    private int rid;

    private String rdate;

    private Attendee attendee;
    private Event event;

}
