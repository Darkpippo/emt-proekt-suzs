package mk.ukim.finki.emt.patientmanagment.events;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.patientmanagment.domain.models.Insurance;
import mk.ukim.finki.emt.patientmanagment.domain.models.MedicalRecord;
import mk.ukim.finki.emt.patientmanagment.domain.models.Patient;
import mk.ukim.finki.emt.patientmanagment.service.InsuranceService;
import mk.ukim.finki.emt.patientmanagment.service.MedicalRecordService;
import mk.ukim.finki.emt.patientmanagment.service.PatientService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PatientEventListener {
    private final MedicalRecordService medicalRecordService;
    private final InsuranceService insuranceService;
    private final PatientService patientService;

    @EventListener
    public void handlePatientCreatedEvent(PatientCreatedEvent event) {
        Patient patient = event.getPatient();
        System.out.println("Received PatientCreatedEvent for patient: " + patient.getName());

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setDiagnoses(patient.getMedicalRecord().getDiagnoses());
        medicalRecord.setTreatments(patient.getMedicalRecord().getTreatments());
        medicalRecord.setMedications(patient.getMedicalRecord().getMedications());
        medicalRecord.setPatient(patient);
        medicalRecordService.save(medicalRecord);

        Insurance insurance = new Insurance();
        insurance.setPolicyNumber(patient.getInsurance().getPolicyNumber());
        insurance.setCoverageType(patient.getInsurance().getCoverageType());
        insurance.setExpiryDate(patient.getInsurance().getExpiryDate());
        insuranceService.save(insurance);

        patient.setInsurance(insurance);
        patientService.save(patient);
    }
}