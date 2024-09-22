package mk.ukim.finki.emt.apointmentscheduling.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.apointmentscheduling.domain.models.Appointment;
import mk.ukim.finki.emt.apointmentscheduling.domain.repository.AppointmentRepository;
import mk.ukim.finki.emt.apointmentscheduling.events.PatientEventPublisher;
import mk.ukim.finki.emt.apointmentscheduling.services.DoctorService;
import mk.ukim.finki.emt.patientmanagment.domain.models.Patient;
import mk.ukim.finki.emt.patientmanagment.service.PatientService;
import mk.ukim.finki.emt.sharedkernel.infra.AppointmentEventPublisher;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl {
    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;
    private final PatientEventPublisher patientEventPublisher;
    private final PatientService patientService;

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Appointment findById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with id: " + id));
    }

    public Appointment save(Appointment appointment) {
        validateAppointment(appointment);
        Appointment savedAppointment = appointmentRepository.save(appointment);

        if (isNewPatient(appointment.getPatient())) {
            patientEventPublisher.publishPatientCreatedEvent(appointment.getPatient());
        }

        return savedAppointment;
    }

    private boolean isNewPatient(Patient patient) {
        return patientService.patientExists(patient.getId());
    }

    private void validateAppointment(Appointment appointment) {
        if (appointment.getAppointmentDate().isWeekend()) {
            throw new IllegalArgumentException("Appointments cannot be scheduled on weekends");
        }
        if (appointment.getStatus().isEmpty()) {
            throw new IllegalArgumentException("Appointment status must be filled");
        }
        if (!doctorService.doctorExists(appointment.getDoctor().getId())) {
            throw new IllegalArgumentException("Doctor does not exist.");
        }
    }

    public void deleteById(Long id) {
        findById(id);
        appointmentRepository.deleteById(id);
    }
}
