package com.example.acidcalculationswebapp.controller;

import com.example.acidcalculationswebapp.AcidCalculationsWebAppApplication;
import com.example.acidcalculationswebapp.dto.ExperimentDto;
import com.example.acidcalculationswebapp.service.ExperimentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/experiments/")
public class ExperimentController {
    private static final Logger log = Logger.getLogger(AcidCalculationsWebAppApplication.class.getName());
    private final ExperimentService experimentService;


    public ExperimentController(ExperimentService experimentService) {
        this.experimentService = experimentService;
    }

    @PostMapping
    public ResponseEntity<ExperimentDto> createExperiment(@RequestBody ExperimentDto experimentDto) {
        ExperimentDto createdExperiment = experimentService.createExperiment(experimentDto);
        log.info("Experiment is saved");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExperiment);
    }

    @GetMapping
    public ResponseEntity<ExperimentDto> getExperimentById(@RequestParam Long id) {
        ExperimentDto experimentDto = experimentService.getExperimentById(id);
        if (experimentDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(experimentDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteExperiment(@RequestParam Long id) {
        experimentService.deleteExperimentById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<ExperimentDto>> getAllExperiments() {
        List<ExperimentDto> experimentDtoList = experimentService.getAllExperiments();
        return ResponseEntity.ok(experimentDtoList);
    }
}
