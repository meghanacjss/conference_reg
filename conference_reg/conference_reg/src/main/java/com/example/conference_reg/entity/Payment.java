package com.example.conference_reg.entity;

import com.example.conference_reg.validation.DateTypeValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {
    @Id
    @GeneratedValue
    private int pid;

    // @JsonIgnore

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "registrationId")
    private Registration registration;
    @Min(value = 1, message = "Amount must be greater than 0")
    private long amount;
    @NotBlank(message = "Payment date cannot be blank")
    @DateTypeValidation
    private String paymentDate;
}
