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
    //  @Future(message="enter date must be in future")
    private String rdate;
    //  @Min(value=1,message="amount must be greater than 1")
  //  private long ramount;

    private Attendee attendee;
    private Event event;

}
