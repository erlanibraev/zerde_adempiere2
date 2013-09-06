/**
 * 
 */
package org.adempiere.apps.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import layout.TableLayout;

import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.AWindow;
import org.compiere.apps.form.FormFrame;
import org.compiere.model.MBSCCard;
import org.compiere.model.MBSCCardLine;
import org.compiere.model.MQuery;
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
public class BSCCardPanel extends JPanel implements MouseListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8434591284973860686L;
	private MBSCCard card = null;
	private double[][] tableSize;
	private TableLayout tableLayout = null;
	private static String[] tableHeader = {"КПД","Вес %","План","Факт","Ед.Изм","Область","---"};
	private JLabel[][] label = null; 
	private static int[] cellWidth = {300,80,80,80,80,80,120};
	private static int[] cellHeight = {50,100};
	private JPanel cardPanel = null;
	private JPanel totalPanel = null;
	private CPanel[][] panels = null;
	
	public BSCCardPanel(MBSCCard card) {
		super();
		Border border = BorderFactory.createLineBorder(Color.BLACK) ;
		setBorder(border);
		addMouseListener(this);
		setCard(card);
	}

	public void init() {
		
		removeAll();
		
		totalPanel = new JPanel();
		cardPanel = new JPanel();
		
		initLayout();

		setSize(getAllDimension());
		setPreferredSize(getAllDimension());
		setMaximumSize(getAllDimension());
		setMinimumSize(getAllDimension());
		
		makePane();
		
		validate();
		repaint();
		updateUI();
	}
	/**
	 * 
	 */
	private void makePane() {
		if (getCard() == null) return;
		
		viewTotal();
		viewCard();
		
		add(totalPanel);
		add(cardPanel);
	}

	/**
	 * 
	 */
	private void viewTotal() {
		totalPanel.add(new CLabel((card == null ? "" : "<html>"+card.getName()+"</html>")));
		double value = (card == null ? 0 : card.getValueNumber().doubleValue() / 100);
		
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
		int sizeY = 50 + cellHeight[0] + cellHeight[1] * (card == null ? 1 : card.getLines(false).length);
		Dimension result = new Dimension(sizeX,sizeY);
		return result;
	};
	
	/**
	 * 
	 */
	private void viewCard() {
		initLabel();
		if (label != null) {
			panels = new CPanel[label.length][tableHeader.length];
			for(int i=0; i < label.length; i++) {
				for(int j = 0; j < tableHeader.length; j++) {
					panels[i][j] = new CPanel();
					if (label[i][j] != null) {
						panels[i][j].add(label[i][j]);
					}
					if ( i > 0) {
						MBSCCardLine cardLine = card.getLines(false)[i-1]; 
						double value = cardLine.getValueNumber().doubleValue();
						if (j == 5) {
							Color c;
							if (value < 0.5) c = Color.RED;
							else if ( value < 1) c = Color.YELLOW;  
							else c = Color.GREEN;
							BSCPointPanel pp = new BSCPointPanel(c);
							panels[i][j].add(pp);
						}
						if (j == 6) {
							BSCThermometer t = new BSCThermometer(value);
							panels[i][j].add(t);
						}
					}
					String pos = Integer.toString((j)) +"," 
				               + Integer.toString((i));
					panels[i][j].setBorder(getBorder());
					cardPanel.add(panels[i][j],pos);
				}
			}
		}
	}

	/**
	 * 
	 */
	private void initLabel() {
		if (card != null && card.getLines(true) != null) {
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
		init();
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
			int size = (card.getCardLine() == null ? 0 : card.getCardLine().size()+1);
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

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(isTitle(e)) {
			openCardWindow(0);
		} else if (label != null) {
			for(int i = 1; i < label.length; i++) {
				if (isLabel(i, e) && i > 0) {
					int BSC_CardLine_ID = card.getLines(false)[i-1].getBSC_CardLine_ID();
					openCardWindow(BSC_CardLine_ID);
				} else if (isGraph(i, e) && i > 0) {
					int BSC_Card_ID = card.getBSC_Card_ID();
					int BSC_CardLine_ID = card.getLines(false)[i-1].getBSC_CardLine_ID();
					openBarChartWindow(BSC_Card_ID, BSC_CardLine_ID);
				}
			}
		}
		
	}

	/**
	 * @param i
	 * @return
	 */
	private boolean isLabel(int i,MouseEvent e) {
		int y = panels[i][0].getY();
		int height = panels[i][0].getHeight();
		int x = panels[i][5].getX();
		boolean result = y < e.getY() && e.getY() < y + height && e.getX() < x;
		return result;
	}

	/**
	 * @param i
	 * @return
	 */
	private boolean isGraph(int i,MouseEvent e) {
		int y = panels[i][0].getY();
		int height = panels[i][0].getHeight();
		int x = panels[i][5].getX();
		boolean result = y < e.getY() && e.getY() < y + height && x < e.getX();
		return result;
	}

	/**
	 * 
	 */
	private void openCardWindow(int BSC_CardLine_ID) {
		System.out.print("BSC_CardLine_ID = ");System.out.print(BSC_CardLine_ID);System.out.println("");
		
		AWindow myWindow = new AWindow(getGraphicsConfiguration());
		int AD_Window_ID = getBSC_CardWindow_ID();
		MQuery query = new MQuery(MBSCCard.Table_ID);
		query.addRestriction("BSC_Card_ID", MQuery.EQUAL, card.getBSC_Card_ID());
		if (BSC_CardLine_ID > 0) {
			query.setZoomTableName(MBSCCardLine.Table_Name);
			query.setZoomColumnName(MBSCCardLine.COLUMNNAME_BSC_CardLine_ID);
			query.setZoomValue(BSC_CardLine_ID);
		}
		if (myWindow.initWindow(AD_Window_ID, query)) {
			AEnv.addToWindowManager(myWindow);
			myWindow.pack();
			myWindow.setVisible(true);
			myWindow.toFront();
		};
		
	}
	
	private int getBSC_CardWindow_ID() {
		int result = 0;
		String sql = "SELECT * FROM AD_Window WHERE Name like 'BSC_Card'";
		result = DB.getSQLValue(null, sql);
		return result;
	}

	private void openBarChartWindow(int BSC_Card_ID, int BSC_CardLine_ID) {
		if(BSC_Card_ID > 0) {
			FormFrame myWindow = new FormFrame(Env.getWindow(0).getGraphicsConfiguration());
			if (myWindow.openForm(getBSCViewBarChart())) {
				BSCViewBarChart view = (BSCViewBarChart) myWindow.getFormPanel();
				view.setBSC_Card_ID(BSC_Card_ID);
				if (BSC_CardLine_ID > 0) {
					MBSCCardLine cl = new MBSCCardLine(Env.getCtx(),BSC_CardLine_ID,null);
					if (cl != null) {
						view.setBSC_Parameter_ID(cl.getBSC_Parameter_Out_ID());
					}
				}
				AEnv.addToWindowManager(myWindow);
				AEnv.showCenterScreen(myWindow);
				myWindow.setVisible(true);
				myWindow.setFocusableWindowState(true);
				myWindow.toFront();
			}			
		}
	}

	public static int getBSCViewBarChart() {
		int result = 0;
		String sql = "SELECT AD_Form_ID FROM AD_Form WHERE Name like 'BSCViewBarChart'";
		result = DB.getSQLValue(null, sql);
		return result;
	}	
	
	/**
	 * @param e
	 * @return
	 */
	private boolean isTitle(MouseEvent e) {
		int y = 0;
		int height = totalPanel.getHeight();
/*		
		System.out.print("Y = ");System.out.print(y);System.out.println("");
		System.out.print("Height = ");System.out.print(height);System.out.println("");
		System.out.print("mouseY = ");System.out.print(e.getY());System.out.println("");
*/		
		boolean result = (y < e.getY() && e.getY() < y + height);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
