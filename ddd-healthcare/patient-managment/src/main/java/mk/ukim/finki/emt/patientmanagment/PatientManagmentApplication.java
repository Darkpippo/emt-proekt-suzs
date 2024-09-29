package mk.ukim.finki.emt.patientmanagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"mk.ukim.finki.emt.patientmanagment", "mk.ukim.finki.emt.sharedkernel"})
public class PatientManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientManagmentApplication.class, args);
    }

}
