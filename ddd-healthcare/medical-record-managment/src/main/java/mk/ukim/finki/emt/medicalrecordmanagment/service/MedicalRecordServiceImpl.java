package mk.ukim.finki.emt.medicalrecordmanagment.service;

import mk.ukim.finki.emt.medicalrecordmanagment.domain.models.MedicalRecord;
import mk.ukim.finki.emt.medicalrecordmanagment.domain.repository.MedicalRecordRepository;
import mk.ukim.finki.emt.sharedkernel.domain.base.PatientId;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordServiceImpl {
    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }


    public MedicalRecord findMedicalRecordByPatientId(PatientId patientId) {
        return medicalRecordRepository.findByPatient_Id(patientId.getId());
    }

    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }
}
