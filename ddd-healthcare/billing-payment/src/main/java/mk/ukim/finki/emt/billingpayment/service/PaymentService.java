package mk.ukim.finki.emt.billingpayment.service;


import mk.ukim.finki.emt.billingpayment.domain.models.Payment;

import java.util.List;

public interface PaymentService {
    Payment createPayment(Payment payment);
    Payment findById(Long id);
    List<Payment> findAll();
    Payment pay(Long id);
}
