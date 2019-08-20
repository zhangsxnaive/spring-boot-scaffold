package com.chmpay.idauth.common.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhangsx
 * @date 2018/9/18
 */
public class DateUtil {


    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /** 存放不同的日期模板格式的sdf的Map */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<>();

    public final static String MDHMSS = "MMddHHmmssSSS";
    public final static String Y2MDHMSS = "yyMMddHHmmss";
    public final static String YMD = "yyyyMMdd";
    public final static String YMD_ = "yyyy-MM-dd";
    public final static String HMS = "HHmmss";
    public final static String HMSc = "HH:mm:ss";
    public final static String YMDHMS = "yyyyMMddHHmmss";
    public final static String YM = "yyMM";
    public final static String YMDHMS_ = "yyyy-MM-dd HH:mm:ss";
    public final static String YM_ = "yyyy-MM";
    public final static String Y = "yyyy";
    public final static String M = "MM";
    public final static String D = "dd";
    public final static String YYMM = "yyyyMM";
    
    
    public static List<Date> getBetweenDates(Date start, Date end,boolean inCludeStart,boolean inCludeEnd) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
  
        if(!inCludeStart){
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        if(inCludeEnd){
        	 while (tempStart.before(tempEnd)||tempStart.equals(tempEnd)) {
                 result.add(tempStart.getTime());
                 tempStart.add(Calendar.DAY_OF_YEAR, 1);
             }
        }else{
        	 while (tempStart.before(tempEnd)) {
                 result.add(tempStart.getTime());
                 tempStart.add(Calendar.DAY_OF_YEAR, 1);
             }
        }
       
        return result;
    }

    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     *
     * @param pattern
     *            SimpleDateFormat规则
     * @return 该实例
     */
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);
        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (tl == null) {
            synchronized (DateUtil.class) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    // 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
                    logger.debug("put new sdf of pattern " + pattern + " to map");
                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new
                    // SimpleDateFormat
                    tl = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            logger.debug("thread: " + Thread.currentThread() + " init pattern: " + pattern);
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, tl);
                }
            }
        }
        return tl.get();
    }

    /**
     * 为指定时间按照相应日历字段增加时间
     *
     * @param date
     *            初始时间
     * @param time
     *            要增加的时间
     * @param filed
     *            日历字段 参考Calendar的静态字段
     * @return 修改后的时间
     */
    public static Date addDate(Date date, int time, int filed) {
        Calendar calendar = Calendar.getInstance(Locale.CHINESE);
        calendar.setTime(date);
        calendar.add(filed, time);
        return calendar.getTime();
    }

    /**
     * 获取今年最初一时刻
     *
     * @return 2016-01-01 00:00:00
     */
    public static String getYearTimeBegin() {
        // 获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_YEAR, 1);// 设置为1号,当前日期既为本月第一天
        String first = getSdf(YMD_).format(c.getTime());
        return first += " 00:00:00";
    }

    /**
     * 获取今年最后一时刻
     *
     * @return 2016-12-31 23:59:59
     */
    public static String getYearTimeLast() {
        // 获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.set(Calendar.DAY_OF_YEAR, ca.getActualMaximum(Calendar.DAY_OF_YEAR));
        String last = getSdf(YMD_).format(ca.getTime());
        return last += " 23:59:59";
    }

    /**
     * 时间字符串转换成给定格式的时间字符串
     *
     * @param timeStr
     *            时间串
     * @param timesdf
     *            初始化的字符串格式
     * @param formatesdf
     *            指定的格式
     * @return
     */
    public static String formateStr(String timeStr, String timesdf, String formatesdf) {
        return formate(parseStrDate(timeStr, timesdf), formatesdf);
    }

    /**
     * 格局给定的SDF格式化时间
     *
     * @param date
     *            时间
     * @param sdf
     *            指定转换格式
     * @return 转换后的串
     */
    public static String formate(Date date, String sdf) {
        return getSdf(sdf).format(date);
    }

    /**
     * 把字符串按照指定格式转换
     *
     * @param str
     *            时间串
     * @param sdf
     *            给定转换格式
     * @return 转换后的时间
     */
    public static Date parseStrDate(String str, String sdf) {

        Date date = null;
        try {
            date = parseCanThrow(str, sdf);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 把字符串按照指定格式转换
     *
     * @param str
     *            时间串
     * @param sdf
     *            给定转换格式
     * @return 转换后的时间
     * @throws ParseException
     *             转换失败则抛出异常
     */
    public static Date parseCanThrow(String str, String sdf) throws ParseException {
        if (StringUtils.isEmpty(str)) {
            return new Date();
        }
        return getSdf(sdf).parse(str);
    }

    /**
     * 转换为指定格式
     *
     * @return 20121103105122/2012-12-22 2012-11-03 10:51:22/2012-12-22
     */
    public static String StringpaseDate(String stringDate) {
        if (stringDate.length() == 8) {
            return stringDate.substring(0, 4) + "-" + stringDate.substring(4, 6) + "-" + stringDate.substring(6, 8);
        } else if (stringDate.length() == 14) {
            return stringDate.substring(0, 4) + "-" + stringDate.substring(4, 6) + "-" + stringDate.substring(6, 8)
                    + " " + stringDate.substring(8, 10) + ":" + stringDate.substring(10, 12) + ":"
                    + stringDate.substring(12, 14);
        } else {
            return "";
        }
    }

    /**
     * 获取前一天的开始时间
     *
     * @param sdf
     *            格式化输出
     * @return 格式化结果
     */
    public static String getStartOfYesterday(String sdf) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        return getSdf(sdf).format(date);
    }

    public static Date getStartOfYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 获取前一天的结束时间
     *
     * @param sdf
     *            格式化输出
     * @return 格式化结果
     */
    public static String getEndOfYesterday(String sdf) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.add(Calendar.DATE, -1);
        Date date = calendar.getTime();
        return getSdf(sdf).format(date);
    }

    public static Date getEndOfYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.add(Calendar.DATE, -1);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 获取后一天的开始时间
     *
     * @param sdf
     *            格式化输出
     * @return 格式化结果
     */
    public static String getStartOfNextday(String sdf) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, +1);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        return getSdf(sdf).format(date);
    }

    public static String getYymm(Date date, int calendarType, int adds) {
        Calendar calendar = Calendar.getInstance(Locale.CHINESE);
        calendar.setTime(date);
        calendar.add(calendarType, adds);
        return getSdf(YM).format(calendar.getTime());
    }

    public static String getYymmdd_(Date date, int calendarType, int adds) {
        Calendar calendar = Calendar.getInstance(Locale.CHINESE);
        calendar.setTime(date);
        calendar.add(calendarType, adds);
        return getSdf(YMD_).format(calendar.getTime());
    }

    // 获取给定时间的下一天

    @SuppressWarnings("static-access")
    public static String getnextDay(String data, String format) {
        Date dataValue = null;

        try {
            dataValue = DateUtil.parseCanThrow(data, format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dataValue == null) {
            return null;
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dataValue);
        // 把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        calendar.add(Calendar.DATE, +1);
        dataValue = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(dataValue);
        return dateString;
    }

    @SuppressWarnings("static-access")
    public static String getBeforeDay(String data, String format) {
        Date dataValue = null;

        try {
            dataValue = DateUtil.parseCanThrow(data, format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dataValue == null) {
            return null;
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dataValue);
        // 把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        calendar.add(Calendar.DATE, -1);
        dataValue = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(dataValue);
        return dateString;
    }

    /**
     * @param date_ym
     *            yyyyMM input yyyyMM date return the max day of month
     * @return yyyyMMdd
     */
    public static String getLastDayMonth(String date_ym) {
        if (StringUtils.isEmpty(date_ym) || date_ym.length() != 6) {
            return null;
        }
        String dd = getMaxByMonth(date_ym);
        return date_ym.concat(dd);
    }

    /**
     * 获取太阳日(例如 2017/05/18 return 17138)
     *
     * @param date
     * @return
     */
    public static String getSolarDay(Date date) {
        String value = "";
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        String year = String.valueOf(ca.get(Calendar.YEAR)).substring(2, 4);
        int days = ca.get(Calendar.DAY_OF_YEAR);
        if (days > 10 && days < 100) {
            value = "0" + days;
        } else if (days < 10) {
            value = "00" + days;
        } else {
            value = "" + days;
        }
        value = year + value;
        return value;
    }

    private static String getMaxByMonth(String date_ym) {
        String mm = date_ym.substring(4);
        String yyyy = date_ym.substring(0, 4);
        return getMonthDayMax(yyyy, mm);
    }

    private static String getMonthDayMax(String yyyy, String mm) {
        String[] maxmonths = { "01", "03", "05", "07", "08", "10", "12" };
        String[] littemonths = { "04", "06", "09", "11" };

        for (String string : maxmonths) {
            if (mm.equals(string)) {
                return "31";
            }
        }
        for (String string : littemonths) {
            if (mm.equals(string)) {
                return "30";
            }
        }
        int year = Integer.parseInt(yyyy);
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            return "29";
        }

        return "28";
    }

    /**
     * 字符串的日期格式的计算
     *
     * @param smdate
     *            较小的日期
     * @param bdate
     *            较大的日期
     * @sdf 日期格式
     * @author hzl
     */
    public static int daysBetween(String smdate, String bdate, SimpleDateFormat sdf) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days)) + 1;
    }

    public static String getCurrentDate(String pattern) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(cal.getTime());
    }

    /**
     * 获取当前毫秒值的后五位
     * @return
     */
    public synchronized static String getLast5TimeMillis() {
        String order = String.valueOf(System.currentTimeMillis());
        return order.substring(order.length() - 5, order.length());
    }
}
