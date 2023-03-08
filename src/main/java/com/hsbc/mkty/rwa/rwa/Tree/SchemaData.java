package com.hsbc.mkty.rwa.rwa.Tree;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class SchemaData {
    private Node[] nodes;
    private Edge[] edges;
}

@Data
@AllArgsConstructor
class Node {
    private String nodeType;
    private String nodeTypeKeyFromProperties;
    private Properties properties;
}

@Data
@AllArgsConstructor
class Edge {
    private String edgeType;
    private String edgeTypeKeyFromProperties;
    private String sourceNodeType;
    private String targetNodeType;
    private Properties properties;
}

@Data
@AllArgsConstructor
@Builder
class Properties {
    private String id;
    private String dataType;
    private String legendType;
    private String company;
    private String type;
    private String latitude;
    private String longitude;
    private String country;
    private String province;
    private String city;
    private String device_type;
    private Integer port;
    private String md5;
    private String manu;
    private String name;
    private String os;
    private String first_seen;
    private String last_seen;
}