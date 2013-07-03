/**
 * 
 */
package main.org.excel;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import main.org.action.BudgetCall;
import main.org.action.ChargeAmount;
import main.org.action.DownloadFileAction;
import main.org.action.PeriodAmount;
import main.org.model.Amount;
import main.org.model.Budget;
import main.org.model.ChargeCode;
import main.org.model.Period;
import main.org.model.Utils;
import org.compiere.apps.ADialog;
import org.compiere.model.*;
import org.compiere.process.ProcessInfo;
import org.compiere.util.*;

import extend.org.compiere.hrm.ExcelCell;
import extend.org.compiere.utils.Util;

/**
 * @author V.Sokolov
 *
 */
public class ExcelPrint extends Budget {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2224730202333059702L;
	/** Current context		*/
	private Properties m_ctx = Env.getCtx();
	/** */
	ProcessInfo pi;
	MPeriod[] period;
	/** */
	private String path = Util.localFilePath + System.getProperty("file.separator");
	private String page;
	// Excel book
	private Workbook tableBook = null;
	private WritableWorkbook copy = null;
	private WritableSheet sheet = null;
	// Types of cells for
	private Label label;
	private Number number;
	//
	private File temporaryFile = null;
	private StringBuffer fullPath = null;
	private WritableFont font12 = new WritableFont (WritableFont.TIMES, 10, WritableFont.BOLD);
	private WritableFont font11 = new WritableFont (WritableFont.TIMES, 11, WritableFont.BOLD);
	private WritableCellFormat borderStyle = new WritableCellFormat();
	private WritableCellFormat borderStyleLeft = new WritableCellFormat(font12);
	private WritableCellFormat borderStyleRight = new WritableCellFormat(font12);
	private WritableCellFormat borderStyleCenter = new WritableCellFormat(font12);
	private WritableCellFormat borderCenter11 = new WritableCellFormat(font11);
	
	public ExcelPrint(DownloadFileAction downloadAction) throws Exception{
		
		this.callID = downloadAction.getCallID();
		this.chargeID = downloadAction.getChargeID();
		this.processID = downloadAction.getProcessID();
		this.periodID = downloadAction.getPeriodID();
		this.tableID = downloadAction.getTableID();
		this.recordID = downloadAction.getRecordID();
		this.page = downloadAction.getPage();
		
		//
		pi = new ProcessInfo ("Process for storing templates budget", processID);
		period = Utils.getPeriod(callID);
		
		// Standart style cell
		borderStyle.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		borderStyle.setAlignment(Alignment.CENTRE);
		borderStyle.setVerticalAlignment(VerticalAlignment.CENTRE);
		borderStyle.setWrap(true);
		// text align LEFT
		borderStyleLeft.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		borderStyleLeft.setAlignment(Alignment.LEFT);
		borderStyleLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
		borderStyleLeft.setWrap(true);
		// text align RIGHT
		borderStyleRight.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		borderStyleRight.setAlignment(Alignment.RIGHT);
		borderStyleRight.setVerticalAlignment(VerticalAlignment.CENTRE);
		borderStyleRight.setWrap(true);
		// text align CENTER and font Bold
		borderStyleCenter.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		borderStyleCenter.setAlignment(Alignment.CENTRE);
		borderStyleCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
		borderStyleCenter.setWrap(true);
		// text align CENTER and font11 Bold
		borderCenter11.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		borderCenter11.setAlignment(Alignment.LEFT);
		borderCenter11.setVerticalAlignment(VerticalAlignment.CENTRE);
		borderCenter11.setWrap(true);
		
	}
	
	public synchronized String getFilePath() throws Exception{
		
		//
		MAttachmentEntry entry = Util.getAttachment(pi, m_ctx, page);
		if(entry == null) return NONE;
		
		// We define a path to generate
		String fileExtension = entry.getName().substring(entry.getName().lastIndexOf("."),entry.getName().length());
		fullPath = new StringBuffer(path); 
		fullPath.append("BudgetCallTemplate").append(fileExtension);
				
		// 
		File templateCopy = new File(fullPath.toString());
		temporaryFile = entry.getFile(Util.localFilePath + page);

		 try {
			 tableBook = Workbook.getWorkbook(temporaryFile);
			 copy = Workbook.createWorkbook(templateCopy, tableBook);
			 sheet = copy.getSheet(0);
		} catch (Exception e) {
		  	ADialog.error(999, null, "Error tableBook. " + e.getMessage());
		   	return "Error tableBook.";
		}
		
		if(page.equals(Utils.TEMPLATE_FIRST))
			return printFirstPage(entry);
		else if(page.equals(Utils.TEMPLATE_SECOND))
			return printSecondPage(entry);
		else if(page.equals(Utils.TEMPLATE_THREE))
			return printThreePage(entry);
		
		return Util.UNKNOWN;
	}
	
