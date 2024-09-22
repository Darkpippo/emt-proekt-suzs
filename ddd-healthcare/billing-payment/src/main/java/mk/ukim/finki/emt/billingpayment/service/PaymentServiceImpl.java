package mk.ukim.finki.emt.billingpayment.service;

import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.billingpayment.domain.models.Payment;
import mk.ukim.finki.emt.billingpayment.domain.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment findById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No payment found with id: " + id));
    }
}