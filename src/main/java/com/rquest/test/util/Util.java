package com.rquest.test.util;

import java.beans.PropertyDescriptor;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {

	public static SimpleDateFormat DATE_FORMATE_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
	public static SimpleDateFormat DATE_FORMATE_yyyy = new SimpleDateFormat("yyyy", Locale.CHINA);
	public static SimpleDateFormat DATE_TIME_FORMATE_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
	private static final Logger logger = LoggerFactory.getLogger(Util.class);
	/**
	 * 维护一个cocurrentHashMap来记录用户的访问次数
	 */
	public static Map<String, Integer> userTimes = new ConcurrentHashMap<String, Integer>();
	public static Integer QUERY_TIMES = 50;

	/**
	 * 判断字符串是否为空字符
	 * 
	 * @str 字符串
	 */
	public static boolean IsEmpty(String str) {
		if (str == null || str.equalsIgnoreCase(""))
			return true;

		else
			return false;
	}
	/*
	 * 获取webqpps下项目的所在相对路径： D:\tomcat\apache-tomcat-8.5.9standalonetest\webapps\Rotterdam\ File file = new File(VaadinServlet.getCurrent().getServletContext().getRealPath("")+File.separator);
	 * 
	 * D:\tomcat\apache-tomcat-8.5.9standalonetest\bin================= String parent2 = file.getParentFile().getParent(); CompressZipFileUtil.deleteFilesBySuffix(parent2+File.separator+"bin", "xls");
	 * CompressZipFileUtil.deleteFilesBySuffix(parent2+File.separator+"bin", "xlsx"); CompressZipFileUtil.deleteFilesBySuffix(parent2+File.separator+"bin", "csv");
	 */

	public static String[] orderString() {
		String[] str = new String[32];
		str[0] = (char) 9 + "" + (char) 9 + "" + (char) 9 + "" + (char) 9 + "" + (char) 9 + "";
		str[1] = (char) 9 + "" + (char) 9 + "" + (char) 9 + "" + (char) 9 + "" + (char) 10 + "";
		str[2] = (char) 9 + "" + (char) 9 + "" + (char) 9 + "" + (char) 10 + "" + (char) 9 + "";
		str[3] = (char) 9 + "" + (char) 9 + "" + (char) 9 + "" + (char) 10 + "" + (char) 10 + "";
		str[4] = (char) 9 + "" + (char) 9 + "" + (char) 10 + "" + (char) 9 + "" + (char) 9 + "";
		str[5] = (char) 9 + "" + (char) 9 + "" + (char) 10 + "" + (char) 9 + "" + (char) 10 + "";
		str[6] = (char) 9 + "" + (char) 9 + "" + (char) 10 + "" + (char) 10 + "" + (char) 9 + "";
		str[7] = (char) 9 + "" + (char) 9 + "" + (char) 10 + "" + (char) 10 + "" + (char) 10 + "";
		str[8] = (char) 9 + "" + (char) 10 + "" + (char) 9 + "" + (char) 9 + "" + (char) 9 + "";
		str[9] = (char) 9 + "" + (char) 10 + "" + (char) 9 + "" + (char) 9 + "" + (char) 10 + "";
		str[10] = (char) 9 + "" + (char) 10 + "" + (char) 9 + "" + (char) 10 + "" + (char) 9 + "";
		str[11] = (char) 9 + "" + (char) 10 + "" + (char) 9 + "" + (char) 10 + "" + (char) 10 + "";
		str[12] = (char) 9 + "" + (char) 10 + "" + (char) 10 + "" + (char) 9 + "" + (char) 9 + "";
		str[13] = (char) 9 + "" + (char) 10 + "" + (char) 10 + "" + (char) 9 + "" + (char) 10 + "";
		str[14] = (char) 9 + "" + (char) 10 + "" + (char) 10 + "" + (char) 10 + "" + (char) 9 + "";
		str[15] = (char) 9 + "" + (char) 10 + "" + (char) 10 + "" + (char) 10 + "" + (char) 10 + "";
		str[16] = (char) 10 + "" + (char) 9 + "" + (char) 9 + "" + (char) 9 + "" + (char) 9 + "";
		str[17] = (char) 10 + "" + (char) 9 + "" + (char) 9 + "" + (char) 9 + "" + (char) 10 + "";
		str[18] = (char) 10 + "" + (char) 9 + "" + (char) 9 + "" + (char) 10 + "" + (char) 9 + "";
		str[19] = (char) 10 + "" + (char) 9 + "" + (char) 9 + "" + (char) 10 + "" + (char) 10 + "";
		str[20] = (char) 10 + "" + (char) 9 + "" + (char) 10 + "" + (char) 9 + "" + (char) 9 + "";
		str[21] = (char) 10 + "" + (char) 9 + "" + (char) 10 + "" + (char) 9 + "" + (char) 10 + "";
		str[22] = (char) 10 + "" + (char) 9 + "" + (char) 10 + "" + (char) 10 + "" + (char) 9 + "";
		str[23] = (char) 10 + "" + (char) 9 + "" + (char) 10 + "" + (char) 10 + "" + (char) 10 + "";
		str[24] = (char) 10 + "" + (char) 10 + "" + (char) 9 + "" + (char) 9 + "" + (char) 9 + "";
		str[25] = (char) 10 + "" + (char) 10 + "" + (char) 9 + "" + (char) 9 + "" + (char) 10 + "";
		str[26] = (char) 10 + "" + (char) 10 + "" + (char) 9 + "" + (char) 10 + "" + (char) 9 + "";
		str[27] = (char) 10 + "" + (char) 10 + "" + (char) 9 + "" + (char) 10 + "" + (char) 10 + "";
		str[28] = (char) 10 + "" + (char) 10 + "" + (char) 10 + "" + (char) 9 + "" + (char) 9 + "";
		str[29] = (char) 10 + "" + (char) 10 + "" + (char) 10 + "" + (char) 9 + "" + (char) 10 + "";
		str[30] = (char) 10 + "" + (char) 10 + "" + (char) 10 + "" + (char) 10 + "" + (char) 9 + "";
		str[31] = (char) 10 + "" + (char) 10 + "" + (char) 10 + "" + (char) 10 + "" + (char) 10 + "";

		return str;
	}

	public static String DateToString(Date date) {
		if (date != null) {
			return DATE_FORMATE_yyyy_MM_dd.format(date);
		} else {
			return "";
		}

	}

	public static String showDateToString(Date date) {
		if (date != null) {
			String show = DATE_FORMATE_yyyy_MM_dd.format(date);
			if (show.equals("1900-01-01")) {
				return "-";
			} else {
				return show;
			}
		} else {
			return "";
		}
	}

	public static Date StringToDate(String dateStr) {
		Date dateTrans = null;
		try {
			dateTrans = DATE_FORMATE_yyyy_MM_dd.parse(dateStr.replace("/", "-"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dateTrans;
	}

	public static String DateTimeToString(Date date) {
		if (date != null) {
			return DATE_TIME_FORMATE_yyyy_MM_dd.format(date);
		} else {
			return "";
		}

	}

	public static Date StringToDateTime(String dateStr) {
		Date dateTrans = null;
		try {
			dateTrans = DATE_TIME_FORMATE_yyyy_MM_dd.parse(dateStr.replace("/", "-"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dateTrans;
	}

	public static String ObjectToString(Object obj) {
		if (obj != null) {
			if (obj.getClass().equals(Double.class) || obj.getClass().equals(Integer.class)) {
				return obj.toString();

			} else if (obj.getClass().equals(Date.class)) {
				return DATE_FORMATE_yyyy_MM_dd.format(obj);

			} else {
				return obj.toString();
			}
		} else {
			return "";
		}
	}

	public static boolean IsDouble(String str, double minValue, double maxValue) {
		boolean convertSuccess = true;
		if (IsEmpty(str)) {
			convertSuccess = false;
		} else {
			double temp = minValue;
			try {
				temp = Double.parseDouble(str.replace(",", ""));
			} catch (Exception e) {
				convertSuccess = false;
			}
			if ((temp < minValue) || (temp > maxValue)) {
				convertSuccess = false;
			}
		}
		return convertSuccess;

	}

	public static boolean IsInteger(String str, int minValue, int maxValue) {
		boolean convertSuccess = true;
		if (IsEmpty(str)) {
			convertSuccess = false;
		} else {

			int temp = minValue;
			try {
				temp = Integer.parseInt(str.replace(",", ""));
			} catch (Exception e) {
				convertSuccess = false;
			}
			if ((temp < minValue) || (temp > maxValue)) {
				convertSuccess = false;
			}
		}
		return convertSuccess;

	}

	public static String NumberFormatSeparate(Double number, int n) {
		String sReturn = "";
		NumberFormat nf = NumberFormat.getInstance(Locale.CANADA);
		nf.setMinimumFractionDigits(n);// 不足n位小数，则以0补位。
		nf.setMaximumFractionDigits(n);// 最多保留n位小数。
		sReturn = nf.format(number);
		if (sReturn.equals("-0.00")) {
			sReturn = "0.00";
		}
		return sReturn;
	}

	/**
	 * 原RequestDateConvert类中的方法
	 * 
	 * @param inDate
	 * @return
	 */
	public static String DateConvert(String inDate) {
		String sRet = "";
		String[] obj = inDate.split(" ");
		String s = obj[1];
		if (s.equals("Jan")) {
			s = "01";
		} else if (s.equals("Feb")) {
			s = "02";
		} else if (s.equals("Mar")) {
			s = "03";
		} else if (s.equals("Apr")) {
			s = "04";
		} else if (s.equals("May")) {
			s = "05";
		} else if (s.equals("Jun")) {
			s = "06";
		} else if (s.equals("Jul")) {
			s = "07";
		} else if (s.equals("Aug")) {
			s = "08";
		} else if (s.equals("Sep")) {
			s = "09";
		} else if (s.equals("Oct")) {
			s = "10";
		} else if (s.equals("Nov")) {
			s = "11";
		} else if (s.equals("Dec")) {
			s = "12";
		}
		sRet = obj[5] + "-" + s + "-" + obj[2];
		return sRet;
	}

	/**
	 * getUniqueID:(使用UUID来生成唯一组件)；<br/>
	 * void；<br/>
	 * unique ID<br/>
	 * 
	 * @author baiguomin
	 */
	public static String getUniqueID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 0D--->D0,30M--->M30
	 * 
	 * @param cdTerm
	 * @return
	 */
	public static String convertCdterm(String cdTerm) {
		String unit = cdTerm.substring(cdTerm.length() - 1);
		String num = cdTerm.substring(0, cdTerm.length() - 1);

		return unit + num;
	}

	/**
	 * 比较 0D 3M 10M 1Y 此类的前后顺序
	 * 
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static int compareDMYNbr(String arg1, String arg2) {
		int l1 = arg1.length();
		int l2 = arg2.length();
		// D M Y
		String letter1 = arg1.substring(l1 - 1, l1);
		String letter2 = arg2.substring(l2 - 1, l2);

		String num1 = arg1.substring(0, l1 - 1);
		String num2 = arg2.substring(0, l2 - 1);

		arg1 = letter1 + num1;
		arg2 = letter2 + num2;

		if (arg1.length() == arg2.length()) {
			return arg1.compareTo(arg2);
		} else {
			if (!letter1.equals(letter2)) {
				return letter1.compareTo(letter2);
			} else {
				Integer arg111 = Integer.valueOf(arg1.substring(1));
				Integer arg222 = Integer.valueOf(arg2.substring(1));
				return arg111 - arg222;
			}
		}
	}

	public static boolean validateIfNotCorrect(String regex, String str) {
		if (str == null || str.trim().equals("")) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}

	public static Date increaseDate(Date initDate, int days) {

		Calendar cNow = Calendar.getInstance();
		cNow.setTime(initDate);
		cNow.add(Calendar.DAY_OF_MONTH, days);
		Date newDate = cNow.getTime();
		return newDate;
	}

	/**
	 * 判断字符串是否为日期
	 * 
	 * @str 字符串
	 */
	public static boolean IsDate(String str) {
		boolean convertSuccess = true;
		try {
			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			DATE_FORMATE_yyyy_MM_dd.setLenient(false);
			DATE_FORMATE_yyyy_MM_dd.parse(str.replace("/", "-"));
		} catch (ParseException e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}

	/**
	 * 判断字符串是否为日期
	 * 
	 * @str 字符串
	 */
	public static boolean IsDateTime(String str) {
		boolean convertSuccess = true;
		try {
			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			DATE_TIME_FORMATE_yyyy_MM_dd.setLenient(false);
			DATE_TIME_FORMATE_yyyy_MM_dd.parse(str.replace("/", "-"));
		} catch (ParseException e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}

	/**
	 * reset the userMap provide for the quartz
	 */
	public static void resetUserMap() {
		// everyday reset the userTimes
		for (Entry<String, Integer> element : userTimes.entrySet()) {
			logger.info("今日该用户的次数试用记录：" + element.getKey() + "剩余次数：" + element.getValue());
		}
		userTimes = new ConcurrentHashMap<String, Integer>();
	}

	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	/**
	 * 将不连续的日期转换成连续的日期，前提是list中按照升序排列从小到大；
	 * 
	 * @param minMaxDate
	 * @return
	 */
	public static List<String> transferContinueDate(List<Date> minMaxDate) {
		List<String> continuousDate = new ArrayList<String>();
		if (minMaxDate.size() > 0) {
			Date minDate = minMaxDate.get(0);
			Date maxDate = minMaxDate.get(minMaxDate.size() - 1);
			continuousDate.add(DATE_FORMATE_yyyy_MM_dd.format(minDate));
			int days = ((int) (maxDate.getTime() / 1000) - (int) (minDate.getTime() / 1000)) / 3600 / 24;
			for (int i = 0; i < days; i++) {
				Calendar cNow = Calendar.getInstance();
				cNow.setTime(minDate);
				cNow.add(Calendar.DAY_OF_MONTH, 1);
				Date newDate = cNow.getTime();
				if (maxDate.after(newDate)) {
					continuousDate.add(DATE_FORMATE_yyyy_MM_dd.format(newDate));
				}
				minDate = newDate;
			}
		}
		return continuousDate;
	}

	/**
	 * 验证输入的是否是纯数字，是返回true，不是返回false; 负数返回false；
	 * 
	 * @return
	 */

	public static boolean validateNumberisNaN(String str) {
		if (validateIfNotCorrect("^-?\\d+(\\.\\d+)?(,\\d+(\\.\\d+)?)*(\\.\\d+)?$", str)) {
			return true;
		}
		return false;
	}
	// public static void main(String[] args) {
	// ModelParameter modelParameter = new ModelParameter();
	// modelParameter.setTypeOfFinance(0.1);
	// System.out.println(getVaueByCdLevel1Name("finance",modelParameter));
	// }
	
	/**
	 * oracle 数据库插入时list 大于1000的情况下进行拆分，分批插入；
	 * @param values
	 * @param size
	 * @return
	 */
	
	public static List<Collection<String>> splitCollection(Collection<String>values , int size) {
		List<Collection<String>> result = new ArrayList<Collection<String>>();
		if(values.size() <= size ){
			result.add(values);
		}else{
				int count =0;
				Collection<String> subCollection= null;
				for(String s : values){
					if(subCollection == null){
						subCollection = new ArrayList<String>();
						result.add(subCollection);
					}
					subCollection.add(s);
					count++;
					if(count == size){
						count =0;
						subCollection = null;
					}
				}
		}
		return result;
	}
	private void mySet(Object obj, String name, Object value) {
		try {
			if (obj != null && name != null && value != null) {
				Class<?> c = obj.getClass();
				if (value instanceof Integer) {
					new PropertyDescriptor(name, c).getWriteMethod().invoke(obj, Long.parseLong(value.toString()));
				}else {
					new PropertyDescriptor(name, c).getWriteMethod().invoke(obj, value);
				}
			}
		} catch (Exception e) {
			logger.info("my set failed, the reason is: " + e.getMessage());
		}
	}

	private Object myGet(Object obj, String name) {
		Object result = null;
		try {
			if (obj != null && name != null) {
				Class<?> c = obj.getClass();
				result = new PropertyDescriptor(name, c).getReadMethod().invoke(obj);
			}
		} catch (Exception e) {
			logger.info("my get failed, the reason is: " + e.getMessage());
		}
		return result;
	}
}
