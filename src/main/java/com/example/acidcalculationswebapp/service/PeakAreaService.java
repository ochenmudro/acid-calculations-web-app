package com.example.acidcalculationswebapp.service;

import com.example.acidcalculationswebapp.dto.PeakAreaDto;
import com.example.acidcalculationswebapp.entity.PeakArea;

import java.util.List;

public interface PeakAreaService {
    PeakArea createPeakArea(PeakAreaDto peakAreaDto);

    PeakAreaDto getPeakAreaById(Long id);

    List<PeakAreaDto> getAllPeakAreas();

    void deletePeakAreaById(Long id);

    List<PeakArea> getPeakAreasByChromatogram(Long chromatogramId);
}
