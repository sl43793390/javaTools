package com.rquest.test.excelutil;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class IndustryExcelModel extends BaseRowModel {
	@ExcelProperty(index = 0)
	private String title;
	@ExcelProperty(index = 1,format = "yyyy/MM/dd")
	private Date dt_publish;
	@ExcelProperty(index = 2)
	private String author;
	@ExcelProperty(index = 3)
	private String nameSource;
	@ExcelProperty(index = 4)
	private String nameSecurities;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDt_publish() {
		return dt_publish;
	}

	public void setDt_publish(Date dt_publish) {
		this.dt_publish = dt_publish;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getNameSource() {
		return nameSource;
	}

	public void setNameSource(String nameSource) {
		this.nameSource = nameSource;
	}

	public String getNameSecurities() {
		return nameSecurities;
	}

	public void setNameSecurities(String nameSecurities) {
		this.nameSecurities = nameSecurities;
	}

	@Override
	public String toString() {
		return "IndustryExcelModel [title=" + title + ", dt_publish=" + dt_publish + ", author=" + author + ", nameSource="
				+ nameSource + ", nameSecurities=" + nameSecurities + "]";
	}

	
}
