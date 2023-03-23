package com.hsbc.mkty.rwa.rwa.excel.columns;

import com.hsbc.mkty.rwa.rwa.Tree.Graph;
import jdk.jfr.Description;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.TestHelper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.math3.distribution.NormalDistribution;

public class ExcelFunctionsTest {

    @ParameterizedTest
    @Description("Formula - extract column letters")
    @CsvSource({
            "0.15491227, 0.56155476",
            "0.50765808, 0.694153424",
    })
    void matchColumnLettersAsNodes(Double input, Double expectedOutput) {
        double v = ExcelFunctionsTest.NormSDist(input);
        System.out.println(v);

        double v1 = new NormalDistribution().cumulativeProbability(input);
        //0.6941534241938206 - result
        //0.694153424 - Excel
        System.out.println(expectedOutput + " - Excel result");
        System.out.println(v1 + " - My Cal1 result");
        System.out.println(v + " - My manual result");


        System.out.println(0.05 + 0.01);

        double a = 0.03;
        double b = 0.04;
        double c = b - a;
        System.out.println("C = " + c);
        System.out.println("a * b " + a * b);


    }


    public static double NormSDist(double z) {
        // this guards against overflow
        if (z > 6)
            return 1;
        if (z < -6)
            return 0;

        double gamma = 0.231641900,
                a1 = 0.319381530,
                a2 = -0.356563782,
                a3 = 1.781477973,
                a4 = -1.821255978,
                a5 = 1.330274429;

        double x = Math.abs(z);
        double t = 1 / (1 + gamma * x);


        double n = 1
                - (1 / (Math.sqrt(2 * Math.PI)) * Math.exp(-z * z / 2))
                * (a1 * t + a2 * Math.pow(t, 2) + a3 * Math.pow(t, 3) + a4
                * Math.pow(t, 4) + a5 * Math.pow(t, 5));
        if (z < 0)
            return 1.0 - n;

        return n;
    }

    @ParameterizedTest
    @Description("Formula - CJ: part1")
    @CsvSource({
            // cb2,dg2,excel result
            "0.0007, 1.25, 0.70516",
            "0.0007, 1.25, 0.70515918756137",
            "0.0009, 1.3, 0.694864393",
            "0.123, 1.612, 0.806147299",
            "10, 20, -1.4",
            "30, 11.1, -0.332",  // ??? code result: 0.33199999999999985
            "0, 0, 1",
            "1, 1, 0.88",
    })
    void tetCJ_PartOne(Double cb2, Double dg2, Double expectedOutput) {
        double normSDistAAAAA = cjRWA2.getNormSDistAAAAA(cb2, dg2);
        System.out.println("Test accuracy ===========> ");
        System.out.println(expectedOutput + "   |expectedOutput");
        System.out.println(normSDistAAAAA + "   |my result");
    }

    @ParameterizedTest
    @Description("Formula - CJ: part2")
    @CsvSource({
//cb2,dg2, ci2, partOne, excel result
            "0.0007, 1.25,0.054, 0.70516, 0.00187643194746",
            "0.0007, 1.25,0.054, 0.70515918756137, 0.00187643194746",
            "0.0009, 1.3,0.061, 0.69486439283404, 0.00268237705290",

            // my extra border try
            "0.123, 1.612, 0.033, 0.80614729928640, 0.01535620122073",
            "0, 0, 1,1.00000000000000, 0 ", //#NUM！
            "1, 1, 0.88,1, 0" //#NUM！
    })
    void tetCJ_PartTwo(Double cb2, Double dg2, Double ci2, Double partOne, Double expectedOutput) {
        double partTwo = cjRWA2.partTwo(ci2, cb2, dg2, partOne);
        System.out.println("Test accuracy ===========> ");
        System.out.println(expectedOutput + "   |expectedOutput");
        System.out.println(partTwo + "   |my result");
    }
}
