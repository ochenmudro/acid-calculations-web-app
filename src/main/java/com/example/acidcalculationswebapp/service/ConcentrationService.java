package com.example.acidcalculationswebapp.service;

import com.example.acidcalculationswebapp.dto.ConcentrationDto;
import com.example.acidcalculationswebapp.entity.Concentration;

import java.util.List;

public interface ConcentrationService {
    Concentration createConcentration(ConcentrationDto concentrationDto);

    ConcentrationDto getConcentrationById(Long id);

    List<ConcentrationDto> getAllConcentrations();

    void deleteConcentrationById(Long id);

    List<Concentration> getConcentrationsByChromatogram(Long chromatogramId);
}
