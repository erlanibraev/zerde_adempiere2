/**
 * 
 */
package org.adempiere.apps.graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

import layout.TableLayout;

import org.compiere.apps.ConfirmPanel;
import org.compiere.model.MBSCKeySuccessFactor;
import org.compiere.model.MBSCPerspective;
import org.compiere.model.Query;
import org.compiere.swing.CLabel;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 *
 */
public class BSCPerspectivePanel extends JPanel implements MouseListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6897230592612134300L;
	private static CLogger log = CLogger.getCLogger (BSCPerspectivePanel.class);
	private static int SizeWidth = 1200;
	private static int SizeHeight = 200;
	
	private static int perspectiveWidth = 50;
	private static int ksfWidth = 300;
	private static int height = 200;
	private static int borderSize = 10;
	public static Dimension paneldimension = new Dimension(SizeWidth, SizeHeight);
	
	private HashMap<MBSCPerspective,List<MBSCKeySuccessFactor>> strategicMap = new HashMap<MBSCPerspective,List<MBSCKeySuccessFactor>>();
	private HashMap<MBSCKeySuccessFactor,MBSCKeySuccessFactor> links = new HashMap<MBSCKeySuccessFactor,MBSCKeySuccessFactor>(); 
	private HashMap<MBSCPerspective,CLabel> label = new HashMap<MBSCPerspective,CLabel>();
	private HashMap<Integer, Rectangle> coords = new HashMap<Integer, Rectangle>();
	
	private ValueComparator bvc =  new ValueComparator(strategicMap);
	private TreeMap<MBSCPerspective,List<MBSCKeySuccessFactor>> sortedStrategicMap = new TreeMap<MBSCPerspective,List<MBSCKeySuccessFactor>>(bvc); 
	
	private double[][] tableSize;
	private TableLayout tableLayout = null;
	
	public BSCPerspectivePanel() throws Exception {
		super();
		
		initStrategicMap();
		initLinks();
		if (strategicMap.size() == 0) {
			throw new Exception(this.getName() +": Strategic Map not found");
		}
		initLayout();
		setSize(getAllDimension());
		setPreferredSize(getAllDimension());
		setMaximumSize(getAllDimension());
		setMinimumSize(getAllDimension());
		
		addComponentsToPane();
	}
	
	/**
	 * 
	 */
	private void initCoords(Graphics g) {
		if (coords.size() > 0) {
			coords.clear();
		}
		int x = 0;
		int y = 0;
		for(MBSCPerspective key:sortedStrategicMap.keySet()) {
			List<MBSCKeySuccessFactor> list = strategicMap.get(key);
			CLabel lbl = label.get(key);
			y = borderSize + lbl.getY();
			x = borderSize + lbl.getX() + lbl.getWidth();
			for(MBSCKeySuccessFactor ksf: list) {
				x += borderSize;
				
				int width = 0;
				int height =  0;
				
				FontMetrics metric = g.getFontMetrics();
				width = metric.stringWidth(ksf.getName()) + borderSize * 2;
				height = metric.getHeight() + borderSize * 2;
				coords.put(ksf.getBSC_KeySuccessFactor_ID(), new Rectangle(x,y,width,height));
				
				x += width;
				y += borderSize + lbl.getHeight() / list.size();
			}
		}
	}

	/**
	 * 
	 */
	private void initLinks() {
		if (links.size() > 0) {
			links.clear();
		}
		if (strategicMap.size() > 0) {
			for(MBSCPerspective p: sortedStrategicMap.keySet()) {
				List<MBSCKeySuccessFactor> listksf = strategicMap.get(p);
				for(MBSCKeySuccessFactor ksf:listksf) {
					List<MBSCKeySuccessFactor> linkksf = getKeySuccessFactorLink(ksf);
					if (linkksf != null && linkksf.size() > 0) {
						for(MBSCKeySuccessFactor lksf:linkksf) {
							links.put(ksf, lksf);
						}
					}
				}
			}
		}
	}

	/**
	 * @param ksf
	 * @return
	 */
	private List<MBSCKeySuccessFactor> getKeySuccessFactorLink(MBSCKeySuccessFactor ksf) {
		if (ksf == null) {
			return null;
		}
		String whereClause = "BSC_KeySuccessFactor_ID IN (SELECT BSC_KeySuccessFactor_Link_ID FROM BSC_KeySuccessFactorLink WHERE BSC_KeySuccessFactor_ID  = ?)";
		List<MBSCKeySuccessFactor> result = new Query(Env.getCtx(),MBSCKeySuccessFactor.Table_Name,whereClause,null)
		                                       .setParameters(ksf.getBSC_KeySuccessFactor_ID())
		                                       .list();
		return result;
	}

	/**
	 * 
	 */
	private void initStrategicMap() {
		if (strategicMap.size() > 0) {
			strategicMap.clear();
		}
		List<MBSCPerspective> perspectives = getPerspectives();
		if (perspectives != null) {
			for(MBSCPerspective perspective: perspectives) {
				List<MBSCKeySuccessFactor> keySuccessFactors = getKeySuccessFactors(perspective);
				strategicMap.put(perspective, keySuccessFactors);
			}
		}
		sortedStrategicMap.putAll(strategicMap);
	}
	
	
	/**
	 * @param perspective
	 * @return
	 */
	private List<MBSCKeySuccessFactor> getKeySuccessFactors(MBSCPerspective perspective) {
		String WhereClause = "BSC_Perspective_ID = ?";
		List<MBSCKeySuccessFactor> result = new Query(Env.getCtx(),MBSCKeySuccessFactor.Table_Name,WhereClause,null)
		                                      .setParameters(perspective.getBSC_Perspective_ID())
		                                      .list();
		return result;
	}

	/**
	 * @return
	 */
	private List<MBSCPerspective> getPerspectives() {
		String WhereClause = "";
		List<MBSCPerspective> result = new Query(Env.getCtx(), MBSCPerspective.Table_Name,WhereClause,null).list()  ;
		return result;
	}

	/**
	 * 
	 */
	private void initLayout() {
		// TODO Auto-generated method stub
		initTableSize();
		tableLayout = new TableLayout(tableSize); 
		setLayout(tableLayout);
	}
	/**
	 * 
	 */
	private void initTableSize() {
		if (strategicMap.size() <= 0) {
			return;
		}
		int size = 0;
		ArrayList<Integer> col = new ArrayList<Integer>();
		ArrayList<Integer> row = new ArrayList<Integer>();
		row.add(borderSize);
		col.add(borderSize);
		int max = 0;
		for(MBSCPerspective p : sortedStrategicMap.keySet()) {
			List<MBSCKeySuccessFactor> l = strategicMap.get(p);
			row.add((height + borderSize * 2));
			if (l.size() > max) max = l.size(); 
		}
		col.add(perspectiveWidth);
		for(int i = 0; i < max; i++) {
			col.add(ksfWidth + borderSize * 2);
		}
		row.add(borderSize);
		col.add(borderSize);
		
		size = (col.size() > row.size() ? col.size() : row.size());
		tableSize = new double[2][size];
		for(int i = 0; i < size; i++) {
			tableSize[0][i] = (i < col.size() ? col.get(i).doubleValue() : 0);
			tableSize[1][i] = (i < row.size() ? row.get(i).doubleValue() : 0);
		}
	}
	
	public Dimension getAllDimension() {
		int sizeX = borderSize * 2 + perspectiveWidth;
		int sizeY = borderSize * 2;
		int max = 0;
		for(MBSCPerspective key:sortedStrategicMap.keySet()) {
			sizeY += height + borderSize * 2;
			List<MBSCKeySuccessFactor> l = strategicMap.get(key);
			if (l.size() > max) max = l.size(); 
		}
		sizeX += max * ksfWidth;
		Dimension result = new Dimension(sizeX,sizeY);
		return result;
	};
	
	public Dimension getPerspectiveDimension() {
		int sizeX = perspectiveWidth;
		int sizeY = height;
		Dimension result = new Dimension(sizeX,sizeY);
		return result;
	}

	/**
	 * @param contentPane
	 */
	public void addComponentsToPane() {

		Border border = BorderFactory.createLineBorder(Color.BLACK) ;
		int i = 0;
		for(MBSCPerspective key:sortedStrategicMap.keySet()) {
			CLabel lbl = new CLabel(key.getName()); 
			lbl.setAlignmentX(CENTER_ALIGNMENT);
			lbl.setAlignmentY(CENTER_ALIGNMENT);
			lbl.setUI(new VerticalLabelUI(false));
			lbl.setBorder(border);
			label.put(key,lbl);
           	String pos="1," + Integer.toString((i+1))+",1," + Integer.toString((i+1));
           	add(lbl,pos);
           	i++;
		}
	}

	/**
	 * @param gbc
	 * @param keySF
	 */
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

    public ActionListener[] getActionListeners() 
    {
        return (ActionListener[])(listenerList.getListeners(ActionListener.class));
    }	//	getActionListeners

	public void addActionListener(ActionListener l) 
    {
    	if (l != null)
    		listenerList.add(ActionListener.class, l);
    }	//	addActionListener

    public void removeActionListener(ActionListener l) 
    {
    	if (l != null)
    		listenerList.remove(ActionListener.class, l);
    }	//	removeActionListener

    @Override
	protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
		initCoords(g);
    	paintLinks(g);
    	paintKeySuccessFactors(g);
    }

	/**
	 * @param g
	 */
	private void paintLinks(Graphics g) {
		
		for(MBSCKeySuccessFactor fromKSF: links.keySet()) {
			MBSCKeySuccessFactor toKSF = links.get(fromKSF);
			
			Rectangle from = coords.get( fromKSF.getBSC_KeySuccessFactor_ID()  );
			Rectangle to = coords.get(toKSF.getBSC_KeySuccessFactor_ID());
			if (from != null && to != null) {
				g.setColor(Color.RED);
				g.drawLine(from.x + from.width /2, from.y + from.height / 2, to.x + to.width / 2, to.y + to.height /2);
			}
		}
	}

	/**
	 * @param g
	 */
	private void paintKeySuccessFactors(Graphics g) {
		for(MBSCPerspective key:sortedStrategicMap.keySet()) {
			List<MBSCKeySuccessFactor> list = strategicMap.get(key);
			for(MBSCKeySuccessFactor ksf: list) {
				drawKSF(g,ksf);
			}
		}
	}

	/**
	 * @param g
	 * @param x
	 * @param y
	 * @param ksf
	 * @return
	 */
	private void drawKSF(Graphics g, MBSCKeySuccessFactor ksf) {
		Rectangle r = coords.get(ksf.getBSC_KeySuccessFactor_ID());
		int x = r.x;
		int y = r.y;
		int width = r.width;
		int height =  r.height;
		
		g.setColor(Color.CYAN);
		g.fillOval(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawString(ksf.getName(), x+10, y+25);
		g.drawOval(x, y, width, height);
		
		return;
	}
	
	class ValueComparator implements Comparator<MBSCPerspective> {

	    Map<MBSCPerspective, List<MBSCKeySuccessFactor>> base;
	    public ValueComparator(Map<MBSCPerspective, List<MBSCKeySuccessFactor>> base) {
	        this.base = base;
	    }

	    // Note: this comparator imposes orderings that are inconsistent with equals.    
	    public int compare(MBSCPerspective a, MBSCPerspective b) {
	    	if (a != null && b != null) {
	    		return a.getName().compareTo(b.getName());
	    	} else {
	    		return 0;
	    	}
	    }
	}
}
