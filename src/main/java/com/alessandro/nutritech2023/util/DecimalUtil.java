package com.alessandro.nutritech2023.util;

import java.text.DecimalFormat;

public class DecimalUtil {

    private static DecimalFormat df = new DecimalFormat("#.##");

    public static String format(Number number) {
        return df.format(number);
    }

}