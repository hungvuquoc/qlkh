package com.example.qlkh.utils;

import com.example.qlkh.constant.Variables;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EbsNumberUtils {
    public static Double formatValue(Double value, int decimalDigits) {
        if (value == null) {
            return null;
        }
        BigDecimal b = BigDecimal.valueOf(value).setScale(decimalDigits, RoundingMode.HALF_UP);
        return b.doubleValue();
    }

    public static Double formatValue(Double value) {
        return formatValue(value, Variables.DECIMAL_DIGITS_DEFAULT);
    }
}
