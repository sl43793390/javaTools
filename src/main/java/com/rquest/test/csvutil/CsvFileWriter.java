
package com.rquest.test.csvutil;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.rquest.test.entity.User;

/**
 * 
 * @author ashraf
 *
 * 
 * 
 */

public class CsvFileWriter {

	// CSV文件分隔符

	private static final String NEW_LINE_SEPARATOR = "\n";

	// CSV文件头

	private static final Object[] FILE_HEADER = { "用户名", "密码", "名称", "年龄" };

	/**
	 * 
	 * 写CSV文件
	 *
	 * 
	 * 
	 * @param fileName
	 * 
	 */

	public static void writeCsvFile(String fileName) {

		FileWriter fileWriter = null;

		CSVPrinter csvFilePrinter = null;

		// 创建 CSVFormat

		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

		try {

			// 初始化FileWriter

			fileWriter = new FileWriter(fileName);

			// 初始化 CSVPrinter

			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

			// 创建CSV文件头

			csvFilePrinter.printRecord(FILE_HEADER);

			// 用户对象放入List

			List<Student> userList = new ArrayList<Student>();

			userList.add(new Student("zhangsan", "123456", "张三", 25));

			userList.add(new Student("lisi", "123", "李四", 23));

			userList.add(new Student("wangwu", "456", "王五", 24));

			userList.add(new Student("zhaoliu", "zhaoliu", "赵六", 20));

			// 遍历List写入CSV

			for (Student user : userList) {

				List<String> userDataRecord = new ArrayList<String>();

				userDataRecord.add(user.getUsername());

				userDataRecord.add(user.getPassword());

				userDataRecord.add(user.getName());

				userDataRecord.add(String.valueOf(user.getAge()));

				csvFilePrinter.printRecord(userDataRecord);

			}

			System.out.println("CSV文件创建成功~~~");

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				fileWriter.flush();

				fileWriter.close();

				csvFilePrinter.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

	/**
	 * 
	 * @param args
	 * 
	 */

	public static void main(String[] args) {

		writeCsvFile("c://users.csv");

	}

}