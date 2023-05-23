package com.example.acidcalculationswebapp.controller;

import com.example.acidcalculationswebapp.AcidCalculationsWebAppApplication;
import com.example.acidcalculationswebapp.dto.ConcentrationDto;
import com.example.acidcalculationswebapp.mapper.ConcentrationMapper;
import com.example.acidcalculationswebapp.service.ConcentrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/concentrations/")
public class ConcentrationController {
    private static final Logger log = Logger.getLogger(AcidCalculationsWebAppApplication.class.getName());
    private final ConcentrationService concentrationService;

    public ConcentrationController(ConcentrationService concentrationService, ConcentrationMapper concentrationMapper) {
        this.concentrationService = concentrationService;
    }

//    @PostMapping
//    public ResponseEntity<ConcentrationDto> createConcentration(@RequestBody ConcentrationDto concentrationDto) {
//        ConcentrationDto createdConcentration = concentrationService.createConcentration(concentrationDto);
//        log.info("Concentration is saved");
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdConcentration);
//    }

    @GetMapping
    public ResponseEntity<ConcentrationDto> getConcentrationById(@RequestParam Long id) {
        ConcentrationDto concentrationDto = concentrationService.getConcentrationById(id);
        if (concentrationDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(concentrationDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteConcentration(@RequestParam Long id) {
        concentrationService.deleteConcentrationById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<ConcentrationDto>> getAllConcentrations() {
        List<ConcentrationDto> concentrationDtoList = concentrationService.getAllConcentrations();
        return ResponseEntity.ok(concentrationDtoList);
    }
}
