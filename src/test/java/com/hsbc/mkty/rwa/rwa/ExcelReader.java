package com.hsbc.mkty.rwa.rwa;

import org.apache.poi.ss.usermodel.*;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {

    public static void main(String[] args) throws Exception {
//        String filename = "D:\\Documents\\HSBC\\RWA\\RWA.xlsx";
//        String filename = "RWA.xlsx";
        File file = new ClassPathResource("RWA.xlsx").getFile();
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(inputStream);


//        InputStream inputStream = new FileInputStream(filename);
//        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        List<ColumnInfo> columnInfoList = new ArrayList<>();
        List<String> headers = new ArrayList<>();

        // Get headers from first row
        Row firstRow = iterator.next();
        Iterator<Cell> headerIterator = firstRow.cellIterator();
        while (headerIterator.hasNext()) {
            headers.add(headerIterator.next().getStringCellValue());
        }

        // Iterate through each row and save data to POJO
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            ColumnInfo columnInfo = new ColumnInfo();
            for (int i = 0; i < headers.size(); i++) {
                Cell currentCell = currentRow.getCell(i);
                switch (headers.get(i)) {
                    case "Deal Number":
                        columnInfo.setDealNumber(currentCell.getStringCellValue());
                        break;
                    case "Group Member":
                        columnInfo.setGroupMember(currentCell.getStringCellValue());
                        break;
                    case "Branch Number":
                        columnInfo.setBranchNumber(currentCell.getStringCellValue());
                        break;
                    case "Profit Centre":
                        columnInfo.setProfitCentre(currentCell.getStringCellValue());
                        break;
                }
            }
            columnInfoList.add(columnInfo);
        }
        workbook.close();
        inputStream.close();
    }
}

// POJO class to hold Excel data
class ColumnInfo {
    private String dealNumber;
    private String groupMember;
    private String branchNumber;
    private String profitCentre;

    // Getters and setters
    public String getDealNumber() {
        return dealNumber;
    }

    public void setDealNumber(String dealNumber) {
        this.dealNumber = dealNumber;
    }

    public String getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(String groupMember) {
        this.groupMember = groupMember;
    }

    public String getBranchNumber() {
        return branchNumber;
    }

    public void setBranchNumber(String branchNumber) {
        this.branchNumber = branchNumber;
    }

    public String getProfitCentre() {
        return profitCentre;
    }

    public void setProfitCentre(String profitCentre) {
        this.profitCentre = profitCentre;
    }
}
