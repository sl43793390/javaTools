package com.rquest.test;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Util {
	
	   public static SimpleDateFormat DATE_FORMATE_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);  
	   public static SimpleDateFormat DATE_FORMATE_yyyyMMddSLANT = new SimpleDateFormat("yyyy/MM/dd",Locale.CHINA);  
	   public static SimpleDateFormat DATE_FORMATE_yyyyMMdd = new SimpleDateFormat("yyyyMMdd",Locale.CHINA);  
	   public static SimpleDateFormat DATE_FORMATE_yyyy = new SimpleDateFormat("yyyy",Locale.CHINA);  
	   public static SimpleDateFormat DATE_FORMATE_mm_dd= new SimpleDateFormat("MM-dd",Locale.CHINA);  
	   public static SimpleDateFormat DATE_FORMATE_dd= new SimpleDateFormat("dd",Locale.CHINA);  
	   public static SimpleDateFormat DATE_TIME_FORMATE_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);  
	   public static String split = ",";
	   private static Logger  logger = LoggerFactory.getLogger(Util.class);
	   public static String[] orderString(){
			String[] str = new String[32] ;  
		    str[0] = (char)9+""+(char)9+""+(char)9+""+(char)9+""+(char)9+"";
		    str[1] = (char)9+""+(char)9+""+(char)9+""+(char)9+""+(char)10+"";
		    str[2] = (char)9+""+(char)9+""+(char)9+""+(char)10+""+(char)9+"";
		    str[3] = (char)9+""+(char)9+""+(char)9+""+(char)10+""+(char)10+"";
		    str[4] = (char)9+""+(char)9+""+(char)10+""+(char)9+""+(char)9+"";
		    str[5] = (char)9+""+(char)9+""+(char)10+""+(char)9+""+(char)10+"";
		    str[6] = (char)9+""+(char)9+""+(char)10+""+(char)10+""+(char)9+"";
		    str[7] = (char)9+""+(char)9+""+(char)10+""+(char)10+""+(char)10+"";
		    str[8] = (char)9+""+(char)10+""+(char)9+""+(char)9+""+(char)9+"";
		    str[9] = (char)9+""+(char)10+""+(char)9+""+(char)9+""+(char)10+"";
		    str[10] = (char)9+""+(char)10+""+(char)9+""+(char)10+""+(char)9+"";
		    str[11] = (char)9+""+(char)10+""+(char)9+""+(char)10+""+(char)10+"";
		    str[12] = (char)9+""+(char)10+""+(char)10+""+(char)9+""+(char)9+"";
		    str[13] = (char)9+""+(char)10+""+(char)10+""+(char)9+""+(char)10+"";
		    str[14] = (char)9+""+(char)10+""+(char)10+""+(char)10+""+(char)9+"";
		    str[15] = (char)9+""+(char)10+""+(char)10+""+(char)10+""+(char)10+"";
		    str[16] = (char)10+""+(char)9+""+(char)9+""+(char)9+""+(char)9+"";
		    str[17] = (char)10+""+(char)9+""+(char)9+""+(char)9+""+(char)10+"";
		    str[18] = (char)10+""+(char)9+""+(char)9+""+(char)10+""+(char)9+"";
		    str[19] = (char)10+""+(char)9+""+(char)9+""+(char)10+""+(char)10+"";
		    str[20] = (char)10+""+(char)9+""+(char)10+""+(char)9+""+(char)9+"";
		    str[21] = (char)10+""+(char)9+""+(char)10+""+(char)9+""+(char)10+"";
		    str[22] = (char)10+""+(char)9+""+(char)10+""+(char)10+""+(char)9+"";
		    str[23] = (char)10+""+(char)9+""+(char)10+""+(char)10+""+(char)10+"";
		    str[24] = (char)10+""+(char)10+""+(char)9+""+(char)9+""+(char)9+"";
		    str[25] = (char)10+""+(char)10+""+(char)9+""+(char)9+""+(char)10+"";
		    str[26] = (char)10+""+(char)10+""+(char)9+""+(char)10+""+(char)9+"";
		    str[27] = (char)10+""+(char)10+""+(char)9+""+(char)10+""+(char)10+"";
		    str[28] = (char)10+""+(char)10+""+(char)10+""+(char)9+""+(char)9+"";
		    str[29] = (char)10+""+(char)10+""+(char)10+""+(char)9+""+(char)10+"";
		    str[30] = (char)10+""+(char)10+""+(char)10+""+(char)10+""+(char)9+"";
		    str[31] = (char)10+""+(char)10+""+(char)10+""+(char)10+""+(char)10+"";
			  
			return str;
		}  

	/**
	 * 判断字符串是否为空字符
	 *@str 字符串
	 */
	public static boolean IsEmpty(CharSequence str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
	}

	
	public static String DateToString(Date date) {
		if(date != null){
//			String format = DATE_FORMATE_yyyy_MM_dd.format(date);
			return DATE_FORMATE_yyyy_MM_dd.format(date);
			
		}else {
			return "";
		}
	}
	
	public static String DatetimeToString(Date date) {
		if(date != null){
			return DATE_TIME_FORMATE_yyyy_MM_dd.format(date);
		}else {
			return "";
		}
		
	}
	
	public static Date stringToDate(String date) {
		if(date != null){
			try {
				if (date.contains("-")) {
					return DATE_FORMATE_yyyy_MM_dd.parse(date);
				}else if (date.contains("/")) {
					return DATE_FORMATE_yyyyMMddSLANT.parse(date);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static String fomatDate(String date){
		String date2 = "";
		if (date!=null&&StringUtils.isNotBlank(date)) {
			if (date.contains("/")) {
				date2 = date.replace("/", "");
			}else if(date.contains("-")){
				date2 = date.replace("-", "");
			}
		}
		return date2;
	}
    public static Date StringToDate(String dateStr)
    {
    	Date dateTrans = null;
		try {
			dateTrans = DATE_FORMATE_yyyy_MM_dd.parse(dateStr.replace("/","-"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTrans;
    }
    
    
    public static Date StringToDatetime(String dateStr)
    {
    	Date dateTrans = null;
		try {
			dateTrans = DATE_TIME_FORMATE_yyyy_MM_dd.parse(dateStr.replace("/","-"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dateTrans;
    }

	
	
	public static String DateTimeToString(Date date) {
		if(date != null){
			return DATE_TIME_FORMATE_yyyy_MM_dd.format(date);
		}else {
			return "";
		}
		
	}
    public static Date StringToDateTime(String dateStr)
    {
    	Date dateTrans = null;
		try {
			dateTrans = DATE_TIME_FORMATE_yyyy_MM_dd.parse(dateStr.replace("/","-"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dateTrans;
    }

	
	public static String ObjectToString(Object obj){
		if(obj != null){
			if(obj.getClass().equals(Double.class) || obj.getClass().equals(Integer.class)){
				return obj.toString();
				
			}else if(obj.getClass().equals(Date.class)){
				return DATE_FORMATE_yyyy_MM_dd.format(obj);
				
			}else {
				return obj.toString();
			}
		}else {
			return "";
		}
	}

	
	public static boolean IsDouble(String str,double minValue, double maxValue ) {
	    boolean convertSuccess=true;
	    if (IsEmpty(str)){
	    	convertSuccess=false;
	    }
	    else {
			double temp=minValue;
			try{
				 temp = Double.parseDouble(str.replace(",",""));
			}
			catch(Exception e){
				convertSuccess=false;
			}
			if ((temp<minValue)||(temp>maxValue)) {
				convertSuccess=false;
			}
	    }
		return convertSuccess;
	
	}
	public static boolean IsDouble(String str) {
	    boolean convertSuccess=true;
	    if (IsEmpty(str)){
	    	convertSuccess=false;
	    }
	    else {
			double temp=0;
			try{
				 temp = Double.parseDouble(str.replace(",",""));
			}
			catch(Exception e){
				convertSuccess=false;
			}
	    }
		return convertSuccess;
	
	}

	
	public static boolean IsInteger(String str,int minValue, int maxValue ) {
	    boolean convertSuccess=true;
	    if (IsEmpty(str)){
	    	convertSuccess=false;
	    }
	    else {
	
			int temp=minValue;
			try{
				 temp = Integer.parseInt(str.replace(",",""));
			}
			catch(Exception e){
				convertSuccess=false;
			}
			if ((temp<minValue)||(temp>maxValue)) {
				convertSuccess=false;
			}
	    }
		return convertSuccess;
	
	}
	
	
	public static String NumberFormatSeparateNComma(double number,int n)
	{
		String sReturn="";
		NumberFormat nf=NumberFormat.getInstance(Locale.CANADA);
	      nf.setMinimumFractionDigits(n);//不足n位小数，则以0补位。
	      nf.setMaximumFractionDigits(n);//最多保留n位小数。
	      sReturn=nf.format(number);
	      if(sReturn.equals("-0.00")){
	    	 sReturn = "0.00";
	      }
	      sReturn=sReturn.replaceAll(",","");
		return sReturn;
	}
	
	/**
	 * 原RequestDateConvert类中的方法
	 * @param inDate
	 * @return
	 */
	 public static String DateConvert(String inDate) {
	        String sRet="";
	        String[] obj=inDate.split(" ");
	        String s=obj[1];
	        if(s.equals("Jan")){
	            s="01";
	        }else if(s.equals("Feb")){
	            s="02";
	        }else if(s.equals("Mar")){
	            s="03";
	        }else if(s.equals("Apr")){
	            s="04";
	        }else if(s.equals("May")){
	            s="05";
	        }else if(s.equals("Jun")){
	            s="06";
	        }else if(s.equals("Jul")){
	            s="07";
	        }else if(s.equals("Aug")){
	            s="08";
	        }else if(s.equals("Sep")){
	            s="09";
	        }else if(s.equals("Oct")){
	            s="10";
	        }else if(s.equals("Nov")){
	            s="11";
	        }else if(s.equals("Dec")){
	            s="12";
	        }
	        sRet=obj[5]+"-"+s+"-"+obj[2];
	        return sRet;
	   }

	 
	 /**
	  * getUniqueID:(使用UUID来生成唯一组件)；<br/>
	  * void；<br/>
	  * unique ID<br/>
	  * @author baiguomin
	  */
	 public static String getUniqueID(){
		 return UUID.randomUUID().toString();
	 }
	 
	 /**
	  * 0D--->D0,30M--->M30
	  * @param cdTerm
	  * @return
	  */
	 public static String convertCdterm(String cdTerm) {
			String unit = cdTerm.substring(cdTerm.length() -1);
			String num = cdTerm.substring(0, cdTerm.length()-1);
			
			return unit + num;
	}
	 
	 /**
	  * 比较   0D 3M 10M 1Y 此类的前后顺序
	  * @param arg1
	  * @param arg2
	  * @return
	  */
	 public static int compareDMYNbr(String arg1, String arg2) {
		int l1 = arg1.length();
		int l2 = arg2.length();
		//D M Y
		String letter1 = arg1.substring(l1-1, l1);
		String letter2 = arg2.substring(l2-1, l2);
		
		String num1 = arg1.substring(0, l1-1);
		String num2 = arg2.substring(0, l2-1);
		
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

		
		public static boolean validateIfNotCorrect(String regex, String str){
		            if (str == null || str.trim().equals("")) {
		                    return false;
		            }
		            Pattern pattern = Pattern.compile(regex);
		            Matcher isNum = pattern.matcher(str);
		            return isNum.matches();
		    }
		/**
		 * 验证输入的是否是纯数字，是返回true，不是返回false; 负数返回false；
		 * @return
		 */
		
		    public static  boolean validateNumberisNaN(String str){
		                if (validateIfNotCorrect("^-?\\d+(\\.\\d+)?(,\\d+(\\.\\d+)?)*(\\.\\d+)?$",str)) {
		                    return true;
		                }
		        return false;
		    }
	 
	    public static Date increaseDate(Date initDate,int days){
	    	  
	        Calendar cNow = Calendar.getInstance();
	        cNow.setTime(initDate);
	        cNow.add(Calendar.DAY_OF_MONTH, days);
	        Date newDate = cNow.getTime();
	    	return newDate;
	    }
	    /**
		 * 判断字符串是否为日期
		 *@str 字符串
		 */
		public static boolean IsDate(String str) {
		    boolean convertSuccess=true;
		    try {
		    	// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
		    	DATE_FORMATE_yyyy_MM_dd.setLenient(false);
		    	DATE_FORMATE_yyyy_MM_dd.parse(str.replace("/", "-"));
		    } 
		    catch (ParseException e) {
		    // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
		               convertSuccess=false;
		     } 
		    return convertSuccess;
		}
	    /**
		 * 判断字符串是否为日期
		 *@str 字符串
		 */
		public static boolean IsMultipleDate(String str) {
		    boolean convertSuccess=false;
		    try {
		    	// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
		    	DATE_FORMATE_yyyy_MM_dd.setLenient(false);
		    	DATE_FORMATE_yyyy_MM_dd.parse(str.replace("/", "-"));
		    	return true;
		    } 
		    catch (ParseException e) {
		    // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
		               convertSuccess=false;
		     } 
		    
		    try {
		    	DATE_FORMATE_yyyyMMdd.setLenient(false);
		    	DATE_FORMATE_yyyyMMdd.parse(str);
		    	return true;
		    } 
		    catch (ParseException e) {
		    // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
		               convertSuccess=false;
		     } 
		    
		    return convertSuccess;
		}
		
	    /**
		 * 判断字符串是否为日期
		 *@str 字符串
		 */
		public static boolean IsDateTime(String str) {
		    boolean convertSuccess=true;
		    try {
		    	// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
		    	DATE_TIME_FORMATE_yyyy_MM_dd.setLenient(false);
		    	DATE_TIME_FORMATE_yyyy_MM_dd.parse(str.replace("/", "-"));
		    } 
		    catch (ParseException e) {
		    // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
		               convertSuccess=false;
		     } 
		    return convertSuccess;
		}
		public static String getNotNullString(String str){
			return str==null?"":str;
		}
		public static Date getNotNullDate(Date date){
			return date==null?StringToDate("1900-01-01"):date;
		}
		public static Integer getNotNullInteger(Integer i){
			return i==null?0:i;
		}

		public static Double getNotNullDouble(Double x){
			return x==null?0:x;
		}

		public static List<String> transferContinueDate(List<Date> minMaxDate) {
			List<String> continuousDate = new ArrayList<String>();
			if (minMaxDate.size()>0) {
				Date minDate = minMaxDate.get(0);
				Date maxDate = minMaxDate.get(minMaxDate.size()-1);
				continuousDate.add(DATE_FORMATE_yyyy_MM_dd.format(minDate));
				int days = ((int)(maxDate.getTime()/1000)-(int)(minDate.getTime()/1000))/3600/24;   
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
	 public static String replaceSeparator(String number){
		 if (number.contains(",")) {
			 String replace = number.replace(",", "");
			return replace;
		}else if (StringUtils.isEmpty(number)) {
			return "0";
		}else{
			return number;
		}
	 }
	 
	 /**
	  * 得到某年某月的第一天
	  * @param year
	  * @param month
	  * @return
	  */
	 public static String getFirstDayOfMonth(String date) {
		 if (date==null||date.equals("")) {
			return null;
		}
	 int year = Integer.parseInt(date.split("-")[0]);
	 int month = Integer.parseInt(date.split("-")[1]);
	  Calendar cal = Calendar.getInstance();
	  cal.set(Calendar.YEAR, year);
	  cal.set(Calendar.MONTH, month-1);
	  cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
	 
	  return DATE_FORMATE_yyyy_MM_dd.format(cal.getTime());
	 }
	 
	 public static Date getFirstDayOfMonthAsDate(String date) {
		 if (date==null||date.equals("")) {
			 return null;
		 }
		 int year = Integer.parseInt(date.split("-")[0]);
		 int month = Integer.parseInt(date.split("-")[1]);
		 Calendar cal = Calendar.getInstance();
		 cal.set(Calendar.YEAR, year);
		 cal.set(Calendar.MONTH, month-1);
		 cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		 
		 return cal.getTime();
	 }
	  
	 /**
	  * 得到某年某月的最后一天
	  * @param year
	  * @param month
	  * @return
	  */
	 public static String getLastDayOfMonth(String date) {
		 if (date==null||date.equals("")) {
				return null;
			}
		 int year = Integer.parseInt(date.split("-")[0]);
		 int month = Integer.parseInt(date.split("-")[1]);
	  Calendar cal = Calendar.getInstance();
	  cal.set(Calendar.YEAR, year);
	  cal.set(Calendar.MONTH, month-1);
	   cal.set(Calendar.DAY_OF_MONTH, 1);
	  int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	  cal.set(Calendar.DAY_OF_MONTH, value);
	 
	  return DATE_FORMATE_yyyy_MM_dd.format(cal.getTime());
	 }
	 public static Date getLastDayOfMonthAsDate(String date) {
		 if (date==null||date.equals("")) {
			 return null;
		 }
		 int year = Integer.parseInt(date.split("-")[0]);
		 int month = Integer.parseInt(date.split("-")[1]);
		 Calendar cal = Calendar.getInstance();
		 cal.set(Calendar.YEAR, year);
		 cal.set(Calendar.MONTH, month-1);
		 cal.set(Calendar.DAY_OF_MONTH, 1);
		 int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		 cal.set(Calendar.DAY_OF_MONTH, value);
		 
		 return cal.getTime();
	 }
	 /**
	 *@param date是为则默认今天日期、可自行设置“2013-06-03”格式的日期 
	 *@return  返回1是星期日、2是星期一、3是星期二、4是星期三、5是星期四、6是星期五、7是星期六 
	 */  
	   
	 public static String getDayofweek(String date){  
	   Calendar cal = Calendar.getInstance();  
	   if (date==null||date.equals("")) {  
	    cal.setTime(new Date(System.currentTimeMillis()));  
	   }else {  
	    cal.setTime(new Date(getDateByStr2(date).getTime()));  
	   }  
	   switch (cal.get(Calendar.DAY_OF_WEEK)) {
	case 1:
		return "星期日";
	case 2:
		return "星期一";
	case 3:
		return "星期二";
	case 4:
		return "星期三";
	case 5:
		return "星期四";
	case 6:
		return "星期五";
	case 7:
		return "星期六";
	default:
		break;
	}
	   return null;
	  }  
	   
	 public static Date getDateByStr2(String dd)  {  
	   Date date;  
	   try {  
	    date = DATE_FORMATE_yyyy_MM_dd.parse(dd);  
	   } catch (ParseException e) {  
	    date = null;  
	    e.printStackTrace();  
	   }  
	   return date;  
	  } 
	 
//	 计算两个日期相隔的天数；
	 public static int differentDaysByMillisecond(Date date1,Date date2)
	    {
	        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
	        return days;
	    }
//	 比较两个日期是否是同一天；
		public static boolean isEqualDate(Date date,Date date2){
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date);
			
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);
			
			if (cal1.get(Calendar.DAY_OF_MONTH) == cal2
					.get(Calendar.DAY_OF_MONTH)) {
				return true;
			}
		return false;
	}
		
		 /** 
	     * 将Date类转换为XMLGregorianCalendar 
	     * @param date 
	     * @return  
	     */  
	    public static XMLGregorianCalendar dateToXmlDate(java.util.Date date){  
	            Calendar cal = Calendar.getInstance();  
	            cal.setTime(date);  
	            DatatypeFactory dtf = null;  
	             try {  
	                dtf = DatatypeFactory.newInstance();  
	            } catch (DatatypeConfigurationException e) {  
	            }  
	            XMLGregorianCalendar dateType = dtf.newXMLGregorianCalendar();  
	            dateType.setYear(cal.get(Calendar.YEAR));  
	            //由于Calendar.MONTH取值范围为0~11,需要加1  
	            dateType.setMonth(cal.get(Calendar.MONTH)+1);  
	            dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));  
	            dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));  
	            dateType.setMinute(cal.get(Calendar.MINUTE));  
	            dateType.setSecond(cal.get(Calendar.SECOND));  
	            return dateType;  
	        }   
	  
	    /** 
	     * 将XMLGregorianCalendar转换为Date 
	     * @param cal 
	     * @return  
	     */  
	    public static Date xmlDate2Date(XMLGregorianCalendar cal){  
	        return cal.toGregorianCalendar().getTime();  
	    }  
	    
	    public static List<Date> xmlDate2Date(List<XMLGregorianCalendar> cal){  
	    	List<Date> dates = new ArrayList<Date>();
	    	for (XMLGregorianCalendar xmlGregorianCalendar : cal) {
				dates.add(xmlDate2Date(xmlGregorianCalendar));
			}
	    	return dates;
	    }  
	    
	    /**
	     * 
	     * @param dataList 原始数据
	     * @param entities 需要过滤的entities
	     * @param idHeader 对应idEntity的那一列的header；
	     * @return
	     */
	 
	    public static List<Map<String, Object>> FilterEntitiesByEntityId(List<Map<String, Object>> dataList,List<String> entities,String idHeader){
	    	List<Map<String, Object>> filterMap = new ArrayList<Map<String,Object>>();
	    	Iterator<Map<String, Object>> iterator2 = dataList.iterator();
	    	for (Iterator iterator = iterator2; iterator.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator.next();
				Object object = map.get(idHeader);
				if (entities.contains(object.toString())) {
					filterMap.add(map);
				}
	    	}
	    	return filterMap;
	    }
	    
	    public static String NumberFormatSeparate(Double number,int n){
			if (number==null) {
				return "";
			}
			String sReturn="";
			NumberFormat nf=NumberFormat.getInstance(Locale.CANADA);
		      nf.setMinimumFractionDigits(n);//不足n位小数，则以0补位。
		      nf.setMaximumFractionDigits(n);//最多保留n位小数。
		      sReturn=nf.format(number);
		      if(sReturn.equals("-0.00")){
		    	 sReturn = "0.00";
		      }
			return sReturn;
		}
	    
	    public static String transferYorN(String yesorno){
	    	if ("Y".equalsIgnoreCase(yesorno)){
	    		return "是";
	    	}else if ("N".equalsIgnoreCase(yesorno)){
	    		return "否";
	    	}else {
	    		return "-";
	    	}
	    }
	    
	    public static String showDateToString(Date date){
			if(date != null){
				String show = DATE_FORMATE_yyyy_MM_dd.format(date);
				if (show.equals("1900-01-01")) {
					return "-";
				}else {
					return show;
				}
			}else {
				return "";
			}
		}
	    
		/**
		 * 获取相对于给定日期的基础上增加或减少addNumber 个月的日期
		 * @param provideDate
		 * @param addNumber
		 * @return
		 */
		public static Date getMonthByAddNumber(Date provideDate,Integer addNumber) {
			Calendar c = Calendar.getInstance();
			c.setTime(provideDate);
			c.add(Calendar.MONTH, addNumber);
			return c.getTime();
		}
		/**
		 * 获取相对于给定日期的基础上增加或减少addNumber 个年
		 * @param provideDate
		 * @param addNumber
		 * @return
		 */
		public static Date getYearByAddNumber(Date provideDate,Integer addNumber) {
			Calendar c = Calendar.getInstance();
			c.setTime(provideDate);
			c.add(Calendar.YEAR, addNumber);
			return c.getTime();
		}
		/**
		 * 获取相对于给定日期的基础上增加或减少addNumber 天的日期
		 * @param provideDate
		 * @param addNumber
		 * @return
		 */
		public static Date getdayByAddNumber(Date provideDate,Integer addNumber) {
			Calendar c = Calendar.getInstance();
			c.setTime(provideDate);
			c.add(Calendar.DATE, addNumber);
			return c.getTime();
		}

		/**
		 * 判断两个日期是不是同一天
		 * @param date1
		 * @param date2
		 * @return
		 */
			public static boolean isSameDate(Date date1, Date date2) {
		        if (DateToString(date1).equals(DateToString(date2)))
		        	return true;
		        else
		        	return false;
				
			}


		
		
	/**
	 * 判断两个日期是不是同年同月
	 * @param date1
	 * @param date2
	 * @return
	 */
		public static boolean isSameYearMonth(Date date1, Date date2) {
	        Calendar calendar1 = Calendar.getInstance();
	        calendar1.setTime(date1);
	        Calendar calendar2 = Calendar.getInstance();
	        calendar2.setTime(date2);
	        int year1 = calendar1.get(Calendar.YEAR);
	        int year2 = calendar2.get(Calendar.YEAR);
	        int month1 = calendar1.get(Calendar.MONTH);
	        int month2 = calendar2.get(Calendar.MONTH);
	        if (year1==year2 && month1==month2)
	        	return true;
	        else
	        	return false;
			
		}

		/**
		 * 判断两个日期是不是同一年度
		 * @param date1
		 * @param date2
		 * @return
		 */
			public static boolean isSameYear(Date date1, Date date2) {
		        Calendar calendar1 = Calendar.getInstance();
		        calendar1.setTime(date1);
		        Calendar calendar2 = Calendar.getInstance();
		        calendar2.setTime(date2);
		        int year1 = calendar1.get(Calendar.YEAR);
		        int year2 = calendar2.get(Calendar.YEAR);
		        if (year1==year2 )
		        	return true;
		        else
		        	return false;
				
			}
		
		/**
		 * 获取两个日期之间的日期
		 * @param start 开始日期
		 * @param end 结束日期
		 * @return 日期集合
		 */
		public static List<Date> getIntervalDates(Date start, Date end) {
		    List<Date> result = new ArrayList<Date>();
		    Calendar tempStart = Calendar.getInstance();
		    tempStart.setTime(start);
		    
		    Calendar tempEnd = Calendar.getInstance();
		    tempEnd.setTime(end);
		    if (isEqualDate(start, end)) {
		    	result.add(start);
		    	return result;
			}
		    while (tempStart.before(tempEnd)) {
		        result.add(tempStart.getTime());
		        tempStart.add(Calendar.DAY_OF_YEAR, 1);
		    }
		    result.add(end);
		    return result;
		}
		
		/** 
		 * 实例代码：处理用户输入内容 
		 */  
		public static String dealWithXSS(String value) {    
		    value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");    
		    value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");    
		    value = value.replaceAll("'", "& #39;");    
		    value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");    
		    value = value.replaceAll("script", "");    
		    value = value.replaceAll("eval\\((.*)\\)", "");    
		    return value;    
		}  
		
  
  public static Date StringToParseDate(String dateStr){
	  Date parse = null;
	try {
		parse = DATE_FORMATE_yyyy_MM_dd.parse(dateStr.replace("/", "-"));
	} catch (ParseException e) {
		e.printStackTrace();
	}
	  return parse;
  }
  /**
   * 
   * @param moduleName 模块名称
   * @param subModuleName 子模块名称
   * @param step 步骤
   * @param objectId 对象ID
   * @param classType 类型
   * @param degree 严重程度
   * @param description 描述
   * @return
   */

  public static boolean logEx(String moduleName,String subModuleName,String step,String objectId,Class<?> classType ,String degree,String description){
	  MDC.put("moduleName", moduleName);
	  MDC.put("subModuleName", subModuleName);
	  MDC.put("step", step);
	  MDC.put("objectId", objectId);
	  MDC.put("classType", classType.getTypeName());
	  MDC.put("degree", degree);
	  MDC.put("description", description);
	  
	  try {
		  logger.info(split+description);
	} catch (Exception e) {
		logger.error("日志打印异常"+moduleName+objectId+description);
		e.printStackTrace();
		return false;
	}finally {
		MDC.clear();
	}
	  return true;
  }
  
}
