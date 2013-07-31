package extend.org.compiere.callout;

import java.util.Hashtable;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.X_AD_Column;
import org.compiere.model.X_AD_Reference;

public class Callout_PFRCalcultaion extends CalloutEngine 
{
	private static Hashtable<String, String> types = new Hashtable<String, String>();
	
	static
	{
		types.put("Amount", "Sum");
		types.put("Integer", "Sum");
		types.put("Quantity", "Sum");
		types.put("Costs+Prices", "Sum");
		types.put("Yes-No", "Yes-No");
		types.put("Date", "Date");
		types.put("Date+Time", "Date");
		types.put("String", "String");
		types.put("Text", "String");
	};
	public void setDstColumnType(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) 
	{	
		if(value != null)
		{
			X_AD_Column column = new X_AD_Column(ctx, (Integer)value, null);
	
			if(column != null)
			{
				X_AD_Reference reference = new X_AD_Reference(ctx, column.getAD_Reference_ID(), null);
				
				if(reference != null)
					mTab.setValue("dstColumnType", types.get(reference.getName()));
			}
		}
	}

	public void setWhereColumnType(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value) 
	{	
		if(value != null)
		{
			X_AD_Column column = new X_AD_Column(ctx, (Integer)value, null);
	
			if(column != null)
			{
				X_AD_Reference reference = new X_AD_Reference(ctx, column.getAD_Reference_ID(), null);
				
				if(reference != null)
					mTab.setValue("dstColumnType", types.get(reference.getName()));
			}
		}
	}
}
