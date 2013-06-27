package org.compiere.process;

import java.io.File;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.apps.ADialog;
import org.compiere.model.MAttachmentEntry;
import org.compiere.util.DB;
import org.compiere.util.Msg;

import extend.org.compiere.hrm.ExcelCell;
import extend.org.compiere.utils.Util;

public class TRM_DepositReport extends SvrProcess 
{
	private String path = "";

	private Timestamp BeginPeriod;
	private Timestamp EndPeriod;
	
	private String depositAccount = "1040";
	
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		
		for(int i = 0; i < para.length; i++) 
		{
			String name = para[i].getParameterName();			
			if (para[i].getParameter() == null);
			else if (name.equals("BeginPeriod"))
				BeginPeriod = (Timestamp)para[i].getParameter();
			else if (name.equals("EndPeriod"))
				EndPeriod = (Timestamp)para[i].getParameter();
			else if (name.equals("Path"))
				path = (String)para[i].getParameter();
			else
			{
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}				
		}
		
		if(path.isEmpty())
		{
			path = Util.localFilePath;
		}
		else
			path = path + System.getProperty("file.separator");
	}

	@Override
	protected String doIt() throws Exception 
	{
		String patternName = "deposit_report";
		
		//get attachment FROM the process
		MAttachmentEntry entry = Util.getAttachment(getProcessInfo(), getCtx(), patternName);
		if(entry == null)
			throw new AdempiereException(Msg.translate(getCtx(), "NotFoundTemplate")+" "+patternName);
		
		CreateLines(BeginPeriod, EndPeriod, entry);
		
		return null;
	}
	
	private BigDecimal StartPeriodSum(Timestamp date)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select coalesce(sum(fa.amtacctcr - fa.amtacctdr),0)");
		sql.append("from fact_acct fa, c_elementvalue ce  ");
		sql.append("where fa.account_id=ce.c_elementvalue_id ");
		sql.append("and PostingType='A'	and fa.dateacct < '" + date + "' and ce.value like '" + depositAccount + "%'");
		
		BigDecimal totalSum = new BigDecimal(0);
		
		try
		{
			@SuppressWarnings("deprecation")
			PreparedStatement pstmt = DB.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				if(rs.getBigDecimal(1) != null)
				{
					totalSum = totalSum.add(rs.getBigDecimal(1));
				}
			}
			
			rs.close(); pstmt.close();
			rs = null; pstmt = null;
		}
		catch(Exception ex) {}
		
		if(totalSum.intValue() == 0)
			totalSum = new BigDecimal(1);
		
		return totalSum;
	}
	
	private BigDecimal EndPeriodSum(Timestamp date, Timestamp date2)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select coalesce(sum(fa.amtacctcr - fa.amtacctdr),0)");
		sql.append("from fact_acct fa, c_elementvalue ce  ");
		sql.append("where fa.account_id=ce.c_elementvalue_id ");
		sql.append("and PostingType='A'	and fa.dateacct BETWEEN '" + date + "' AND '" + date2 + "' and ce.value like '" + depositAccount + "%'");
		
		BigDecimal totalSum = new BigDecimal(0);
		
		try
		{
			@SuppressWarnings("deprecation")
			PreparedStatement pstmt = DB.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				if(rs.getBigDecimal(1) != null)
				{
					totalSum = totalSum.add(rs.getBigDecimal(1));
				}
			}
			
			rs.close(); pstmt.close();
			rs = null; pstmt = null;
		}
		catch(Exception ex) {}
		
		if(totalSum.intValue() == 0)
			totalSum = new BigDecimal(1);
		
		return totalSum;
	}
	
	@SuppressWarnings("deprecation")
	private void CreateLines(Timestamp beginperiod, Timestamp endperiod, MAttachmentEntry entry) throws WriteException
	{
		
		// We define a path to generate
		String fileExtension = entry.getName().substring(entry.getName().lastIndexOf("."),entry.getName().length());
		StringBuffer fullPath = new StringBuffer(path);		
		fullPath.append("Temporary TRM Deposit Contract Sheets");		
		fullPath.append(fileExtension);
				
		File templateCopy = new File(fullPath.toString());
		File temporaryFile = entry.getFile(Util.localFilePath+ entry.getName());
		Workbook tableBook = null;
		WritableWorkbook copy = null;
		WritableSheet sheet = null;
				
		try 
		{
			tableBook = Workbook.getWorkbook(temporaryFile);
			copy = Workbook.createWorkbook(templateCopy, tableBook);
			sheet = copy.getSheet(0);
        } 
		catch (Exception e) {
        	ADialog.error(999, null, "Error tableBook. " + e.getMessage());
        }
		
		ExcelCell cellStart =  Util.getCellStart(tableBook,">>");
		
		// Style for value column
		WritableCellFormat nn = new WritableCellFormat();
		nn.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
		nn.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
		nn.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
		nn.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
		nn.setAlignment(Alignment.CENTRE);
		nn.setVerticalAlignment(VerticalAlignment.CENTRE);
		nn.setWrap(true);
	    
		
	    WritableCellFormat valuestyle = new WritableCellFormat();
	    valuestyle.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
	    valuestyle.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
	    valuestyle.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
	    valuestyle.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
	    valuestyle.setAlignment(Alignment.LEFT);
	    valuestyle.setVerticalAlignment(VerticalAlignment.CENTRE);
	    valuestyle.setWrap(true);
	    // Style for name column
	    WritableCellFormat namestyle = new WritableCellFormat();
	    namestyle.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
	    namestyle.setAlignment(Alignment.LEFT);
	    namestyle.setVerticalAlignment(VerticalAlignment.CENTRE);
	    namestyle.setWrap(true);
	    //Style for number column
	    WritableCellFormat numcolstyle = new WritableCellFormat();
	    numcolstyle.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
	    numcolstyle.setAlignment(Alignment.RIGHT);
	    numcolstyle.setVerticalAlignment(VerticalAlignment.CENTRE);
	    numcolstyle.setWrap(true);
	    //Style for last number column
	    WritableCellFormat lastNumcolstyle = new WritableCellFormat();
	    lastNumcolstyle.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
	    lastNumcolstyle.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
	    lastNumcolstyle.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
	    lastNumcolstyle.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
	    lastNumcolstyle.setAlignment(Alignment.RIGHT);
	    lastNumcolstyle.setVerticalAlignment(VerticalAlignment.CENTRE);
	    lastNumcolstyle.setWrap(true);
	    //Style for total row
	    WritableCellFormat totalrowstyle = new WritableCellFormat();
	    totalrowstyle.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
	    totalrowstyle.setAlignment(Alignment.CENTRE);
	    totalrowstyle.setVerticalAlignment(VerticalAlignment.CENTRE);
	    totalrowstyle.setWrap(true);
	    totalrowstyle.setBackground(Colour.GREY_25_PERCENT);
	    
	    DateFormat customDateFormat = new DateFormat ("dd.MM.yy");
	    WritableCellFormat dateFormat = new WritableCellFormat (customDateFormat);
	    dateFormat.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
	    dateFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
	    dateFormat.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
	    dateFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
	    dateFormat.setAlignment(Alignment.RIGHT);
	    dateFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
	    	    
	    Number number;
		Label label;
		DateTime time;
		
		try
		{
			tableBook = Workbook.getWorkbook(temporaryFile);
			copy = Workbook.createWorkbook(templateCopy, tableBook);
			sheet = copy.getSheet(0);
		}
		catch (Exception e) 
		{
		     ADialog.error(999, null, "Error tableBook. " + e.getMessage());
		}
		
		int col = cellStart.getC();
	    int row = cellStart.getR();
		int index = row;
		
		sheet.mergeCells(col, row, col+17, row);
		
		sheet.addCell(new Label(col,row, "Информация по депозитам за период: " + (beginperiod.getDate() < 10 ? "0" + beginperiod.getDate() : beginperiod.getDate())  + "." + 
															(beginperiod.getMonth() < 10 ? "0" + beginperiod.getMonth() : beginperiod.getMonth()) 
															+ "." + 
															(beginperiod.getYear() + 1900) + " - " + 
															(endperiod.getDate() < 10 ? "0" + endperiod.getDate() : endperiod.getDate())  + "." + 
															(endperiod.getMonth() < 10 ? "0" + endperiod.getMonth() : endperiod.getMonth()) + "." + 
															(endperiod.getYear() + 1900), valuestyle));
		
		
		index += 3;
		

		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT b.Name as \"bankName\"");
		sql.append(", (SELECT SUM(LineSum) FROM TRM_DepositLine WHERE DateAcct <= '" + beginperiod + "' AND TRM_Deposit_ID = d.TRM_Deposit_ID) as \"BeginningBalance\"");
		sql.append(", (SELECT SUM(LineSum) FROM TRM_DepositLine WHERE DateAcct <= '" + endperiod + "' AND TRM_Deposit_ID = d.TRM_Deposit_ID) as \"EndingBalance\"");
		sql.append(", d.EstablishSublimit");
		sql.append(", (SELECT SUM(LineSum) FROM TRM_DepositLine WHERE DateAcct <= '" + beginperiod + "' AND TRM_Deposit_ID = d.TRM_Deposit_ID) - (SELECT SUM(LineSum) FROM TRM_DepositLine WHERE DateAcct <= '" + endperiod + "' AND TRM_Deposit_ID = d.TRM_Deposit_ID) as \"Balance\"");
		sql.append(", (SELECT LineSum FROM TRM_DepositLine WHERE DateAcct = (SELECT MIN(DateAcct) FROM TRM_DepositLine WHERE TRM_Deposit_ID = d.TRM_Deposit_ID) AND TRM_Deposit_ID = d.TRM_Deposit_ID) as \"FirstSum\"");
		sql.append(", d.InterestRate");
		sql.append(", c.PlacementPeriod");
		sql.append(", c.BeginningDateExecution");
		sql.append(", c.EndDateExecution");
		sql.append(", d.isPartialWithdrawal");
		sql.append(", d.MinBalance");
		sql.append(", p.Name");
		sql.append(", c.isCapitalization ");
		sql.append(", (SELECT SUM(Premium) FROM TRM_Reward WHERE DateReward BETWEEN '" + beginperiod + "' AND '" + endperiod + "' AND TRM_Deposit_ID = d.TRM_Deposit_ID) as \"Premium\"");
		sql.append("FROM TRM_Deposit d ");
		sql.append("INNER JOIN C_Bank b ON d.C_Bank_ID = b.C_Bank_ID ");
		sql.append("INNER JOIN CMS_Contract c ON c.CMS_Contract_ID = d.CMS_Contract_ID ");
		sql.append("LEFT JOIN CMS_PaymenyCompensationType p ON c.CMS_PaymenyCompensationType_ID = p.CMS_PaymenyCompensationType_ID");
		
		int counter = 1;
		
		BigDecimal startPeriodTotalSum = new BigDecimal(0); //StartPeriodSum(beginperiod);
		BigDecimal endPeriodTotalSum = new BigDecimal(0);//EndPeriodSum(beginperiod, endperiod);
		
		BigDecimal totalPremium = new BigDecimal(0);
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				BigDecimal lineStartPeriodSum = rs.getBigDecimal("BeginningBalance") == null ? new BigDecimal(0.0) : rs.getBigDecimal("BeginningBalance");
				BigDecimal lineEndPeriodSum = rs.getBigDecimal("EndingBalance") == null ? new BigDecimal(0.0) : rs.getBigDecimal("EndingBalance");
				
				startPeriodTotalSum = startPeriodTotalSum.add(lineStartPeriodSum);
				endPeriodTotalSum = endPeriodTotalSum.add(lineEndPeriodSum);
				
				number = new Number(col, index, counter++ , nn);
				sheet.addCell(number);
				
				label = new Label(col + 1, index, rs.getString("bankName") == null ? "" : rs.getString("bankName"), valuestyle);
		    	sheet.addCell(label);

		    	number = new Number(col+2, index, lineStartPeriodSum.doubleValue(), numcolstyle);
				sheet.addCell(number);
				
				number = new Number(col+3, index, lineEndPeriodSum.doubleValue(), numcolstyle);
				sheet.addCell(number);
				
				number = new Number(col+4, index, rs.getBigDecimal("EstablishSublimit") == null ? 0.0 : rs.getBigDecimal("EstablishSublimit").doubleValue(), numcolstyle);
				sheet.addCell(number);
				
				number = new Number(col+5, index, lineEndPeriodSum.subtract(lineStartPeriodSum).doubleValue() , numcolstyle);
				sheet.addCell(number);
				
				number = new Number(col+6, index, rs.getBigDecimal("FirstSum") == null ? 0.0 : rs.getBigDecimal("FirstSum").doubleValue(), numcolstyle);
				sheet.addCell(number);
				
				number = new Number(col+7, index, lineEndPeriodSum.divide(endPeriodTotalSum, 2, RoundingMode.HALF_UP).doubleValue(), numcolstyle);
				sheet.addCell(number);
				
