package com.tena.formula1;

import com.tena.formula1.model.Racer;
import com.tena.formula1.model.Season;
import com.tena.formula1.model.Team;
import com.tena.formula1.repository.RacerRepo;
import com.tena.formula1.repository.SeasonRepo;
import com.tena.formula1.repository.TeamRepo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class XlsxExtractor {

    /**
     * Method read data from formula1.xlsx file and put them into database
     * @param racerRepo
     * @param teamRepo
     * @param seasonRepo
     */
    public void readFIle(RacerRepo racerRepo, TeamRepo teamRepo, SeasonRepo seasonRepo) {

        try {
            File file = new ClassPathResource("formula1.xlsx").getFile();
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();
            Row header = itr.next();
            Integer year =0;
            while (itr.hasNext()) {
                Row row = itr.next();
                Racer racer = null;
                Team team = null;
                Season season = null;
                for (int i = 0; i < 4; i++) {
                    Cell cell = row.getCell(i);

                    switch (i) {
                        case 0:
                            if(!(cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK)){
                              year = (int)cell.getNumericCellValue();
                            }
                            season = new Season();
                            season.setYear(year);
                            break;
                        case 1:
                            racer = racerRepo.findByName(cell.getStringCellValue())
                                    .orElseGet(()-> new Racer(cell.getStringCellValue()));
                            if(racer.getId() == null){
                                racer = racerRepo.save(racer);
                            }
                            season.setRacer(racer);
                            break;
                        case 2:
                            team = teamRepo.findByName(cell.getStringCellValue())
                                    .orElseGet(()-> new Team(cell.getStringCellValue()));
                            if(team.getId() == null){
                                team = teamRepo.save(team);
                            }
                            season.setTeam(team);
                            break;
                        case 3:
                            season.setPlace((int)cell.getNumericCellValue());
                            break;
                    }
                }

                seasonRepo.save(season);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
