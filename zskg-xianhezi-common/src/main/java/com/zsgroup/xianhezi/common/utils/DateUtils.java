package com.zsgroup.xianhezi.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期工具类, 继承org.apache.commons.lang3.time.DateUtils类
 *
 * @author wyl
 * @version 2014年9月18日
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    //public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");


    private static final String TIME_OF_DAY_BEGIN = " 00:00:00";
    private static final String TIME_OF_DAY_END = " 23:59:59";

    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss"};

    private static Date todayStartTime = null;       //当天开始时间
    private static Date nextDayStartTime = null;     //第二天开始时间
    private static Date nextStartTime = null;   //下一个起始时间
    private static Date startTime = null;       //起始时间
    //
    //public static  String formatDate(Date date)throws ParseException{
    //    return FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(date);
    //}
    //
    //public static Date parse(String strDate) throws ParseException{
    //    return FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").parse(strDate);
    //}

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     *
     * @param pattern 时间格式
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式（"yyyy-MM-dd","yyyy/MM/dd"）
     */
    public static Date parseDate(String str) {
        try {
            return parseDate(str, parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取系统当前时间戳-秒数
     *
     * @return 自 1970 年 1 月 1 日 00:00:00 GMT 以来此日期表示的秒数。
     */
    public static long getTimeStampSeconds() {
        return new Date().getTime() / 1000;
    }

    /**
     * 获取系统当前时间戳-秒数
     *
     * @return 自 1970 年 1 月 1 日 00:00:00 GMT 以来此日期表示的秒数。
     */
    public static String getCurrTimeStampString() {
        return String.valueOf(getTimeStampSeconds());
    }

    /**
     * 获取指定时间的时间戳-秒数
     *
     * @param date
     * @return 自 1970 年 1 月 1 日 00:00:00 GMT 以来此日期表示的秒数。
     */
    public static long getTimeStampSeconds(Date date) {
        return (date.getTime() / 1000);
    }

    /**
     * 时间戳(秒数)转换为时间
     *
     * @param timeStamp 十位数
     * @return
     */
    public static Date getDate(long timeStamp) {
        Date date = new Date();
        date.setTime(timeStamp * 1000);
        return date;
    }

    /**
     * 时间戳转换为时间字符串
     *
     * @param timeStamp 十位数
     * @param pattern   时间格式
     * @return
     */
    public static String getDateString(long timeStamp, Object... pattern) {
        return formatDate(getDate(timeStamp), pattern);
    }


    /**
     * 时间戳转换为时间字符串，默认输出格式为：yyyy-MM-dd HH:mm:ss
     *
     * @param timeStamp 十位数字符串
     * @return
     */
    public static String getDateString(String timeStamp) {
        if (timeStamp == null || "".equals(timeStamp)) {
            return "";
        }
        return getDateString(Long.parseLong(timeStamp), parsePatterns[1]);
    }

    /**
     * 时间戳转换为时间字符串，默认输出格式为：yyyy-MM-dd
     *
     * @param timeStamp 十位数字符串
     * @return
     */
    public static String getSimpleDateString(String timeStamp) {
        if (timeStamp == null || "".equals(timeStamp)) {
            return "";
        }
        return getDateString(Long.parseLong(timeStamp), parsePatterns[0]);
    }

    /**
     * 获取系统当前时间的秒位时间戳
     *
     * @return
     */
    public static int getDateLong() {
        return (int) (new Date().getTime() / 1000);
    }

    /**
     * @param now
     * @param days
     * @return 字符串
     */
    public static String addDays(String now, int days) {
        Date nowDate = DateUtils.getDate(Long.parseLong(now));
        Date newDate = DateUtils.addDays(nowDate, days);
        return String.valueOf(getTimeStampSeconds(newDate));
    }
    public static String addMonths(String now, int months) {
        Date nowDate = DateUtils.getDate(Long.parseLong(now));
        Date newDate = DateUtils.addMonths(nowDate, months);
        return String.valueOf(getTimeStampSeconds(newDate));
    }



    public static Date setDayZero(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }

    public static Date setDayLast(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        return cal.getTime();
    }


    /**
     * 获取昨日日期 返回格式：yyyy-MM-dd hh:mi:ss
     */
    public static Timestamp getBeginTimeOfDate(String beginDate) {
        return Timestamp.valueOf(beginDate + TIME_OF_DAY_END);
    }

    /**
     * 获取明天日期 返回格式：yyyy-MM-dd hh:mi:ss
     */
    public static Timestamp getEndTimeOfDate(String endDate) {
        return Timestamp.valueOf(endDate + TIME_OF_DAY_END);
    }

    /**
     * 当天起始时间
     * @return
     */
    public static Date getDayStartTime(Date date){
        Calendar today = Calendar.getInstance();
        today.setTime(date);
        today.set(Calendar.HOUR_OF_DAY, 0);    //获取当天小时并至0
        today.set(Calendar.MINUTE, 0);          //分至0
        today.set(Calendar.SECOND, 0);          //秒至0
        today.set(Calendar.MILLISECOND, 0);    //毫秒至0

        //startTime = today.getTime();

        return today.getTime();
    }
    /**
     * 第二天起始时间
     * @return
     */
    public static Date getNextDayStartTime(Date date){
        Calendar today = Calendar.getInstance();
        today.setTime(date);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        today.add(Calendar.DATE, 1);			 //当天起始时间加1天

        //nextStartTime = today.getTime();

        return today.getTime();
    }
    /**
     * 本周第一天起始时间
     * @return
     */
    public static Date getWeekStartTime(Date date){
        Calendar weekStart = Calendar.getInstance();
        weekStart.setTime(date);
        weekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);   //获取本周第一天
        weekStart.set(Calendar.HOUR_OF_DAY, 0);
        weekStart.set(Calendar.MINUTE, 0);
        weekStart.set(Calendar.SECOND, 0);
        weekStart.set(Calendar.MILLISECOND, 0);
        return weekStart.getTime();
    }
    /**
     * 下周第一天起始时间
     * @return
     */
    public static Date getNextWeekStartTime(Date date){
        Calendar weekStart = Calendar.getInstance();
        weekStart.setTime(date);
        weekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);   //获取本周第一天
        weekStart.set(Calendar.HOUR_OF_DAY, 0);
        weekStart.set(Calendar.MINUTE, 0);
        weekStart.set(Calendar.SECOND, 0);
        weekStart.set(Calendar.MILLISECOND, 0);

        weekStart.add(Calendar.WEDNESDAY, 1);	                   //本周第一天加1周

        return weekStart.getTime();
    }
    /**
     * 当月第一天起始时间
     * @return
     */
    public static Date getMonthStartTime(Date date){
        Calendar monthStart = Calendar.getInstance();
        monthStart.setTime(date);
        monthStart.set(Calendar.DAY_OF_MONTH, 1);	//获取本月第一天
        monthStart.set(Calendar.HOUR_OF_DAY, 0);	//将小时至0
        monthStart.set(Calendar.MINUTE, 0);  		//将分钟至0
        monthStart.set(Calendar.SECOND, 0);  		//将秒至0
        monthStart.set(Calendar.MILLISECOND, 0);	//将毫秒至0

        //startTime = monthStart.getTime();

        return monthStart.getTime();
    }
    /**
     * 下个月第一天起始时间
     * @return
     */
    public static Date getNextMonthStartTime(Date date){
        Calendar monthStart = Calendar.getInstance();
        monthStart.setTime(date);
        monthStart.set(Calendar.DAY_OF_MONTH, 1);	//获取本月第一天
        monthStart.set(Calendar.HOUR_OF_DAY, 0);
        monthStart.set(Calendar.MINUTE, 0);
        monthStart.set(Calendar.SECOND, 0);
        monthStart.set(Calendar.MILLISECOND, 0);

        monthStart.add(Calendar.MONTH, 1);         //本月第一天加一个月

        //nextStartTime = monthStart.getTime();

        return monthStart.getTime();
    }

    /**
     * 获取当月第一天
     * @return 当月第一天
     */
    public static Date getMonthFirst(){
        Calendar today = Calendar.getInstance();

        today.set(Calendar.HOUR_OF_DAY, 0);   //获取当天小时并至0
        today.set(Calendar.MINUTE, 0);         //分至0
        today.set(Calendar.SECOND, 0);         //秒至0
        today.set(Calendar.MILLISECOND, 0);   //毫秒至0

        today.add(Calendar.DATE,0);		   //当天起始时间加或减n天
        //todayStartTime = today.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }


    /**
     * 获取当月最后一天
     * @return 当月最后一天
     */
    public static Date getMonthLast(){
        Calendar calendar = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        today.set(Calendar.HOUR_OF_DAY, 23);   //获取当天小时并至0
        today.set(Calendar.MINUTE, 59);         //分至0
        today.set(Calendar.SECOND, 59);         //秒至0
        today.set(Calendar.MILLISECOND, 0);   //毫秒至0

        today.add(Calendar.DATE,0);
        //todayStartTime = today.getTime();
        calendar.setTime(today.getTime());
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();

    }

    /**
     * 当年第一天起始时间
     * @return
     */
    public static Date getYearStartTime(Date date){

        Calendar yearStart = Calendar.getInstance();
        yearStart.setTime(date);
        yearStart.set(Calendar.DAY_OF_YEAR, 1);    //本年第一天
        yearStart.set(Calendar.HOUR_OF_DAY, 0);
        yearStart.set(Calendar.MINUTE, 0);
        yearStart.set(Calendar.SECOND, 0);
        yearStart.set(Calendar.MILLISECOND, 0);

        //startTime = yearStart.getTime();

        return yearStart.getTime();
    }
    /**
     * 下一年第一天起始时间
     * @return
     */
    public static Date getNextYearStartTime(Date date){

        Calendar yearStart = Calendar.getInstance();
        yearStart.setTime(date);
        yearStart.set(Calendar.DAY_OF_YEAR, 1);    //本年第一天
        yearStart.set(Calendar.HOUR_OF_DAY, 0);
        yearStart.set(Calendar.MINUTE, 0);
        yearStart.set(Calendar.SECOND, 0);
        yearStart.set(Calendar.MILLISECOND, 0);

        yearStart.add(Calendar.YEAR, 1);            //当年第一天加一年

        //nextStartTime = yearStart.getTime();

        return yearStart.getTime();
    }

    public static Map<String,Object> getDateRegion(String groupType,Date date){
        Map<String, Object> statMap = new HashMap<>();
        if(groupType.equals("day")){
            statMap.put("groupType", "day");
            statMap.put("startTime", DateUtils.getDayStartTime(date));        //当天开始时间
            statMap.put("endTime", DateUtils.getNextDayStartTime(date));
        }
        else if(groupType.equals("week")){
            statMap.put("groupType", "week");
            statMap.put("startTime", DateUtils.getWeekStartTime(date));        //当周开始时间
            statMap.put("endTime", DateUtils.getNextWeekStartTime(date));
        }
        else if(groupType.equals("month")){
            statMap.put("groupType", "month");
            statMap.put("startTime", DateUtils.getMonthStartTime(date));        //当周开始时间
            statMap.put("endTime", DateUtils.getNextMonthStartTime(date));
        }
        else if(groupType.equals("year")){
            statMap.put("groupType", "year");
            statMap.put("startTime", DateUtils.getYearStartTime(date));        //当周开始时间
            statMap.put("endTime", DateUtils.getNextYearStartTime(date));
        }
        return  statMap;
    }

    /**
     * 判断当前系统时间是否在指定时间范围内
     */
    public static Boolean getDateRange(String beginDate,String endDate){
        Date date=new Date();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long newDate=date.getTime();
        long begin=0;
        long end=0;
        try {
            c.setTime(sdf.parse(beginDate));
            begin=c.getTimeInMillis();
            c.setTime(sdf.parse(endDate));
            end=c.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (newDate >= begin) && (newDate < end);
    }

    /**
     * 判断某时间是否在制定时间范围内
     */
    public static Boolean getDateRange(String beginDate,String endDate,Date date){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long newDate=date.getTime();
        long begin=0;
        long end=0;
        try {
            c.setTime(sdf.parse(beginDate));
            begin=c.getTimeInMillis();
            c.setTime(sdf.parse(endDate));
            end=c.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(newDate>=begin && newDate<end){
            return true;
        }else {
            return false;
        }
    }

    public static String getTimeByMinute(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        return new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
    }

    //计算两个天数相差田素
    public static int daysOfTwo(Date fDate, Date oDate) {

        Calendar aCalendar = Calendar.getInstance();
        Calendar bCalendar = Calendar.getInstance();

        aCalendar.setTime(oDate);
        bCalendar.setTime(fDate);

        aCalendar.get(Calendar.DAY_OF_YEAR);

        bCalendar.get(Calendar.DAY_OF_YEAR);

        int days = 0;
        while(aCalendar.before(bCalendar)){
            days++;
            aCalendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return days;
    }

    /*
    * 判断是否是20号
    * */
    public static Boolean isTwenty(){
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE,20);//设为当前月的20号
        Date date = lastDate.getTime();
        if (daysOfTwo(new Date(),date)==0){
            return true;
        }else {
            return false;
        }
    }

    /*
   * 获取下一个20号的日期
   * */
    public static Date getNextDay(){
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE,20);//设为当前月的20号
        Date date = lastDate.getTime();
        if (daysOfTwo(new Date(),date)>0){   //日期小于20号
            return date;
        }else {
            lastDate.add(Calendar.MONTH, 1);//加一个月，变为下月的20号
            return lastDate.getTime();
        }
    }


    /*
     * 获取上个月当前时间
     * */
    public static Date getLastMonth(Date date){
        Calendar lastDate = Calendar.getInstance();
        lastDate.setTime(date);
        lastDate.add(Calendar.MONTH, -1);
        return lastDate.getTime();

    }

    public static void main(String[] args) {
        Date date = DateUtils.addHours(new Date(),1);

        Calendar aCalendar = Calendar.getInstance();
        Calendar bCalendar = Calendar.getInstance();

        aCalendar.setTime(new Date());
        bCalendar.setTime(date);

        aCalendar.get(Calendar.DAY_OF_YEAR);

        bCalendar.get(Calendar.DAY_OF_YEAR);

        int days = 0;
        while(aCalendar.before(bCalendar)){
            days++;
            aCalendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        System.out.println(days);

    }

}
