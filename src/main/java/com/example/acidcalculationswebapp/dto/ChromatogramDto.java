package com.example.acidcalculationswebapp.dto;

import com.example.acidcalculationswebapp.entity.Concentration;
import com.example.acidcalculationswebapp.entity.PeakArea;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class ChromatogramDto {
    private Long id;
    private Long acidId;
    private OffsetDateTime date;
    private Double t;
    private String name;
    private List<Double> concentrationValues;
    private List<Double> PeakAreaValues;
}
