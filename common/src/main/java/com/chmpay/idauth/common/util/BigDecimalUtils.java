package com.chmpay.idauth.common.util;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;

public final class BigDecimalUtils {

    public static BigDecimal add(String a, String b) {
        if (StringUtils.isEmpty(a)) {
            a = "0";
        }
        if (StringUtils.isEmpty(b)) {
            b = "0";
        }
        return new BigDecimal(a).add(new BigDecimal(b));
    }

    public static BigDecimal subtract(String a, String b) {
        if (StringUtils.isEmpty(a)) {
            a = "0";
        }
        if (StringUtils.isEmpty(b)) {
            b = "0";
        }
        return new BigDecimal(a).subtract(new BigDecimal(b));
    }

    public static BigDecimal multi(String a, String b, int scale) {
        return new BigDecimal(a).multiply(new BigDecimal(b)).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public static String divScale(Integer a1, Integer b1) {
        if (a1 == null || a1 == 0) {
            return "0.0%";
        } else {
            if (b1 == null || b1 == 0) {
                b1 = 1;
            }
            return new BigDecimal(a1).divide(new BigDecimal(b1), 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(1, BigDecimal.ROUND_HALF_UP).toString() + "%";
        }
    }

    public static BigDecimal zero = new BigDecimal("0");

    public static BigDecimal divide(Integer a1, Integer b1, int scale) {
        if (a1 == null || a1 == 0) {
            return zero;
        } else {
            if (b1 == null || b1 == 0) {
                return zero;
            }
            return new BigDecimal(a1).divide(new BigDecimal(b1), scale, BigDecimal.ROUND_HALF_UP);
        }
    }

    public static BigDecimal multiply(String aDouble, String v) {
        return new BigDecimal(aDouble).multiply(new BigDecimal(v));
    }
}
