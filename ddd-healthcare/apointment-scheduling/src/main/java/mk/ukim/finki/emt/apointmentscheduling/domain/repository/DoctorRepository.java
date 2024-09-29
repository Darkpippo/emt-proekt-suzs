package mk.ukim.finki.emt.apointmentscheduling.domain.repository;

import mk.ukim.finki.emt.apointmentscheduling.domain.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}