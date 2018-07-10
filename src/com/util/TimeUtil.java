package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeUtil {
	// private static Logger log = Logger.getLogger(TimeUtil.class);

	public static final long DAYTIMEINMILLIS = 24 * 60 * 60 * 1000;
	
	/**
	 * 获取几天后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static long getAfterDayTime(long time, int day) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(time);
		calendar.add(Calendar.DATE, day);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取指定时间到现在的时间毫秒数
	 * 
	 * @param time
	 * @return
	 */
	public static long getDurationToNow(long time) {
		return System.currentTimeMillis() - time;
	}

	/**
	 * 获取指定时间到现在的时间秒数
	 * 
	 * @param time
	 * @return 秒
	 */
	public static int getDurationToNowSec(long time) {
		return (int) (getDurationToNow(time) / 1000);
	}

	/**
	 * 判断两个时间是否在同一天
	 * 
	 * @param time
	 * @param time2
	 * @return
	 */
	public static boolean isSameDay(long time, long time2) {
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		int year = instance.get(Calendar.YEAR);
		int dayOfYear = instance.get(Calendar.DAY_OF_YEAR);
		instance.setTimeInMillis(time2);
		int year2 = instance.get(Calendar.YEAR);
		int dayOfYear2 = instance.get(Calendar.DAY_OF_YEAR);
		return year==year2&&dayOfYear==dayOfYear2;		
	}

	/**
	 * 判断是否在今天几点之后 true为大于指定时间
	 * 当前时间判断在hour点之前 还是之后
	 * @param hour
	 * @return
	 */
	public static boolean isAfterHourOfCurrentDay(int hour, long time) {
		long currentTimeMillis = System.currentTimeMillis();
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(currentTimeMillis);
		instance.set(Calendar.HOUR_OF_DAY, hour);
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.SECOND, 0);
		instance.set(Calendar.MILLISECOND, 0);
		long timeInMillis = instance.getTimeInMillis();
		return time - timeInMillis > 0;
	}

	/**
	 * 获取指定时间的UNIXTime yyyy-MM-dd HH:mm:ss
	 * 
	 * @param express
	 * @return
	 * @throws ParseException
	 */
	public static long getFixTime(int year, int month, int date, int hourOfDay, int minute, int second) throws ParseException {
		Calendar instance = Calendar.getInstance();
		instance.set(year, month - 1, date, hourOfDay, minute, second);
		instance.set(Calendar.MILLISECOND, 0);
		return instance.getTimeInMillis();
	}

	/**
	 * 获取当日指定点的long值
	 * 
	 * @param hourofday
	 * @param minute
	 * @param second
	 * @return
	 */
	public static long getFixTime(int hourofday, int minute, int second) {
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(System.currentTimeMillis());
		instance.set(Calendar.HOUR_OF_DAY, hourofday);
		instance.set(Calendar.MINUTE, minute);
		instance.set(Calendar.SECOND, second);
		instance.set(Calendar.MILLISECOND, 0);
		return instance.getTimeInMillis();
	}

	/**
	 * 指定时间的年份
	 * 
	 * @param time
	 * @return
	 */
	public static int getYear(long time) {
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		return instance.get(Calendar.YEAR);
	}

	/**
	 * 指定时间的月份
	 * 
	 * @param time
	 * @return
	 */
	public static int getMonth(long time) {
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		return instance.get(Calendar.MONTH);
	}

	/**
	 * 获取日期
	 * 
	 * @param time
	 * @return
	 */
	public static int getDayOfMonth(long time) {
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		return instance.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取指定时间 是一月内的第几周
	 * 
	 * @param time
	 * @return
	 */
	public static int getDayOfWeekInMonth(long time) {
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		return instance.get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}

	/**
	 * 获取星期几
	 * 
	 * @param time
	 * @return
	 */
	public static int getDayOfWeek(long time) {
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		int i = instance.get(Calendar.DAY_OF_WEEK);
		if (i == 1) {
			return 7;
		} else {
			i -= 1;
		}
		return i;
	}

	/**
	 * 获取一年内的第几天
	 * 
	 * @param time
	 * @return
	 */
	public static int getDayOfYear(long time) {
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		return instance.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 判断输入的字符串是否满足时间格式 ： yyyy-MM-dd HH:mm:ss
	 * 
	 * @param patternString
	 *            需要验证的字符串
	 * @return 合法返回 true ; 不合法返回false
	 */
	public static boolean isTimeLegal(String patternString) {
		Pattern a = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher b = a.matcher(patternString);
		if (b.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 当日10点
	 * 
	 * @return
	 */
	public static long getTenClockTime() {

		Calendar date = Calendar.getInstance();
		date.setTime(new java.util.Date(System.currentTimeMillis()));
		long time = 0l;
		try {
			time = TimeUtil.getFixTime(date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH), 10, 0, 0);
		} catch (ParseException e) {

		}
		return time;
	}
	
	/**
	 * 获得当日某整点时间
	 * @param hour
	 * @return
	 */
	public static long getClockTime(int hour) {
		Calendar date = Calendar.getInstance();
		date.setTime(new Date(System.currentTimeMillis()));
		long time=0l;
		try {
			time=TimeUtil.getFixTime(date.get(Calendar.YEAR), date.get(Calendar.MONTH)+1, date.get(Calendar.DAY_OF_MONTH), hour, 0, 0);
		} catch (Exception e) {
			
		}
		return time;
		
	}
	
	/**
	 * 判断是否在每日固定时间之前
	 * @param hour
	 * @param time
	 * @return
	 */
	public static boolean isBeforeTodayClock(int hour,long time) {
		long clockTime = getClockTime(hour);
		long yesterdayTime=clockTime-24*60*60*1000;
		long nowTime = System.currentTimeMillis();
		if(nowTime<clockTime) {
			if(time<yesterdayTime) {
				return true;
			} else {
				return false;
			}
		} else {
			if(time<clockTime) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public static void main(String[] args) {
		try {

			System.out.println(getFixTime(10, 10, 10));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// long time=System.currentTimeMillis();
		//
		//
		//
		// List<String> list =
		// TimeUtil.translate("[2011][11][w1,w2,w5][18:05-18:06]");
		// list.addAll(TimeUtil.translate("[*][*][10-20][18:05-18:05]"));
		// for (int i = 0; i < list.size(); i++) {
		// try{
		// }catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// long time=System.currentTimeMillis()-1000*60*60*24;
	}

	public static String getFormatString(long now) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		return sdf.format(new Date(now));
	}

	public static Date getFormatDate(String express) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(express);
	}

	/**
	 * 
	 * @param now
	 * @param format
	 *            yyyy年MM月dd日HH时mm分ss秒
	 * @return
	 */
	public static String getFormatDate(long now, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(now));
	}

	// public static String getFormatString(long exp) {
	// long timeTemp = exp;
	// long time = timeTemp;
	// long mSec = time % 1000;
	// time /= 1000;
	// long year = time / (365 * 24 * 3600);
	// time = time % (365 * 24 * 3600);
	// long month = time / (30 * 24 * 3600);
	// time = time % (30 * 24 * 3600);
	// long day = time / (24 * 3600);
	// time = time % (24 * 3600);
	// long hour = time / 3600;
	// time = time % 3600;
	// long min = time / 60;
	// time = time % 60;
	// long sec = time;
	// String s="";
	//
	//
	//
	// System.out.println(timeTemp + "毫秒是：" + year + "年" + month + "月" + day +
	// "天" + hour + "小时" + min + "分钟" + sec + "秒零" + mSec + "毫秒");
	// }
}
