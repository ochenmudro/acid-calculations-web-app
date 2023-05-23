package com.example.acidcalculationswebapp.service.impl;

import com.example.acidcalculationswebapp.dto.ExperimentDto;
import com.example.acidcalculationswebapp.entity.Experiment;
import com.example.acidcalculationswebapp.mapper.ExperimentMapper;
import com.example.acidcalculationswebapp.repository.ExperimentRepository;
import com.example.acidcalculationswebapp.service.ExperimentService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ExperimentServiceImpl implements ExperimentService {
    private final ExperimentRepository experimentRepository;
    private final ExperimentMapper experimentMapper;

    public ExperimentServiceImpl(ExperimentRepository experimentRepository, ExperimentMapper experimentMapper) {
        this.experimentRepository = experimentRepository;
        this.experimentMapper = experimentMapper;
    }

    @Override
    public ExperimentDto createExperiment(ExperimentDto experimentDto) {
        Experiment experiment = experimentMapper.toEntity(experimentDto);
        Experiment createdExperiment = experimentRepository.save(experiment);
        return experimentMapper.toDto(createdExperiment);
    }

    @Override
    public ExperimentDto getExperimentById(Long id) {
        Experiment experiment = experimentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Experiment with id=" + id + " not found"));
        return experimentMapper.toDto(experiment);
    }

    @Override
    public List<ExperimentDto> getAllExperiments() {
        List<Experiment> experimentList = experimentRepository.findAll();
        return experimentMapper.toDtoList(experimentList);
    }

    @Override
    public List<Experiment> getAllExperimentsEntities(){
        return experimentRepository.findAll();
    }

    @Override
    public void deleteExperimentById(Long id) {
        if (!experimentRepository.existsById(id)) {
            throw new EntityNotFoundException("Experiment with id=" + id + " not found");
        }
        experimentRepository.deleteById(id);
    }
}
