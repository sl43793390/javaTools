package com.rquest.test.excelutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rquest.riskmaster.util.Util;

public class ExcelReader {

	private static final Logger log = LoggerFactory.getLogger(ExcelReader.class);


	/**
	 * sheetNo 读取的sheet index 从零开始
	 * @param sheetNo
	 * @return
	 */
	public List<Map<String, Object>> readExcel(Integer sheetNo) {

		InputStream ins = null;
		Workbook wb = null;
		String filePath = ResourceBundle.getBundle("application").getString("input.file.path");
		try {
			ins = new FileInputStream(new File(filePath));
			wb = WorkbookFactory.create(ins);
			ins.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 3.寰楀埌Excel宸ヤ綔琛ㄥ璞�
		Sheet sheet = wb.getSheetAt(sheetNo);
		// 鎬昏鏁�
		int trLength = sheet.getLastRowNum();
		// 4.寰楀埌Excel宸ヤ綔琛ㄧ殑琛�
		Row row0 = sheet.getRow(0);
		// 鎬诲垪鏁�
		int tdLength = row0.getLastCellNum();

		List<Map<String, Object>> tableList = new ArrayList<Map<String, Object>>();

		for (int i = 1; i < trLength + 1; i++) {
			// 寰楀埌Excel宸ヤ綔琛ㄧ殑琛�
			Row row = sheet.getRow(i);
			if (row != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int j = 0; j < tdLength; j++) {
					Cell cell = row.getCell(j);
					if (cell == null) {
						map.put(row0.getCell(j).getStringCellValue(), null);
					} else {
						int cellType = cell.getCellType();
						switch (cellType) {
						case Cell.CELL_TYPE_NUMERIC: //数字  
							if (DateUtil.isCellDateFormatted(cell)){
				                Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
				                String value = Util.DateToString(date);
				                map.put(row0.getCell(j).getStringCellValue(),value);
				            }else {
				            	map.put(row0.getCell(j).getStringCellValue(), cell.getNumericCellValue());
				            }
			                break;  
			            case Cell.CELL_TYPE_STRING: //字符串  
			            	map.put(row0.getCell(j).getStringCellValue(), cell.getStringCellValue());
			                break;  
			            case Cell.CELL_TYPE_BOOLEAN: //Boolean  
			            	map.put(row0.getCell(j).getStringCellValue(), cell.getBooleanCellValue());
			                break;  
			            case Cell.CELL_TYPE_FORMULA: //公式  
			            	map.put(row0.getCell(j).getStringCellValue(), cell.getCellFormula());
			                break;  
			            case Cell.CELL_TYPE_BLANK: //空值   
			            	map.put(row0.getCell(j).getStringCellValue(), ""); 
			                break;  
			            case Cell.CELL_TYPE_ERROR: //故障  
			            	map.put(row0.getCell(j).getStringCellValue(), cell.getErrorCellValue());  
			                break;  
			            default:  
			            	map.put(row0.getCell(j).getStringCellValue(),null);
			                break;
						}
						
					}

				}

				tableList.add(map);
			}
		}
		return tableList;
	}

}
