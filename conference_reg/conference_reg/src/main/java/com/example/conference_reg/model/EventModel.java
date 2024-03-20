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
    //    @NotBlank(message = "Event name is required")
//    @Size(max = 255, message = "Event name must not exceed 255 characters")
    private String ename;
    //    @NotNull(message = "Event date is required")
//    @Future(message = "Event date must be in the future")
    private String date;
    //  @NotBlank(message = "Event venue is required")
    private String venue;

}
