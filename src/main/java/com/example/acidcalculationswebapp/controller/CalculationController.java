package com.example.acidcalculationswebapp.controller;

import com.example.acidcalculationswebapp.AcidCalculationsWebAppApplication;
import com.example.acidcalculationswebapp.dto.CalculationDto;
import com.example.acidcalculationswebapp.entity.Calculation;
import com.example.acidcalculationswebapp.mapper.CalculationMapper;
import com.example.acidcalculationswebapp.service.CalculationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/calculations/")
public class CalculationController {
    private static final Logger log = Logger.getLogger(AcidCalculationsWebAppApplication.class.getName());
    private final CalculationService calculationService;
    private final CalculationMapper calculationMapper;

    public CalculationController(CalculationService calculationService, CalculationMapper calculationMapper) {
        this.calculationService = calculationService;
        this.calculationMapper = calculationMapper;
    }

    @PostMapping
    public ResponseEntity<CalculationDto> createCalculation(@RequestBody CalculationDto calculationDto) {
        CalculationDto createdCalculation = calculationService.createCalculation(calculationDto);
        log.info("Calculation is saved");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCalculation);
    }

    @GetMapping
    public ResponseEntity<CalculationDto> getCalculationById(@RequestParam Long id) {
        CalculationDto calculationDto = calculationMapper.toDto(calculationService.getCalculationById(id));
        return ResponseEntity.ok(calculationDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCalculation(@RequestParam Long id) {
        calculationService.deleteCalculationById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<Calculation>> getAllCalculations() {
        List<CalculationDto> calculationDtoList = calculationService.getAllCalculations();
        List<Calculation> calculationList = calculationDtoList.stream().map(calculationMapper::toEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(calculationList);
    }

    @PostMapping("calc/")
    public ResponseEntity<CalculationDto> concentrationCalculation(@RequestParam Long expId, @RequestParam Long acidId) {
        Calculation calculation = calculationService.getCalculationByParam(expId, acidId);
        if (acidId == 7) {
            calculation.setConcentration(calculationService.calculateIsoC4Concentration(calculation));
        } else if (calculation.getExperiment().getCalculations().stream().anyMatch(c -> c.getAcid().getId() == 7)) {
            calculation.setConcentration(calculationService.calculateConcentration(calculation));
        } else {
            throw new EntityNotFoundException("Calculation iso-C4 not found");
        }
        return ResponseEntity.ok(calculationMapper.toDto(calculation));
    }

}
