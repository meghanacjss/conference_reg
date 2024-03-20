package com.example.conference_reg.repository;

import com.example.conference_reg.entity.Payment;
import com.example.conference_reg.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    List<Payment> findByRegistration(Registration registration);

    void deleteByRegistration(Registration registration);
}
