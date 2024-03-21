package com.example.conference_reg.model;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventModel {

    private int eid;

    private String ename;

    private String date;
  
    private String venue;

}
