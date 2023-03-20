package com.hsbc.mkty.rwa.rwa.Tree;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Edge {
    private String source;
    private String target;
    private String label;
    private String type;

    private int curveOffset;
    private String cluster;
}