package com.example.acidcalculationswebapp.controller;

import com.example.acidcalculationswebapp.AcidCalculationsWebAppApplication;
import com.example.acidcalculationswebapp.dto.ExperimentDto;
import com.example.acidcalculationswebapp.service.ExperimentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping
public class ExperimentController {
    private static final Logger log = Logger.getLogger(AcidCalculationsWebAppApplication.class.getName());
    private final ExperimentService experimentService;


    public ExperimentController(ExperimentService experimentService) {
        this.experimentService = experimentService;
    }

//    @GetMapping("calculations/create_experiment")
//    public String createExperimentForm(Model model){
//        ExperimentDto experimentDto = new ExperimentDto();
//        model.addAttribute("experimentDto", experimentDto);
//        return "create_experiment";
//    }
//
//    @PostMapping("calculations/")
//    public String saveExperiment(@ModelAttribute("experiment") ExperimentDto experimentDto){
//        experimentService.createExperiment(experimentDto);
//        return "redirect:/calculations";
//    }

    @GetMapping
    public ResponseEntity<ExperimentDto> getExperimentById(@RequestParam Long experimentId) {
        ExperimentDto experimentDto = experimentService.getExperimentById(experimentId);
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

    @GetMapping("all/")
    public String getAllExperiments(Model model) {
        List<ExperimentDto> experimentDtoList = experimentService.getAllExperiments();
        model.addAttribute("experiments", experimentDtoList);
        return "calculations";
    }
}
