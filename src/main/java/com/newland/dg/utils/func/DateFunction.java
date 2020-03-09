package com.newland.dg.utils.func;

import com.newland.dg.utils.DateFormatCache;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFunction {
    private static final String DEFAULT_FORMAT = "yyyyMMddHHmmss";

    public static Date now() {
        return new Date();
    }

    public static String toChar(Date date) {
        SimpleDateFormat dateFormat = DateFormatCache.getOrCreateDateFormat(DEFAULT_FORMAT);
        return dateFormat.format(date);
    }

    public static String toChar(Date date, String format) {
        SimpleDateFormat dft = DateFormatCache.getOrCreateDateFormat(format);
        return dft.format(date);
    }
}
