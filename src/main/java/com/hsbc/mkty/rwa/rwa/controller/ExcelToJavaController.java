package com.hsbc.mkty.rwa.rwa.controller;

import com.hsbc.mkty.rwa.rwa.ColumnInfo;
import com.hsbc.mkty.rwa.rwa.Tree.Edge;
import com.hsbc.mkty.rwa.rwa.Tree.Graph;
import com.hsbc.mkty.rwa.rwa.Tree.Node;
import com.hsbc.mkty.rwa.rwa.Tree.NodeAndEdgeData;
import org.apache.poi.ss.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ExcelToJavaController {
    @GetMapping("/deal-data")
    public NodeAndEdgeData getDealData() throws IOException {
        List<ColumnInfo> columnInfos = getData();
        NodeAndEdgeData allNodeAndEdge = new NodeAndEdgeData();

        Set<Node> nodes = new HashSet<>();
        Set<Edge> edges = new HashSet<>();
        for (ColumnInfo columnInfo : columnInfos) {
            if (columnInfo.getIndex() == null) {
                continue;
            }
            NodeAndEdgeData nodesAndEdge = Graph.getNodesAndEdge(columnInfo);

            nodes.addAll(nodesAndEdge.getNodes());
            edges.addAll(nodesAndEdge.getEdges());
        }
        allNodeAndEdge.getNodes().addAll(nodes);
        allNodeAndEdge.getEdges().addAll(edges);
        return allNodeAndEdge;
    }

    private static List<ColumnInfo> getData() throws IOException {
        File file = new ClassPathResource("RWA-2003.xls").getFile();
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(inputStream);
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
                if (currentCell != null) {
                    switch (headers.get(i)) {
                        case "Index":
                            columnInfo.setIndex(currentCell.toString());
                            break;
                        case "Column Excel Index":
                            columnInfo.setExcelIndex(currentCell.getStringCellValue());
                            break;
                        case "Header":
                            columnInfo.setHeader(currentCell.getStringCellValue());
                            break;
                        case "Java Field Name":
                            columnInfo.setJavaFieldName(currentCell.getStringCellValue());
                            break;
                        case "Type":
                            columnInfo.setType(currentCell.getStringCellValue());
                            break;
                        case "Formula":
                            columnInfo.setFormula(currentCell != null ? currentCell.getStringCellValue() : "");
                            break;
                    }
                }
            }
            columnInfoList.add(columnInfo);
        }
        workbook.close();
        inputStream.close();
        return columnInfoList;
    }
}
