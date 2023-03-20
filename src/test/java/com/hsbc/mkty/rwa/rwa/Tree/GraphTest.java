package com.hsbc.mkty.rwa.rwa.Tree;

import jdk.jfr.Description;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.TestHelper;

import javax.management.DescriptorKey;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class GraphTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getNodesAndEdge() {
    }

    @ParameterizedTest
    @Description("Formula - extract column letters")
    @CsvSource({
            "'M2', 'M'",
            "'IF(AR2=\"B\", \"Y\", \"N\") ', 'AR'",
            "'IF(OFFSET(myTrue, MATCH(O2, COUNTRY_CODE_LIST, 0)=F2, 4)', 'F,O'",
            "'A2+B2+B2+C2', 'A,B,C'",
//            'AX,BT,BU,CB,CH,CI,DG'"  // CJ  - RW% - New Methodology
            "'IF(CH2=\"T\", 0, IF(AX2=\"T\", IF(BT2>0, (CI2*NORMSDIST((1-DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50)))))^-0.5*NORMSINV(CB2)+(DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))/(1-DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))))^0.5*NORMSINV(0.999))-CB2*CI2)*(1-1.5*((0.11852-0.05478*LN(CB2))^2))^-1*(1+(BU2/365-2.5)*((0.11852-0.05478*LN(CB2))^2))*12.5*1.06,0), \"\"))', 'AX,BT,BU,CB,CH,CI,DG'"  // CJ  - RW% - New Methodology
    })
    void matchColumnLettersAsNodes(String input, String expectedOutput) {
        List<String> output = Graph.matchColumnLettersAsNodes(input);
        List<String> expect = Arrays.stream(expectedOutput.split(",")).collect(Collectors.toList());
        assertTrue(TestHelper.isEqualIgnoringOrder(output, expect));
    }

    @Test
    void testGetNodesAndEdge() {
//        NodeAndEdgeData nodeAndEdgeData = Graph.getNodesAndEdge("AA", "IF(OR(U2=\"MCNNI\", U2=\"ZCNNI\", U2=\"RRP\", U2=\"Call\"), \"L\", IF(AND(OR(U2=\"MM041\", U2=\"MM032\", U2=\"ZCNI\", U2=\"REPO BOND\"), (K2=\"CDS\"))");
//        log.info("testGetNodesAndEdge -> nodeAndEdgeData: {}", testGetNodesAndEdge);
//        System.out.println(testGetNodesAndEdge);
//        log.info(String.valueOf(nodeAndEdgeData));

    }
}