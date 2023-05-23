package com.example.acidcalculationswebapp.service;

import com.example.acidcalculationswebapp.dto.CalculationDto;
import com.example.acidcalculationswebapp.entity.Calculation;

import java.util.List;

public interface CalculationService {
    Calculation createCalculation(CalculationDto calculationDto);

    CalculationDto updateCalculation(Calculation calculation);

    Calculation getCalculationById(Long id);

    List<CalculationDto> getAllCalculations();

    List<Calculation> getAllCalculationsEntities();

    void deleteCalculationById(Long id);

    Calculation getCalculationByParam(Long expId, Long acidId);

    double calculateConcentration(Calculation calc);

    double calculateIsoC4Concentration(Calculation iso);
}
