package com.example.acidcalculationswebapp.service;

import com.example.acidcalculationswebapp.dto.ConcentrationDto;

import java.util.List;

public interface ConcentrationService {
    ConcentrationDto createConcentration(ConcentrationDto concentrationDto);

    ConcentrationDto getConcentrationById(Long id);

    List<ConcentrationDto> getAllConcentrations();

    void deleteConcentrationById(Long id);
}
