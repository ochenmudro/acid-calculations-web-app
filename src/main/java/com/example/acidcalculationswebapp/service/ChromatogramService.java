package com.example.acidcalculationswebapp.service;

import com.example.acidcalculationswebapp.dto.ChromatogramDto;
import com.example.acidcalculationswebapp.entity.Chromatogram;

import java.util.List;

public interface ChromatogramService {
    Chromatogram createChromatogram(ChromatogramDto chromatogramDto);

    Chromatogram getChromatogramById(Long id);

    List<Chromatogram> getAllChromatograms();

    void deleteChromatogramById(Long id);

    Chromatogram updateChromatogram(Chromatogram chromatogram);
}
