package com.pulse.telehackfourthbackend.controllers;

import com.pulse.telehackfourthbackend.entities.Measure;
import com.pulse.telehackfourthbackend.services.MeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@CrossOrigin
@ResponseBody
public class MeasureController {

    private final MeasureService measureService;

    @Autowired
    public MeasureController(MeasureService measureService) {
        this.measureService = measureService;
    }

    @PostMapping("/api/add")
    public List<Measure> input(@RequestParam("federal_district") String federalDistrict, @RequestParam("place_of_measure") String placeOfMeasure,
                               @RequestParam ("start_date") LocalDate startDate, @RequestParam("end_date") LocalDate endDate ,
                               @RequestParam("file") MultipartFile measureFile) {
        log.info("Post request /mapping/input");
        return measureService.add(federalDistrict, placeOfMeasure, startDate, endDate, measureFile);
    }

    @GetMapping("/api")
    public String def() {
        log.info("Get request /api");
        return "|^__^| HI!!";
    }
}