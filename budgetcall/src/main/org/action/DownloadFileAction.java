package main.org.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import main.org.excel.ExcelPrint;
import main.org.model.Budget;

public class DownloadFileAction extends Budget{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2768921050067508777L;
	private InputStream fileInputStream;
	private String filename;
	private static int countDownload = 1;

	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public static int getCountDownload() {
		return countDownload;
	}
	public static void setCountDownload(int countDownload) {
		DownloadFileAction.countDownload = countDownload;
	}


	public String execute() throws Exception {
		
		if(processID == 0)
			return NONE;
		
		filename = getPage()+"_"+countDownload;

		ExcelPrint excelFile = new ExcelPrint(this);
		if(excelFile.getFilePath().equals(NONE))
			return NONE;
		File inpFile = new File(excelFile.getFilePath()); 
	
		if(!inpFile.exists())
			return NONE;
		
	    fileInputStream = new FileInputStream(inpFile);
	    
	    countDownload++;
	    
    	return SUCCESS;
	}
	
}