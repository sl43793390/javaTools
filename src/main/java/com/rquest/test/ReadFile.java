package com.rquest.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.srccodes.tools.crypto.EncryptionUtils;


public class ReadFile {

	public static void main(String[] args) {
		List<String> readLines  = null;
		try {
			readLines = FileUtils.readLines(new File("D:\\creditrisk.txt"),"GBK");
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String sql : readLines) {
			String[] split = sql.split(",");
			String str = split[12];
			String[] split2 = str.split("\\(");
//			System.out.println(split2[1]);
//			System.out.println(split2[1]);
			String cre = "信用分析平台";
			System.out.println("INSERT INTO `transfer`.`user_platform` (`user_id`, `platform`, `description`) VALUES ("+split2[1]+", 'CREDIT_RSIK_PLATFORM', '"+cre+"');");
			
			String getkeyByAlgorithm = EncryptionUtils.getkeyByAlgorithm("SM3", split2[1].replace("'", ""));
			String sm3key = "'"+getkeyByAlgorithm+"'";
			split[15] = sm3key;
			StringBuffer bf = new StringBuffer();
			for (int i = 0; i < split.length; i++) {
				if (i==24) {
					continue;
				}
				if (i==split.length-1) {
					bf.append(split[i]);
				}else{
					bf.append(split[i]+",");
				}
			}
		}
//			System.out.println(bf.toString());
	}

}
