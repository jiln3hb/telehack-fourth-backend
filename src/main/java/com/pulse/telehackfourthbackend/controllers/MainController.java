package com.pulse.telehackfourthbackend.controllers;

import com.pulse.telehackfourthbackend.DTOs.OverallDTO;
import com.pulse.telehackfourthbackend.entities.Measure;
import com.pulse.telehackfourthbackend.services.DBService;
import com.pulse.telehackfourthbackend.services.ParseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@CrossOrigin
@ResponseBody
public class MainController { // контроллер, который занимается всеми запросами, поступающими на сервер

    private final DBService dbService;
    private final ParseService parseService;

    @Autowired
    public MainController(DBService dbService, ParseService parseService) {
        this.dbService = dbService;
        this.parseService = parseService;
    }

    // запрос для добавления измерения в базу данных
    // (5 аргументов: федеральный округ, место измерения, начальная и конечная даты, и сам эксель файл)
    @PostMapping("/api/measures")
    public Measure addMeasure(@RequestParam(value = "federal_district") String federalDistrict, @RequestParam("place_of_measure") String placeOfMeasure,
                              @RequestParam ("start_date") LocalDate startDate, @RequestParam("end_date") LocalDate endDate ,
                              @RequestParam("file") MultipartFile measureFile) {
        log.info("Post request /api/measures");
        return parseService.parseThanSave(federalDistrict, placeOfMeasure, startDate, endDate, measureFile);
    }

    // запрос для получения измерения по ID
    @GetMapping("/api/measures/{id}")
    public Measure getMeasureById(@PathVariable long id) {
        log.info("Get request api/measures/{id} with id {}", id);
        return dbService.getById(id);
    }

    // запрос для получения измерений постранично
    @GetMapping("/api/measures/page")
    public Page<Measure> getMeasurePage(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                 @RequestParam(value = "limit", defaultValue = "10") int limit,
                                 @RequestParam(value = "sortBy", defaultValue = "measureId") String sortBy) {
        log.info("Get request api/measures/page");
        return dbService.getPage(offset, limit, sortBy);
    }

    // запрос для редактирования измерения
    @PutMapping(path = "/api/measures/{id}")
    public Measure updateMeasure(@PathVariable long id, @RequestBody OverallDTO overallDTO) {
        log.info("Put request /api/measures/{id} with id {}", id);
        dbService.update(id, overallDTO);

        return dbService.getById(id);
    }

    // запрос для удаления измерения по ID
    @DeleteMapping("/api/measures/{id}")
    public ResponseEntity<?> deleteMeasureById(@PathVariable long id) {
        log.info("Delete request /api/measures/{id} with id {}", id);
        dbService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    // запрос для получения всех измерений
    @GetMapping("/api/measures/all")
    public List<Measure> getAll() {
        log.info("Get request /api/measures/all");
        return dbService.getAll();
    }
}