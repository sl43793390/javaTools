package com.rquest.test.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

	//这个是使用jdk8 新增的日期解析工具类进行解析和格式化日期的方法==============
		public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		public static String formatDate (LocalDate date){
			return dateFormatter.format(date);
		}
		
		public static LocalDateTime parseDate(String date){
			return LocalDateTime.parse(date, dateFormatter);
		}
//		===================================================
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			// 解析日期
			String dateStr = "2016-10-25";
			LocalDate parseDate = LocalDate.parse(dateStr, dateFormatter);
			System.out.println(parseDate);
			
			String formatDate = formatDate(parseDate);
			System.out.println(formatDate);

			// ThreadLocal来限制SimpleDateFormat
			System.out.println(format(new Date()));
		}

		//使用java.util.Date 的话使用这个来解析避免线程安全问题；
		// 要在高并发环境下能有比较好的体验，可以使用ThreadLocal来限制SimpleDateFormat只能在线程内共享，这样就避免了多线程导致的线程安全问题。
		private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
			@Override
			protected DateFormat initialValue() {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
		};

		public static String format(Date date) {
			return threadLocal.get().format(date);
		}
		
		
}
