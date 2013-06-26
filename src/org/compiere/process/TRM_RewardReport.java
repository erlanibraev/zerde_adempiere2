package org.compiere.process;

import java.awt.Color;
import java.io.File;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.persistence.PrePersist;

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

import org.adempiere.exceptions.AdempiereException;
import org.compiere.apps.ADialog;
import org.compiere.model.MAttachmentEntry;
import org.compiere.model.MBank;
import org.compiere.model.MBankAccount;
import org.compiere.model.MBankAccountAcct;
import org.compiere.model.MElementValue;
import org.compiere.model.MTRMDeposit;
import org.compiere.model.X_C_ValidCombination;
import org.compiere.util.DB;
import org.compiere.util.Msg;

import extend.org.compiere.hrm.ExcelCell;
import extend.org.compiere.utils.Util;

public class TRM_RewardReport extends SvrProcess 
{
	private Timestamp BeginPeriod;
	private Timestamp EndPeriod;
	
	private String path = "";
	
	private int TRM_Deposit_ID;
		
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
		
		TRM_Deposit_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception 
	{
		String patternName = "reward_report";
		
		//get attachment FROM the process
		MAttachmentEntry entry = Util.getAttachment(getProcessInfo(), getCtx(), patternName);
		if(entry == null)
			throw new AdempiereException(Msg.translate(getCtx(), "NotFoundTemplate")+" "+patternName);
		
		if(TRM_Deposit_ID == -1)
			return "Not available deposit";
		
		CreateLines(BeginPeriod, EndPeriod, entry);
		
		return null;
	}
	
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
	    
	    WritableCellFormat totalDateFormat = new WritableCellFormat (customDateFormat);
	    totalDateFormat.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
	    totalDateFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
	    totalDateFormat.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
	    totalDateFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
	    totalDateFormat.setAlignment(Alignment.RIGHT);
	    totalDateFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
	    totalDateFormat.setBackground(Colour.GREY_25_PERCENT);
	    
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
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT Index, DateReward, IncomingBalance, LineNetAmt, Withdrawal, Premium ");
		sql.append("FROM TRM_Reward WHERE TRM_Deposit_ID = ");
		sql.append(TRM_Deposit_ID);
		sql.append(" AND DateReward BETWEEN '");
		sql.append(beginperiod + "' AND '" + endperiod + "'");
		sql.append(" ORDER BY DateReward");
		
		int month = 0;
		
		int col = cellStart.getC();
	    int row = cellStart.getR();
		int index = row;
		
		MTRMDeposit deposit = new MTRMDeposit(getCtx(), TRM_Deposit_ID, get_TrxName());
		
		MBank bank = new MBank(getCtx(), deposit.getC_Bank_ID(), get_TrxName());
		
		sheet.mergeCells(col, row - 4, col+5, row - 4);
		sheet.mergeCells(col, row - 3, col+5, row - 3);
		sheet.mergeCells(col, row - 2, col+5, row - 2);
		
		sheet.addCell(new Label(col,row - 4, "Банк: " + bank.getName(), valuestyle));
		sheet.addCell(new Label(col,row - 3, "Ставка вознаграждения: " + deposit.getInterestRate().round(new MathContext(3)).toString(), valuestyle));
		sheet.addCell(new Label(col,row - 2, "Период: " + 	(beginperiod.getDate() < 10 ? "0" + beginperiod.getDate() : beginperiod.getDate())  + "." + 
															(beginperiod.getMonth() < 10 ? "0" + beginperiod.getMonth() : beginperiod.getMonth()) 
															+ "." + 
															(beginperiod.getYear() + 1900) + " - " + 
															(endperiod.getDate() < 10 ? "0" + endperiod.getDate() : endperiod.getDate())  + "." + 
															(endperiod.getMonth() < 10 ? "0" + endperiod.getMonth() : endperiod.getMonth()) + "." + 
															(endperiod.getYear() + 1900), valuestyle));
		
		label = new Label(col+5, index, "", valuestyle);
		
		BigDecimal premium = new BigDecimal(0);
		
		Timestamp currentDate = null;
		
		double withdrawal = 0.0;
		
		Timestamp from = null;
		Timestamp to = null;
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				currentDate = rs.getTimestamp("DateReward");
				
				if(from == null) from = currentDate;
				
				month = currentDate.getMonth();
				//Индекс
				number = new Number(col, index, rs.getInt("Index"), numcolstyle);
				sheet.addCell(number);
				//Дата
				time = new DateTime(col+1, index, new java.sql.Date(currentDate.getTime()), dateFormat);
        		sheet.addCell(time);
        		//Изъятия/взносы
        		
        		withdrawal = rs.getBigDecimal("Withdrawal") == null ? 0 : rs.getBigDecimal("Withdrawal").doubleValue();
        			
        		number = new Number(col+2, index, withdrawal, numcolstyle);
				sheet.addCell(number);
				//Входящий остаток
        		number = new Number(col+3, index, rs.getBigDecimal("IncomingBalance").doubleValue(), numcolstyle);
				sheet.addCell(number);
				//Фактически начисленное вознаграждение
				number = new Number(col+4, index, rs.getBigDecimal("LineNetAmt").doubleValue(), numcolstyle);
				sheet.addCell(number);
				
				label = new Label(col+5, index, "", valuestyle);
		    	sheet.addCell(label);
				
