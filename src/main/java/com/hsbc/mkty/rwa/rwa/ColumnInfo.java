package com.hsbc.mkty.rwa.rwa;

import lombok.Data;

@Data
public class ColumnInfo {
    private String index;
    private String excelIndex;
    private String header;
    private String javaFieldName;
    private String type;
    private String formula;

}
