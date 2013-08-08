/**
 * 
 */
package org.adempiere.apps.graph;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import layout.TableLayout;

import org.compiere.model.MBSCCard;
import org.compiere.model.MBSCCardLine;
import org.compiere.model.MRefList;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.ValueNamePair;

/**
 * @author Y.Ibrayev
 *
 */
public class BSCCardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8434591284973860686L;
	private MBSCCard card = null;
	private double[][] tableSize;
	private TableLayout tableLayout = null;
	private static String[] tableHeader = {"КПД","Вес %","План","Факт","Ед.Изм","Статус","Область","---"};
	private JLabel[][] label = null; 
	private static int[] cellWidth = {200,100,100,100,100,100,100,200};
	private static int[] cellHeight = {50,150};
	private JPanel cardPanel = new JPanel();
	private JPanel totalPanel = new JPanel();
	
	public BSCCardPanel(MBSCCard card) {
		super();
		Border border = BorderFactory.createLineBorder(Color.BLACK) ;
		setBorder(border);
		
		setCard(card);
		initLayout();

		setSize(getAllDimension());
		setPreferredSize(getAllDimension());
		setMaximumSize(getAllDimension());
		setMinimumSize(getAllDimension());
		
		makePane();
	}
	
	/**
	 * 
	 */
	private void makePane() {
		
		viewTotal();
		viewCard();
		
		add(totalPanel);
		add(cardPanel);
	}

	/**
	 * 
	 */
	private void viewTotal() {
		totalPanel.add(new CLabel(card.getName()));
		double value = card.getValueNumber().doubleValue() / 100;
		
		Color c;
		if (value < 0.5) c = Color.RED;
		else if ( value < 1) c = Color.YELLOW;  
		else c = Color.GREEN;
		
		totalPanel.add(new BSCPointPanel(c));
		totalPanel.add(new BSCThermometer(value));
	}

	public Dimension getAllDimension() {
		int sizeX = 0;
		for(int i:cellWidth) {
			sizeX += i;
		}
		int sizeY = 50 + cellHeight[0] + cellHeight[1] * (card.getLines(false).length);
		Dimension result = new Dimension(sizeX,sizeY);
		return result;
	};
	
	/**
	 * 
	 */
	private void viewCard() {
		initLabel();
		if (label != null) { 
			for(int i=0; i < label.length; i++) {
				for(int j = 0; j < tableHeader.length; j++) {
					CPanel panel = new CPanel();
					if (label[i][j] != null) {
						panel.add(label[i][j]);
					}
					if ( i > 0) {
						MBSCCardLine cardLine = card.getLines(false)[i-1]; 
						double value = cardLine.getValueNumber().doubleValue();
						if (j == 6) {
							Color c;
							if (value < 0.5) c = Color.RED;
							else if ( value < 1) c = Color.YELLOW;  
							else c = Color.GREEN;
							BSCPointPanel pp = new BSCPointPanel(c);
							panel.add(pp);
						}
						if (j == 7) {
							BSCThermometer t = new BSCThermometer(value);
							panel.add(t);
						}
					}
					String pos = Integer.toString((j)) +"," 
				               + Integer.toString((i));
					panel.setBorder(getBorder());
					cardPanel.add(panel,pos);
				}
			}
		}
	}

	/**
	 * 
	 */
	private void initLabel() {
		if (card.getLines(false) != null) {
			label = new JLabel[card.getLines(false).length + 1][tableHeader.length];
			for(int i = 0; i < label[0].length; i++) {
				label[0][i] = new JLabel(tableHeader[i]);
			}
			String tmpS = null;
			for(int i = 0; i < card.getLines(false).length; i++) {
				tmpS = "<html>"+(card.getLines(false)[i].getName() == null ? "" : card.getLines(false)[i].getName())+"</html>";
				label[i+1][0] = new JLabel(tmpS,JLabel.LEFT);
				label[i+1][0].setSize(new Dimension(cellWidth[0], cellHeight[1]));
				label[i+1][0].setPreferredSize(new Dimension(cellWidth[0], cellHeight[1]));
				label[i+1][0].setMinimumSize(new Dimension(cellWidth[0], cellHeight[1]));
				label[i+1][0].setMaximumSize(new Dimension(cellWidth[0], cellHeight[1]));
				tmpS =  String.format("%.2f%n", card.getLines(false)[i].getWeight());
				label[i+1][1] = new JLabel(tmpS);
				tmpS = String.format("%.2f%n", card.getLines(false)[i].getValueMax());
				label[i+1][2] = new JLabel(tmpS);
				tmpS = String.format("%.2f%n", card.getLines(false)[i].getValueNumber());
				tmpS = card.getLines(false)[i].getValue();
				label[i+1][3] = new JLabel(tmpS);
				tmpS = getRefListValue(card.getLines(false)[i].getUnit());
				label[i+1][4] = new JLabel(tmpS);
			}
		}
	}

	/**
	 * @param unit
	 * @return
	 */
	private String getRefListValue(String unit) {
		String result = null;
		int AD_Reference_ID = DB.getSQLValue(null, "SELECT AD_Reference_ID FROM AD_Reference  WHERE Name = 'UnitCard'");
		ValueNamePair[] vnpList = MRefList.getList(Env.getCtx(), AD_Reference_ID, false);
		for(ValueNamePair vnp : vnpList) {
			if (vnp.getValue().equals(unit)) {
				result = vnp.getName();  
			}
		}
		return result;
	}

	public MBSCCard getCard() {
		return card;
	}
	public void setCard(MBSCCard card) {
		this.card = card;
	}
	
	/**
	 * 
	 */
	private void initLayout() {
		initTableSize();
		tableLayout = new TableLayout(tableSize); 
		cardPanel.setLayout(tableLayout);
	}

	/**
	 * 
	 */
	private void initTableSize() {
		if (card != null) {
			int size = (card.getCardLine() == null ? 0 : card.getCardLine().size());
			if (size+1 < tableHeader.length) {
				size = tableHeader.length + 1;
			}
			tableSize = new double[2][size];
			for(int i=0; i < size; i++) {
				tableSize[0][i] = ( i < cellWidth.length ? cellWidth[i] : 0);
				tableSize[1][i] = ( i == 0 ? cellHeight[0] : cellHeight[1]);
			}
		}
	}
}
