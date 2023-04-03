package com.hsbc.mkty.rwa.rwa.excel.columns;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class cjRWBig {

    /**
     * CJ formula
     * IF(CH2="T", 0, IF(AX2="T", IF(BT2>0, (CI2*NORMSDIST((1-DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50)))))^-0.5*NORMSINV(CB2)+(DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))/(1-DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))))^0.5*NORMSINV(0.999))-CB2*CI2)*(1-1.5*((0.11852-0.05478*LN(CB2))^2))^-1*(1+(BU2/365-2.5)*((0.11852-0.05478*LN(CB2))^2))*12.5*1.06,0), ""))
     */
    public static BigDecimal getRWNewMethodology(String ch2, String ax2, BigDecimal bt2, BigDecimal ci2, BigDecimal cb2, BigDecimal dg2, BigDecimal bu2) {
        BigDecimal result = null;
//        if (ch2.equals("T")) {
//            result = BigDecimal.ZERO;
//        } else if (ax2.equals("T")) {
//            final NormalDistribution normalDis = new NormalDistribution();
//            final NormalDistribution normal = new NormalDistribution();
//            BigDecimal partOne = getPartOne(cb2, dg2);
//            BigDecimal normSInv = new BigDecimal(normalDis.inverseCumulativeProbability(0.999));
//            BigDecimal innerExpression = BigDecimal.ONE.divide((partOne.multiply(new BigDecimal(-1)).multiply(new BigDecimal(0.5))), 20, RoundingMode.HALF_UP).multiply(new BigDecimal(normal.inverseCumulativeProbability(cb2))).add(dg2.multiply(new BigDecimal(0.12).multiply(BigDecimal.ONE.subtract(new BigDecimal(Math.exp(-50 * cb2)))).divide(BigDecimal.ONE.subtract(new BigDecimal(Math.exp(-50))).add(new BigDecimal(0.24).multiply(BigDecimal.ONE.subtract(BigDecimal.ONE.subtract(new BigDecimal(Math.exp(-50 * cb2)))).divide(BigDecimal.ONE.subtract(new BigDecimal(Math.exp(-50))))).divide(partOne, 20, RoundingMode.HALF_UP))));
//            BigDecimal partTwo = getPartTwo(ci2, cb2, dg2, partOne);
//            BigDecimal partThree = getPartThree(cb2, bu2, partTwo);
//            result = partThree;
//        } else {
//            result = BigDecimal.ZERO;
//        }
        return result;
    }

    /**
     * CJ: part1
     * (1-DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50)))))
     *
     * @param cb2 0.0007
     * @param dg2 1.25
     * @return
     */

    public static BigDecimal getPartOne(BigDecimal cb2, BigDecimal dg2) {
        BigDecimal partOne = new BigDecimal(String.valueOf(new NormalDistribution().cumulativeProbability(1 - dg2.doubleValue() * (0.12 * (1 - Math.exp(-50 * cb2.doubleValue())) / (1 - Math.exp(-50)) + 0.24 * (1 - (1 - Math.exp(-50 * cb2.doubleValue())) / (1 - Math.exp(-50)))))));
        return partOne;
    }


    /**
     * CJ:  part2
     * (CI2*NORMSDIST(partOne^-0.5*NORMSINV(CB2)+(DG2*(0.12*(1-EXP(-50*CB2))/(1-EXP(-50))+0.24*(1-(1-EXP(-50*CB2))/(1-EXP(-50))))/partOne)^0.5*NORMSINV(0.999))-CB2*CI2)
     *
     * @param ci2
     * @param cb2
     * @param dg2
     * @param partOne
     */

    public static BigDecimal getPartTwo(BigDecimal ci2, BigDecimal cb2, BigDecimal dg2, BigDecimal partOne) {

        // double:
//        final NormalDistribution normalDis = new NormalDistribution();
//        final NormalDistribution normal = new NormalDistribution();
//        double myPart2 = (ci2 *
//                normal.cumulativeProbability(Math.pow(partOne, -0.5)* normalDis.inverseCumulativeProbability(cb2) +
//                        Math.pow((dg2 * (0.12*
//                                        (1 - Math.exp(-50 * cb2))/(1 - Math.exp(-50))
//                                        +0.24*(1 - (1-Math.exp(-50* cb2)/(1-Math.exp(-50))))/ partOne
//                                ))
//                                , 0.5)
//                                * normalDis.inverseCumulativeProbability(0.999))
//                - cb2 * ci2);
//
//        return myPart2;
//
//

// new error
//        final NormalDistribution normalDis = new NormalDistribution();
//        final NormalDistribution normal = new NormalDistribution();
//        BigDecimal term1 = partOne.pow(-0.5)
//                .multiply(new BigDecimal(normalDis.inverseCumulativeProbability(cb2)))
//                .add(dg2.multiply(new BigDecimal(0.12)
//                        .multiply(new BigDecimal(1 - Math.exp(-50 * cb2)))
//                        .divide(new BigDecimal(1 - Math.exp(-50)), MathContext.DECIMAL64)
//                        .add(new BigDecimal(0.24)
//                                .multiply(new BigDecimal(1 - (1 - Math.exp(-50 * cb2)) / (1 - Math.exp(-50))))
//                                .divide(partOne, MathContext.DECIMAL64))));
//        BigDecimal term2 = term1.multiply(term1).add(BigDecimal.valueOf(0.999))
//                .sqrt(MathContext.DECIMAL64)
//                .multiply(new BigDecimal(normalDis.inverseCumulativeProbability(0.999)));
//        BigDecimal myPart2 = ci2.multiply(term2).subtract(cb2.multiply(ci2));
//        return myPart2;

        return null;
    }

    /**
     * partTwo * ( 1 - 1.5 * ( ( 0.11852 - 0.05478 *
     * LN(
     * CB2
     * ) ) ^ 2 ) ) ^- 1 * ( 1 + ( BU2 / 365 - 2.5 ) * ( ( 0.11852 - 0.05478 *
     * LN(
     * CB2
     * ) ) ^ 2 ) ) * 12.5 * 1.06
     * simplify:
     * partTwo * ( 1 - 1.5 * ( a ) ^ 2 ) ) ^- 1 * ( 1 + ( BU2 / 365 - 2.5 ) * (a) ^ 2 ) ) * 12.5 * 1.06
     *
     * @param partTwo
     * @param cb2
     * @param bu2
     * @return
     */
    public static BigDecimal getPartThree(BigDecimal cb2, BigDecimal bu2, BigDecimal partTwo) {
        BigDecimal a = new BigDecimal(0.11852).subtract(new BigDecimal(0.05478).multiply(new BigDecimal(Math.log(cb2.doubleValue()))));
        BigDecimal result = partTwo.multiply(BigDecimal.ONE.subtract(new BigDecimal(1.5).multiply(a.pow(2)))).pow(-1).multiply(new BigDecimal(1).add(bu2.divide(new BigDecimal(365), 20, RoundingMode.HALF_UP).subtract(new BigDecimal(2.5)).multiply(a.pow(2)))).multiply(new BigDecimal(12.5)).multiply(new BigDecimal(1.06));
        return result;
    }
}
