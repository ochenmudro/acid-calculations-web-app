package com.example.acidcalculationswebapp.service.impl;

import com.example.acidcalculationswebapp.dto.AcidDto;
import com.example.acidcalculationswebapp.mapper.AcidMapper;
import com.example.acidcalculationswebapp.entity.Acid;
import com.example.acidcalculationswebapp.repository.AcidRepository;
import com.example.acidcalculationswebapp.service.AcidService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AcidServiceImpl implements AcidService {
    private final AcidRepository acidRepository;
    private final AcidMapper acidMapper;

    public AcidServiceImpl(AcidRepository acidRepository, AcidMapper acidMapper) {
        this.acidRepository = acidRepository;
        this.acidMapper = acidMapper;
    }

    public AcidDto createAcid(AcidDto acidDto) {
        Acid acid = acidMapper.toEntity(acidDto);
        Acid createdAcid = acidRepository.save(acid);
        return acidMapper.toDto(createdAcid);
    }


    @Override
    public AcidDto getAcidById(Long id) {
        Acid acid = acidRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Acid with id=" + id +
                " not found"));
        return acidMapper.toDto(acid);
    }

    @Override
    public List<AcidDto> getAllAcids() {
        List<Acid> acidList = acidRepository.findAll();
        return acidMapper.toDtoList(acidList);
    }

    @Override
    public void deleteAcidById(Long id) {
        if (!acidRepository.existsById(id)) {
            throw new EntityNotFoundException("Acid with id=" + id + " not found");
        }
        acidRepository.deleteById(id);
    }

}

