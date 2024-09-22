package mk.ukim.finki.emt.apointmentscheduling.xport;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.apointmentscheduling.domain.models.Appointment;
import mk.ukim.finki.emt.apointmentscheduling.domain.models.Doctor;
import mk.ukim.finki.emt.apointmentscheduling.services.impl.AppointmentServiceImpl;
import mk.ukim.finki.emt.apointmentscheduling.services.DoctorService;
import mk.ukim.finki.emt.patientmanagment.domain.models.Patient;
import mk.ukim.finki.emt.patientmanagment.service.PatientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AppointmentResource {
    private final AppointmentServiceImpl appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    @GetMapping("/appointments")
    public List<Appointment> getAll() {
        return appointmentService.findAll();
    }

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.findAll();
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientService.findAll();
    }

}
