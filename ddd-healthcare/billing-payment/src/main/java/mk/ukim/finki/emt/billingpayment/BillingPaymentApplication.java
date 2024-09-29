package mk.ukim.finki.emt.billingpayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"mk.ukim.finki.emt.billingpayment", "mk.ukim.finki.emt.sharedkernel"})
@EnableAsync
public class BillingPaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(BillingPaymentApplication.class, args);
    }

}
