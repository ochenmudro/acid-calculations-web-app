package com.example.acidcalculationswebapp.dto;

import lombok.Data;

@Data
public class ConcentrationDto {
    private Long id;
    private Double value;
    private Long chromatogramId;
}
