package com.example.acidcalculationswebapp.dto;

import lombok.Data;

@Data
public class PeakAreaDto {
    private Long id;
    private Double value;
    private Long chromatogramId;
}
