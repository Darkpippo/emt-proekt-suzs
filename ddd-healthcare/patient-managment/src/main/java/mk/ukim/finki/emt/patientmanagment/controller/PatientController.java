package mk.ukim.finki.emt.patientmanagment.controller;

import mk.ukim.finki.emt.patientmanagment.domain.models.Insurance;
import mk.ukim.finki.emt.patientmanagment.domain.models.MedicalRecord;
import mk.ukim.finki.emt.patientmanagment.domain.models.Patient;
import mk.ukim.finki.emt.patientmanagment.service.PatientService;
import mk.ukim.finki.emt.sharedkernel.domain.base.ContactInformation;
import mk.ukim.finki.emt.sharedkernel.domain.models.PatientDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public PatientDTO getPatientById(@PathVariable Long id) {
        Patient patient = patientService.findPatientById(id);
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(String.valueOf(patient.getId()));
        patientDTO.setContactInformation(patient.getContactInformation());
        patientDTO.setFirstName(patient.getName());
        return patientDTO;
    }

    @GetMapping()
    public List<Patient> findAll(){
        return patientService.findAll();
    }

    @PostMapping()
    public Patient save(@RequestBody Patient patient) {
        if (patient.getMedicalRecord() != null) {
            patient.getMedicalRecord().setPatient(patient);
        }
        return patientService.save(patient);
    }
}