	private String printFirstPage(MAttachmentEntry entry) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BigDecimal[] sumMonth = new BigDecimal[period.length];
		for(int b = 0; b < period.length; b++){ // array initialization
			sumMonth[b] = new BigDecimal(0);
		}
		
		
		 
		ExcelCell cellProper =  Util.getCellStart(tableBook,">>");
		printProperty(sheet, cellProper);
		ExcelCell cellData =  Util.getCellStart(tableBook,">>>");
		
		BudgetCall bc = new BudgetCall();
		ChargeCode[] chargeCode = bc.getValues(callID);
		
		int col = cellData.getC(); // offset by the number of first columns
		int row = cellData.getR();
		int k = 0;
		int rowC = row;
		for(ChargeCode ch: chargeCode){
			
			if(k > 0)
				sheet.insertRow(row);
			
			// charge code
			label = new Label(col, row, ch.getCode(), borderStyleLeft); 
			sheet.addCell(label);
			sheet.setRowView(row, 500);
			// charge name
			label = new Label(col+1, row, ch.getName(), borderStyleLeft); 
			sheet.addCell(label);
			
			BigDecimal total = new BigDecimal(0);
			int i = 0;
			int c = col+2;
			for(MPeriod p: period){
				
				try
				{
					pstmt = DB.prepareStatement (bc.getSql(), null);
					pstmt.setInt(1, callID);
					pstmt.setInt(2, ch.getChargeID());
					pstmt.setInt(3, p.getC_Period_ID());
					rs = pstmt.executeQuery();
					while (rs.next ())
					{
						if(rs.getDouble(1) != 0){
							sumMonth[i] = sumMonth[i].add(new BigDecimal(rs.getDouble(1))); 
							total = total.add(new BigDecimal(rs.getDouble(1)));
							
							// charge amount per month
							number = new Number(c, row, rs.getDouble(1), borderStyle); 
						}else{
							number = new Number(c, row, 0, borderStyle);
						}
						sheet.addCell(number);
					}
				}
				catch (SQLException e)
				{
					CLogger.get().log(Level.INFO, bc.getSql(), e);
				} finally {
					DB.close(rs, pstmt);
					rs = null; pstmt = null;
				}
				i++;
				c++;
			}		
			
			number = new Number((col+2+period.length), row, total.setScale(2, RoundingMode.HALF_UP).doubleValue(), borderStyleRight);
			sheet.addCell(number);
			
			row++;
			k++;
			rowC = row;
		}
		
		int colC = col+2;
		for(int t = 0; t < period.length; t++){
			number = new Number(colC, rowC, sumMonth[t].setScale(2, RoundingMode.HALF_UP).doubleValue(), borderStyleCenter);
			sheet.addCell(number);
			colC++;
		}
		
		copy.write();
		copy.close();
		tableBook.close();
		temporaryFile.delete();
		
