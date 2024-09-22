package mk.ukim.finki.emt.apointmentscheduling.services;

import mk.ukim.finki.emt.apointmentscheduling.domain.models.Doctor;

import java.util.List;

public interface DoctorService {
    boolean doctorExists(Long doctorId);
    Doctor findDoctorById(Long doctorId);
    Doctor save(Doctor doctor);
    Doctor findById(Long doctorId);
    List<Doctor> findAll();
}
