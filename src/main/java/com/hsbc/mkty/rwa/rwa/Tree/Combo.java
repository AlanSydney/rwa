package com.hsbc.mkty.rwa.rwa.Tree;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Combo {
    private String id;
    private String label;
    private boolean collapsed;
}