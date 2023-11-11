package com.pulse.telehackfourthbackend.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.pulse.telehackfourthbackend.DTOs.*;
import com.pulse.telehackfourthbackend.entities.Measure;
import com.pulse.telehackfourthbackend.entities.quality_params.BackgroundInformation;
import com.pulse.telehackfourthbackend.entities.quality_params.QualityOfDT;
import com.pulse.telehackfourthbackend.entities.quality_params.QualityOfText;
import com.pulse.telehackfourthbackend.entities.quality_params.QualityOfVoice;
import com.pulse.telehackfourthbackend.exceptions.BadRequestException;
import com.pulse.telehackfourthbackend.services.DBService;
import com.pulse.telehackfourthbackend.services.MeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Queue;

@Slf4j
@Controller
@CrossOrigin
@ResponseBody
public class MainController {

    private final DBService dbService;
    private final MeasureService measureService;

    @Autowired
    public MainController(DBService dbService, MeasureService measureService) {
        this.dbService = dbService;
        this.measureService = measureService;
    }

    @PostMapping("/api/measures")
    public Measure addMeasure(@RequestParam("federal_district") String federalDistrict, @RequestParam("place_of_measure") String placeOfMeasure,
                              @RequestParam ("start_date") LocalDate startDate, @RequestParam("end_date") LocalDate endDate ,
                              @RequestParam("file") MultipartFile measureFile) {
        log.info("Post request /api/measures");
        return measureService.parseThanSave(federalDistrict, placeOfMeasure, startDate, endDate, measureFile);
    }

    @GetMapping("/api/measures/{id}")
    public Measure getMeasureById(@PathVariable long id) {
        log.info("Get request api/measures/{id} with id {}", id);
        return dbService.getById(id);
    }

    @GetMapping("/api/measures/page")
    public Page<Measure> getMeasurePage(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                 @RequestParam(value = "limit", defaultValue = "10") int limit,
                                 @RequestParam(value = "sortBy", defaultValue = "measureId") String sortBy) {
        log.info("Get request api/measures/page");
        return dbService.getPage(offset, limit, sortBy);
    }

    @PutMapping(path = "/api/measures/{id}")
    public Measure updateMeasure(@PathVariable long id, @RequestBody OverallDTO overallDTO) {
        log.info("Put request /api/measures/{id} with id {}", id);

        Measure measure = dbService.getById(id);
        measure.setParams(overallDTO);
        dbService.save(measure);

        for (BackgroundInformationDTO backgroundInformationDTO : overallDTO.getBackgroundInformationDTOList()) {
            BackgroundInformation backgroundInformation = new BackgroundInformation(measure, backgroundInformationDTO);
            dbService.save(backgroundInformation);
        }

        for (QualityOfVoiceDTO qualityOfVoiceDTO : overallDTO.getQualityOfVoiceDTOList()) {
            QualityOfVoice qualityOfVoice = new QualityOfVoice(measure, qualityOfVoiceDTO);
            dbService.save(qualityOfVoice);
        }

        for (QualityOfTextDTO qualityOfTextDTO : overallDTO.getQualityOfTextDTOList()) {
            QualityOfText qualityOfText = new QualityOfText(measure, qualityOfTextDTO);
            dbService.save(qualityOfText);
        }

        for (QualityOfDTDTO qualityOfDTDTO : overallDTO.getQualityOfDTDTOList()) {
            QualityOfDT qualityOfDT = new QualityOfDT(measure, qualityOfDTDTO);
            dbService.save(qualityOfDT);
        }

        return dbService.getById(id);
    }

    @DeleteMapping("/api/measures/{id}")
    public ResponseEntity<?> deleteMeasureById(@PathVariable long id) {
        log.info("Delete request /api/measures/{id} with id {}", id);
        dbService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/measures/all")
    public List<Measure> getAll() {
        log.info("Get request /api/measures/all");
        return dbService.getAll();
    }

    @DeleteMapping("/api/measures/delete-all")
    public ResponseEntity<?> deleteAll() {
        log.info("Delete request /api/measures/delete-all");
        dbService.deleteAll();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api")
    public String def() {
        log.info("Get request /api");
        return "|^__^| HI!!";
    }

    private Measure applyPatchToMeasure(JsonPatch patch, Measure measure) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        JsonNode patched = patch.apply(objectMapper.convertValue(measure, JsonNode.class));
        return objectMapper.treeToValue(patched, Measure.class);
    }
}