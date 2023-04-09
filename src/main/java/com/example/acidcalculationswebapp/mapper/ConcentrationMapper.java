package com.example.acidcalculationswebapp.mapper;

import com.example.acidcalculationswebapp.dto.ConcentrationDto;
import com.example.acidcalculationswebapp.entity.Concentration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConcentrationMapper extends BaseMapper<Concentration, ConcentrationDto> {
    @Override
    @Mapping(target = "chromatogram.id", source = "chromatogramId")
    Concentration toEntity(ConcentrationDto dto);

    @Override
    @Mapping(target = "chromatogramId", source = "chromatogram.id")
    ConcentrationDto toDto(Concentration entity);

}
