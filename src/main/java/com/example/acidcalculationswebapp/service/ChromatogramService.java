package com.example.acidcalculationswebapp.service;

import com.example.acidcalculationswebapp.dto.ChromatogramDto;

import java.util.List;

public interface ChromatogramService {
    ChromatogramDto createChromatogram(ChromatogramDto chromatogramDto);

    ChromatogramDto getChromatogramById(Long id);

    List<ChromatogramDto> getAllChromatograms();

    void deleteChromatogramById(Long id);
}
