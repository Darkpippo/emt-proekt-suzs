package mk.ukim.finki.emt.apointmentscheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"mk.ukim.finki.emt.apointmentscheduling", "mk.ukim.finki.emt.patientmanagment"})
public class AppointmentSchedulingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentSchedulingApplication.class, args);
    }

}
