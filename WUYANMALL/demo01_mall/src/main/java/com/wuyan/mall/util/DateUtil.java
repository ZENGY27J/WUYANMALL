package com.wuyan.mall.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
     public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }
}
