package mk.ukim.finki.emt.sharedkernel.domain.base;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor
@Getter
public class ContactInformation {

    private String email;
    private String phoneNumber;
    private String address;

    public ContactInformation(String email, String phoneNumber, String address) {
        validateEmail(email);
        validatePhoneNumber(phoneNumber);
        validateAddress(address);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    private void validateEmail(String email) {
        if (!Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (!Pattern.compile("^\\+?[0-9. ()-]{7,25}$").matcher(phoneNumber).matches()) {
            throw new IllegalArgumentException("Invalid phone number.");
        }
    }

    private void validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty.");
        }
    }
}