package com.hsbc.mkty.rwa.rwa;

import org.apache.poi.ss.usermodel.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@SpringBootApplication
public class RwaApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(RwaApplication.class, args);
//
//        //        String filename = "D:\\Documents\\HSBC\\RWA\\RWA.xlsx";
////        String filename = "RWA.xlsx";
//        File file = new ClassPathResource("RWA-2003.xls").getFile();
//        FileInputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        Workbook workbook = WorkbookFactory.create(inputStream);
//
//
////        InputStream inputStream = new FileInputStream(filename);
////        Workbook workbook = new XSSFWorkbook(inputStream);
//        Sheet sheet = workbook.getSheetAt(0);
//        Iterator<Row> iterator = sheet.iterator();
//        List<DealData> dealDataList = new ArrayList<>();
//        List<String> headers = new ArrayList<>();
//
//        // Get headers from first row
//        Row firstRow = iterator.next();
//        Iterator<Cell> headerIterator = firstRow.cellIterator();
//        while (headerIterator.hasNext()) {
//            headers.add(headerIterator.next().getStringCellValue());
//        }
//
//        // Iterate through each row and save data to POJO
//        while (iterator.hasNext()) {
//            Row currentRow = iterator.next();
//            DealData dealData = new DealData();
//            for (int i = 0; i < headers.size(); i++) {
//                Cell currentCell = currentRow.getCell(i);
//                if (currentCell != null){
//                    switch (headers.get(i)) {
//                        case "Index":
//                            dealData.setIndex(currentCell.toString());
//                            break;
//                        case "Column Excel Index":
//                            dealData.setExcelIndex(currentCell.getStringCellValue());
//                            break;
//                        case "Header":
//                            dealData.setHeader(currentCell.getStringCellValue());
//                            break;
//                        case "Java Field Name":
//                            dealData.setJavaFieldName(currentCell.getStringCellValue());
//                            break;
//                        case "Type":
//                            dealData.setType(currentCell.getStringCellValue());
//                            break;
//                        case "Formula":
//                            dealData.setFormula(currentCell != null ? currentCell.getStringCellValue() : "");
//                            break;
//                    }
//                }
//            }
//            dealDataList.add(dealData);
//        }
//        workbook.close();
//        inputStream.close();
    }

}
