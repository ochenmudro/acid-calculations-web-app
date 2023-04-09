package com.example.acidcalculationswebapp.controller;

import com.example.acidcalculationswebapp.AcidCalculationsWebAppApplication;
import com.example.acidcalculationswebapp.dto.ChromatogramDto;
import com.example.acidcalculationswebapp.entity.Chromatogram;
import com.example.acidcalculationswebapp.mapper.ChromatogramMapper;
import com.example.acidcalculationswebapp.service.ChromatogramService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chromatograms/")
public class ChromatogramController {
    private static final Logger log = Logger.getLogger(AcidCalculationsWebAppApplication.class.getName());
    private final ChromatogramService chromatogramService;
    private final ChromatogramMapper chromatogramMapper;

    public ChromatogramController(ChromatogramService chromatogramService, ChromatogramMapper chromatogramMapper) {
        this.chromatogramService = chromatogramService;
        this.chromatogramMapper = chromatogramMapper;
    }

    @PostMapping
    public ResponseEntity<ChromatogramDto> createChromatogram(@RequestBody ChromatogramDto chromatogramDto) {
        ChromatogramDto createdChromatogram = chromatogramService.createChromatogram(chromatogramDto);
        log.info("Chromatogram is saved");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChromatogram);
    }

    @GetMapping
    public ResponseEntity<ChromatogramDto> getChromatogramById(@RequestParam Long id) {
        ChromatogramDto chromatogramDto = chromatogramService.getChromatogramById(id);
        if (chromatogramDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(chromatogramDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteChromatogram(@RequestParam Long id) {
        chromatogramService.deleteChromatogramById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<Chromatogram>> getAllChromatograms() {
        List<ChromatogramDto> chromatogramDtoList = chromatogramService.getAllChromatograms();
        List<Chromatogram> chromatogramList = chromatogramDtoList.stream().map(chromatogramMapper::toEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(chromatogramList);
    }
}
