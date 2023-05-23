package com.example.acidcalculationswebapp.controller;

import com.example.acidcalculationswebapp.AcidCalculationsWebAppApplication;
import com.example.acidcalculationswebapp.dto.PeakAreaDto;
import com.example.acidcalculationswebapp.service.PeakAreaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/peak-areas/")
public class PeakAreaController {
    private static final Logger log = Logger.getLogger(AcidCalculationsWebAppApplication.class.getName());
    private final PeakAreaService peakAreaService;

    public PeakAreaController(PeakAreaService peakAreaService) {
        this.peakAreaService = peakAreaService;
    }

//    @PostMapping
//    public ResponseEntity<PeakAreaDto> createPeakArea(@RequestBody PeakAreaDto peakAreaDto) {
//        PeakAreaDto createdPeakArea = peakAreaService.createPeakArea(peakAreaDto);
//        log.info("Peak Area is saved");
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdPeakArea);
//    }

    @GetMapping
    public ResponseEntity<PeakAreaDto> getPeakAreaById(@RequestParam Long id) {
        PeakAreaDto peakAreaDto = peakAreaService.getPeakAreaById(id);
        if (peakAreaDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(peakAreaDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePeakArea(@RequestParam Long id) {
        peakAreaService.deletePeakAreaById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<PeakAreaDto>> getAllPeakAreas() {
        List<PeakAreaDto> peakAreaDtoList = peakAreaService.getAllPeakAreas();
        return ResponseEntity.ok(peakAreaDtoList);
    }
}
