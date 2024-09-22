package mk.ukim.finki.emt.apointmentscheduling.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.apointmentscheduling.client.MedicalRecordServiceClient;
import mk.ukim.finki.emt.apointmentscheduling.domain.models.Appointment;
import mk.ukim.finki.emt.apointmentscheduling.domain.models.Doctor;
import mk.ukim.finki.emt.apointmentscheduling.domain.repository.AppointmentRepository;
import mk.ukim.finki.emt.apointmentscheduling.domain.repository.DoctorRepository;
import mk.ukim.finki.emt.apointmentscheduling.events.PatientEventPublisher;
import mk.ukim.finki.emt.patientmanagment.domain.models.Insurance;
import mk.ukim.finki.emt.patientmanagment.domain.models.MedicalRecord;
import mk.ukim.finki.emt.patientmanagment.domain.models.Patient;
import mk.ukim.finki.emt.patientmanagment.service.PatientService;
import mk.ukim.finki.emt.sharedkernel.domain.base.AppointmentDate;
import mk.ukim.finki.emt.sharedkernel.domain.base.ContactInformation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientService patientService;
    private final PatientEventPublisher patientEventPublisher;

    @PostConstruct
    public void initData() {
        ContactInformation doctorContactInfo = new ContactInformation("email@asd.com", "123456789", "Ul. Orce Nikolov Bb");
        Doctor doctor = new Doctor();
        doctor.setName("Nikola");
        doctor.setContactInformation(doctorContactInfo);
        doctorRepository.save(doctor);

        ContactInformation patientContactInfo = new ContactInformation("patient@asd.com", "987654321", "Ul. Aleksandar Makedonski");
        Insurance insurance = new Insurance(null, "PN.1223", "Maximum Coverage", "12-12-2024");
        MedicalRecord medicalRecord = new MedicalRecord(null, "Asthma", "Inhalations", "none", null);
        Patient patient = new Patient(null, "Aleksandar", patientContactInfo, medicalRecord, insurance);

        System.out.println("Publishing PatientCreatedEvent for patient: " + patient.getName());
        patientEventPublisher.publishPatientCreatedEvent(patient);

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(new AppointmentDate(LocalDateTime.now().plusHours(3)));
        appointment.setStatus("Reserved");
//        appointment.setPatient(patient);

        appointmentRepository.save(appointment);
    }
}