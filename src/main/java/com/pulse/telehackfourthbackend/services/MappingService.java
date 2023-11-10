package com.pulse.telehackfourthbackend.services;

import com.pulse.telehackfourthbackend.entities.MeasureResult;
import com.pulse.telehackfourthbackend.exceptions.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MappingService {

    private final DBService dbService;

    @Autowired
    public MappingService(DBService dbService) {
        this.dbService = dbService;
    }

    public List<MeasureResult> map(MultipartFile dataFile)  {
        ArrayList<MeasureResult> list = new ArrayList<>();

        XSSFWorkbook workbook;

        try {
            workbook = new XSSFWorkbook(dataFile.getInputStream());
        } catch (IOException | RuntimeException e) {
            log.error("ERROR: BadRequestException Provided file must be excel format");
            throw new BadRequestException("Provided file must be excel format");
        }

        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; ; i++) {
            XSSFRow row = worksheet.getRow(i);

            if (row == null || isRowEmpty(row)) break;

            MeasureResult measure = new MeasureResult();
            measure.setData(row.getCell(0).getStringCellValue());

            try {
                measure.setDateTime(row.getCell(1).getLocalDateTimeCellValue());
            } catch (IllegalStateException e) {
                log.error("ERROR: IllegalStateException cell [{}, {}] that contains date must be NUMERIC type (this cell has STRING type)", row.getRowNum(), 1);
                throw new BadRequestException("Cell [" + row.getRowNum() + ", " + 1 + "] that contains date must be NUMERIC type (this cell has STRING type)");
            } catch (NumberFormatException e) {
                log.error("ERROR: in cell [{},{}] {}", row.getRowNum(), 1, e.getMessage());
                throw new BadRequestException("Cell [" + row.getRowNum() + ", " + 1 + "] isn't a parsable double");
            }

            dbService.save(measure);
            list.add(measure);

            log.info("Entity with id {} mapped from excel and saved in DB", measure.getMeasureId());
        }

        return list;
    }

    private boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK)
                return false;
        }
        return true;
    }
}