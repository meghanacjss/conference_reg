package com.example.conference_reg.entity;

import com.example.conference_reg.validation.AffiliationTypeValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Attendee {
    @Id
    @GeneratedValue
    private int aid;
    @NotBlank(message="name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String aname;
    @NotBlank(message="email is required")
    @Email(message="invalid email format")
    private String email;
    @AffiliationTypeValidation
    private String affiliation;
    // @JsonIgnore

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "event_id")
    private Event event;
}
