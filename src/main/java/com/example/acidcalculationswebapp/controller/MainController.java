package com.example.acidcalculationswebapp.controller;

import com.example.acidcalculationswebapp.service.AcidService;
import com.example.acidcalculationswebapp.service.CalculationService;
import com.example.acidcalculationswebapp.service.ChromatogramService;
import com.example.acidcalculationswebapp.service.ExperimentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    private final ChromatogramService chromatogramService;
    private final ExperimentService experimentService;
    private final CalculationService calculationService;
    private final AcidService acidService;

    public MainController(ChromatogramService chromatogramService, ExperimentService experimentService, CalculationService calculationService, AcidService acidService) {
        this.chromatogramService = chromatogramService;
        this.experimentService = experimentService;
        this.calculationService = calculationService;
        this.acidService = acidService;
    }


    @GetMapping("/home")
    public String getMainPage(HttpServletRequest request, Model model) {
        model.addAttribute("request", request);
        return "main";
    }

    @GetMapping("/statistic")
    public String getStatisticPage(HttpServletRequest request, Model model) {
        model.addAttribute("chromatograms", chromatogramService.getAllChromatograms());
        model.addAttribute("calculations", calculationService.getAllCalculationsEntities());
        model.addAttribute("experiments", experimentService.getAllExperimentsEntities());
        model.addAttribute("acids", acidService.getAllAcids());
        model.addAttribute("request", request);
        return "statistic";
    }
}
