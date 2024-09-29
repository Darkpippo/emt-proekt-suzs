package mk.ukim.finki.emt.apointmentscheduling.infra;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.events.AppointmentCreatedEvent;
import mk.ukim.finki.emt.sharedkernel.infra.AppointmentEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointmentEventPublisherImpl implements AppointmentEventPublisher {
//    private final KafkaTemplate<String, AppointmentCreatedEvent> kafkaTemplate;
//    @Override
//    public void publish(AppointmentCreatedEvent event) {
//        this.kafkaTemplate.send("appointments-topic", event);
//    }
}
