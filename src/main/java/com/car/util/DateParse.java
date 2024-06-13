package com.car.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParse {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 生成当前日期的格式化字符串
    public static String genDate() {
        Date date = new Date();
        return dateFormat.format(date);
    }

    // 将格式化的日期字符串解析为Date对象
    public static Date parseDate(String dateString) throws ParseException {
        return dateFormat.parse(dateString);
    }
}
