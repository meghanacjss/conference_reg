package com.example.conference_reg.model;

import com.example.conference_reg.entity.Registration;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentModel {

    private int pid;
    // @Min(value = 1, message = "Amount must be greater than 0")
    private long amount;
    // @NotBlank(message = "Payment date cannot be blank")
    private String paymentDate;

   private Registration registration;
   // private RegistrationModel registration;
}
