package com.pulse.telehackfourthbackend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.pulse.telehackfourthbackend.DTOs.*;
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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DBService { // сервис, который занимается взаимодействием с БД

    // константа, в которой содержатся все поля, подлежащие сортировке
    private final static String[] MEASURE_FIELDS = {"measureId", "federalDistrict", "placeOfMeasure", "startDate", "endDate"};

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

    // методы для сохранения всех сущностей в БД
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

    // метод, позволяющий редактировать все сущности в БД путём подачи на вход объекта, содержащего все данные об измерении
    public void update(long id, OverallDTO overallDTO) {
        Measure measure = getById(id);
        measure.setParams(overallDTO);
        save(measure);

        for (BackgroundInformationDTO backgroundInformationDTO : overallDTO.getBackgroundInformationList()) {
            long idd = backgroundInformationDTO.getBackgroundInformationId();
            Optional<BackgroundInformation> optBackgroundInformation = backgroundInformationRepo.findById(idd);

            if (optBackgroundInformation.isEmpty()) {
                log.error("ERROR: NotFoundException: BackgroundInformation entity with such id ({}) not exists", idd);
                throw new NotFoundException("BackgroundInformation entity with such id (" + idd + ") not exists");
            }

            BackgroundInformation backgroundInformation = optBackgroundInformation.get();
            backgroundInformation.setParams(measure, backgroundInformationDTO);
            save(backgroundInformation);
        }

        for (QualityOfVoiceDTO qualityOfVoiceDTO : overallDTO.getQualityOfVoiceList()) {
            long idd = qualityOfVoiceDTO.getQualityOfVoiceId();
            Optional<QualityOfVoice> optQualityOfVoice = qualityOfVoiceRepo.findById(idd);

            if (optQualityOfVoice.isEmpty()) {
                log.error("ERROR: NotFoundException: QualityOfVoice entity with such id ({}) not exists", idd);
                throw new NotFoundException("QualityOfVoice entity with such id (" + idd + ") not exists");
            }

            QualityOfVoice qualityOfVoice = optQualityOfVoice.get();
            qualityOfVoice.setParams(measure, qualityOfVoiceDTO);
            save(qualityOfVoice);
        }

        for (QualityOfTextDTO qualityOfTextDTO : overallDTO.getQualityOfTextList()) {
            long idd = qualityOfTextDTO.getQualityOfTextId();
            Optional<QualityOfText> optQualityOfText = qualityOfTextRepo.findById(idd);

            if (optQualityOfText.isEmpty()) {
                log.error("ERROR: NotFoundException: QualityOfText entity with such id ({}) not exists", idd);
                throw new NotFoundException("QualityOfText entity with such id (" + idd + ") not exists");
            }

            QualityOfText qualityOfText = optQualityOfText.get();
            qualityOfText.setParams(measure, qualityOfTextDTO);
            save(qualityOfText);
        }

        for (QualityOfDTDTO qualityOfDTDTO : overallDTO.getQualityOfDTList()) {
            long idd = qualityOfDTDTO.getQualityOfDTId();
            Optional<QualityOfDT> optQualityOfDT = qualityOfDTRepo.findById(idd);

            if (optQualityOfDT.isEmpty()) {
                log.error("ERROR: NotFoundException: QualityOfDT entity with such id ({}) not exists", idd);
                throw new NotFoundException("QualityOfDT entity with such id (" + idd + ") not exists");
            }

            QualityOfDT qualityOfDT = optQualityOfDT.get();
            qualityOfDT.setParams(measure, qualityOfDTDTO);
            save(qualityOfDT);
        }
    }

    // метод, для получения сущности из БД по ID
    public Measure getById(long id) {
        log.info("DBService getById method executed with id {}", id);

        if (id < 1) {
            log.error("ERROR: BadRequestException: Id must be >= 1");
            throw new BadRequestException("Id must be >= 1");
        }

        Optional<Measure> measure;

        try {
            measure = measureRepo.findById(id);

            if (measure.isEmpty()) {
                log.error("ERROR: NotFoundException: Measure entity with id {} not found in db", id);
                throw new NotFoundException("Measure entity with id " + id + " not found in db");
            } else return measure.get();
        } catch (IllegalArgumentException e) {
            log.error("ERROR: BadRequestException: id {} is not valid", id);
            throw new BadRequestException("id " + id + " is not valid");
        }
    }

    // метод, для получения постранично сущностей из БД
    public Page<Measure> getPage(int offset, int limit, String sortBy) {
        if (offset < 0) {
            log.error("ERROR: BadRequestException: Page index must not be less than zero");
            throw new BadRequestException("Page index must not be less than zero");
        }

        if (limit < 1) {
            log.error("ERROR: BadRequestException: Page size must not be less than one");
            throw new BadRequestException("Page size must not be less than one");
        }

        if (Arrays.stream(MEASURE_FIELDS).noneMatch(s -> s.equals(sortBy))) {
            log.error("ERROR: BadRequestException: sortBy param is not valid");
            throw new BadRequestException("sortBy param is not valid");
        }

        Page<Measure> page = measureRepo.findAll(PageRequest.of(offset, limit, Sort.by(sortBy)));
        log.info("DBService getPage method executed with offset {} and limit {} and total pages {}", offset, limit, page.getTotalPages());

        return page;
    }

    // метод удаления сущности из БД по ID
    public void deleteById(long id) {
        getById(id);
        try {
            measureRepo.deleteById(id);
            log.info("DBService deleted measure with id {}", id);
        } catch (IllegalArgumentException e) {
            log.error("ERROR: BadRequestException: id {} is not valid", id);
            throw new BadRequestException("id " + id + " is not valid");
        }
    }

    // метод, для получения всех сущностей из БД
    public List<Measure> getAll() {
        log.info("DBService getAll method executed");
        return measureRepo.findAll();
    }
}