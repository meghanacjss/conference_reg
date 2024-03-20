package com.example.conference_reg.controller;
import com.example.conference_reg.entity.Registration;
import com.example.conference_reg.model.PaymentModel;
import com.example.conference_reg.service.PaymentService;
import com.example.conference_reg.service.RegistrationService;
import com.example.conference_reg.service.serviceinterface.PaymentInter;
import com.example.conference_reg.service.serviceinterface.RegistrationInter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    private PaymentInter paymentService;

    @Autowired
    private RegistrationInter registrationService;

    @PostMapping("/create/")
    public ResponseEntity<PaymentModel> createPayment(@Valid @RequestBody PaymentModel paymentModel, @RequestParam int rid) {
        Registration registration = new Registration();
        registration.setRid(rid);
//        Registration registration = registrationService.getRegistrationById(rid);
//        if (registration == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        paymentModel.setRegistration(registration);
        PaymentModel createdPayment = paymentService.createPayment(paymentModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PaymentModel>> getAllPayments() {
        try {
            List<PaymentModel> payments = paymentService.getAllPayments();
            return ResponseEntity.ok(payments);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<PaymentModel>> getAllPayments() {
//        List<PaymentModel> payments = paymentService.getAllPayments();
//        return ResponseEntity.ok(payments);
//    }

    @GetMapping("/byRegistration/")
    public ResponseEntity<List<PaymentModel>> getPaymentsByRegistration(@RequestParam int rid) {
        Registration registration = new Registration();
        registration.setRid(rid);
        if (registration == null) {
            return ResponseEntity.notFound().build();
        }
        List<PaymentModel> payments = paymentService.getPaymentsByRegistration(registration);
        return ResponseEntity.ok(payments);
    }
    @GetMapping("/totalPayments/")
    public ResponseEntity<Long> calculateTotalPayments(@RequestParam int rid) {
        try {
            Registration registration = new Registration();
            registration.setRid(rid);
            long totalPayments = paymentService.calculateTotalPayments(registration);
            return ResponseEntity.ok(totalPayments);
        } catch (Exception e) {
            return new ResponseEntity<>(0L, HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/refund/")
//    public ResponseEntity<String> refundPayment(@RequestParam("pid") int pid, @RequestParam("amount") long amount) {
//        try {
//            paymentService.refundPayment(pid, amount);
//            return ResponseEntity.ok("Payment refunded successfully.");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }

//    @GetMapping("/totalPayments/")
//    public ResponseEntity<Long> calculateTotalPayments(@RequestParam int rid) {
//        Registration registration = new Registration();
//        registration.setRid(rid);
//        if (registration == null) {
//            return new ResponseEntity<>(0L, HttpStatus.NOT_FOUND);
//        }
//        long totalPayments = paymentService.calculateTotalPayments(registration);
//        return ResponseEntity.ok(totalPayments);
//    }
}



//    @PostMapping("/create/")
//    public ResponseEntity<PaymentModel> createPayment(@Valid @RequestBody PaymentModel paymentModel, @RequestParam int rid) {
//        RegistrationModel registration = registrationService.getRegistrationById(rid);
//
//        if (registration == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        paymentModel.setRegistration(registration);
//
//        PaymentModel createdPayment = paymentService.createPayment(paymentModel,registration);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
//    }