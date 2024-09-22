package mk.ukim.finki.emt.patientmanagment.domain.repository;

import mk.ukim.finki.emt.patientmanagment.domain.models.MedicalRecord;
import mk.ukim.finki.emt.patientmanagment.domain.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByMedicalRecord(MedicalRecord medicalRecord);
    Optional<Patient> findByNameIgnoreCase(String name);
}
