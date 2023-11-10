package com.pulse.telehackfourthbackend.services;

import com.pulse.telehackfourthbackend.entities.MeasureResult;
import com.pulse.telehackfourthbackend.exceptions.BadRequestException;
import com.pulse.telehackfourthbackend.exceptions.NotFoundException;
import com.pulse.telehackfourthbackend.repos.MeasureResultRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DBService {

    private final MeasureResultRepo measureResultRepo;

    @Autowired
    public DBService(MeasureResultRepo measureResultRepo) {
        this.measureResultRepo = measureResultRepo;
    }

    public List<MeasureResult> getAll() {
        log.info("DBService getAll method executed");
        return measureResultRepo.findAll();
    }

    public Optional<MeasureResult> getById(long id) {
        log.info("DBService getById method executed");

        Optional<MeasureResult> measure = measureResultRepo.findById(id);
        if (measure.isPresent()) return measure;
        else {
            log.error("ERROR: NotFoundException Entity with id {} not found in db", id);
            throw new NotFoundException("Entity with id " + id + " not found in db");
        }
    }

    public Page<MeasureResult> getPage(int offset, int limit, String sortBy) {
        if (offset < 0) {
            log.error("ERROR: BadRequestException: Page index must not be less than zero");
            throw new BadRequestException("Page index must not be less than zero");
        }

        if (limit < 1) {
            log.error("ERROR: BadRequestException: Page size must not be less than one");
            throw new BadRequestException("Page size must not be less than one");
        }

        Page<MeasureResult> page = measureResultRepo.findAll(PageRequest.of(offset, limit, Sort.by(sortBy)));
        log.info("DBService getPage method executed with offset {} and limit {} and total pages {}", offset, limit, page.getTotalPages());

        return page;
    }

    public void deleteAll() {
        log.info("DBService deleteAll method executed");
        measureResultRepo.deleteAll();
    }

    public void save(MeasureResult measureResult) {
        try {
            measureResultRepo.save(measureResult);
            log.info("DBService saved entity with id {}", measureResult.getMeasureId());
        } catch (Exception e) {
            log.error("ERROR: {}", e.getMessage());
        }
    }
}
