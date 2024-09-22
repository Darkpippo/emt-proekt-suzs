package mk.ukim.finki.emt.sharedkernel.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.base.ContactInformation;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private Long id;
    private String name;
    private ContactInformation contactInformation;
}