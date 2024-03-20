package com.example.conference_reg.model;

import com.example.conference_reg.entity.Event;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AttendeeModel {

    private int aid;
    @NotBlank(message="name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String aname;
    @NotBlank(message="email is required")
    @Email(message="invalid email format")
    private String email;
    private String affiliation;

    private Event event;

}
