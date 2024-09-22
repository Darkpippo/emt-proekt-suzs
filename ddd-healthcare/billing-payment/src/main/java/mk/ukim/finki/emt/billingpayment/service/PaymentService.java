package mk.ukim.finki.emt.billingpayment.service;

import mk.ukim.finki.emt.billingpayment.domain.models.Payment;

public interface PaymentService {
    Payment createPayment(Payment payment);
    Payment findById(Long id);
}
