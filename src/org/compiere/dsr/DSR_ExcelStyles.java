package org.compiere.dsr;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

public class DSR_ExcelStyles 
{	
	public static CellStyle getHeaderStyle(HSSFWorkbook wb)
	{
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		CellStyle headerStyle = wb.createCellStyle();
		headerStyle.setBorderBottom((short)1);
		headerStyle.setBorderTop((short)1);
		headerStyle.setBorderLeft((short)1);
		headerStyle.setBorderRight((short)1);
		headerStyle.setWrapText(true);
		headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerStyle.setFont(font);
		
		return headerStyle;
	}
	
	public static CellStyle getCommonStyle(HSSFWorkbook wb)
	{
		CellStyle commonStyle = wb.createCellStyle();
		commonStyle.setBorderBottom((short)1);
		commonStyle.setBorderTop((short)1);
		commonStyle.setBorderLeft((short)1);
		commonStyle.setBorderRight((short)1);
		commonStyle.setWrapText(true);
		commonStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		commonStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		
		return commonStyle;
	}
	
	public static CellStyle getRowHeader(HSSFWorkbook wb)
	{
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		CellStyle rowHeader = wb.createCellStyle();
		rowHeader.setBorderBottom((short)1);
		rowHeader.setBorderTop((short)1);
		rowHeader.setBorderLeft((short)1);
		rowHeader.setBorderRight((short)1);
		rowHeader.setWrapText(true);
		rowHeader.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		rowHeader.setAlignment(CellStyle.ALIGN_CENTER);
		rowHeader.setFont(font);
		return rowHeader;
	}
}
