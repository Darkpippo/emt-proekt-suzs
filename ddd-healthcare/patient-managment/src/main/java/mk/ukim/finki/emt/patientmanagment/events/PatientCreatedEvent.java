package mk.ukim.finki.emt.patientmanagment.events;

import lombok.Getter;
import mk.ukim.finki.emt.patientmanagment.domain.models.Patient;
import org.springframework.context.ApplicationEvent;

@Getter
public class PatientCreatedEvent {
    private final Patient patient;

    public PatientCreatedEvent(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }
}