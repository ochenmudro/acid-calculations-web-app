package com.example.acidcalculationswebapp.service;

import com.example.acidcalculationswebapp.dto.ExperimentDto;
import com.example.acidcalculationswebapp.entity.Experiment;

import java.util.List;

public interface ExperimentService {
    ExperimentDto createExperiment(ExperimentDto experimentDto);

    ExperimentDto getExperimentById(Long id);

    List<ExperimentDto> getAllExperiments();

    List<Experiment> getAllExperimentsEntities();

    void deleteExperimentById(Long id);
}
