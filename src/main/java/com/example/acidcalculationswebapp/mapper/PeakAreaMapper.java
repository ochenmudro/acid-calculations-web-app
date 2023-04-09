package com.example.acidcalculationswebapp.mapper;

import com.example.acidcalculationswebapp.dto.PeakAreaDto;
import com.example.acidcalculationswebapp.entity.PeakArea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PeakAreaMapper extends BaseMapper<PeakArea, PeakAreaDto> {
    @Override
    @Mapping(target = "chromatogram.id", source = "chromatogramId")
    PeakArea toEntity(PeakAreaDto dto);

    @Override
    @Mapping(target = "chromatogramId", source = "chromatogram.id")
    PeakAreaDto toDto(PeakArea entity);
}
