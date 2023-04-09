package com.example.acidcalculationswebapp.dto;

import lombok.Data;

@Data
public class CalculationDto {

    private Long id;
    private double concentration;
    private double absK;
    private double s;
    private double sSt;
    private Long acidId;
    private Long experimentId;
}
