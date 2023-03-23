package com.hsbc.mkty.rwa.rwa.excel.columns;


import org.apache.commons.math3.distribution.NormalDistribution;

public class cjRWA {
    private static final double DG2 = 0.1294 * Math.log(365) - 0.2426;
    private static final double EXP_50 = Math.exp(-50);

    public static double convert(boolean ch2, boolean ax2, double bt2, double ci2) {
        if (ch2) {
            return 0;
        } else if (ax2) {
            if (bt2 > 0) {
                double d1 = calculateD1(ch2, ci2, bt2);
                double d2 = calculateD2(d1, ch2, ci2, bt2);
                double numerator = ci2 * Math.exp(-d2 * d2 / 2) * d1;
                double denominator = new NormalDistribution().density(0) * Math.sqrt(2 * Math.PI);
                return numerator / denominator - ci2 * (1 - 1.5 * Math.pow(0.11852 - 0.05478 * Math.log(ci2), 2)) * (1 + (365 - bt2) / 365 * (0.11852 - 0.05478 * Math.log(ci2)) * (0.11852 - 0.05478 * Math.log(ci2))) * 12.5 * 1.06;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    private static double calculateD1(boolean ch2, double ci2, double bt2) {
        double numerator = 0.12 * (1 - EXP_50 * Math.pow(ci2 / bt2, 50)) / (1 - EXP_50) + 0.24 * (1 - (1 - EXP_50 * Math.pow(ci2 / bt2, 50)) / (1 - EXP_50));
        double denominator = 1 - DG2 * numerator;
        double logTerm = Math.log(ci2 / bt2) + (numerator - 0.5 * Math.pow(numerator, 2)) / denominator;
        double sqrtTerm = Math.sqrt(numerator / denominator);
        return (ch2 ? -1 : 1) * logTerm / sqrtTerm;
    }

    private static double calculateD2(double d1, boolean ch2, double ci2, double bt2) {
        double numerator = 0.12 * (1 - EXP_50 * Math.pow(ci2 / bt2, 50)) / (1 - EXP_50) + 0.24 * (1 - (1 - EXP_50 * Math.pow(ci2 / bt2, 50)) / (1 - EXP_50));
        double denominator = 1 - DG2 * numerator;
        return ch2 ? -1 : 1 * (d1 - Math.sqrt(numerator / denominator));
    }
}
