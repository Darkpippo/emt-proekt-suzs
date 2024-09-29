package mk.ukim.finki.emt.apointmentscheduling.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.apointmentscheduling.config.PatientServiceClient;
import mk.ukim.finki.emt.apointmentscheduling.domain.models.Appointment;
import mk.ukim.finki.emt.apointmentscheduling.domain.repository.AppointmentRepository;
import mk.ukim.finki.emt.apointmentscheduling.services.DoctorService;
import mk.ukim.finki.emt.sharedkernel.domain.events.AppointmentCreatedEvent;
import mk.ukim.finki.emt.sharedkernel.domain.base.AppointmentDate;
import mk.ukim.finki.emt.sharedkernel.domain.models.PatientDTO;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl {
    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;
    private final PatientServiceClient patientServiceClient;
    private final ApplicationEventPublisher applicationEventPublisher;

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Appointment findById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with id: " + id));
    }

    public Appointment save(Appointment appointmentDTO) {
        try {
            PatientDTO patient = patientServiceClient.getPatientById(appointmentDTO.getPatientId().toString());
            if (patient == null) {
                throw new RuntimeException("Patient not found!");
            }

            Appointment appointment = new Appointment();
            appointment.setDoctor(appointmentDTO.getDoctor());
            appointment.setPatientId(appointmentDTO.getPatientId());
            appointment.setAppointmentDate(new AppointmentDate(appointmentDTO.getAppointmentDate().getAppointmentDateTime()));
            appointment.setStatus("Reserved");

            Appointment savedAppointment = appointmentRepository.save(appointment);

            AppointmentCreatedEvent event = new AppointmentCreatedEvent(
                    savedAppointment.getId(),
                    savedAppointment.getPatientId(),
                    savedAppointment.getDoctor().getId(),
                    savedAppointment.getStatus());

            applicationEventPublisher.publishEvent(event);

            return savedAppointment;
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public Appointment edit(Long appointmentId, Appointment editedAppointment) {
        Appointment savedAppointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new IllegalArgumentException("Invalid appointment id: "+ appointmentId));
        PatientDTO patient = patientServiceClient.getPatientById(editedAppointment.getPatientId().toString());
        if (patient == null) {
            throw new RuntimeException("Patient not found!");
        }
        savedAppointment.setAppointmentDate(editedAppointment.getAppointmentDate());
        savedAppointment.setStatus(editedAppointment.getStatus());
        savedAppointment.setDoctor(editedAppointment.getDoctor());
        savedAppointment.setPatientId(editedAppointment.getPatientId());

        return appointmentRepository.save(savedAppointment);
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

    public Appointment updateAppointmentByDoctor(Long id, Appointment updatedAppointment) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        existingAppointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
        existingAppointment.setStatus(updatedAppointment.getStatus());

        return appointmentRepository.save(existingAppointment);
    }

    public void deleteById(Long id) {
        findById(id);
        appointmentRepository.deleteById(id);
    }
}
