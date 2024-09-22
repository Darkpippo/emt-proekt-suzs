package mk.ukim.finki.emt.billingpayment.domain.repository;

import mk.ukim.finki.emt.billingpayment.domain.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}