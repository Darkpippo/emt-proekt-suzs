package mk.ukim.finki.emt.sharedkernel.domain.events;

import lombok.Getter;

import java.io.Serializable;

public class AppointmentCreatedEvent implements Serializable {
    private final Long appointmentId;
    private final Long patientId;
    private final Long doctorId;
    private final String status;

    public AppointmentCreatedEvent(Long appointmentId, Long patientId, Long doctorId, String status) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.status = status;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public String getStatus() {
        return status;
    }
}
