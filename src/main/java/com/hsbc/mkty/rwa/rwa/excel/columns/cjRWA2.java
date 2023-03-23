package com.hsbc.mkty.rwa.rwa.excel.columns;


import org.apache.commons.math3.distribution.NormalDistribution;

public class cjRWA2 {
    // CJ formula
    // IF(CH2="T", 0, IF(AX2="T", IF(BT2>0, (CI2*NORMSDIST((1-DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50)))))^-0.5*NORMSINV(CB2)+(DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))/(1-DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))))^0.5*NORMSINV(0.999))-CB2*CI2)*(1-1.5*((0.11852-0.05478*LN(CB2))^2))^-1*(1+(BU2/365-2.5)*((0.11852-0.05478*LN(CB2))^2))*12.5*1.06,0), ""))

    public static double myMethod(String ch2, String ax2, double bt2, double ci2, double cb2, double dg2, double bu2) {
        double result;

        //  NumberUtils.	toScaledBigDecimal
        if (ch2.equals("T")) {
            result = 0;
        } else if (ax2.equals("T")) {
            final NormalDistribution normalDis = new NormalDistribution();
            final NormalDistribution normal = new NormalDistribution();

            // (1-DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50)))))
            double normSDistAAAAA = getNormSDistAAAAA(cb2, dg2);
            double normSInv = new NormalDistribution().inverseCumulativeProbability(0.999);
            //(CI2*NORMSDIST(normSDistAAAAA^-0.5*NORMSINV(CB2)+(DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))/normSDistAAAAA)^0.5*NORMSINV(0.999))-CB2*CI2)
            double innerExpression = Math.pow((ci2 * normSDistAAAAA * normSInv + dg2 * (0.12 * (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50)) + 0.24 * (1 - (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50)))) / (1 - dg2 * (0.12 * (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50)) + 0.24 * (1 - (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50)))))), -0.5);


            double term1 = ci2 * (1 - normal.cumulativeProbability((Math.sqrt(2) / 2 * normal.inverseCumulativeProbability(cb2 / normSDistAAAAA) + dg2 * (0.12 * (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50)) + 0.24 * (1 - (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50))))) / Math.sqrt(normSDistAAAAA)));


            partTwo(ci2, cb2, dg2, normSDistAAAAA);

            result = innerExpression * (1 - 1.5 * Math.pow(0.11852 - 0.05478 * Math.log(cb2), 2)) * (1 + (bu2 / 365 - 2.5) * Math.pow(0.11852 - 0.05478 * Math.log(cb2), 2)) * 12.5 * 1.06;
        } else {
            result = 0;
        }
        return result;
    }

    // IF(CH2="T", 0, IF(AX2="T", IF(BT2>0, (CI2*NORMSDIST((1-DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50)))))^-0.5*NORMSINV(CB2)+(DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))/(1-DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))))^0.5*NORMSINV(0.999))-CB2*CI2)*(1-1.5*((0.11852-0.05478*LN(CB2))^2))^-1*(1+(BU2/365-2.5)*((0.11852-0.05478*LN(CB2))^2))*12.5*1.06,0), ""))
    /**
     * CJ: part1
     *  (1-DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50)))))
     * @param cb2 0.0007
     * @param dg2 1.25
     * @return
     */
    public static double getNormSDistAAAAA(double cb2, double dg2) {
//        double normSDistAAAAA = new NormalDistribution().cumulativeProbability(1 - dg2 * (0.12 * (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50)) + 0.24 * (1 - (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50)))));
        double normSDistAAAAA = (1 - dg2 * (0.12 * (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50)) + 0.24 * (1 - (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50)))));
        return normSDistAAAAA;
    }

    /**
     * CJ:  part2
     * (CI2*NORMSDIST(normSDistAAAAA^-0.5*NORMSINV(CB2)+(DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))/normSDistAAAAA)^0.5*NORMSINV(0.999))-CB2*CI2)
     * @param ci2
     * @param cb2
     * @param dg2
     * @param normSDistAAAAA
     */
    public static double partTwo(double ci2, double cb2, double dg2, double normSDistAAAAA) {
        //            (CI2*
//                    NORMSDIST(normSDistAAAAA^-0.5*NORMSINV(CB2)+
//                            (DG2*(0.12*
//                                    (1-EXP(-50*CB2))/(1-EXP(-50))
//                                    +0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))/normSDistAAAAA)
//                                ^0.5
//                            *NORMSINV(0.999))
//                    -CB2*CI2)
        final NormalDistribution normalDis = new NormalDistribution();
        final NormalDistribution normal = new NormalDistribution();
        double myPart2 = (ci2 *
                normal.cumulativeProbability(Math.pow(normSDistAAAAA, -0.5)* normalDis.inverseCumulativeProbability(cb2) +
                                Math.pow((dg2 * (0.12*
                                        (1 - Math.exp(-50 * cb2))/(1 - Math.exp(-50))
                                        +0.24*(1 - (1-Math.exp(-50* cb2)/(1-Math.exp(-50))))/ normSDistAAAAA
                                ))
                                        , 0.5)
                                * normalDis.inverseCumulativeProbability(0.999))
                - cb2 * ci2);

        return myPart2;
    }
}
