package mk.ukim.finki.emt.patientmanagment.domain.dto;

public class MedicalRecordUpdateRequest {
    private Long patientId;
    private String diagnosis;
    private String treatment;
    public MedicalRecordUpdateRequest(Long patientId, String diagnosis, String treatment) {
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public Long getPatientId() {
        return patientId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }
}