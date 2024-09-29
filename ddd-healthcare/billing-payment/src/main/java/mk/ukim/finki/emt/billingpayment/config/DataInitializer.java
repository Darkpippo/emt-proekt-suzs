package mk.ukim.finki.emt.billingpayment.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.sharedkernel.domain.events.AppointmentCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostConstruct
    public void testEventPublishing() {
        AppointmentCreatedEvent event = new AppointmentCreatedEvent(1L, 1L, 1L, "Reserved");
        eventPublisher.publishEvent(event);
        System.out.println("Manually published AppointmentCreatedEvent");
    }
}
