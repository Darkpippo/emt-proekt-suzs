package mk.ukim.finki.emt.apointmentscheduling.config;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.apointmentscheduling.domain.repository.AppointmentRepository;
import mk.ukim.finki.emt.apointmentscheduling.domain.repository.DoctorRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

}