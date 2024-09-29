package mk.ukim.finki.emt.apointmentscheduling.domain.repository;

import mk.ukim.finki.emt.apointmentscheduling.domain.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}