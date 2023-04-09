package com.example.acidcalculationswebapp.mapper;

import com.example.acidcalculationswebapp.dto.ExperimentDto;
import com.example.acidcalculationswebapp.entity.Experiment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExperimentMapper extends BaseMapper<Experiment, ExperimentDto> {

}
