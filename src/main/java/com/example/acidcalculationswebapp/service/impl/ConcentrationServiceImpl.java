package com.example.acidcalculationswebapp.service.impl;

import com.example.acidcalculationswebapp.dto.ConcentrationDto;
import com.example.acidcalculationswebapp.entity.Concentration;
import com.example.acidcalculationswebapp.mapper.ConcentrationMapper;
import com.example.acidcalculationswebapp.repository.ChromatogramRepository;
import com.example.acidcalculationswebapp.repository.ConcentrationRepository;
import com.example.acidcalculationswebapp.service.ConcentrationService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ConcentrationServiceImpl implements ConcentrationService {
    private final ConcentrationRepository concentrationRepository;
    private final ConcentrationMapper concentrationMapper;
    private final ChromatogramRepository chromatogramRepository;

    public ConcentrationServiceImpl(ConcentrationRepository concentrationRepository, ConcentrationMapper concentrationMapper,
                                    ChromatogramRepository chromatogramRepository) {
        this.concentrationRepository = concentrationRepository;
        this.concentrationMapper = concentrationMapper;
        this.chromatogramRepository = chromatogramRepository;
    }

    @Override
    public Concentration createConcentration(ConcentrationDto concentrationDto) {
        Concentration concentration = concentrationMapper.toEntity(concentrationDto);
        concentration.setChromatogram(chromatogramRepository.getById(concentrationDto.getChromatogramId()));
        return concentrationRepository.save(concentration);
    }

    @Override
    public ConcentrationDto getConcentrationById(Long id) {
        Concentration concentration = concentrationRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Concentration with id=" + id +
                " not found"));
        return concentrationMapper.toDto(concentration);
    }

    @Override
    public List<ConcentrationDto> getAllConcentrations() {
        List<Concentration> concentrationList = concentrationRepository.findAll();
        return concentrationMapper.toDtoList(concentrationList);
    }

    @Override
    public void deleteConcentrationById(Long id) {
        if (!concentrationRepository.existsById(id)) {
            throw new EntityNotFoundException("Concentration with id=" + id + " not found");
        }
        concentrationRepository.deleteById(id);
    }

    @Override
    public List<Concentration> getConcentrationsByChromatogram(Long chromatogramId) {
        return concentrationRepository.findByChromatogramId(chromatogramId);
    }
}
