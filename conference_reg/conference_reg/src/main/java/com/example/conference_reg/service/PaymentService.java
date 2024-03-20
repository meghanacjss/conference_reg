package com.example.conference_reg.service;
import com.example.conference_reg.conversions.EntityToModel;
import com.example.conference_reg.conversions.ModelToEntity;
import com.example.conference_reg.entity.Payment;
import com.example.conference_reg.entity.Registration;
import com.example.conference_reg.model.PaymentModel;
import com.example.conference_reg.repository.PaymentRepository;
import com.example.conference_reg.service.serviceinterface.PaymentInter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service
public class PaymentService implements PaymentInter {
@Autowired
private PaymentRepository paymentRepository;
    @Autowired
    private EntityToModel entityToModel;
    @Autowired
    private ModelToEntity modelToEntity;
    public PaymentModel createPayment(@Valid PaymentModel paymentModel) {
        Payment payment = modelToEntity.convertToEntity4(paymentModel);
        Payment savedPayment = paymentRepository.save(payment);
        return entityToModel.convertToModel4(savedPayment);
    }

//    public PaymentModel createPayment(@Valid PaymentModel paymentModel, Registration registration) {
//        Payment payment = convertToEntity(paymentModel);
//        payment.setRegistration(registration);
//        Payment savedPayment = paymentRepository.save(payment);
//       return convertToModel(savedPayment);
//    }

    public List<PaymentModel> getAllPayments(){
        return paymentRepository.findAll().stream()
                .filter(payment -> payment.getAmount() > 500)
                .sorted(Comparator.comparing(Payment::getPaymentDate))
                .map(entityToModel::convertToModel4)
                .toList();
    }
    public List<PaymentModel>getPaymentsByRegistration(Registration registration) {
        List<Payment> payments = paymentRepository.findByRegistration(registration);
        return payments.stream()
                .map(entityToModel::convertToModel4)
                .toList();
    }
    public long calculateTotalPayments(Registration registration) {
        List<Payment> payments = paymentRepository.findByRegistration(registration);
        return payments.stream().mapToLong(Payment::getAmount).sum();
    }

//    public void refundPayment(int pid, long amount) {
//        Optional<Payment> optionalPayment = paymentRepository.findById(pid);
//        if (optionalPayment.isPresent()) {
//            Payment payment = optionalPayment.get();
//            System.out.println(payment.getPaymentDate());
//            Registration registration = payment.getRegistration();
//            if (registration != null && registration.getRdate() != null && registration.getRdate().after(new Date())) {
//              //  System.out.println(payment.getPaymentDate());
//                if (amount > 0 && amount <= payment.getAmount()) {
//                  //  System.out.println(payment.getPaymentDate());
//                    long remainingAmount = payment.getAmount() - amount;
//                    payment.setAmount(remainingAmount);
//                    paymentRepository.save(payment);
//                } else {
//                    throw new IllegalArgumentException("Invalid refund amount.");
//                }
//            } else {
//                System.out.println("current date"+" "+new Date());
//                throw new IllegalArgumentException("Refund is not allowed for past registrations.");
//            }
//        } else {
//            throw new IllegalArgumentException("Payment id not found.");
//        }
//    }



//    public PaymentModel convertToModel4(Payment payment) {
//        PaymentModel paymentModel = new PaymentModel();
//        paymentModel.setPid(payment.getPid());
//        paymentModel.setAmount(payment.getAmount());
//        paymentModel.setPaymentDate(payment.getPaymentDate());
//        paymentModel.setRegistration(payment.getRegistration());
//        return paymentModel;
//    }

//    public Payment convertToEntity4(PaymentModel paymentModel) {
//        Payment payment = new Payment();
//        payment.setPid(paymentModel.getPid());
//        payment.setAmount(paymentModel.getAmount());
//        payment.setPaymentDate(paymentModel.getPaymentDate());
//        payment.setRegistration(paymentModel.getRegistration());
//        return payment;
//    }
}
