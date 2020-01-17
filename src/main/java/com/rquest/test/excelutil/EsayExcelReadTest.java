package com.rquest.test.excelutil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.IndexedColors;

import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.metadata.TableStyle;
import com.alibaba.excel.support.ExcelTypeEnum;

public class EsayExcelReadTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStream inputStream;
		try {
			inputStream = new FileInputStream("D:\\news\\屈庆债券论坛4.26-6.12.xlsx");
			List<Object> readExcelWithModel = EasyExcelUtil.readExcelWithModel(inputStream, IndustryExcelModel.class,
					ExcelTypeEnum.XLSX);
			for (Object object : readExcelWithModel) {
				((IndustryExcelModel) object).toString();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/*
	 * public void testReadExcelWithStringList() { try (InputStream inputStream = new FileInputStream(
	 * "C:\\Users\\Shinelon\\IdeaProjects\\webmagic\\webmagic\\src\\test\\java\\etherscan-page1-1000.xls"); OutputStream outputStream = new FileOutputStream(
	 * "C:\\Users\\Shinelon\\IdeaProjects\\webmagic\\webmagic\\src\\test\\java\\etherscan-page1-1000String.xlsx")) { // 读入文件,每一行对应一个List<String> List<List<String>> stringLists =
	 * EasyExcelUtil.readExcelWithStringList(inputStream, ExcelTypeEnum.XLS);
	 * 
	 * // 定义Excel正文背景颜色 TableStyle tableStyle = new TableStyle(); tableStyle.setTableContentBackGroundColor(IndexedColors.WHITE);
	 * 
	 * // 定义Excel正文字体大小 Font font = new Font(); font.setFontHeightInPoints((short) 10);
	 * 
	 * tableStyle.setTableContentFont(font);
	 * 
	 * Table table = new Table(0); table.setTableStyle(tableStyle);
	 * 
	 * EasyExcelUtil.writeExcelWithStringList(outputStream, stringLists, table, ExcelTypeEnum.XLSX); } catch (IOException e) { e.printStackTrace(); } }
	 * 
	 * public void testReadExcelWithModel() { try (InputStream inputStream = new FileInputStream( "n-page1-1000.xls"); OutputStream outputStream = new FileOutputStream( "0model.xlsx")) { //
	 * 读入文件,每一行对应一个 Model ,获取Model 列表 List<Object> objectList = EasyExcelUtil.readExcelWithModel(inputStream, ETUInfo.class, ExcelTypeEnum.XLS); List<ETUInfo> etuInfoList = (List) objectList; //
	 * 定义Excel正文背景颜色 TableStyle tableStyle = new TableStyle(); tableStyle.setTableContentBackGroundColor(IndexedColors.WHITE);
	 * 
	 * // 定义Excel正文字体大小 Font font = new Font(); font.setFontHeightInPoints((short) 10); tableStyle.setTableContentFont(font);
	 * 
	 * Table table = new Table(0); table.setTableStyle(tableStyle);
	 * 
	 * EasyExcelUtil.writeExcelWithModel(outputStream, etuInfoList, table, ETUInfo.class, ExcelTypeEnum.XLSX); } catch (IOException e) { e.printStackTrace(); } }
	 */

}
