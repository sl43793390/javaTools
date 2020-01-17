package com.rquest.datetime.util;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateUtils;


public class Java8InstantTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Instant now = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));;
		System.out.println(now.toEpochMilli());
		System.out.println(Instant.now().atZone(ZoneId.systemDefault()));
		
		Date parseDate = null;
		try {
			parseDate = DateUtils.parseDate("2019-09-20", "yyyy-MM-dd");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(parseDate);
		LocalDateTime localDateTime = LocalDateTime.now();
		//LocalDateTime转Instant
		Instant localDateTime2Instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		System.out.println("LocalDateTime 毫秒数:"+localDateTime2Instant.toEpochMilli());
	}

}
