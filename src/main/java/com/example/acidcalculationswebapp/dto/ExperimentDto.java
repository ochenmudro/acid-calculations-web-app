package com.example.acidcalculationswebapp.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ExperimentDto {
    private Long id;
    private OffsetDateTime date;
    private String name;
    private double mSt;
    private double v;
}
