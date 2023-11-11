package com.pulse.telehackfourthbackend.services;

import com.pulse.telehackfourthbackend.entities.Measure;
import com.pulse.telehackfourthbackend.entities.quality_params.BackgroundInformation;
import com.pulse.telehackfourthbackend.entities.quality_params.QualityOfDT;
import com.pulse.telehackfourthbackend.entities.quality_params.QualityOfText;
import com.pulse.telehackfourthbackend.entities.quality_params.QualityOfVoice;
import com.pulse.telehackfourthbackend.exceptions.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@Service
public class ParseService {

    private final DBService dbService;

    @Autowired
    public ParseService(DBService dbService) {
        this.dbService = dbService;
    }

    public Measure parseThanSave(String federalDistrict, String placeOfMeasure, LocalDate startDate, LocalDate endDate, MultipartFile measureFile) {

        Measure measure = new Measure();
        measure.setFederalDistrict(federalDistrict);
        measure.setPlaceOfMeasure(placeOfMeasure);
        measure.setStartDate(startDate);
        measure.setEndDate(endDate);

        dbService.save(measure);

        XSSFWorkbook workbook;

        try {
            workbook = new XSSFWorkbook(measureFile.getInputStream());
        } catch (IOException | RuntimeException e) {
            log.error("ERROR: BadRequestException: Provided file must be excel format");
            throw new BadRequestException("Provided file must be excel format");
        }

        XSSFSheet worksheet = workbook.getSheetAt(0);
        XSSFRow row = worksheet.getRow(17);
        XSSFCell cell;

        int measureCount = countMeasures(row);


        for (int i = 2; i < measureCount + 2; i++) { // TODO ПАРСИТ ЧИСЛА ВСЁ ЕЩЕ НЕ ОЧЕНЬ ПОНЯТНО НОРМ ИЛИ НЕТ
            row = worksheet.getRow(17);

            String operator = row.getCell(i).getStringCellValue();
            double[] qosv = new double[4];
            double[] qost = new double[2];
            double[] qosdt = new double[4];
            int[] backgr = new int[6];

            for (int j = 18; j < 22; j++) {
                row = worksheet.getRow(j);
                try {
                    qosv[j-18] = row.getCell(i).getNumericCellValue();
                } catch (IllegalStateException e) {
                    log.error("ERROR: BadRequestException: Wrong type of cell");
                    throw new BadRequestException("Wrong type of cell");
                } catch (NumberFormatException e) {
                    log.error("ERROR: BadRequestException: Cell value isn't a parsable double");
                    throw new BadRequestException("Cell value isn't a parsable double");
                }
            }
            for (int j = 23; j < 25; j++) {
                row = worksheet.getRow(j);
                try {
                    qosv[j-23] = row.getCell(i).getNumericCellValue();
                } catch (IllegalStateException e) {
                    log.error("ERROR: BadRequestException: Wrong type of cell");
                    throw new BadRequestException("Wrong type of cell");
                } catch (NumberFormatException e) {
                    log.error("ERROR: BadRequestException: Cell value isn't a parsable double");
                    throw new BadRequestException("Cell value isn't a parsable double");
                }
            }
            for (int j = 26; j < 30; j++) {
                row = worksheet.getRow(j);
                try {
                    qosv[j-26] = row.getCell(i).getNumericCellValue();
                } catch (IllegalStateException e) {
                    log.error("ERROR: BadRequestException: Wrong type of cell");
                    throw new BadRequestException("Wrong type of cell");
                } catch (NumberFormatException e) {
                    log.error("ERROR: BadRequestException: Cell value isn't a parsable double");
                    throw new BadRequestException("Cell value isn't a parsable double");
                }
            }
            for (int j = 31; j < 37; j++) {
                row = worksheet.getRow(j);
                try {
                    backgr[j-31] = (int) row.getCell(i).getNumericCellValue();
                } catch (IllegalStateException e) {
                    log.error("ERROR: BadRequestException: Wrong type of cell");
                    throw new BadRequestException("Wrong type of cell");
                } catch (NumberFormatException e) {
                    log.error("ERROR: BadRequestException: Cell value isn't a parsable double");
                    throw new BadRequestException("Cell value isn't a parsable double");
                }
            }

            BackgroundInformation backgroundInformation = new BackgroundInformation(measure, backgr, operator);
            QualityOfVoice qualityOfVoice = new QualityOfVoice(measure, qosv, operator);
            QualityOfText qualityOfText = new QualityOfText(measure, qost, operator);
            QualityOfDT qualityOfDT = new QualityOfDT(measure, qosdt, operator);

            dbService.save(backgroundInformation);
            dbService.save(qualityOfVoice);
            dbService.save(qualityOfText);
            dbService.save(qualityOfDT);

            measure.addBackgroundInformation(backgroundInformation);
            measure.addQualityOfVoice(qualityOfVoice);
            measure.addQualityOfText(qualityOfText);
            measure.addQualityOfDT(qualityOfDT);
        }

        return measure;
    }

    int countMeasures(XSSFRow row) {
        int measuresCount = 0;

        for (int i = 2; ; i++) {
            XSSFCell cell = row.getCell(i);

            if (cell.getCellType() == CellType.BLANK) break;
            measuresCount++;
        }

        return measuresCount;
    }
}