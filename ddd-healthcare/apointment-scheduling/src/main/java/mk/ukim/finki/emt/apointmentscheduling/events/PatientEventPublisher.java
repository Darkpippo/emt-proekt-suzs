package mk.ukim.finki.emt.apointmentscheduling.events;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.patientmanagment.domain.models.Patient;
import mk.ukim.finki.emt.patientmanagment.events.PatientCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatientEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;
    public void publishPatientCreatedEvent(Patient patient) {
        PatientCreatedEvent event = new PatientCreatedEvent(patient);
        applicationEventPublisher.publishEvent(event);
    }
}