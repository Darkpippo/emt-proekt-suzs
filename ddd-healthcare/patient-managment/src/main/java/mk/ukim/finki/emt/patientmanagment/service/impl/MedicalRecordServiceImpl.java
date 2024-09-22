package mk.ukim.finki.emt.patientmanagment.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.patientmanagment.domain.models.MedicalRecord;
import mk.ukim.finki.emt.patientmanagment.domain.models.Patient;
import mk.ukim.finki.emt.patientmanagment.domain.repository.MedicalRecordRepository;
import mk.ukim.finki.emt.patientmanagment.domain.repository.PatientRepository;
import mk.ukim.finki.emt.patientmanagment.service.MedicalRecordService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;
    @Override
    public void updateRecord(Long patientId, String diagnosis, String treatment) {
        MedicalRecord medicalRecord = medicalRecordRepository.findByPatientId(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Medical record not found for patient ID: " + patientId));
        medicalRecord.setDiagnoses(diagnosis);
        medicalRecord.setTreatments(treatment);
        medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord save(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }
}
