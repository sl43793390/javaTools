package com.rquest.test.itextdemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.org.apache.xml.internal.dtm.DTMException;

public class SimpleTable {
	public static final String DEST = "D://simple_table.pdf";

	public static void main(String[] args) throws IOException, DTMException {
		File file = new File(DEST);
		file.getParentFile().mkdirs();
	}

	public void createPdf(String dest) throws IOException, DocumentException {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(dest));
		document.open();
		PdfPTable table = new PdfPTable(8);
		for (int aw = 0; aw < 16; aw++) {
			table.addCell("hi");
		}
		document.add(table);
		document.close();
	}

}