		return fullPath.toString();
		
	}
	
	private String printSecondPage(MAttachmentEntry entry) throws Exception{
		
		ExcelCell cellProper =  Util.getCellStart(tableBook,">>");
		printProperty(sheet, cellProper);
		ExcelCell cellData =  Util.getCellStart(tableBook,">>>");
		
		//
		ChargeAmount charge = new ChargeAmount();
		Amount[] amt = charge.getValues(callID, chargeID, periodID);

		int k = 0;
		int n = 1;
		int col = cellData.getC(); // offset by the number of first columns
		int row = cellData.getR();
		int rowC = row;
		for(Amount a: amt) {
			
			if(k > 0)
				sheet.insertRow(row);
			
			// 
			number = new Number(col, row, n, borderStyleCenter);
			sheet.addCell(number);
			sheet.setRowView(row, 500);
			// charge code
			label = new Label(col+1, row, a.getName(), borderStyleLeft); 
			sheet.addCell(label);
			// Quantity
			number = new Number(col+2, row, 0, borderStyle);
			sheet.addCell(number);
			// Amount unit
			number = new Number(col+3, row, 0, borderStyle);
			sheet.addCell(number);
			// Amount
			number = new Number(col+4, row, Double.valueOf(a.getAmount()), borderStyle);
			sheet.addCell(number);
			
			row++;
			rowC = row;
			k++;
			n++;
		}
		
		// TOTAL
		// Quantity
		number = new Number(col+2, rowC, 0, borderStyleCenter);
		sheet.addCell(number);
		// Amount
		number = new Number(col+4, rowC, 0, borderStyleCenter);
		sheet.addCell(number);
		
		
		copy.write();
		copy.close();
		tableBook.close();
		temporaryFile.delete();
		
		return fullPath.toString();
	}
	
	private String printThreePage(MAttachmentEntry entry) throws Exception{
		
		ExcelCell cellProper =  Util.getCellStart(tableBook,">>");
		printProperty(sheet, cellProper);
		ExcelCell cellData =  Util.getCellStart(tableBook,">>>");
		
		PeriodAmount amtPeriod = new PeriodAmount();
		Period[] period = amtPeriod.getValues(callID, tableID, recordID);
		
		int k = 0;
		int col = cellData.getC(); // offset by the number of first columns
		int row = cellData.getR();
		
		WritableCell readCell = sheet.getWritableCell(cellProper.getC()-1, cellProper.getR());
		CellFormat readFormat = readCell.getCellFormat();
		// Type name
		label = new Label(cellProper.getC()-1, cellProper.getR()+4, "Вид", readFormat); 
		sheet.addCell(label);
		sheet.mergeCells(cellProper.getC(), cellProper.getR()+4 , cellProper.getC()+3, cellProper.getR()+4);
		label = new Label(cellProper.getC(), cellProper.getR()+4, "      "+period[0].getDescription(), borderCenter11); 
		sheet.addCell(label);
		
		int rowC = row;
		for(Period p: period){
			
			if(k > 0)
				sheet.insertRow(row);
			
			// name
			sheet.setRowView(row, 500);
			label = new Label(col, row, p.getName(), borderStyleCenter); 
			sheet.addCell(label);
			//
			label = new Label(col+1, row, p.getUom(), borderStyle); 
			sheet.addCell(label);
			//
			label = new Label(col+2, row, p.getMonth().substring(0, p.getMonth().indexOf("-")), borderStyleCenter); 
			sheet.addCell(label);
			//
			number = new Number(col+3, row, 0, borderStyle);
			sheet.addCell(number);
			//
			number = new Number(col+4, row, 0, borderStyle);
			sheet.addCell(number);
			//
			number = new Number(col+5, row, Double.valueOf(p.getAmount()), borderStyleCenter);
			sheet.addCell(number);
			//
			label = new Label(col+6, row, p.getPayment(), borderStyle); 
			sheet.addCell(label);
			
			row++;
			rowC = row;
			k++;
		}
		
		//
		number = new Number(col+3, rowC, amtPeriod.getsQuantity(), borderStyleCenter);
		sheet.addCell(number);
		//
		number = new Number(col+5, rowC, Double.valueOf(amtPeriod.getsAmount()), borderStyleCenter);
		sheet.addCell(number);
		
		
		copy.write();
		copy.close();
		tableBook.close();
		temporaryFile.delete();
		
		return fullPath.toString();
	}
	
	private void printProperty(WritableSheet sheet, ExcelCell cellStart) throws Exception{
		
		MBPMBudgetCall bCall = new MBPMBudgetCall(m_ctx, callID, null);
		MBPMABP abp = new MBPMABP(Env.getCtx(), bCall.getBPM_ABP_ID(), null);
		MYear ye = new MYear(Env.getCtx(), bCall.getC_Year_ID(), null);
		MProject project = new MProject(Env.getCtx(), bCall.getC_Project_ID(), null); 
		MCharge cha = new MCharge(Env.getCtx(), chargeID, null);
		MPeriod year = new MPeriod(Env.getCtx(), periodID, null);
		
		int col = cellStart.getC();
		int row = cellStart.getR();
		
		WritableCell readCell = sheet.getWritableCell(1, 3);
		CellFormat readFormat = readCell.getCellFormat();

		// Number Budgrt Call
		label = new Label(1, 3, sheet.getCell(1, 3).getContents()+bCall.getValue(), readFormat);
		sheet.addCell(label);
		
		readCell = sheet.getWritableCell(col, row);
		readFormat = readCell.getCellFormat();
		
		// YEAR
		label = new Label(col, row, ye.getFiscalYear(), readFormat); 
		sheet.addCell(label);
		// PROJECT
		label = new Label(col, row+1, project.getName(), readFormat); 
		sheet.addCell(label);
		// ABP
		label = new Label(col, row+2, abp.getHR_Department().getName(), readFormat); 
		sheet.addCell(label);
		
		if(page.equals(Utils.TEMPLATE_SECOND) || page.equals(Utils.TEMPLATE_THREE)){
			
			readCell = sheet.getWritableCell(col-1, row);
			CellFormat readFormat1 = readCell.getCellFormat();
			
			// Charge name
			label = new Label(col-1, row+3, "Статья", readFormat1); 
			sheet.addCell(label);
			sheet.mergeCells(col, row+3 , col+3, row+3);
			label = new Label(col, row+3, "      "+cha.getFI_Code()+" "+cha.getName(), borderCenter11); 
			sheet.addCell(label);
			
			if(periodID != 0 && page.equals(Utils.TEMPLATE_SECOND)){
				// Month name
				label = new Label(col-1, row+4, "Месяц", readFormat1); 
				sheet.addCell(label);
				sheet.mergeCells(col, row+4 , col+3, row+4);
				label = new Label(col, row+4, "      "+year.getName().substring(0, year.getName().indexOf("-")), borderCenter11); 
				sheet.addCell(label);
			}
			
		}
		
	}

}
