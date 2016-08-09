package com.jade.library.utils;

import android.text.TextUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期时间处理工具类
 */
public class DateUtil {

    /**
     * Date format pattern this is often used.
     */
    public static final String PATTERN_YMD = "yyyy-MM-dd";

    /**
     * Date format pattern this is often used.
     */
    public static final String PATTERN_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_VALIDATE = "yyyy/MM/dd HH-mm-ss";

    /**
     * Date format pattern this is often used.
     */
    public static final String PATTERN_YMD2 = "yyyy/MM/dd";

    /**
     * Date format pattern this is often used.
     */
    public static final String PATTERN_E = "E";

    /**
     * Date format pattern this is often used.
     */
    public static final String PATTERN_EEEE = "EEEE";

    /**
     * Date format pattern this is often used.
     */
    public static final String PATTERN_HM = "HH:mm";

    /**
     * 中文大写年月日
     */
    public static final String CN_UPPER_LONG = "cn_upper_long";

    /**
     * 中文大写年月
     */
    public static final String CN_UPPER_SHORT = "cn_upper_short";


    public static final String PATTERN_LONG_CN = "cn_long";
    public static final String PATTERN_SHORT_CN = "cn_short";
    public static final String PATTERN_LONG_EN = "en_long";
    public static final String PATTERN_SHORT_EN = "en_short";
    public static final String PATTERN_MD = "M月d日";

    /**
     * Formats the given date according to the YMD pattern.
     *
     * @param date The date to format.
     * @return An YMD formatted date string.
     * @see #PATTERN_YMD
     */
    public static String formatDate(Date date) {
        return formatDate(date, PATTERN_YMD);
    }

    public static String format(Date date, String pattern) {
        return formatDate(date, pattern);
    }

    /**
     * Formats the given date according to the YMD pattern.
     *
     * @param pattern         The pattern to format.
     * @param currentLanguage The location to format.
     * @return An YMD formatted date string.
     * @see #PATTERN_YMD
     */
    public static String formatDate(String pattern, String currentLanguage) {
        return formatDate(0, pattern, currentLanguage);
    }

    /**
     * Formats the given date according to the YMD pattern.
     *
     * @param pattern         The pattern to format.
     * @param currentLanguage The location to format.
     * @return An YMD formatted date string.
     * @see #PATTERN_YMD
     */
    public static String formatTime(String pattern, String currentLanguage) {
        return formatTime(0, pattern, currentLanguage);
    }

    /**
     * Formats the given date according to the Week  pattern.
     *
     * @param pattern         The pattern to format.
     * @param currentLanguage The location to format.
     * @return An YMD formatted date string.
     * @see #PATTERN_YMD
     */
    public static String formatWeek(String pattern, String currentLanguage) {
        return formatWeek(0, pattern, currentLanguage);
    }

    /**
     * Formats the given date according to the YMD pattern.
     *
     * @param pattern         The pattern to format.
     * @param currentLanguage The location to format.
     * @return An YMD formatted date string.
     * @see #PATTERN_YMD
     */
    public static String formatDate(long mills, String pattern, String currentLanguage) {
        try {
            if (TextUtils.isEmpty(pattern)) {
                pattern = PATTERN_YMD2;
            }

            if (TextUtils.equals(pattern, CN_UPPER_LONG)) {
                return mills <= 0 ? dateToUpper(new Date()) : dateToUpper(new Date(mills));
            }

            if (TextUtils.equals(pattern, CN_UPPER_SHORT)) {
                return mills <= 0 ? dateToUpperShort(new Date()) : dateToUpperShort(new Date(mills));
            }

            if (TextUtils.equals(currentLanguage, "en")) {
                return mills <= 0 ? formatDate(new Date(), pattern, Locale.ENGLISH) : formatDate(new Date(mills), pattern, Locale.ENGLISH);
            }
            return mills <= 0 ? formatDate(new Date(), pattern, Locale.CHINESE) : formatDate(new Date(mills), pattern, Locale.CHINESE);

        } catch (Exception e) {
            return formatDate(new Date(), PATTERN_YMD2, Locale.CHINESE);
        }
    }

    /**
     * Formats the given date according to the YMD pattern.
     *
     * @param pattern         The pattern to format.
     * @param currentLanguage The location to format.
     * @return An YMD formatted date string.
     * @see #PATTERN_YMD
     */
    public static String formatTime(long mills, String pattern, String currentLanguage) {
        try {
            if (TextUtils.isEmpty(pattern)) {
                pattern = PATTERN_HM;
            }

            if (TextUtils.equals(currentLanguage, "en")) {
                return mills <= 0 ? formatDate(new Date(), pattern, Locale.ENGLISH) : formatDate(new Date(mills), pattern, Locale.ENGLISH);
            }
            return mills <= 0 ? formatDate(new Date(), pattern, Locale.CHINESE) : formatDate(new Date(mills), pattern, Locale.CHINESE);
        } catch (Exception e) {
            return formatDate(new Date(), PATTERN_HM, Locale.CHINESE);
        }
    }

