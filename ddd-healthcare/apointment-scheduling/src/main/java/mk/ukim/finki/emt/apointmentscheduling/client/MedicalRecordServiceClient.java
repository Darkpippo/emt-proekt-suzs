package mk.ukim.finki.emt.apointmentscheduling.client;

import mk.ukim.finki.emt.patientmanagment.domain.dto.MedicalRecordUpdateRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MedicalRecordServiceClient {

    private final RestTemplate restTemplate;

    public MedicalRecordServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void updateMedicalRecord(Long patientId, String diagnosis, String treatment) {
        MedicalRecordUpdateRequest request = new MedicalRecordUpdateRequest(patientId, diagnosis, treatment);
        restTemplate.put("http://localhost:9093/api/medical-records/update", request);
    }
}