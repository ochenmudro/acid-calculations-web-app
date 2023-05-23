package com.example.acidcalculationswebapp.controller;

import com.example.acidcalculationswebapp.AcidCalculationsWebAppApplication;
import com.example.acidcalculationswebapp.dto.AcidDto;
import com.example.acidcalculationswebapp.dto.CalculationDto;
import com.example.acidcalculationswebapp.dto.ExperimentDto;
import com.example.acidcalculationswebapp.entity.Calculation;
import com.example.acidcalculationswebapp.mapper.CalculationMapper;
import com.example.acidcalculationswebapp.service.AcidService;
import com.example.acidcalculationswebapp.service.CalculationService;
import com.example.acidcalculationswebapp.service.ExperimentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/calculations/")
public class CalculationController {
    private static final Logger log = Logger.getLogger(AcidCalculationsWebAppApplication.class.getName());
    final int ISOC4_ID = 7;
    private final CalculationService calculationService;
    private final CalculationMapper calculationMapper;
    private final ExperimentService experimentService;
    private final AcidService acidService;

    public CalculationController(CalculationService calculationService, CalculationMapper calculationMapper,
                                 ExperimentService experimentService, AcidService acidService) {
        this.calculationService = calculationService;
        this.calculationMapper = calculationMapper;
        this.experimentService = experimentService;
        this.acidService = acidService;
    }

    @GetMapping("home")
    public String showCalculationsPage(HttpServletRequest request, Model model) {
        model.addAttribute("experiments", experimentService.getAllExperiments());
        List<AcidDto> acids = acidService.getAllAcids();
        model.addAttribute("acids", acidService.sortAcidsByName(acids));//???
        model.addAttribute("calculation", new CalculationDto());
        model.addAttribute("request", request);
        return "calculations";
    }

    @PostMapping("new/")
    public String createCalculation(@ModelAttribute("calculation") CalculationDto calculationDto,
                                    RedirectAttributes redirectAttributes) {
        try {
            Calculation calculation = calculationService.createCalculation(calculationDto);
            if (calculation.getAcid().getId() == ISOC4_ID) {
                calculation.setConcentration(calculationService.calculateIsoC4Concentration(calculation));
            } else if (calculation.getExperiment().getCalculations() != null && calculation.getExperiment()
                    .getCalculations().stream().anyMatch(c -> c.getAcid().getId() == ISOC4_ID)) {
                calculation.setConcentration(calculationService.calculateConcentration(calculation));
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Невозможно произвести расчет, " +
                        "так как нет данных изомасляной кислоты в данной пробе.");
                return "redirect:/calculations/home";
            }
            redirectAttributes.addFlashAttribute("calculationResult", calculationService.updateCalculation(calculation));
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/calculations/home";
    }

    @GetMapping("create_experiment")
    public String createExperimentForm(HttpServletRequest request, Model model) {
        model.addAttribute("experimentDto", new ExperimentDto());
        model.addAttribute("request", request);
        return "create_experiment";
    }

    @PostMapping("save")
    public String saveExperiment(@ModelAttribute("experimentDto") ExperimentDto experimentDto) {
        experimentService.createExperiment(experimentDto);
        return "redirect:/calculations/home";
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
        List<Calculation> calculationList = calculationDtoList.stream().map(calculationMapper::toEntity).collect(Collectors.toList());
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
