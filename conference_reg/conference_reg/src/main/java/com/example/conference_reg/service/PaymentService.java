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
    public PaymentModel createPayment(PaymentModel paymentModel) {
        Payment payment = modelToEntity.paymentModelToEntity(paymentModel);
        Payment savedPayment = paymentRepository.save(payment);
        return entityToModel.paymentEntityToModel(savedPayment);
    }
    public List<PaymentModel> getAllPayments(){
        return paymentRepository.findAll().stream()
                .filter(payment -> payment.getAmount() > 500)
                .sorted(Comparator.comparing(Payment::getPaymentDate))
                .map(entityToModel::paymentEntityToModel)
                .toList();
    }
    public List<PaymentModel>getPaymentsByRegistration(Registration registration) {
        List<Payment> payments = paymentRepository.findByRegistration(registration);
        return payments.stream()
                .map(entityToModel::paymentEntityToModel)
                .toList();
    }
    public long calculateTotalPayments(Registration registration) {
        List<Payment> payments = paymentRepository.findByRegistration(registration);
        return payments.stream().mapToLong(Payment::getAmount).sum();
    }
}
