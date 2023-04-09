package com.example.acidcalculationswebapp.service.impl;

import com.example.acidcalculationswebapp.dto.ChromatogramDto;
import com.example.acidcalculationswebapp.entity.Chromatogram;
import com.example.acidcalculationswebapp.mapper.ChromatogramMapper;
import com.example.acidcalculationswebapp.repository.ChromatogramRepository;
import com.example.acidcalculationswebapp.service.ChromatogramService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ChromatogramServiceImpl implements ChromatogramService {
    private final ChromatogramRepository chromatogramRepository;
    private final ChromatogramMapper chromatogramMapper;

    public ChromatogramServiceImpl(ChromatogramRepository chromatogramRepository, ChromatogramMapper chromatogramMapper) {
        this.chromatogramRepository = chromatogramRepository;
        this.chromatogramMapper = chromatogramMapper;
    }

    public ChromatogramDto createChromatogram(ChromatogramDto chromatogramDto) {
        Chromatogram chromatogram = chromatogramMapper.toEntity(chromatogramDto);
        Chromatogram createdChromatogram = chromatogramRepository.save(chromatogram);
        return chromatogramMapper.toDto(createdChromatogram);
    }


    @Override
    public ChromatogramDto getChromatogramById(Long id) {
        Chromatogram chromatogram = chromatogramRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Chromatogram with id=" + id +
                " not found"));
        return chromatogramMapper.toDto(chromatogram);
    }

    @Override
    public List<ChromatogramDto> getAllChromatograms() {
        List<Chromatogram> chromatogramList = chromatogramRepository.findAll();
        return chromatogramMapper.toDtoList(chromatogramList);
    }

    @Override
    public void deleteChromatogramById(Long id) {
        if (!chromatogramRepository.existsById(id)) {
            throw new EntityNotFoundException("Chromatogram with id=" + id + " not found");
        }
        chromatogramRepository.deleteById(id);
    }
}
