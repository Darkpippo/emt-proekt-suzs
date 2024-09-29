package mk.ukim.finki.emt.patientmanagment.service;

import mk.ukim.finki.emt.patientmanagment.domain.models.MedicalRecord;

public interface MedicalRecordService {
    public void updateRecord(Long patientId, String diagnosis, String treatment);
    MedicalRecord save(MedicalRecord medicalRecord);
}
