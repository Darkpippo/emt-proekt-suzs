package mk.ukim.finki.emt.patientmanagment.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.base.ContactInformation;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private ContactInformation contactInformation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_record_id", referencedColumnName = "id")
    private MedicalRecord medicalRecord;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "insurance_id", referencedColumnName = "id")
    private Insurance insurance;
}