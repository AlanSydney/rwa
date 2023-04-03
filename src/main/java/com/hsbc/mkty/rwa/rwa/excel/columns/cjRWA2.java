package com.hsbc.mkty.rwa.rwa.excel.columns;


import org.apache.commons.math3.distribution.NormalDistribution;

import java.math.BigDecimal;

public class cjRWA2 {
    // CJ formula
    // IF(CH2="T", 0, IF(AX2="T", IF(BT2>0, (CI2*NORMSDIST((1-DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50)))))^-0.5*NORMSINV(CB2)+(DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))/(1-DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))))^0.5*NORMSINV(0.999))-CB2*CI2)*(1-1.5*((0.11852-0.05478*LN(CB2))^2))^-1*(1+(BU2/365-2.5)*((0.11852-0.05478*LN(CB2))^2))*12.5*1.06,0), ""))

    public static double getRWNewMethodology(String ch2, String ax2, double bt2, double ci2, double cb2, double dg2, double bu2) {
        double result;

        //  NumberUtils.	toScaledBigDecimal
        if (ch2.equals("T")) {
            result = 0;
        } else if (ax2.equals("T")) {
            final NormalDistribution normalDis = new NormalDistribution();
            final NormalDistribution normal = new NormalDistribution();

            // (1-DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50)))))
            double partOne = getPartOne(cb2, dg2);
            double normSInv = new NormalDistribution().inverseCumulativeProbability(0.999);
            //(CI2*NORMSDIST(partOne^-0.5*NORMSINV(CB2)+(DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))/partOne)^0.5*NORMSINV(0.999))-CB2*CI2)
            double innerExpression = Math.pow((ci2 * partOne * normSInv + dg2 * (0.12 * (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50)) + 0.24 * (1 - (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50)))) / (1 - dg2 * (0.12 * (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50)) + 0.24 * (1 - (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50)))))), -0.5);

            double partTwo = getPartTwo(ci2, cb2, dg2, partOne);
            double partThree = getPartThree(cb2, bu2, partTwo);

            result = partThree;
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
    public static double getPartOne(double cb2, double dg2) {
//        double partOne = new NormalDistribution().cumulativeProbability(1 - dg2 * (0.12 * (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50)) + 0.24 * (1 - (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50)))));
        double partOne = (1 - dg2 * (0.12 * (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50)) + 0.24 * (1 - (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50)))));
        return partOne;
    }

    /**
     * CJ:  part2
     * (CI2*NORMSDIST(partOne^-0.5*NORMSINV(CB2)+(DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))/partOne)^0.5*NORMSINV(0.999))-CB2*CI2)
     * @param ci2
     * @param cb2
     * @param dg2
     * @param partOne
     */
    public static double getPartTwo(double ci2, double cb2, double dg2, double partOne) {
//                    (CI2*
//                    NORMSDIST(partOne^-0.5*NORMSINV(CB2)+
//                            (DG2*(0.12*
//                                    (1-EXP(-50*CB2))/(1-EXP(-50))
//                                    +0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))/partOne)
//                                ^0.5
//                            *NORMSINV(0.999))
//                    -CB2*CI2)
        final NormalDistribution normalDis = new NormalDistribution();
        final NormalDistribution normal = new NormalDistribution();
        double myPart2 = (ci2 *
                normal.cumulativeProbability(Math.pow(partOne, -0.5)* normalDis.inverseCumulativeProbability(cb2) +
                                Math.pow((dg2 * (0.12*
                                        (1 - Math.exp(-50 * cb2))/(1 - Math.exp(-50))
                                        +0.24*(1 - (1-Math.exp(-50* cb2))/(1-Math.exp(-50))))/ partOne)
                                        , 0.5)
                                * normalDis.inverseCumulativeProbability(0.999))
                - cb2 * ci2);

        return myPart2;
    }

    /**
     *
     * partTwo * ( 1 - 1.5 * ( ( 0.11852 - 0.05478 *
     *                         LN(
     *                             CB2
     *                         ) ) ^ 2 ) ) ^- 1 * ( 1 + ( BU2 / 365 - 2.5 ) * ( ( 0.11852 - 0.05478 *
     *                         LN(
     *                             CB2
     *                         ) ) ^ 2 ) ) * 12.5 * 1.06
     *
     * simplify:
     * partTwo * ( 1 - 1.5 * ( a ) ^ 2 ) ) ^- 1 * ( 1 + ( BU2 / 365 - 2.5 ) * (a) ^ 2 ) ) * 12.5 * 1.06
     * @param partTwo
     * @param cb2
     * @param bu2
     * @return
     */
    public static double getPartThree( double cb2, double bu2, double partTwo){
        double a = 0.11852 - 0.05478 * Math.log(cb2);
        double result = partTwo * Math.pow(1 - 1.5 * Math.pow(a, 2), -1) * (1 + (bu2 / 365 -2.5)*Math.pow(a,2)) * 12.5 * 1.06;

        return result;
    }
}

