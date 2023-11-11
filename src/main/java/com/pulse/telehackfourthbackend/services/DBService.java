package com.pulse.telehackfourthbackend.services;

import com.pulse.telehackfourthbackend.entities.Measure;
import com.pulse.telehackfourthbackend.entities.quality_params.BackgroundInformation;
import com.pulse.telehackfourthbackend.entities.quality_params.QualityOfDT;
import com.pulse.telehackfourthbackend.entities.quality_params.QualityOfText;
import com.pulse.telehackfourthbackend.entities.quality_params.QualityOfVoice;
import com.pulse.telehackfourthbackend.exceptions.BadRequestException;
import com.pulse.telehackfourthbackend.exceptions.NotFoundException;
import com.pulse.telehackfourthbackend.repos.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DBService {

    private final MeasureRepo measureRepo;
    private final BackgroundInformationRepo backgroundInformationRepo;
    private final QualityOfVoiceRepo qualityOfVoiceRepo;
    private final QualityOfTextRepo qualityOfTextRepo;
    private final QualityOfDTRepo qualityOfDTRepo;

    @Autowired
    public DBService(MeasureRepo measureRepo, BackgroundInformationRepo backgroundInformationRepo,
                     QualityOfVoiceRepo qualityOfVoiceRepo, QualityOfTextRepo qualityOfTextRepo, QualityOfDTRepo qualityOfDTRepo) {
        this.measureRepo = measureRepo;
        this.backgroundInformationRepo = backgroundInformationRepo;
        this.qualityOfVoiceRepo = qualityOfVoiceRepo;
        this.qualityOfTextRepo = qualityOfTextRepo;
        this.qualityOfDTRepo = qualityOfDTRepo;
    }

    public void save(Measure measure) {
        try {
            measureRepo.save(measure);
            log.info("DBService saved measure entity with id {}", measure.getMeasureId());
        } catch (Exception e) {
            log.error("ERROR: {}", e.getMessage());
        }
    }

    public void save(BackgroundInformation backgroundInformation) {
        try {
            backgroundInformationRepo.save(backgroundInformation);
            log.info("DBService saved backgroundInformation entity with id {}", backgroundInformation.getBackgroundInformationId());
        } catch (Exception e) {
            log.error("ERROR: {}", e.getMessage());
        }
    }

    public void save(QualityOfVoice qualityOfVoice) {
        try {
            qualityOfVoiceRepo.save(qualityOfVoice);
            log.info("DBService saved qualityOfVoice entity with id {}", qualityOfVoice.getQualityOfVoiceId());
        } catch (Exception e) {
            log.error("ERROR: {}", e.getMessage());
        }
    }

    public void save(QualityOfText qualityOfText) {
        try {
            qualityOfTextRepo.save(qualityOfText);
            log.info("DBService saved qualityOfText entity with id {}", qualityOfText.getQualityOfTextId());
        } catch (Exception e) {
            log.error("ERROR: {}", e.getMessage());
        }
    }

    public void save(QualityOfDT qualityOfDT) {
        try {
            qualityOfDTRepo.save(qualityOfDT);
            log.info("DBService saved qualityOfDT entity with id {}", qualityOfDT.getQualityOfDTId());
        } catch (Exception e) {
            log.error("ERROR: {}", e.getMessage());
        }
    }

    public Measure getById(long id) {
        Optional<Measure> measure = measureRepo.findById(id);

        if (measure.isEmpty()) {
            log.error("ERROR: NotFoundException Measure entity with id {} not found in db", id);
            throw new NotFoundException("Measure entity with id " + id + " not found in db");
        } else return measure.get();
    }

    public Page<Measure> getPage(int offset, int limit, String sortBy) {
        if (offset < 0) {
            log.error("ERROR: BadRequestException: Page index must not be less than zero");
            throw new BadRequestException("Page index must not be less than zero");
        }

        if (limit < 1) {
            log.error("ERROR: BadRequestException: Page size must not be less than one");
            throw new BadRequestException("Page size must not be less than one");
        }

        Page<Measure> page = measureRepo.findAll(PageRequest.of(offset, limit, Sort.by(sortBy)));
        log.info("DBService getPage method executed with offset {} and limit {} and total pages {}", offset, limit, page.getTotalPages());

        return page;
    }

    public void deleteById(long id) {
        measureRepo.deleteById(id);
        log.info("DBService deleted measure with id {}", id);
    }

    public List<Measure> getAll() {
        log.info("DBService getAll method executed");
        return measureRepo.findAll();
    }

    public void deleteAll() {
        log.info("DBService deleteAll method executed");
        measureRepo.deleteAll();
    }
}