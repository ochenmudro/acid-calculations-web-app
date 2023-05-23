package com.example.acidcalculationswebapp.service;

import com.example.acidcalculationswebapp.dto.AcidDto;
import com.example.acidcalculationswebapp.entity.Acid;

import java.util.List;

public interface AcidService {
    AcidDto createAcid(AcidDto acidDto);
    AcidDto getAcidById(Long id);
    List<AcidDto> getAllAcids();
    void deleteAcidById(Long id);

    List<AcidDto> sortAcidsByName(List<AcidDto> acids);
}
