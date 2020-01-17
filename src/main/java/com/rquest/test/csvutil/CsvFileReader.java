package com.rquest.test.csvutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.rquest.test.util.Util;

/**
 * @author ashraf_sarhan
 */

public class CsvFileReader {

	// CSV文件头

	private static final String[] FILE_HEADER = { "用户名", "密码", "名称", "年龄" };

	public static void main(String[] args) {
		readCsvFile("d://demo.csv");

	}

	public static void readCsvFile(String fileName) {

		BufferedReader fileReader = null;
		CSVParser csvFileParser = null;
		// 创建CSVFormat（header mapping）
		 CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER);
//		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withIgnoreEmptyLines().withIgnoreHeaderCase();
		try {
			// 初始化FileReader object
			fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)), "GBK"));
			// 初始化 CSVParser object
			csvFileParser = new CSVParser(fileReader, csvFileFormat);
			// CSV文件records
			List<CSVRecord> csvRecords = csvFileParser.getRecords();
			// CSV
			List<Student> userList = new ArrayList<Student>();

			for (int i = 1; i < csvRecords.size(); i++) {

				CSVRecord record = csvRecords.get(i);

				// 创建用户对象填入数据
				Integer age = null;
				boolean validateNumberisNaN = Util.validateNumberisNaN(record.get("年龄"));
				if (validateNumberisNaN) {
					age = Integer.parseInt(record.get("年龄").replaceAll(",", ""));
				}
				Student user = new Student(record.get("用户名"), record.get("密码"),record.get("名称"), age);

				userList.add(user);

			}

			for (Student user : userList) {
				System.out.println(user.toString());
			}

		}

		catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				fileReader.close();

				csvFileParser.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

}