package com.chmpay.idauth.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtils {

    public static String getStrDateFormat(String val,String pattern) {
        try {
//        parseStrDate(month, "yyyy-MM")
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date date = simpleDateFormat.parse(val);
            return simpleDateFormat.format(date);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getStrDateFormat("2016-12","yyyy-MM"));
        System.out.println(getStrDateFormat("2016-12-12","yyyy-MM"));
        System.out.println(getStrDateFormat("2016-12-12 00:00:00","yyyy-MM"));
        System.out.println(getStrDateFormat("20161212 00:00:00","yyyy-MM"));
    }
}
