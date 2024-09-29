package mk.ukim.finki.emt.patientmanagment.application;

import mk.ukim.finki.emt.patientmanagment.domain.dto.MedicalRecordUpdateRequest;
import mk.ukim.finki.emt.patientmanagment.service.MedicalRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateMedicalRecord(@RequestBody MedicalRecordUpdateRequest request) {
        medicalRecordService.updateRecord(request.getPatientId(), request.getDiagnosis(), request.getTreatment());
        return ResponseEntity.ok().build();
    }
}
