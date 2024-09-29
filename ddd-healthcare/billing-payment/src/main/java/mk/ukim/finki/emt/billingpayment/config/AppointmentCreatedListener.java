package mk.ukim.finki.emt.billingpayment.config;

import mk.ukim.finki.emt.billingpayment.domain.models.Payment;
import mk.ukim.finki.emt.billingpayment.domain.valueObjects.Amount;
import mk.ukim.finki.emt.billingpayment.domain.valueObjects.Currency;
import mk.ukim.finki.emt.billingpayment.service.PaymentService;
import mk.ukim.finki.emt.sharedkernel.domain.events.AppointmentCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class AppointmentCreatedListener {

    private final PaymentService paymentService;

    public AppointmentCreatedListener(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleAppointmentCreatedEvent(AppointmentCreatedEvent event) {
        System.out.println("Received AppointmentCreatedEvent for appointment ID: " + event.getAppointmentId());

        Payment payment = new Payment();
        payment.setAmount(new Amount(new BigDecimal(750), Currency.MKD));
        payment.setDate(LocalDateTime.now());
        payment.setStatus("Pending");
        payment.setAppointmentId(event.getAppointmentId());

        paymentService.createPayment(payment);
    }

    @EventListener
    public void handleAppointmentCreatedEvent2(AppointmentCreatedEvent event) {
        System.out.println("Received AppointmentCreatedEvent for appointment ID: " + event.getAppointmentId());
    }
}