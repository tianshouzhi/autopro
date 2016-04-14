package ${basePackage}.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static final String PATTERN_YYYY_MM_DD="yyyy-MM-dd";
	public static final String PATTERN_YYYY_MM="yyyy-MM";
	public static final String PATTERN_YYYY="yyyy";
	
	public static String format(Date date,String pattern){
		DateFormat dateFormat=new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}
	public static Date format(String dateStr,String pattern){
		DateFormat dateFormat=new SimpleDateFormat(pattern);
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 获取两个日期之前相差的天数
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static int getDaysCount(Date startDate, Date endDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			startDate = sdf.parse(sdf.format(startDate));
			endDate = sdf.parse(sdf.format(endDate));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		long startTimeMillis = cal.getTimeInMillis();
		cal.setTime(endDate);
		long endTimeMillis = cal.getTimeInMillis();
		long between_days = (endTimeMillis - startTimeMillis)/ (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}
	
	/**
	 * 获取两个日期之前相差的天数，包含开始和结束的日期
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static int getDaysCountInclude(Date startDate, Date endDate){
		return getDaysCount(startDate, endDate)+1;
	}
	
	public static void main(String[] args) throws ParseException {
		/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date d1=sdf.parse("2012-09-08 10:10:10");  
        Date d2=sdf.parse("2012-09-15 00:00:00"); 
        System.out.println(getDaysCountInclude(d1,d2)); */
		/*System.out.println(getBeforeDay(new Date()).toLocaleString());*/
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date d1=sdf.parse("2012-09-16 10:10:10");  
        Date d2=sdf.parse("2012-09-17 00:00:00"); 
        System.out.println(isSameDay(d1, d2));
	}
	
	/**
	 * 获取当前日期的前一天
	 * @param date
	 * @return
	 */
	public static Date getBeforeDay(Date date){
		int dayOfMonth = getDayOfMonth(date);
		int monthOfYear=getMonthOfYear(date);
		int year=getYear(date);
		return getDate(year, monthOfYear, dayOfMonth-1);
	}
	
	/**
	 * 获取当前日期的后一天
	 * @param date
	 * @return
	 */
	public static Date getAfterDay(Date date){
		int dayOfMonth = getDayOfMonth(date);
		int monthOfYear=getMonthOfYear(date);
		int year=getYear(date);
		return getDate(year, monthOfYear, dayOfMonth+1);
	}
	
	/**
	 * 获取下一个月
	 * @param date
	 * @return
	 */
	public static Date getAfterMonth(Date date){
		int monthOfYear=getMonthOfYear(date);
		int year=getYear(date);
		return getDate(year, monthOfYear+1, 1);
	}
	
	/**
	 * 获取下一年
	 * @param date
	 * @return
	 */
	public static Date getAfterYear(Date date){
		int monthOfYear=getMonthOfYear(date);
		int year=getYear(date);
		return getDate(year+1, monthOfYear, 1);
	}
	
	public static int getDayOfMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		return dayOfMonth;
	}
	
	public static int getMonthOfYear(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int monthOfYear = cal.get(Calendar.MONTH);
		return monthOfYear;
	}
	
	public static int getYear(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int monthOfYear = cal.get(Calendar.YEAR);
		return monthOfYear;
	}
	
	public static Date getDate(int year,int month,int date){
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, date,0,0,0);
		return cal.getTime();
	}

	public static boolean isSameDay(Date date1, Date date2) {
		int date1_year = getYear(date1);
		int date1_month = getMonthOfYear(date1);
		int date1_date = getDayOfMonth(date1);
		
		int date2_year = getYear(date2);
		int date2_month = getMonthOfYear(date2);
		int date2_date = getDayOfMonth(date2);
		if(date1_year==date2_year&&date1_month==date2_month&&date1_date==date2_date){
			return true;
		}
		return false;
	}
}
