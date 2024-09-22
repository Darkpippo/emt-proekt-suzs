package mk.ukim.finki.emt.apointmentscheduling.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.events.AppointmentCreatedEvent;
import mk.ukim.finki.emt.sharedkernel.infra.AppointmentEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointmentEventPublisherImpl implements AppointmentEventPublisher {
    private final KafkaTemplate<String,String> kafkaTemplate;
    @Override
    public void publish(AppointmentCreatedEvent event) {
        this.kafkaTemplate.send(String.valueOf("Appointment id: " + event.getAppointmentId() +" Patient id: " + event.getPatientId()), event.toJson());
    }
}
