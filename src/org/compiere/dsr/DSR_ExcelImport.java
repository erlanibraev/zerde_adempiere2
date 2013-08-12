package org.compiere.dsr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.compiere.model.MAttachmentEntry;

import extend.org.compiere.utils.Util;

public class DSR_ExcelImport 
{
	static CellStyle headerStyle = null;
	static CellStyle commonStyle = null;
	static CellStyle rowHeader = null;
	
	public static void collectionImport(MAttachmentEntry entry, DSR_DataCollection collection) throws IOException, JRException
	{
		HSSFWorkbook wb = new HSSFWorkbook(entry.getInputStream());
		HSSFSheet sheet = wb.getSheetAt(0);
		
		headerStyle = DSR_ExcelStyles.getHeaderStyle(wb);
		commonStyle = DSR_ExcelStyles.getCommonStyle(wb);
		rowHeader = DSR_ExcelStyles.getRowHeader(wb);
		
		int rowCount = collection.size();
		
		createRow(sheet, collection.getHeader(), 0);
		
		for(int i = 0; i < rowCount; i++)
		{
			createRow(sheet, collection.getRow(i), i + 1);
		}
		
		String fileOutPath = getXlsName(entry.getFile().getPath());
		
		FileOutputStream fileOut = new FileOutputStream(fileOutPath);
		wb.write(fileOut);
		fileOut.close();
		
		Util.launchFile(new File(fileOutPath));
	}
	
	private static void createRow(HSSFSheet sheet, DSR_Row dsrRow, int index)
	{
		Row xlsRow = sheet.createRow(index);
		DSR_Cell dsrCell = null;		
		Cell xlsCell = null;
		xlsRow.setHeight((short)600);
		int cellCount = dsrRow.size();	
		for(int j = 0; j < cellCount; j++)
		{
			dsrCell = dsrRow.getCell(j);
			xlsCell = xlsRow.createCell(j);
			
			if(dsrCell.isHeader)
				xlsCell.setCellStyle(headerStyle);
			else if(dsrCell.isRow)
				xlsCell.setCellStyle(rowHeader);
			else
				xlsCell.setCellStyle(commonStyle);
			
			xlsCell.setCellValue(dsrCell.getValue().toString());
		}
	}
	
	private static String getXlsName(String reportName) throws JRException {
		String Result = null;
		String tmpName = reportName;
		Integer i = 0;
		
		File d = new File(Util.localFilePath);
        String[] lF = d.list(new WildcardFileFilter(tmpName+"*.xls"));
        
        for(int j=0; j < lF.length; j++) {
        	File tmp = new File(lF[j]);
        	tmp.delete();
        }
        
		boolean flag = true;
		while (flag) {
			File f = new File(Util.localFilePath +"/"+ tmpName+".xls");
			try { 
				if (f.exists() && !f.delete()) {
					tmpName = reportName + "(" +(++i).toString() + ")";
				} else {
					flag = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				tmpName = reportName + "(" +(++i).toString() + ")";
			}
		}
		Result = Util.localFilePath +"/"+ tmpName+".xls";
		return Result;
	}
}
