package com.hsbc.mkty.rwa.rwa.excel.columns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class cjRWATest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void convert() {
        double result = cjRWA.convert(false, true, 584, 0.054);
        System.out.println("The CJ result of RW% is: " + result);
    }
    @Test
    void convert2() {
        double result = cjRWA2.getRWNewMethodology("F", "T", 584, 0.054, 0.00008,1.25, 584);
        System.out.println("The CJ result of RW% is: " + result);
    }
}