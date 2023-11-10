package com.pulse.telehackfourthbackend.controllers;

import com.pulse.telehackfourthbackend.entities.MeasureResult;
import com.pulse.telehackfourthbackend.services.DBService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@CrossOrigin
@ResponseBody
public class DBController {

    private final DBService dbService;

    @Autowired
    public DBController(DBService dbService) {
        this.dbService = dbService;
    }

    @GetMapping("api/db/all")
    public List<MeasureResult> getAll() {
        log.info("Get request api/db/all");
        return dbService.getAll();
    }

    @GetMapping("api/db/{id}")
    public Optional<MeasureResult> getById(@PathVariable long id) {
        log.info("Get request api/db/{id} with id {}", id);
        return dbService.getById(id);
    }

    @GetMapping("api/db/page")
    public Page<MeasureResult> getPage(@RequestParam (value = "offset", defaultValue = "0") int offset,
                                       @RequestParam(value = "limit", defaultValue = "10") int limit,
                                       @RequestParam(value = "sortBy", defaultValue = "measureId") String sortBy) {
        log.info("Get request api/db/page");
        return dbService.getPage(offset, limit, sortBy);
    }

    @DeleteMapping("api/db/delete")
    public ResponseEntity<?> deleteAll() {
        log.info("Delete request api/db/delete");
        dbService.deleteAll();

        return ResponseEntity.ok().build();
    }
}
