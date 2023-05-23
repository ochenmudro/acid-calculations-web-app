package com.example.acidcalculationswebapp.controller;

import com.example.acidcalculationswebapp.AcidCalculationsWebAppApplication;
import com.example.acidcalculationswebapp.dto.ChromatogramDto;
import com.example.acidcalculationswebapp.dto.ConcentrationDto;
import com.example.acidcalculationswebapp.dto.PeakAreaDto;
import com.example.acidcalculationswebapp.entity.Chromatogram;
import com.example.acidcalculationswebapp.entity.Concentration;
import com.example.acidcalculationswebapp.entity.PeakArea;
import com.example.acidcalculationswebapp.service.AcidService;
import com.example.acidcalculationswebapp.service.ChromatogramService;
import com.example.acidcalculationswebapp.service.ConcentrationService;
import com.example.acidcalculationswebapp.service.PeakAreaService;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Controller
@RequestMapping("/chromatograms/")
public class ChromatogramController {
    private static final Logger log = Logger.getLogger(AcidCalculationsWebAppApplication.class.getName());
    private final ChromatogramService chromatogramService;
    private final AcidService acidService;
    private final ConcentrationService concentrationService;
    private final PeakAreaService peakAreaService;
    private Long chromatogramId;

    public ChromatogramController(ChromatogramService chromatogramService, AcidService acidService, ConcentrationService concentrationService, PeakAreaService peakAreaService) {
        this.chromatogramService = chromatogramService;
        this.acidService = acidService;
        this.concentrationService = concentrationService;
        this.peakAreaService = peakAreaService;
    }

    @GetMapping("home")
    public String showChromatogramsPage(HttpServletRequest request, Model model) {
        model.addAttribute("acids", acidService.getAllAcids());
        model.addAttribute("chromatogramDto", new ChromatogramDto());
        model.addAttribute("concentrations", new ArrayList<Concentration>());
        model.addAttribute("peakAreas", new ArrayList<PeakArea>());
        model.addAttribute("request", request);
        return "chromatograms";
    }

    @PostMapping("new/")
    public String createChromatogram(@ModelAttribute("chromatogramDto") ChromatogramDto chromatogramDto,
                                     Model model, RedirectAttributes redirectAttributes) {
        Chromatogram chromatogram = chromatogramService.createChromatogram(chromatogramDto);
        chromatogramId = chromatogram.getId();
        List<Double> concentrationValues = new ArrayList<>();
        chromatogramDto.getConcentrationValues().forEach(concentrationValue -> {
            concentrationValues.add(concentrationValue);
            ConcentrationDto concentration = new ConcentrationDto();
            concentration.setValue(concentrationValue);
            concentration.setChromatogramId(chromatogram.getId());
            concentrationService.createConcentration(concentration);
        });
        List<Double> peakAreaValues = new ArrayList<>();
        chromatogramDto.getPeakAreaValues().forEach(peakAreaValue -> {
            peakAreaValues.add(peakAreaValue);
            PeakAreaDto peakArea = new PeakAreaDto();
            peakArea.setValue(peakAreaValue);
            peakArea.setChromatogramId(chromatogram.getId());
            peakAreaService.createPeakArea(peakArea);
        });

        int n = concentrationValues.size();
        SimpleRegression regression = new SimpleRegression(false);
        for (int i = 0; i < n; i++) {
            regression.addData(concentrationValues.get(i), peakAreaValues.get(i));
        }

        double coefficient = regression.getSlope();

        List<Double> trendLineValues = new ArrayList<>();
        concentrationValues.forEach(xValues -> trendLineValues.add(coefficient * xValues));

        chromatogram.setT(coefficient);
        chromatogramService.updateChromatogram(chromatogram);

        redirectAttributes.addFlashAttribute("chromatogramDto", chromatogramDto);
        redirectAttributes.addFlashAttribute("concentrationValues", concentrationValues);
        redirectAttributes.addFlashAttribute("peakAreaValues", peakAreaValues);
        redirectAttributes.addFlashAttribute("coefficient", coefficient);
        redirectAttributes.addFlashAttribute("trendLineValues", trendLineValues);

        return "redirect:/chromatograms/chart";
    }

    @GetMapping("chart")
    public String showChart(HttpServletRequest request, @ModelAttribute("chromatogram") ChromatogramDto chromatogramDto,
                            @ModelAttribute("concentrationValues") List<Double> concentrationValues,
                            @ModelAttribute("peakAreaValues") List<Double> peakAreaValues,
                            @ModelAttribute("coefficient") Double coefficient,
                            Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("chromatogramDto", chromatogramDto);
        model.addAttribute("image", "");
        model.addAttribute("chromatogramDto", chromatogramDto);
        model.addAttribute("coefficient", coefficient);
        model.addAttribute("concentrationValues", concentrationValues);
        model.addAttribute("peakAreaValues", peakAreaValues);
        model.addAttribute("request", request);
        return "chart";
    }

    @PostMapping("saveChartImage")
    public ResponseEntity<String> saveChartImage(@RequestParam("image") String image) {

        Chromatogram chromatogram = chromatogramService.getChromatogramById(chromatogramId);
        chromatogram.setChartImage(image);
        chromatogramService.updateChromatogram(chromatogram);
        if (Objects.equals(chromatogram.getChartImage(), image)) {
            return ResponseEntity.ok("Ok");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not ok");
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteChromatogram(@RequestParam Long id) {
        chromatogramService.deleteChromatogramById(id);
        return ResponseEntity.noContent().build();
    }

}
