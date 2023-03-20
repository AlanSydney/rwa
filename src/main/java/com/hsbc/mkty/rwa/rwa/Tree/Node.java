package com.hsbc.mkty.rwa.rwa.Tree;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Node {
    private String id;
    private String label;

    private String type;
    private String description;
//    private double x;
//    private double y;
    private String cluster;
}
