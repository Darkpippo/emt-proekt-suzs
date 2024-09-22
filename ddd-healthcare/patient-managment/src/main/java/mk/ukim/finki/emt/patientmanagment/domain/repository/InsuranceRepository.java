package mk.ukim.finki.emt.patientmanagment.domain.repository;

import mk.ukim.finki.emt.patientmanagment.domain.models.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}
