package mk.ukim.finki.emt.patientmanagment.service.impl;

import mk.ukim.finki.emt.patientmanagment.domain.models.Insurance;
import mk.ukim.finki.emt.patientmanagment.domain.models.MedicalRecord;
import mk.ukim.finki.emt.patientmanagment.domain.models.Patient;
import mk.ukim.finki.emt.patientmanagment.domain.repository.InsuranceRepository;
import mk.ukim.finki.emt.patientmanagment.domain.repository.MedicalRecordRepository;
import mk.ukim.finki.emt.patientmanagment.domain.repository.PatientRepository;
import mk.ukim.finki.emt.patientmanagment.service.PatientService;
import mk.ukim.finki.emt.sharedkernel.domain.base.PatientId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final InsuranceRepository insuranceRepository;

    public PatientServiceImpl(PatientRepository patientRepository, MedicalRecordRepository medicalRecordRepository, InsuranceRepository insuranceRepository) {
        this.patientRepository = patientRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.insuranceRepository = insuranceRepository;
    }

    @Override
    public Patient findPatientById(Long patientId) {
        return patientRepository.findById(patientId).orElseThrow(() -> new IllegalArgumentException("There is no patient with id: "+ patientId));
    }

    @Override
    public Patient save(Patient patient) {
        insuranceRepository.save(patient.getInsurance());
        Patient savedPatient = patientRepository.save(patient);
        medicalRecordRepository.save(savedPatient.getMedicalRecord());
        return savedPatient;
    }

    @Override
    public void deletePatient(PatientId patientId) {
        patientRepository.deleteById(patientId.getId());
    }
    @Override
    public boolean patientExists(Long patientId) {
        return patientRepository.existsById(patientId);
    }

    @Override
    public Patient findByName(String name) {
        return patientRepository.findByNameIgnoreCase(name).orElseThrow(() -> new IllegalArgumentException("No patient with name: "+name));
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
}
