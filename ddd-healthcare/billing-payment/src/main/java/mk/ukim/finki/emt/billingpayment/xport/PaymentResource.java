package mk.ukim.finki.emt.billingpayment.xport;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.billingpayment.domain.models.Payment;
import mk.ukim.finki.emt.billingpayment.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PaymentResource {
    private final PaymentService paymentService;
    @GetMapping("/payments")
    public List<Payment> findAll(){
        return paymentService.findAll();
    }

    @GetMapping("/payments/{id}")
    public Payment pay(@PathVariable Long id) {
        return paymentService.pay(id);
    }
}
