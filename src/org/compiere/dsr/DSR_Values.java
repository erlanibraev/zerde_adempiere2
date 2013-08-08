/**
 * 
 */
package org.compiere.dsr;

/**
 * @author V.Sokolov
 *
 */
public class DSR_Values {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int BPM_Form_ID = 1000010;
		
		DSR_DataCollection collection = new DSR_DataCollection(BPM_Form_ID);
		
		int size = collection.size();
		
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < size; i++)
		{
			builder.append(collection.getRow(i));
			builder.append("\n");
		}
		
		System.out.println(builder);

	}

}
