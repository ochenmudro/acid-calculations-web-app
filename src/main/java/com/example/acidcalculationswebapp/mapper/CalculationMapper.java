package com.example.acidcalculationswebapp.mapper;

import com.example.acidcalculationswebapp.dto.CalculationDto;
import com.example.acidcalculationswebapp.entity.Calculation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CalculationMapper extends BaseMapper<Calculation, CalculationDto> {

    @Override
    @Mapping(target = "acid.id", source = "acidId")
    @Mapping(target = "experiment.id", source = "experimentId")
    Calculation toEntity(CalculationDto dto);

    @Override
    @Mapping(target = "acidId", source = "acid.id")
    @Mapping(target = "experimentId", source = "experiment.id")
    CalculationDto toDto(Calculation entity);
}
