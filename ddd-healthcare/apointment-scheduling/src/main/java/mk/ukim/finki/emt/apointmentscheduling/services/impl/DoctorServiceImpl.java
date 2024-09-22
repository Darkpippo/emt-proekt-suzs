package mk.ukim.finki.emt.apointmentscheduling.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.apointmentscheduling.domain.models.Doctor;
import mk.ukim.finki.emt.apointmentscheduling.domain.repository.DoctorRepository;
import mk.ukim.finki.emt.apointmentscheduling.services.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Override
    public boolean doctorExists(Long doctorId) {
        return doctorRepository.existsById(doctorId);
    }

    @Override
    public Doctor findDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId).orElseThrow(() ->
                new IllegalArgumentException("Doctor with id " + doctorId + " not found"));
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor findById(Long doctorId) {
        return doctorRepository.findById(doctorId).orElseThrow(() -> new IllegalArgumentException("Doctor with id " + doctorId + " not found"));
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }
}
