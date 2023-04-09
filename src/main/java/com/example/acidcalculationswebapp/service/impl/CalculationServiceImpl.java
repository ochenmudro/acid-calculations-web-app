package com.example.acidcalculationswebapp.service.impl;

import com.example.acidcalculationswebapp.dto.CalculationDto;
import com.example.acidcalculationswebapp.entity.Calculation;
import com.example.acidcalculationswebapp.mapper.CalculationMapper;
import com.example.acidcalculationswebapp.repository.AcidRepository;
import com.example.acidcalculationswebapp.repository.CalculationRepository;
import com.example.acidcalculationswebapp.repository.ExperimentRepository;
import com.example.acidcalculationswebapp.service.CalculationService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CalculationServiceImpl implements CalculationService {
    private final CalculationRepository calculationRepository;
    private final CalculationMapper calculationMapper;
    private final ExperimentRepository experimentRepository;
    private final AcidRepository acidRepository;

    public CalculationServiceImpl(CalculationRepository calculationRepository, CalculationMapper calculationMapper,
                                  ExperimentRepository experimentRepository, AcidRepository acidRepository) {
        this.calculationRepository = calculationRepository;
        this.calculationMapper = calculationMapper;
        this.experimentRepository = experimentRepository;
        this.acidRepository = acidRepository;
    }

    @Override
    public CalculationDto createCalculation(CalculationDto calculationDto) {
        Calculation calculation = calculationMapper.toEntity(calculationDto);
        calculation.setAcid(acidRepository.getById(calculationDto.getAcidId()));
        calculation.setExperiment(experimentRepository.getById(calculationDto.getExperimentId()));
        Calculation createdCalculation = calculationRepository.save(calculation);
        return calculationMapper.toDto(createdCalculation);
    }

    @Override
    public Calculation getCalculationById(Long id) {
        return calculationRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Calculation with id=" + id + " not found"));
    }

    @Override
    public List<CalculationDto> getAllCalculations() {
        List<Calculation> calculationList = calculationRepository.findAll();
        return calculationMapper.toDtoList(calculationList);
    }

    @Override
    public void deleteCalculationById(Long id) {
        if (!calculationRepository.existsById(id)) {
            throw new EntityNotFoundException("Calculation with id=" + id + " not found");
        }
        calculationRepository.deleteById(id);
    }

    @Override
    public Calculation getCalculationByParam(Long expId, Long acidId){
        return calculationRepository.findByAcidIdAndExperimentId(acidId,expId);
    }

    public double calculateConcentration(Calculation calc) {
        Calculation iso = calculationRepository.findByAcidIdAndExperimentId(7L, calc.getExperiment().getId());
        return calc.getExperiment().getMSt() / (calc.getExperiment().getV() * (iso.getSSt() * iso.getAbsK() /
                (calc.getSSt() * calc.getAbsK()) - iso.getS() * iso.getAbsK() / (calc.getS() * calc.getAbsK())));
    }

    public double calculateIsoC4Concentration(Calculation iso) {
        return iso.getExperiment().getMSt() / ((iso.getSSt() / iso.getS() - 1) * iso.getExperiment().getV());
    }
}
