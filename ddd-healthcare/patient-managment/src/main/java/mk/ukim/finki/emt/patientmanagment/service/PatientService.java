package mk.ukim.finki.emt.patientmanagment.service;

import mk.ukim.finki.emt.patientmanagment.domain.models.Patient;
import mk.ukim.finki.emt.sharedkernel.domain.base.PatientId;

import java.util.List;

public interface PatientService {
    Patient findPatientById(Long patientId);
    Patient save(Patient patient);
    void deletePatient(PatientId patientId);
    boolean patientExists(Long patientId);
    Patient findByName(String name);
    List<Patient> findAll();
}
