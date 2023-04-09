package com.example.acidcalculationswebapp.service;

import com.example.acidcalculationswebapp.dto.AcidDto;

import java.util.List;

public interface AcidService {
    AcidDto createAcid(AcidDto acidDto);
    AcidDto getAcidById(Long id);
    List<AcidDto> getAllAcids();
    void deleteAcidById(Long id);
}
