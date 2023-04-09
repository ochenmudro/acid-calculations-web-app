package com.example.acidcalculationswebapp.service;

import com.example.acidcalculationswebapp.dto.ExperimentDto;

import java.util.List;

public interface ExperimentService {
    ExperimentDto createExperiment(ExperimentDto experimentDto);

    ExperimentDto getExperimentById(Long id);

    List<ExperimentDto> getAllExperiments();

    void deleteExperimentById(Long id);
}
