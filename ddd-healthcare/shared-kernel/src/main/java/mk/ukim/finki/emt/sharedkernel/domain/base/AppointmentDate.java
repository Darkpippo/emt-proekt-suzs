package mk.ukim.finki.emt.sharedkernel.domain.base;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Embeddable
public class AppointmentDate {
    private LocalDateTime appointmentDateTime;

    public AppointmentDate(LocalDateTime appointmentDateTime) {
        if (!isFutureDate(appointmentDateTime)) {
            throw new IllegalArgumentException("Appointment date must be in the future");
        }
        this.appointmentDateTime = appointmentDateTime;
    }

    public AppointmentDate() {
    }

    public LocalDateTime getDateTime() {
        return appointmentDateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.appointmentDateTime = dateTime;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    private boolean isFutureDate(LocalDateTime dateTime) {
        return dateTime.isAfter(LocalDateTime.now());
    }

    public boolean isWeekend() {
        int dayOfWeek = appointmentDateTime.getDayOfWeek().getValue();
        return dayOfWeek == 6 || dayOfWeek == 7;
    }

    public long calculateTimeDifference(LocalDateTime anotherDateTime) {
        return ChronoUnit.MINUTES.between(appointmentDateTime, anotherDateTime);
    }
}
