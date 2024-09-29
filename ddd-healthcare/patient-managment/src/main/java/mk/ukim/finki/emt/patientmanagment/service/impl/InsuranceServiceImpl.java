package mk.ukim.finki.emt.patientmanagment.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.patientmanagment.domain.models.Insurance;
import mk.ukim.finki.emt.patientmanagment.domain.repository.InsuranceRepository;
import mk.ukim.finki.emt.patientmanagment.service.InsuranceService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {
    private final InsuranceRepository insuranceRepository;
    @Override
    public Insurance save(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }
}
