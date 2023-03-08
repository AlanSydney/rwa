package controller;

import com.hsbc.mkty.rwa.rwa.Tree.NodeAndEdgeData;
import org.apache.poi.ss.usermodel.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class ExcelToJavaController {

    @PostMapping("/convert")
    public ResponseEntity<NodeAndEdgeData> convertExcelToJava(@RequestBody Resource resource) throws IOException {
        // Get the input stream from the resource
        InputStream inputStream = resource.getInputStream();

        // Create the workbook object from the input stream
        Workbook workbook = WorkbookFactory.create(inputStream);

        // Get the first sheet from the workbook
        Sheet sheet = workbook.getSheetAt(0);

        // Initialize the lists to hold the data
        List<NodeAndEdgeData.Node> nodes = new ArrayList<>();
        List<NodeAndEdgeData.Edge> edges = new ArrayList<>();
        List<NodeAndEdgeData.Combo> combos = new ArrayList<>();

        // Iterate through each row in the sheet
        for (Row row : sheet) {
            // Get the first cell in the row
            Cell cell = row.getCell(0);

            // Check if the cell is null or empty
            if (cell == null || cell.getCellType() == CellType.BLANK) {
                continue;
            }

            // Get the value of the cell
            String value = cell.getStringCellValue();

            // Check if the value matches "Nodes"
            if (value.equalsIgnoreCase("Nodes")) {
                // Iterate through each row in the "Nodes" section
                Iterator<Row> nodesIterator = sheet.rowIterator();
                while (nodesIterator.hasNext()) {
                    Row nodesRow = nodesIterator.next();
                    // Check if the first cell in the row is null or empty
                    if (nodesRow.getCell(0) == null || nodesRow.getCell(0).getCellType() == CellType.BLANK) {
                        continue;
                    }
                    // Check if the value of the first cell is "Edges" or "Combos"
                    String nodesValue = nodesRow.getCell(0).getStringCellValue();
                    if (nodesValue.equalsIgnoreCase("Edges") || nodesValue.equalsIgnoreCase("Combos")) {
                        break;
                    }
                    // Create a new node object and add it to the list
                    NodeAndEdgeData.Node node = NodeAndEdgeData.Node.builder()
                            .id(nodesRow.getCell(0).getStringCellValue())
                            .label(nodesRow.getCell(1).getStringCellValue())
                            .description(nodesRow.getCell(2) != null ? nodesRow.getCell(2).getStringCellValue() : null)
                            .x(nodesRow.getCell(3).getNumericCellValue())
                            .y(nodesRow.getCell(4).getNumericCellValue())
                            .cluster(nodesRow.getCell(5).getStringCellValue())
                            .build();
                    nodes.add(node);
                }
            }

            // Check if the value matches "Edges"
            if (value.equalsIgnoreCase("Edges")) {
                // Iterate through each row in the "Edges" section
                Iterator<Row> edgesIterator = sheet.rowIterator();
                while (edgesIterator.hasNext()) {
                    Row edgesRow = edgesIterator.next();
                    // Check if the first cell in the row is null or empty
                    if (edgesRow.getCell(0) == null || edgesRow.getCell(0).getCellType() == CellType.BLANK) {
                        continue;
                    }
                    // Check if the value of the first cell is "Nodes" or "Combos"
                    String edgesValue = edgesRow.getCell(0).getStringCellValue();
                    if (edgesValue.equalsIgnoreCase("Nodes") || edgesValue.equalsIgnoreCase("Combos")) {
                        break;
                    }
                    // Create a new edge object and add it to the list
                    NodeAndEdgeData.Edge edge = NodeAndEdgeData.Edge.builder()
                            .source(edgesRow.getCell(0).getStringCellValue())
                            .target(edgesRow.getCell(1).getStringCellValue())
                            .label(edgesRow.getCell(2).getStringCellValue())
                            .curveOffset((int) edgesRow.getCell(3).getNumericCellValue())
                            .cluster(edgesRow.getCell(4).getStringCellValue())
                            .build();
                    edges.add(edge);
                }
            }

            // Check if the value matches "Combos"
            if (value.equalsIgnoreCase("Combos")) {
                // Iterate through each row in the "Combos" section
                Iterator<Row> combosIterator = sheet.rowIterator();
                while (combosIterator.hasNext()) {
                    Row combosRow = combosIterator.next();
                    // Check if the first cell in the row is null or empty
                    if (combosRow.getCell(0) == null || combosRow.getCell(0).getCellType() == CellType.BLANK) {
                        continue;
                    }
                    // Check if the value of the first cell is "Nodes" or "Edges"
                    String combosValue = combosRow.getCell(0).getStringCellValue();
                    if (combosValue.equalsIgnoreCase("Nodes") || combosValue.equalsIgnoreCase("Edges")) {
                        break;
                    }
                    // Create a new combo object and add it to the list
                    NodeAndEdgeData.Combo combo = NodeAndEdgeData.Combo.builder()
                            .id(combosRow.getCell(0).getStringCellValue())
                            .label(combosRow.getCell(1).getStringCellValue())
                            .collapsed(combosRow.getCell(2).getBooleanCellValue())
                            .build();
                    combos.add(combo);
                }
            }
        }

        // Create the NodeAndEdgeData object and return it in the response
        NodeAndEdgeData nodeAndEdgeData = NodeAndEdgeData.builder()
                .nodes(nodes)
                .edges(edges)
                .combos(combos)
                .build();
        return new ResponseEntity<>(nodeAndEdgeData, HttpStatus.OK);
    }
}