//				label = new Label(col + 7, index, 0 + "%", valuestyle);
//		    	sheet.addCell(label);
				
				label = new Label(col + 8, index, rs.getBigDecimal("InterestRate") == null ? "" : rs.getBigDecimal("InterestRate").doubleValue() + "%", nn);
		    	sheet.addCell(label);
		    	
		    	number = new Number(col+9, index, rs.getBigDecimal("PlacementPeriod") == null ? 0 : rs.getBigDecimal("PlacementPeriod").intValue(), nn);
				sheet.addCell(number);
				
				if(rs.getDate("BeginningDateExecution") == null)
				{
					label = new Label(col + 10, index, "", valuestyle);
			    	sheet.addCell(label);
				}
				else
				{
					time = new DateTime(col+10, index, new java.sql.Date(rs.getDate("BeginningDateExecution").getTime()), dateFormat);
					sheet.addCell(time);
				}
				
				if(rs.getDate("EndDateExecution") == null)
				{
					label = new Label(col + 11, index, "", valuestyle);
			    	sheet.addCell(label);
				}
				else
				{
					time = new DateTime(col+11, index, new java.sql.Date(rs.getDate("EndDateExecution").getTime()), dateFormat);
					sheet.addCell(time);
				}
				
				BigDecimal premium = rs.getBigDecimal("Premium") == null ? new BigDecimal(0) : rs.getBigDecimal("Premium");
        		
        		number = new Number(col+12, index, premium.doubleValue(), numcolstyle);
				sheet.addCell(number);
				
				totalPremium = totalPremium.add(premium);
				
				boolean isPartialWithdrawal = rs.getString(11).equals("Y");
				
				label = new Label(col + 13, index, isPartialWithdrawal ? "Да" : "Нет", nn);
		    	sheet.addCell(label);
		    	
		    	number = new Number(col+14, index, rs.getBigDecimal("MinBalance") == null ? 0 : rs.getBigDecimal("MinBalance").doubleValue(), numcolstyle);
				sheet.addCell(number);
		    	
		    	label = new Label(col + 15, index, rs.getString("Name") == null ? "" : rs.getString("Name"), nn);
		    	sheet.addCell(label);
		    	
		    	number = new Number(col+16, index, rs.getBigDecimal("Premium") == null ? 0 : rs.getBigDecimal("Premium").multiply(new BigDecimal(0.15)).doubleValue(), numcolstyle);
				sheet.addCell(number);
				
				label = new Label(col + 17, index, rs.getBoolean("isCapitalization") ? "Да" : "Нет", nn);
		    	sheet.addCell(label);
		    	
		    	index++;
			}
			
			LoadLine(sheet, startPeriodTotalSum, endPeriodTotalSum, totalPremium, totalrowstyle, totalrowstyle,  col, index);
			
			copy.write();
		    copy.close();
		    tableBook.close();
		    temporaryFile.delete();
			
			rs.close(); pstmt.close();
			rs = null; pstmt = null;
			
			try
			{    
		   		String fileName = fullPath.toString();
		   		String command = "excel \""+fileName+"\"";
		   		Runtime.getRuntime().exec("cmd /c start "+command);
		   	}
			catch(Exception e)
			{
				e.printStackTrace();
		   	}
		}
		catch(Exception ex){}
	}

	private void LoadLine(WritableSheet sheet, BigDecimal startSum, BigDecimal endSum, BigDecimal premium, WritableCellFormat valuestyle, WritableCellFormat numcolstyle, int col, int index) throws RowsExceededException, WriteException
	{
		Number number;
		Label label;
		
		label = new Label(col, index, "", valuestyle);
    	sheet.addCell(label);
		
		label = new Label(col + 1, index, "Итого", valuestyle);
    	sheet.addCell(label);

    	number = new Number(col+2, index, startSum.doubleValue(), numcolstyle);
		sheet.addCell(number);
		
		number = new Number(col+3, index, endSum.doubleValue(), numcolstyle);
		sheet.addCell(number);
		
		label = new Label(col + 4, index, "", valuestyle);
    	sheet.addCell(label);
				
		number = new Number(col+5, index, endSum.subtract(startSum).doubleValue() , numcolstyle);
		sheet.addCell(number);
		
		label = new Label(col + 6, index, "", valuestyle);
    	sheet.addCell(label);
		
    	label = new Label(col + 7, index, "100%", valuestyle);
    	sheet.addCell(label);
		
    	label = new Label(col + 8, index, "", valuestyle);
    	sheet.addCell(label);
    	
    	label = new Label(col + 9, index, "", valuestyle);
    	sheet.addCell(label);
		
    	label = new Label(col + 10, index, "", valuestyle);
    	sheet.addCell(label);
		
    	label = new Label(col + 11, index, "", valuestyle);
    	sheet.addCell(label);
		
		number = new Number(col+12, index, premium.doubleValue(), numcolstyle);
		sheet.addCell(number);
		
		label = new Label(col + 13, index, "", valuestyle);
    	sheet.addCell(label);
    	
    	label = new Label(col + 14, index, "", valuestyle);
    	sheet.addCell(label);
    	
    	label = new Label(col + 15, index, "", valuestyle);
    	sheet.addCell(label);
    	
    	label = new Label(col + 16, index, "", valuestyle);
    	sheet.addCell(label);
		
    	label = new Label(col + 17, index, "", valuestyle);
    	sheet.addCell(label);
	}
	
}
