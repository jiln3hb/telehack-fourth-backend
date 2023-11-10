package com.pulse.telehackfourthbackend.controllers;

import com.pulse.telehackfourthbackend.entities.Measure;
import com.pulse.telehackfourthbackend.services.MappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@CrossOrigin
@ResponseBody
public class MappingController {

    private final MappingService mappingService;

    @Autowired
    public MappingController(MappingService mappingService) {
        this.mappingService = mappingService;
    }

    @PostMapping("/api/input")
    public List<Measure> input(@RequestParam("file") MultipartFile dataFile) {
        log.info("Post request /mapping/input");
        return mappingService.map(dataFile);
    }

    @GetMapping("/api")
    public String def() {
        log.info("Get request /api");
        return "|^__^| HI!!";
    }
}