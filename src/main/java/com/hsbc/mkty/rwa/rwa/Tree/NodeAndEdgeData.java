package com.hsbc.mkty.rwa.rwa.Tree;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class NodeAndEdgeData {
    private List<Node> nodes;
    private List<Edge> edges;
    private List<Combo> combos;

    @Data
    @Builder
    @AllArgsConstructor
    public static class Node {
        private String id;
        private String label;
        private String description;
        private double x;
        private double y;
        private String cluster;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Edge {
        private String source;
        private String target;
        private String label;
        private int curveOffset;
        private String cluster;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Combo {
        private String id;
        private String label;
        private boolean collapsed;
    }
}
