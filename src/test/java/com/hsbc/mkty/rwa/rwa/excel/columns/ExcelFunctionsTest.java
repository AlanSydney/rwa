package com.hsbc.mkty.rwa.rwa.excel.columns;

import jdk.jfr.Description;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.math3.distribution.NormalDistribution;

public class ExcelFunctionsTest {

    @ParameterizedTest
    @Description("Formula - extract column letters")
    @CsvSource({
            "0.15491227, 0.56155476",
            "0.50765808, 0.694153424",
            "0.73766724285302,0.769641658747220",
            "0.23305634833302,0.592141174710988",
            "0.96193874451072,0.831959812580115",
            "0.28269352178960,0.611294108679485",
            "0.55542998095810,0.710699704761310",
            "0.70774708160695,0.760448833002040",
            "0.56889286302961,0.715285575471301",
            "0.76945556817390,0.779188544190227",
            "0.81570289703775,0.792664959154896",
            "0.11584679780740,0.546113019557935",
            "0.40384340650582,0.656836062539508",
            "0.75464123464397,0.774767862363996",
            "0.23900849419183,0.594450501904757",
            "0.89733650196187,0.815230307821973",
            "0.62106015657471,0.732719978337187",
            "0.04367785060819,0.517419402491009",
            "0.97282083275992,0.834678821592055",
            "0.51966713232573,0.698352200522923",
            "0.74503564964959,0.771874915557647",
            "0.60915961878875,0.728790678298693",
            "0.92770661653297,0.823220112311131",
            "0.78539788546377,0.783889839920999"
    })
    void matchColumnLettersAsNodes(Double input, Double expectedOutput) {
//        double v = ExcelFunctionsTest.NormSDist(input);
//        System.out.println(v);

        double v1 = new NormalDistribution().cumulativeProbability(input);
        //0.6941534241938206 - result
        //0.694153424 - Excel
        System.out.println(expectedOutput + " - Excel result");
        System.out.println(v1 + " - Java Result");
//        System.out.println(v + " - My manual result");


//        System.out.println(0.05 + 0.01);
//
//        double a = 0.03;
//        double b = 0.04;
//        double c = b - a;
//        System.out.println("C = " + c);
//        System.out.println("a * b " + a * b);


    }


    @ParameterizedTest
    @Description("testNORMSINV: Excel's NORMSINV compare with Java ")
    @CsvSource({
            "0.73766724285302,0.63617016901039",
            "0.23305634833302,-0.72881849488865",
            "0.96193874451072,1.77364124382141",
            "0.28269352178960,-0.57485843370617",
            "0.55542998095810,0.13939245007664",
            "0.70774708160695,0.54681499994373",
            "0.56889286302961,0.17355617824150",
            "0.76945556817390,0.73705506503996",
            "0.81570289703775,0.89910974876200",
            "0.11584679780740,-1.19600759401139",
            "0.40384340650582,-0.24341127142849",
            "0.75464123464397,0.68916803347872",
            "0.23900849419183,-0.70949558809314",
            "0.89733650196187,1.26651993738406",
            "0.62106015657471,0.30826632635810",
            "0.04367785060819,-1.70951442934632",
            "0.97282083275992,1.92397007553826",
            "0.51966713232573,0.04931817529830",
            "0.74503564964959,0.65894871691214",
            "0.60915961878875,0.27712938074369",
            "0.92770661653297,1.45892131073238",
            "0.78539788546377,0.79055411969743"
    })
    void testNORMSINV(Double input, Double expectedOutput) {
        double v1 = new NormalDistribution().inverseCumulativeProbability(input);
           System.out.println(expectedOutput + " - Excel result");
        System.out.println(v1 + " - Java Result");
    }



    @ParameterizedTest
    @Description("testNORMSINV: Excel's NORMSINV compare with Java:  =(1 - EXP(-50 * C5))/(1 - EXP(-50))")
    @CsvSource({
            "0.0007,0.03439458374243",
            "0.001,0.04877057549929",
            "0.0013,0.06293253662260",
            "0.0016,0.07688365361336",
            "0.0019,0.09062706553177",
            "0.0022,0.10416586470347",
            "0.0025,0.11750309741541",
            "0.0028,0.13064176460119",
            "0.0031,0.14358482251639",
            "0.0034,0.15633518340362",
            "0.0037,0.16889571614787",
            "0.004,0.18126924692202"
    })
    //(1 - EXP(-50 * C5))/(1 - EXP(-50))
    void testPartTwo_EXP(Double input, Double expectedOutput) {
        double v1 = (1 - Math.exp(-50 * input)) / (1 - Math.exp(-50));
        System.out.println(expectedOutput + " - Excel result");
        System.out.println(v1 + " - Java Result");
    }

