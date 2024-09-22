package mk.ukim.finki.emt.medicalrecordmanagment.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.base.PatientId;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecord {
    @Id
    @Column(insertable=false, updatable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diagnoses;
    private String treatments;
    private String medications;

    @ElementCollection
    private List<String> testResults;

    @Embedded
    private PatientId patient;
}