    public static int getWeekIndex(long dateMill) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(dateMill));

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        if (w == 0) {
            w = 7;
        }
        return w;
    }


    public static long getTimeMillis(String time) {
        if (time != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN_YMDHMS);
            try {
                Date date = simpleDateFormat.parse(time);
                return date.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return System.currentTimeMillis();
    }

    /**
     * Formats the given date according to the Week  pattern.
     *
     * @param pattern         The pattern to format.
     * @param currentLanguage The location to format.
     * @return An YMD formatted date string.
     * @see #PATTERN_YMD
     */
    public static String formatWeek(long mills, String pattern, String currentLanguage) {
        try {
            if (TextUtils.isEmpty(pattern)) {
                pattern = PATTERN_EEEE;
            }

            if (TextUtils.equals(currentLanguage, "en")) {
                return mills <= 0 ? formatDate(new Date(), pattern, Locale.ENGLISH) : formatDate(new Date(mills), pattern, Locale.ENGLISH);
            }

            return mills <= 0 ? formatDate(new Date(), pattern, Locale.CHINESE) : formatDate(new Date(mills), pattern, Locale.CHINESE);
        } catch (Exception e) {
            return formatDate(new Date(), PATTERN_EEEE, Locale.CHINESE);
        }
    }


    /**
     * 获取当前日期是星期几
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt, String pattern) {

        String[] weekDays = null;
        switch (pattern) {
            default:
            case PATTERN_LONG_CN:
                weekDays = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
                break;
            case PATTERN_SHORT_CN:
                weekDays = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
                break;
            case PATTERN_LONG_EN:
                weekDays = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday ", "Friday", "Saturday "};
                break;
            case PATTERN_SHORT_EN:
                weekDays = new String[]{"Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat"};
                break;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    /**
     * Formats the given date according to the specified pattern. The pattern
     * must conform to that used by the {@link SimpleDateFormat simple date
     * format} class.
     *
     * @param date    The date to format.
     * @param pattern The pattern to use for formatting the date.
     * @return A formatted date string.
     * @throws IllegalArgumentException If the given date pattern is invalid.
     * @see SimpleDateFormat
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null)
            throw new IllegalArgumentException("date is null");
        if (pattern == null) {
            pattern = DateUtil.PATTERN_YMD2;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     * Formats the given date according to the specified pattern. The pattern
     * must conform to that used by the {@link SimpleDateFormat simple date
     * format} class.
     *
     * @param date    The date to format.
     * @param pattern The pattern to use for formatting the date.
     * @param locale  The locale to use for formatting the date.
     * @return A formatted date string.
     * @throws IllegalArgumentException If the given date pattern is invalid.
     * @see SimpleDateFormat
     */
    public static String formatDate(Date date, String pattern, Locale locale) {
        if (date == null)
            throw new IllegalArgumentException("date is null");
        if (pattern == null) {
            pattern = DateUtil.PATTERN_YMD2;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
        return formatter.format(date);
    }

    public static String dateToUpper(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH) + 1;
        int day = ca.get(Calendar.DAY_OF_MONTH);
        return numToUpper(year) + "年" + monthToUpper(month) + "月" + dayToUpper(day) + "日";
    }

    public static String dateToUpperShort(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH) + 1;
        return numToUpper(year) + "年" + monthToUpper(month) + "月";
    }

    /***
     * <b>function:</b> 将数字转化为大写
     *
     * @param num 数字
     * @return 转换后的大写数字
     * @createDate 2010-5-27 上午10:28:12
     */
    public static String numToUpper(int num) {
        String u[] = {"〇", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        char[] str = String.valueOf(num).toCharArray();
        String rstr = "";
        for (int i = 0; i < str.length; i++) {
            rstr = rstr + u[Integer.parseInt(str[i] + "")];
        }
        return rstr;
    }

    /***
     * <b>function:</b> 月转化为大写
     *
     * @param month 月份
     * @return 返回转换后大写月份
     * @createDate 2010-5-27 上午10:41:42
     */
    public static String monthToUpper(int month) {
        if (month < 10) {
            return numToUpper(month);
        } else if (month == 10) {
            return "十";
        } else {
            return "十" + numToUpper(month - 10);
        }
    }

    /***
     * <b>function:</b> 日转化为大写
     *
     * @param day 日期
     * @return 转换大写的日期格式
     * @createDate 2010-5-27 上午10:43:32
     */
    public static String dayToUpper(int day) {
        if (day < 20) {
            return monthToUpper(day);
        } else {
            char[] str = String.valueOf(day).toCharArray();
            if (str[1] == '0') {
                return numToUpper(Integer.parseInt(str[0] + "")) + "十";
            } else {
                return numToUpper(Integer.parseInt(str[0] + "")) + "十" + numToUpper(Integer.parseInt(str[1] + ""));
            }
        }
    }

    /**
     * Parses a date value. The format used for parsing the date value are
     * retrieved from the default PATTERN_YMD.
     *
     * @param dateValue the date value to parse
     * @return the parsed date
     * @throws IllegalArgumentException If the given dateValue is invalid.
     */
    public static Date parseDate(String dateValue) {
        return parseDate(dateValue, null);
    }

    /**
     * Parses the date value using the given date format.
     *
     * @param dateValue  the date value to parse
     * @param dateFormat the date format to use
     * @return the parsed date. if parse is failed , return null
     * @throws IllegalArgumentException If the given dateValue is invalid.
     */
    public static Date parseDate(String dateValue, String dateFormat) {
        if (dateValue == null) {
            throw new IllegalArgumentException("dateValue is null");
        }
        if (dateFormat == null) {
            dateFormat = PATTERN_YMD;
        }

        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Date result = null;
        try {
            result = df.parse(dateValue);
        } catch (ParseException pe) {
            pe.printStackTrace();// 日期型字符串格式错误
        }
        return result;
    }

    /**
     * Adds a number of years to a date returning a new object. The original
     * date object is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addYears(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    /**
     * Adds a number of years to a timestamp returning a new object. The
     * original timestamp object is unchanged.
     *
     * @param timestamp the timestamp, not null
     * @param amount    the amount to add, may be negative
     * @return the new timestamp object with the amount added
     * @throws IllegalArgumentException if the timestamp is null
     */
    public static Timestamp addYears(Timestamp timestamp, int amount) {
        return add(timestamp, Calendar.YEAR, amount);
    }

    /**
     * Adds a number of months to a date returning a new object. The original
     * date object is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addMonths(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    /**
     * Adds a number of months to a timestamp returning a new object. The
     * original timestamp object is unchanged.
     *
     * @param timestamp the timestamp, not null
     * @param amount    the amount to add, may be negative
     * @return the new timestamp object with the amount added
     * @throws IllegalArgumentException if the timestamp is null
     */
    public static Timestamp addMonths(Timestamp timestamp, int amount) {
        return add(timestamp, Calendar.MONTH, amount);
    }

    // -----------------------------------------------------------------------

    /**
     * Adds a number of days to a date returning a new object. The original date
     * object is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DATE, amount);
    }

    /**
     * Adds a number of days to a timestamp returning a new object. The original
     * timestamp object is unchanged.
     *
     * @param timestamp the timestamp, not null
     * @param amount    the amount to add, may be negative
     * @return the new timestamp object with the amount added
     * @throws IllegalArgumentException if the timestamp is null
     */
    public static Timestamp addDays(Timestamp timestamp, int amount) {
        return add(timestamp, Calendar.DATE, amount);
    }

    // -----------------------------------------------------------------------

    /**
     * Adds a number of minutes to a timestamp returning a new object. The
     * original timestamp object is unchanged.
     *
     * @param timestamp the timestamp, not null
     * @param amount    the amount to add, may be negative
     * @return the new timestamp object with the amount added
     * @throws IllegalArgumentException if the timestamp is null
     */
    public static Timestamp addMinutes(Timestamp timestamp, int amount) {
        return add(timestamp, Calendar.MINUTE, amount);
    }

    /**
     * Adds a number of days to current time returning a new object.
     *
     * @param amount the amount to add, may be negative
     * @return the new timestamp object with the amount added
     */
    public static Timestamp addDays(int amount) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, amount);
        return new Timestamp(c.getTimeInMillis());
    }

    // -----------------------------------------------------------------------

    /**
     * Adds to a date returning a new object. The original date object is
     * unchanged.
     *
     * @param date          the date, not null
     * @param calendarField the calendar field to add to
     * @param amount        the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    private static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    /**
     * Adds to a timestamp returning a new object. The original timestamp object
     * is unchanged.
     *
     * @param timestamp     the timestamp, not null
     * @param calendarField the calendar field to add to
     * @param amount        the amount to add, may be negative
     * @return the new timestamp object with the amount added
     * @throws IllegalArgumentException if the timestamp is null
     */
    private static Timestamp add(Timestamp timestamp, int calendarField, int amount) {
        if (timestamp == null) {
            throw new IllegalArgumentException("The timestamp must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(timestamp);
        c.add(calendarField, amount);
        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * @return 最小的当天日期值
     */
    public static Timestamp now() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * This class should not be instantiated.
     */
    private DateUtil() {
    }
}