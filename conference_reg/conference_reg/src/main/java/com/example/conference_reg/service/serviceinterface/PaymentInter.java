package com.example.conference_reg.service.serviceinterface;

import com.example.conference_reg.entity.Registration;
import com.example.conference_reg.model.PaymentModel;

import java.util.List;

public interface PaymentInter {

    PaymentModel createPayment(PaymentModel paymentModel);

    List<PaymentModel> getAllPayments();

    List<PaymentModel> getPaymentsByRegistration(Registration registration);

    long calculateTotalPayments(Registration registration);
}