    @ParameterizedTest
    @Description("testNORMSINV: Excel's NORMSINV compare with Java:  = (DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))/partOne)^0.5")
    @CsvSource({
            "1.25,0.0007,0.70515918756137,0.646621611,0.03439458374243,0.96560541625757,0.23587264995091,0.64662161100680",
            "1.3,0.001,0.75515918756137,0.634888137,0.04877057549929,0.95122942450071,0.23414753094009,0.63488813681313",
            "1.612,0.0013,0.80515918756137,0.682188884,0.06293253662260,0.93706746337740,0.23244809560529,0.68218888401113",
            "1.253,0.0016,0.85515918756137,0.581494291,0.07688365361336,0.92311634638664,0.23077396156640,0.58149429091088",
            "1.303,0.0019,0.90515918756137,0.574309143,0.09062706553177,0.90937293446823,0.22912475213619,0.57430914254168",
            "1.615,0.0022,0.95515918756137,0.620210589,0.10416586470347,0.89583413529653,0.22750009623558,0.62021058925340",
            "1.256,0.0025,1.00515918756137,0.531294297,0.11750309741541,0.88249690258460,0.22589962831015,0.53129429749800",
            "1.306,0.0028,1.05515918756137,0.526925828,0.13064176460119,0.86935823539881,0.22432298824786,0.52692582829517",
            "1.618,0.0031,1.10515918756137,0.571090608,0.14358482251639,0.85641517748361,0.22276982129803,0.57109060760569",
            "1.259,0.0034,1.15515918756137,0.491047556,0.15633518340362,0.84366481659638,0.22123977799157,0.49104755587448",
            "1.309,0.0037,1.20515918756137,0.488533981,0.16889571614787,0.83110428385213,0.21973251406226,0.48853398131712",
            "1.621,0.004,1.25515918756137,0.530905141,0.18126924692202,0.81873075307798,0.21824769036936,0.53090514112824",
    })
//    (DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))/partOne)^0.5
    void testPartTwo_POW_1(Double dg2, Double cb2, Double partOne, Double expectedOutput, Double v1Expect, Double v2Expect, Double v3Expect, Double v4Expect) {
        double v1 =(1 - Math.exp(-50 * cb2))/(1 - Math.exp(-50)); // ok
        double v2 = 1 - v1; // ok

        // !!!!!!! INCORRECT FORMULA
        // (0.12*v1+0.24*(1-v1))
        double v3 = (0.12*v1+0.24*(1-v1));  //ok


//    (DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))/partOne)^0.5
        double v4 = Math.pow((dg2 * v3 / partOne), 0.5); // 0k


        System.out.println(v4Expect + " - Excel result");
        System.out.println(v4 + " - Java result");
    }


    // !!!! big diffs in here 0.00xx --- Fix
    @ParameterizedTest
    @Description("testNORMSINV: Excel's NORMSINV compare with Java:  = (DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))/partOne)^0.5")
    @CsvSource({
            "1.25,0.0007,0.70515918756137,0.646621611",
            "1.3,0.001,0.75515918756137,0.634888137",
            "1.612,0.0013,0.80515918756137,0.682188884",
            "1.253,0.0016,0.85515918756137,0.581494291",
            "1.303,0.0019,0.90515918756137,0.574309143",
            "1.615,0.0022,0.95515918756137,0.620210589",
            "1.256,0.0025,1.00515918756137,0.531294297",
            "1.306,0.0028,1.05515918756137,0.526925828",
            "1.618,0.0031,1.10515918756137,0.571090608",
            "1.259,0.0034,1.15515918756137,0.491047556",
            "1.309,0.0037,1.20515918756137,0.488533981",
            "1.621,0.004,1.25515918756137,0.530905141"
    })
//    (DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))/partOne)^0.5
    void testPartTwo_POW(Double dg2, Double cb2, Double partOne, Double expectedOutput) {
        double v1 = Math.pow((dg2 * (0.12*
                        (1 - Math.exp(-50 * cb2))/(1 - Math.exp(-50))
                        +0.24*(1 - (1 - Math.exp(-50 * cb2))/(1 - Math.exp(-50))))/ partOne
                )
                , 0.5);

        System.out.println(expectedOutput + " - Excel result");
        System.out.println(v1 + " - Java Result");
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
        double normSDistAAAAA = cjRWA2.getPartOne(cb2, dg2);
        System.out.println("Test accuracy ===========> ");
        System.out.println(expectedOutput + "   |expectedOutput");
        System.out.println(normSDistAAAAA + "   |Java Result");
    }

    @ParameterizedTest
    @Description("Formula - CJ: part2")
    @CsvSource({
//cb2,dg2, ci2, partOne, excel result
            "0.0007, 1.25,0.054, 0.70515918756137, 0.00187643194746",
            "0.0009, 1.3,0.061, 0.69486439283404, 0.00268237705290",

            // my extra border try
            "0.123, 1.612, 0.033, 0.80614729928640, 0.01535620122073",
            "0, 0, 1,1.00000000000000, 0 ", //#NUM！
            "1, 1, 0.88,1, 0" //#NUM！
    })
    void tetCJ_PartTwo(Double cb2, Double dg2, Double ci2, Double partOne, Double expectedOutput) {
        double partTwo = cjRWA2.getPartTwo(ci2, cb2, dg2, partOne);
        System.out.println("Test accuracy ===========> ");
        System.out.println(expectedOutput + "   |expectedOutput");
        System.out.println(partTwo + "   |Java Result");
    }
    @ParameterizedTest
    @Description("Formula - CJ: part3")
    @CsvSource({
//cb2,dg2, ci2, partOne, excel result
            "0.0007, 584, 0.00187643194746, 0.03149568124233",
            "0.0009, 584, 0.00268237705290, 0.04422013288471"
    })
    void tetCJ_PartThree(Double cb2, Double bu2, Double partTwo, Double expectedOutput) {
        double myResult = cjRWA2.getPartThree(cb2, bu2, partTwo);
        System.out.println("Test accuracy ===========> ");
        System.out.println(expectedOutput + "   |expectedOutput");
        System.out.println(myResult + "   |Java Result");
    }
}
