package com.newland.dg.utils;

import java.text.SimpleDateFormat;

public class DateFormatCache {
    private static int maxSize = Integer.valueOf(System.getProperty("groovy.date_format.cache.max", "256"));
    private static ThreadLocal<LRUMap<String, SimpleDateFormat>> formatCache = new ThreadLocal<>();

    public DateFormatCache() {
    }

    public static SimpleDateFormat getOrCreateDateFormat(String format) {
        LRUMap<String, SimpleDateFormat> cache = formatCache.get();
        if (cache == null) {
            cache = new LRUMap<>(maxSize);
            formatCache.set(cache);
        }

        SimpleDateFormat rt = (SimpleDateFormat) cache.get(format);
        if (rt == null) {
            rt = new SimpleDateFormat(format);
            cache.put(format, rt);
        }

        return rt;
    }
}