package com.example.acidcalculationswebapp.controller;

import com.example.acidcalculationswebapp.AcidCalculationsWebAppApplication;
import com.example.acidcalculationswebapp.dto.AcidDto;
import com.example.acidcalculationswebapp.mapper.AcidMapper;
import com.example.acidcalculationswebapp.service.AcidService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/acids/")
public class AcidController {
    private static final Logger log = Logger.getLogger(AcidCalculationsWebAppApplication.class.getName());
    private final AcidService acidService;
    private final AcidMapper acidMapper;

    public AcidController(AcidService acidService, AcidMapper acidMapper) {
        this.acidService = acidService;
        this.acidMapper = acidMapper;
    }

    @PostMapping
    public ResponseEntity<AcidDto> createAcid(@RequestBody AcidDto acidDto) {
        AcidDto createdAcid = acidService.createAcid(acidDto);
        log.info("Client is saved");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAcid);
    }

    @GetMapping
    public ResponseEntity<AcidDto> getAcidById(@RequestParam Long id) {
        AcidDto acidDto = acidService.getAcidById(id);
        if (acidDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(acidDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAcid(@RequestParam Long id) {
        acidService.deleteAcidById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<AcidDto>> getAllAcids() {
        List<AcidDto> acidDtoList = acidService.getAllAcids();
        return ResponseEntity.ok(acidDtoList);
    }

}

