package com.example.acidcalculationswebapp.service.impl;

import com.example.acidcalculationswebapp.dto.ChromatogramDto;
import com.example.acidcalculationswebapp.entity.Chromatogram;
import com.example.acidcalculationswebapp.mapper.ChromatogramMapper;
import com.example.acidcalculationswebapp.repository.AcidRepository;
import com.example.acidcalculationswebapp.repository.ChromatogramRepository;
import com.example.acidcalculationswebapp.service.ChromatogramService;
import com.example.acidcalculationswebapp.service.ConcentrationService;
import com.example.acidcalculationswebapp.service.PeakAreaService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ChromatogramServiceImpl implements ChromatogramService {
    private final ChromatogramRepository chromatogramRepository;
    private final ChromatogramMapper chromatogramMapper;
    private final ConcentrationService concentrationService;
    private final PeakAreaService peakAreaService;
    private final AcidRepository acidRepository;

    public ChromatogramServiceImpl(ChromatogramRepository chromatogramRepository, ChromatogramMapper chromatogramMapper, ConcentrationService concentrationService, PeakAreaService peakAreaService, AcidRepository acidRepository) {
        this.chromatogramRepository = chromatogramRepository;
        this.chromatogramMapper = chromatogramMapper;
        this.concentrationService = concentrationService;
        this.peakAreaService = peakAreaService;
        this.acidRepository = acidRepository;
    }

    @Override
    public Chromatogram createChromatogram(ChromatogramDto chromatogramDto) {
        Chromatogram chromatogram = chromatogramMapper.toEntity(chromatogramDto);
        chromatogram.setAcid(acidRepository.getById(chromatogramDto.getAcidId()));
        chromatogram.setConcentrations(concentrationService.getConcentrationsByChromatogram(chromatogram.getId()));
        chromatogram.setPeakAreas(peakAreaService.getPeakAreasByChromatogram(chromatogram.getId()));
        chromatogramRepository.save(chromatogram);
        return chromatogram;
    }


    @Override
    public Chromatogram getChromatogramById(Long id) {
        return chromatogramRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Chromatogram with id=" + id +
                " not found"));
    }

    @Override
    public List<Chromatogram> getAllChromatograms() {
        return chromatogramRepository.findAll();
    }

    @Override
    public void deleteChromatogramById(Long id) {
        if (!chromatogramRepository.existsById(id)) {
            throw new EntityNotFoundException("Chromatogram with id=" + id + " not found");
        }
        chromatogramRepository.deleteById(id);
    }

    @Override
    public Chromatogram updateChromatogram(Chromatogram chromatogram) {
        Chromatogram oldChromatogram = chromatogramRepository.getById(chromatogram.getId());
        if (!oldChromatogram.getConcentrations().equals(chromatogram.getConcentrations())) {
            chromatogram.setConcentrations(concentrationService.getConcentrationsByChromatogram(chromatogram.getId()));
        }
        if (!oldChromatogram.getPeakAreas().equals(chromatogram.getPeakAreas())) {
            chromatogram.setPeakAreas(peakAreaService.getPeakAreasByChromatogram(chromatogram.getId()));
        }

            chromatogram.setChartImage(chromatogram.getChartImage());

        return chromatogramRepository.save(chromatogram);
    }
}
