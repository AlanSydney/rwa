package com.hsbc.mkty.rwa.rwa.Tree;

import com.hsbc.mkty.rwa.rwa.ColumnInfo;
import io.netty.util.internal.StringUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Graph {
    /**
     * //     * @param columnLetter the index in Excel, like: A, B, ... AE,.. AP
     * //     * @param formula      IF(AR2="B", "Y", "N")  or OFFSET('Lookup-GHO'!A$1, MATCH(AQ2, GHO_Class_INFO, 0), 3)
     * <p>
     * get Nodes and Edges for ONE cell
     *
     * @return list of nodes and Edge
     */
    public static NodeAndEdgeData getNodesAndEdge(ColumnInfo columnInfo) {

        String columnLetter = columnInfo.getExcelIndex();
        String formula = columnInfo.getFormula();
        String header = columnInfo.getHeader();
        boolean isOriginalValue = Double.valueOf(columnInfo.getIndex()) < 43;

        String type = isOriginalValue ? "raw" : "other";
        NodeAndEdgeData nodeAndEdge = new NodeAndEdgeData();

        nodeAndEdge.getNodes().add(Node.builder()
                .id(columnLetter)
                .label(columnLetter + ": " + header)
                .type(type)
                .description(columnInfo.getFormula())
                .build());

        if (!StringUtil.isNullOrEmpty(formula)) {
            nodeAndEdge.getEdges().addAll(getEdgesToColumns(columnLetter, formula));

            // add other nodes used in formula
            List<Node> nodesFromFormula = getNodesFromFormula(formula);
            nodeAndEdge.getNodes().addAll(nodesFromFormula);
            List<Edge> edgesToTables = getEdgesToTables(columnLetter, nodesFromFormula);

            if (edgesToTables != null && edgesToTables.size() > 0) {
                nodeAndEdge.getEdges().addAll(edgesToTables);
            }
        }


        return nodeAndEdge;

    }

    /**
     * get edges that refers to columns (columns letters), not include lookup talbes
     *
     * @param columnLetter current cell
     * @param formula
     * @return
     */
    public static List<Edge> getEdgesToColumns(String columnLetter, String formula) {


        List<Edge> edges = new ArrayList<>();
        for (String matched : Graph.matchColumnLettersAsNodes(formula)) {
            Edge node = Edge.builder()
                    .source(matched)
                    .target(columnLetter)
                    .build();
            edges.add(node);
        }
        return edges;
    }

    /**
     * get edges that refers to tables
     *
     * @param columnLetter
     * @param tableNoes
     * @return
     */
    public static List<Edge> getEdgesToTables(String columnLetter, List<Node> tableNoes) {
        List<Edge> edges = new ArrayList<>();
        for (Node dependencyNode : tableNoes) {
            Edge node = Edge.builder()
                    .source(dependencyNode.getId())
                    .target(columnLetter)
                    .type(dependencyNode.getType())
                    .build();
            edges.add(node);
        }
        return edges;
    }

    public static List<Node> getNodesFromFormula(String formula) {
        Set<Node> edges = new HashSet<>();

        // Lookup and Match tables
        for (String matched : Graph.matchLookupAndMatchAsNodes(formula)) {
            Node node = Node.builder()
                    .id(matched)
                    .label(matched)
                    .type("lookupTable")
                    .build();
            edges.add(node);
        }

        for (String matched : Graph.matchSpreadSheetNodes(formula)) {
            Node node = Node.builder()
                    .id(matched)
                    .label(matched)
                    .type("spreadSheet")
                    .build();
            edges.add(node);
        }
        return edges.stream().collect(Collectors.toList());
    }

    /**
     * find out column letters from the formula
     *
     * @param formula
     * @return no duplicated column letter
     */
    public static List<String> matchColumnLettersAsNodes(String formula) {
        String regex = "[A-Z]{1,2}2";

        Set<String> matchedWords = new HashSet<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(formula);
        while (matcher.find()) {
            matchedWords.add(matcher.group().replace("2", "")); // remove row number
        }
        return matchedWords.stream().collect(Collectors.toList());
    }


    /**
     * find Look up tables from formula
     *
     * @param formula
     * @return
     */
    public static List<String> matchLookupAndMatchAsNodes(String formula) {
        String regex = "(VLOOKUP|MATCH)\\s*\\([A-Z]{1,2}2\\s*,\\s*([A-Z_]*)\\s*,";
        Set<String> matchedWords = new HashSet<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(formula);
        while (matcher.find()) {
            matchedWords.add(matcher.group(2)); // remove row number
        }
        return matchedWords.stream().collect(Collectors.toList());
    }

    /**
     * find Look up tables from formula
     *
     * @param formula
     * @return
     */
    public static List<String> matchSpreadSheetNodes(String formula) {
        String regex = "'(.*?)'!";
        Set<String> matchedWords = new HashSet<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(formula);
        while (matcher.find()) {
            matchedWords.add(matcher.group(1)); // remove row number
        }
        return matchedWords.stream().collect(Collectors.toList());
    }

//    private static List<String> getRegexStrings(String regex, String formula) {
//        Set<String> matchedWords = new HashSet();
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(formula);
//        while (matcher.find()) {
//            matchedWords.add(matcher.group()); // remove row number
//        }
//        return matchedWords.stream().collect(Collectors.toList());
//    }
}
