package com.example.acidcalculationswebapp.mapper;

import com.example.acidcalculationswebapp.dto.ChromatogramDto;
import com.example.acidcalculationswebapp.entity.Chromatogram;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChromatogramMapper extends BaseMapper<Chromatogram, ChromatogramDto> {
    @Override
    @Mapping(target = "acid.id", source = "acidId")
    Chromatogram toEntity(ChromatogramDto dto);

    @Override
    @Mapping(target = "acidId", source = "acid.id")
    ChromatogramDto toDto(Chromatogram entity);

}
