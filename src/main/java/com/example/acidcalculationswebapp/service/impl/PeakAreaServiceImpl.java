package com.example.acidcalculationswebapp.service.impl;

import com.example.acidcalculationswebapp.dto.PeakAreaDto;
import com.example.acidcalculationswebapp.entity.PeakArea;
import com.example.acidcalculationswebapp.mapper.PeakAreaMapper;
import com.example.acidcalculationswebapp.repository.PeakAreaRepository;
import com.example.acidcalculationswebapp.service.PeakAreaService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PeakAreaServiceImpl implements PeakAreaService {
    private final PeakAreaRepository peakAreaRepository;
    private final PeakAreaMapper peakAreaMapper;

    public PeakAreaServiceImpl(PeakAreaRepository peakAreaRepository, PeakAreaMapper peakAreaMapper) {
        this.peakAreaRepository = peakAreaRepository;
        this.peakAreaMapper = peakAreaMapper;
    }

    public PeakAreaDto createPeakArea(PeakAreaDto peakAreaDto) {
        PeakArea peakArea = peakAreaMapper.toEntity(peakAreaDto);
        PeakArea createdPeakArea = peakAreaRepository.save(peakArea);
        return peakAreaMapper.toDto(createdPeakArea);
    }


    @Override
    public PeakAreaDto getPeakAreaById(Long id) {
        PeakArea peakArea = peakAreaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("PeakArea with id=" + id +
                " not found"));
        return peakAreaMapper.toDto(peakArea);
    }

    @Override
    public List<PeakAreaDto> getAllPeakAreas() {
        List<PeakArea> peakAreaList = peakAreaRepository.findAll();
        return peakAreaMapper.toDtoList(peakAreaList);
    }

    @Override
    public void deletePeakAreaById(Long id) {
        if (!peakAreaRepository.existsById(id)) {
            throw new EntityNotFoundException("PeakArea with id=" + id + " not found");
        }
        peakAreaRepository.deleteById(id);
    }
}
