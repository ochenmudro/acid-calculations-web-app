package com.example.acidcalculationswebapp.service;

import com.example.acidcalculationswebapp.dto.PeakAreaDto;

import java.util.List;

public interface PeakAreaService {
    PeakAreaDto createPeakArea(PeakAreaDto peakAreaDto);

    PeakAreaDto getPeakAreaById(Long id);

    List<PeakAreaDto> getAllPeakAreas();

    void deletePeakAreaById(Long id);
}
