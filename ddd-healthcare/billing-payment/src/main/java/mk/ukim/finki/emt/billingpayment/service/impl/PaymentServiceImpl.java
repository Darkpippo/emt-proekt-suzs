package mk.ukim.finki.emt.billingpayment.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.emt.billingpayment.domain.models.Payment;
import mk.ukim.finki.emt.billingpayment.domain.repository.PaymentRepository;
import mk.ukim.finki.emt.billingpayment.domain.valueObjects.Amount;
import mk.ukim.finki.emt.billingpayment.domain.valueObjects.Currency;
import mk.ukim.finki.emt.billingpayment.service.PaymentService;
import mk.ukim.finki.emt.sharedkernel.domain.events.AppointmentCreatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class PaymentServiceImpl implements PaymentService {
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

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment pay(Long id) {
        Payment payment = paymentRepository.findByAppointmentId(id);
        if ("Paid".equals(payment.getStatus())) {
            throw new IllegalStateException("Payment has already been processed.");
        }
        payment.setStatus("Paid");

        return paymentRepository.save(payment);
    }

    @TransactionalEventListener
    public void handleAppointmentCreatedEvent(AppointmentCreatedEvent event) {
        log.info("Appointment created event received: {}", event);

        Payment payment = new Payment();
        payment.setAmount(new Amount(new BigDecimal(750), Currency.MKD));
        payment.setDate(LocalDateTime.now());
        payment.setStatus("Pending");
        payment.setAppointmentId(event.getAppointmentId());

        log.info("Creating payment for appointment ID: {}", event.getAppointmentId());
        paymentRepository.save(payment);
        log.info("Payment created: {}", payment);
    }

}