package com.example.acidcalculationswebapp.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ChromatogramDto {
    private Long id;
    private Long acidId;
    private OffsetDateTime date;
    private Double t;
    private String name;
}
