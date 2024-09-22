package mk.ukim.finki.emt.medicalrecordmanagment.domain.repository;

import mk.ukim.finki.emt.medicalrecordmanagment.domain.models.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    MedicalRecord findByPatient_Id(Long patientId);
}