package com.hsbc.mkty.rwa.rwa.Tree;

public class TestCreateSchema {
    public TestCreateSchema() {
        SchemaData schemaData = new SchemaData();
        // sample data
        Properties properties = Properties.builder()
                .id("string")
                .dataType("string")
                .legendType("string")
                .company("string")
                .type("string")
                .latitude("string")
                .longitude("string")
                .country("string")
                .province("string")
                .city("string")
                .device_type("string")
                .port(0)
                .md5("string")
                .manu("string")
                .name("string")
                .os("string")
                .first_seen("string")
                .last_seen("string")
                .build();


        NodeSchema[] nodeSchemas = {
                new NodeSchema("在线设备资产", "legendType", properties),
                new NodeSchema("国家", "legendType", properties),
                new NodeSchema("设备制造商", "legendType", properties),
                new NodeSchema("固件", "legendType", properties),
                new NodeSchema("单文件", "legendType", properties),
                new NodeSchema("漏洞事件", "legendType", properties),
                new NodeSchema("僵尸网络家族", "legendType", properties)
        };
        EdgeSchema[] edgeSchemas = {
                new EdgeSchema("assets_to_country", "edgeType", "在线设备资产", "国家", properties),
                new EdgeSchema("firmware_to_assets", "edgeType", "固件", "在线设备资产", properties),
                new EdgeSchema("manu_to_country", "edgeType", "设备制造商", "国家", properties),
                new EdgeSchema("firmware_to_manu", "edgeType", "固件", "设备制造商", properties),
                new EdgeSchema("file_to_firmware", "edgeType", "单文件", "固件", properties),
                new EdgeSchema("cve_to_file", "edgeType", "漏洞事件", "单文件", properties),
                new EdgeSchema("botnet_to_cve", "edgeType", "僵尸网络家族", "漏洞事件", properties)
        };

        schemaData.setNodeSchemas(nodeSchemas);
        schemaData.setEdgeSchemas(edgeSchemas);

    }
}
