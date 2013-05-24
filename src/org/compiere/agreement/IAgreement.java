package org.compiere.agreement;

public interface IAgreement {

	public int getStage();
	
	public boolean setStage(int AGR_Stage_ID);
	
	public boolean setStatusAgreement(String status);
	
	public boolean save();
}
