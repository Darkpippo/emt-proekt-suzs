package mk.ukim.finki.emt.sharedkernel.domain.models;


import mk.ukim.finki.emt.sharedkernel.domain.base.ContactInformation;

public class PatientDTO {
    private String id;
    private String firstName;
    private String lastName;

    private ContactInformation contactInformation;

    private String diagnosis;
    private String treatment;
    private String medications;
    private String insurancePolicyNumber;
    private String insurancePlan;
    private String insuranceExpirationDate;

    public PatientDTO(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getInsurancePolicyNumber() {
        return insurancePolicyNumber;
    }

    public void setInsurancePolicyNumber(String insurancePolicyNumber) {
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    public String getInsurancePlan() {
        return insurancePlan;
    }

    public void setInsurancePlan(String insurancePlan) {
        this.insurancePlan = insurancePlan;
    }

    public String getInsuranceExpirationDate() {
        return insuranceExpirationDate;
    }

    public void setInsuranceExpirationDate(String insuranceExpirationDate) {
        this.insuranceExpirationDate = insuranceExpirationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public PatientDTO() {
    }

    public PatientDTO(String id, String firstName, String lastName, ContactInformation contactInformation, String diagnosis, String treatment, String medications, String insurancePolicyNumber, String insurancePlan, String insuranceExpirationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInformation = contactInformation;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.medications = medications;
        this.insurancePolicyNumber = insurancePolicyNumber;
        this.insurancePlan = insurancePlan;
        this.insuranceExpirationDate = insuranceExpirationDate;
    }
}
