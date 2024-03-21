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
 
    private long amount;

    private String paymentDate;

   private Registration registration;
  
}