				premium = premium.add(rs.getBigDecimal("Premium") == null ? new BigDecimal(0) : rs.getBigDecimal("Premium"));
				
				currentDate.setDate(currentDate.getDate() + 1);
				
				if(currentDate.getMonth() != month)
				{
					to = currentDate;
					
					//--------------------------Итого за месяц
					index++;
					label = new Label(col, index, "", totalrowstyle);
			    	sheet.addCell(label);
					//Дата
					time = new DateTime(col+1, index, new java.sql.Date(currentDate.getTime()), totalDateFormat);
	        		sheet.addCell(time);
	        		
	        		label = new Label(col+2, index, "", totalrowstyle);
			    	sheet.addCell(label);
			    	
			    	label = new Label(col+3, index, "", totalrowstyle);
			    	sheet.addCell(label);
			    	
					//Фактически начисленное вознаграждение
					number = new Number(col+4, index, premium.doubleValue(), totalrowstyle);
					sheet.addCell(number);
								    	
					BigDecimal totalSum = getTotalLineSum(from, to);
					
					number = new Number(col+5, index, totalSum.doubleValue(), totalrowstyle);
			    	sheet.addCell(number);		
					
					from = null;
					to = null;
					
					//-------------------------За вычетом налога
					index++;
					label = new Label(col, index, "", totalrowstyle);
			    	sheet.addCell(label);
					//Дата
			    	label = new Label(col+1, index, "За вычетом налога", totalrowstyle);
	        		sheet.addCell(label);
	        		
	        		label = new Label(col+2, index, "", totalrowstyle);
			    	sheet.addCell(label);
			    	
			    	label = new Label(col+3, index, "", totalrowstyle);
			    	sheet.addCell(label);
			    	
					//Фактически начисленное вознаграждение
					number = new Number(col+4, index, premium.multiply(new BigDecimal(0.85d)).doubleValue(), totalrowstyle);
					sheet.addCell(number);
					
					label = new Label(col+5, index, "", totalrowstyle);
			    	sheet.addCell(label);
					
			    	premium = new BigDecimal(0);
					month = currentDate.getMonth();
					
					
					
				}
				
				index++;
			}
			
			copy.write();
		    copy.close();
		    tableBook.close();
		    temporaryFile.delete();
			
		    try{    
		   		   String fileName = fullPath.toString();
		   		   String command = "excel \""+fileName+"\"";
		   		   Runtime.getRuntime().exec("cmd /c start "+command);
		   	   }catch(Exception e){
		   		   e.printStackTrace();
		   	   }
			
		}
		catch(Exception ex){}
	}
	
	private String Accounts(int C_BankAccount_ID)
	{
		String retValue = "";
		
		MBankAccount bankAccount = new MBankAccount(getCtx(), C_BankAccount_ID, get_TrxName());
				
		MBankAccountAcct accountLine = MBankAccountAcct.getOfBankAccount(getCtx(), bankAccount.get_ID(), get_TrxName());
				
		if(accountLine != null)
		{
			X_C_ValidCombination validCombination = new X_C_ValidCombination(getCtx(), accountLine.getB_Asset_Acct(), get_TrxName());
			
			MElementValue elementValue = new MElementValue(getCtx(), validCombination.getAccount_ID(), get_TrxName());
			
			retValue = elementValue.getValue();
		}
		
		return retValue;
	}
	
	private BigDecimal getTotalLineSum(Timestamp dateacctFrom, Timestamp dateacctTo)
	{
		if(dateacctFrom == null || dateacctTo == null) return new BigDecimal(0);
		
		MTRMDeposit deposit = new MTRMDeposit(getCtx(), TRM_Deposit_ID, get_TrxName());
		String account = Accounts(deposit.getC_BankAccount_ID());
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		
		dateacctFrom.setDate(dateacctFrom.getDate() - 1);
		dateacctTo.setDate(dateacctTo.getDate() - 1);
		
		sql.append("SELECT SUM(l.AmtSourceCr) as \"Credit\", SUM(l.AmtSourceDr) as \"Debit\" ");
		sql.append("FROM GL_JournalLine l ");
		sql.append("INNER JOIN C_ValidCombination v on l.C_ValidCombination_ID = v.C_ValidCombination_ID ");
		sql.append("INNER JOIN C_ElementValue e on e.C_ElementValue_ID = v.Account_ID ");
		sql.append("INNER JOIN GL_JournalLine l2 ON l.GL_Journal_ID = l2.GL_Journal_ID ");
		sql.append("INNER JOIN C_ValidCombination v2 ON l2.C_ValidCombination_ID = v2.C_ValidCombination_ID ");
		sql.append("INNER JOIN C_ElementValue e2 ON e2.C_ElementValue_ID = v2.Account_ID ");
		sql.append("INNER JOIN GL_Journal j ON j.GL_Journal_ID = l.GL_Journal_ID ");
		sql.append("WHERE  e.Value LIKE '%6110%' AND e2.Value LIKE '"+ account +"' AND j.DateAcct BETWEEN '" + dateacctFrom + "' AND '" + dateacctTo + "'");
		
		BigDecimal retValue = new BigDecimal(0);
		
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				retValue = rs.getBigDecimal("Credit");	
			}
			
			rs.close(); pstmt.close();
			rs = null; pstmt = null;
		}
		catch(Exception ex){}
		
		if(retValue == null)
			retValue = new BigDecimal(0);
		
		return retValue;
	}
	
}
