package mk.ukim.finki.emt.apointmentscheduling.config;

import mk.ukim.finki.emt.sharedkernel.domain.models.PatientDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PatientServiceClient {
    private final RestTemplate restTemplate;

    public PatientServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PatientDTO getPatientById(String patientId) {
        String url = "http://localhost:9093/api/patients/{id}";
        try {
            return restTemplate.getForObject(url, PatientDTO.class, patientId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Patient not found");
        }
    }
}