package mk.ukim.finki.emt.apointmentscheduling.xport;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.apointmentscheduling.domain.models.Appointment;
import mk.ukim.finki.emt.apointmentscheduling.domain.models.Doctor;
import mk.ukim.finki.emt.apointmentscheduling.services.impl.AppointmentServiceImpl;
import mk.ukim.finki.emt.apointmentscheduling.services.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AppointmentResource {
    private final AppointmentServiceImpl appointmentService;
    private final DoctorService doctorService;

    @GetMapping("/appointments")
    public List<Appointment> getAll() {
        return appointmentService.findAll();
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<Appointment> getById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.findById(id));
    }

    @PostMapping("/appointments")
    public ResponseEntity<Appointment> save(@RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentService.save(appointment);
        return ResponseEntity.ok(savedAppointment);
    }

    @PutMapping("/appointments/{id}")
    public ResponseEntity<Appointment> edit(@PathVariable Long id, @RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.edit(id, appointment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment updatedAppointment) {
        Appointment appointment = appointmentService.updateAppointmentByDoctor(id, updatedAppointment);
            return ResponseEntity.ok(appointment);
    }

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.findAll();
    }

    @PostMapping("/doctors")
    public Doctor saveDoctor(@RequestBody Doctor doctor) {
        return doctorService.save(doctor);
    }
}
