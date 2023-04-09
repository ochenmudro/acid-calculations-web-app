package com.example.acidcalculationswebapp.service;

import com.example.acidcalculationswebapp.dto.CalculationDto;
import com.example.acidcalculationswebapp.entity.Calculation;

import java.util.List;

public interface CalculationService {
    CalculationDto createCalculation(CalculationDto calculationDto);

    Calculation getCalculationById(Long id);

    List<CalculationDto> getAllCalculations();

    void deleteCalculationById(Long id);

    Calculation getCalculationByParam(Long expId, Long acidId);

    double calculateConcentration(Calculation calc);

    double calculateIsoC4Concentration(Calculation iso);
}
