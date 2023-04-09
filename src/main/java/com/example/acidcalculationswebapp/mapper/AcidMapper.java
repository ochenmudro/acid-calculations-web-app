package com.example.acidcalculationswebapp.mapper;

import com.example.acidcalculationswebapp.dto.AcidDto;
import com.example.acidcalculationswebapp.entity.Acid;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AcidMapper extends BaseMapper<Acid, AcidDto> {

}




