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
import java.awt.event.MouseMotionListener;
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

import org.compiere.apps.ADialog;
import org.compiere.apps.ConfirmPanel;
import org.compiere.model.MBSCKeySuccessFactor;
import org.compiere.model.MBSCKeySuccessFactorLink;
import org.compiere.model.MBSCPerspective;
import org.compiere.model.MBSCStrategicMap;
import org.compiere.model.Query;
import org.compiere.swing.CLabel;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * @author Y.Ibrayev
 *
 */
public class BSCPerspectivePanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener{

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
	private int[][] links; 
	private HashMap<MBSCPerspective,JPanel> label = new HashMap<MBSCPerspective,JPanel>();
	private HashMap<Integer, Rectangle> coords = new HashMap<Integer, Rectangle>();
	
	private ValueComparator bvc =  new ValueComparator(strategicMap);
	private TreeMap<MBSCPerspective,List<MBSCKeySuccessFactor>> sortedStrategicMap = new TreeMap<MBSCPerspective,List<MBSCKeySuccessFactor>>(bvc); 
	
	private double[][] tableSize;
	private TableLayout tableLayout = null;
	
	private int dragKSF = 0;
	private Point dragP = new Point(0,0);
	private JPanel dragPanel = null;
	
	private boolean drawLink = false;
	private int linkKSFFrom = 0;
	private int linkKSFTo = 0;
	private Rectangle ksfFrom = new Rectangle();
	private Rectangle ksfTo = new Rectangle();
	
	private boolean isDelete = false;
	
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
		addMouseListener(this);
		addMouseMotionListener(this);
		load();
	}
	
	/**
	 * 
	 */
	private void initCoords(Graphics g) {
		int x = 0;
		int y = 0;
		for(MBSCPerspective key:sortedStrategicMap.keySet()) {
			List<MBSCKeySuccessFactor> list = strategicMap.get(key);
			JPanel lbl = label.get(key);
			y = borderSize + lbl.getY();
			x = borderSize + lbl.getX() + lbl.getWidth();
			for(MBSCKeySuccessFactor ksf: list) {
				x += borderSize;
				int width = 0;
				int height =  0;
				if (coords.get(ksf.getBSC_KeySuccessFactor_ID()) == null) {
					
					FontMetrics metric = g.getFontMetrics();
					width = metric.stringWidth(ksf.getName()) + borderSize * 2;
					height = metric.getHeight() + borderSize * 2;
					coords.put(ksf.getBSC_KeySuccessFactor_ID(), new Rectangle(x,y,width,height));
					
					x += width;
					y += borderSize + lbl.getHeight() / list.size();
				} else {
					FontMetrics metric = g.getFontMetrics();
					width = metric.stringWidth(ksf.getName()) + borderSize * 2;
					height = metric.getHeight() + borderSize * 2;
					coords.get(ksf.getBSC_KeySuccessFactor_ID()).height = height;
					coords.get(ksf.getBSC_KeySuccessFactor_ID()).width = width;
				}
			}
		}
	}

	/**
	 * 
	 */
	private void initLinks() {
		MBSCKeySuccessFactorLink[] KSFLink = MBSCKeySuccessFactorLink.getKeySuccessFactorLink(null);
		if (KSFLink != null && KSFLink.length > 0) {
			links = new int[KSFLink.length][2];
			for(int i=0; i < KSFLink.length; i++) {
				links[i][0] = KSFLink[i].getBSC_KeySuccessFactor_ID();
				links[i][1] = KSFLink[i].getBSC_KeySuccessFactor_Link_ID();
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
			JPanel panel = new JPanel();
			panel.setLayout(new GridBagLayout());
			CLabel lbl = new CLabel(key.getName()); 
			lbl.setAlignmentX(CENTER_ALIGNMENT);
			lbl.setAlignmentY(CENTER_ALIGNMENT);
			lbl.setUI(new VerticalLabelUI(false));
			panel.setBorder(border);
			panel.add(lbl);
			label.put(key,panel);
           	String pos="1," + Integer.toString((i+1))+",1," + Integer.toString((i+1));
           	add(panel,pos);
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
		if (isDelete()) {
			deleteLink(e.getX(),e.getY());
			repaint();
		}
	}

	/**
	 * @param x
	 * @param y
	 */
	private void deleteLink(int x, int y) {
		
		int KSF = getKSF(x,y);
		int deleteNum = 0;
		int count = 0;
		if (KSF <= 0) {
			System.out.println("-----------------------");
			for(int i = 0; i < links.length; i++) {
				Rectangle r1 = coords.get(links[i][0]);
				Rectangle r2 = coords.get(links[i][1]);
				int x1 = r1.x + r1.width / 2;
				int y1 = r1.y + r1.height / 2;
				int x2 = r2.x + r2.width / 2;
				int y2 = r2.y + r2.height / 2;
				if (byLine(x,y,x1,y1,x2,y2)) {
					deleteNum = i+1;
					count++;
					System.out.print(links[i][0]);
					System.out.print(" - ");
					System.out.print(links[i][1]);
					MBSCKeySuccessFactor f1 = MBSCKeySuccessFactor.get(links[i][0]);
					MBSCKeySuccessFactor f2 = MBSCKeySuccessFactor.get(links[i][1]);
					if (f1 != null && f2 != null) {
						String s = " (" + f1.getName() + " - " + f2.getName() + ")";
						System.out.println(s);
					}
				}
			}
			
			if(deleteNum > 0 && count == 1) {
				int[][] newLinks = new int[links.length - 1][2];
				int j=0;
				for(int i = 0; i < links.length; i++) {
					if (i != deleteNum-1) {
						newLinks[j][0] = links[i][0];
						newLinks[j][1] = links[i][1];
						j++;
					}
				}
				links = newLinks.clone();
			}
		}
	}

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param x3
	 * @param y3
	 * @return
	 */
	private boolean byLine(int x1, int y1, int x2, int y2, int x3, int y3) {
		boolean result = false;
		int delta = 2;
		
		int maxX = (x2 < x3 ? x3 : x2);
		int maxY = (y2 < y3 ? y3 : y2);
		int minX = (x2 < x3 ? x2 : x3);
		int minY = (y2 < y3 ? y2 : y3);
		
		if (minX < x1 && x1 < maxX && minY < y1 && y1 < maxY) {
			Point[] square = new Point[4];
			for(int i = 0 ; i < square.length; i++)
				square[i] = new Point();
			
			square[0].x = x1 - delta;
			square[0].y = y1 - delta;
			
			square[1].x = x1 - delta;
			square[1].y = y1 + delta;
			
			square[2].x = x1 + delta;
			square[2].y = y1 + delta;
			
			square[3].x = x1 + delta;
			square[3].y = y1 - delta;
			
			
			int[] r = new int[4];
			for(int i = 0 ; i < r.length; i++) {
				r[i] = f1(square[i].x,square[i].y,x2,y2,x3,y3);
			}
			int[] p = new int[4];
			for(int i = 0 ; i < r.length; i++) {
				if (i+1 < r.length) {
					p[i] = r[i]*r[i+1];
				} else {
					p[i] = r[i]*r[0];
				}
			}
			int count = 0;
			for(int i = 0 ; i < p.length; i++) {
				if (p[i] < 0) {
					count++;
				}
			}
			
			result = count == 2;
		}
		return result;
	}
	
	private int f1(int x1, int y1, int x2, int y2, int x3, int y3) {
		return (x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		
		if (drawLink && !isDelete()) {
			linkKSFFrom = getKSF(e.getX(),e.getY());
		} else if (isDelete()) {
			
		} else {
			dragKSF = getKSF(e.getX(),e.getY());
			if (dragKSF > 0 ) {
				for(MBSCPerspective p : sortedStrategicMap.keySet()) {
					for(MBSCKeySuccessFactor ksf : sortedStrategicMap.get(p)) {
						if(ksf.getBSC_KeySuccessFactor_ID() == dragKSF) {
							dragPanel = label.get(p);
						}
					}
				}
			}
		}
	}
	
	private int getKSF(int x, int y) {
		int result = 0;
		for(int BSC_KeySuccessFactor_ID: coords.keySet()) {
			Rectangle r = coords.get(BSC_KeySuccessFactor_ID);
			if (r.x < x && x < r.x + r.width && r.y < y && y < r.y + r.height) {
				result = BSC_KeySuccessFactor_ID;
				dragP.x = x - r.x;
				dragP.y = y - r.y;
				break;
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (drawLink && !isDelete()) {
			if(linkKSFFrom > 0) {
				linkKSFTo = getKSF(e.getX(), e.getY());
				if (linkKSFTo > 0) {
					addLink(linkKSFFrom, linkKSFTo);
				}
			}
			linkKSFFrom = 0;
			linkKSFTo = 0;
			repaint();
		} else if (isDelete()) {
			
		} else {
			dragKSF = 0;
			dragPanel = null;
		}
	}

	/**
	 * @param linkKSFFrom2
	 * @param linkKSFTo2
	 */
	private void addLink(int linkKSFFrom, int linkKSFTo) {
		if (!haveLink(linkKSFFrom,linkKSFTo)) {
			int[][] newLink = new int[links.length + 1][2];
			for(int i=0; i<links.length; i++) {
				newLink[i][0] = links[i][0];
				newLink[i][1] = links[i][1];
			}
			newLink[links.length][0] = linkKSFFrom;
			newLink[links.length][1] = linkKSFTo;
			links = newLink.clone();
		}
	}

	/**
	 * @param linkKSFFrom2
	 * @return
	 */
	private MBSCKeySuccessFactor getKeySuccessFactor(int BSC) {
		// TODO Auto-generated method stub
		return null;
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
		paintPanel(g);
    	paintLinks(g);
    	paintKeySuccessFactors(g);
    	if (drawLink && linkKSFFrom > 0) {
    		g.setColor(Color.GREEN);
    		g.drawLine(ksfFrom.x + ksfFrom.width /2 , ksfFrom.y + ksfFrom.height /2, ksfTo.x, ksfTo.y);
    	}
    }

	/**
	 * @param g
	 */
	private void paintPanel(Graphics g) {
		Dimension d  = getSize();
		for(MBSCPerspective key:label.keySet()) {
			JPanel p = label.get(key);
			
			int x = p.getX() + p.getWidth() + 1;
			int y = p.getY();
			int width = d.width - x;
			int height = p.getHeight();
			
			
			g.setColor(Color.WHITE);
			g.fillRect(x, y, width, height-2);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height-2);
		}
	}

	/**
	 * @param g
	 */
	private void paintLinks(Graphics g) {
		if (links != null) {
			for(int[] item:links) {
				int fromKSF = item[0];
				int toKSF = item[1];
				Rectangle from = coords.get(fromKSF);
				Rectangle to = coords.get(toKSF);
				if (from != null && to != null) {
					g.setColor(Color.RED);
					g.drawLine(from.x + from.width /2, from.y + from.height / 2, to.x + to.width / 2, to.y + to.height /2);
				}
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

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (drawLink && !isDelete()) {
			if (linkKSFFrom > 0) {
				ksfFrom.setBounds(coords.get(linkKSFFrom));
				ksfTo.x = e.getX();
				ksfTo.y = e.getY();
				repaint();
			}
		} else if (isDelete()) {
		} else {
			if (dragKSF > 0) {
				int newX = e.getX() - dragP.x;
				int newY = e.getY() - dragP.y;
				
				if(dragPanel != null) {
					int dpX = dragPanel.getX() + dragPanel.getWidth(); 
					int dpY = dragPanel.getY() ;
					int dpWidth = getWidth() - dpX;
					int dpHeight = dragPanel.getHeight() - borderSize * 2;
					if ( dpX < newX && newX < dpX + dpWidth && dpY < newY && newY < dpY + dpHeight) {
						coords.get(dragKSF).x = newX;
						coords.get(dragKSF).y = newY;
						repaint();
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	public boolean save() {
		boolean result = true;
		
		
		result = saveStrategicMap();
		
		if (result) {
			result = saveLinks();
		}
		
		return result;
	}
	
	/**
	 * @return
	 */
	private boolean saveLinks() {
		boolean result = true;
		for(int i = 0; i < links.length; i++  ) {
			if(!MBSCKeySuccessFactorLink.haveLink(links[i][0], links[i][1])) {
				result = result && MBSCKeySuccessFactorLink.addLink(links[i][0], links[i][1]);
			}
		}
		for(MBSCKeySuccessFactorLink item : MBSCKeySuccessFactorLink.getKeySuccessFactorLink(null)) {
			if (!haveLink(item.getBSC_KeySuccessFactor_ID(), item.getBSC_KeySuccessFactor_Link_ID())) {
				item.delete(true);
			}
		}
		return result;
	}

	/**
	 * @param bsc_KeySuccessFactor_ID
	 * @param bsc_KeySuccessFactor_Link_ID
	 * @return
	 */
	private boolean haveLink(int bsc_KeySuccessFactor_ID, int bsc_KeySuccessFactor_Link_ID) {
		boolean result = false;
		for(int i=0; i<links.length; i++) {
			if((links[i][0] == bsc_KeySuccessFactor_ID && links[i][1] == bsc_KeySuccessFactor_Link_ID) 
			|| (links[i][1] == bsc_KeySuccessFactor_ID && links[i][0] == bsc_KeySuccessFactor_Link_ID))
				result = true;
		}
		return result;
	}

	/**
	 * @return
	 */
	private boolean saveStrategicMap() {
		boolean result = true;
		List<MBSCStrategicMap> listSM = MBSCStrategicMap.getStrategicMap();
		for(int BSC_KeySuccessFactor_ID : coords.keySet()) {
			boolean have = false;
			for(MBSCStrategicMap sm: listSM) {
				if(sm.getBSC_KeySuccessFactor_ID() == BSC_KeySuccessFactor_ID) {
					sm.setRectangle(coords.get(BSC_KeySuccessFactor_ID));
					result = result && sm.save();
					have = true;
				}
			}
			if (!have) {
				MBSCStrategicMap newSM = new MBSCStrategicMap(Env.getCtx(),0,null);
				newSM.setBSC_KeySuccessFactor_ID(BSC_KeySuccessFactor_ID);
				newSM.setRectangle(coords.get(BSC_KeySuccessFactor_ID));
				result = result && newSM.save();
			}
		}
		MBSCStrategicMap.loadStrategicMap();
		
		return result;
	}

	public boolean load() {
		boolean result = true;
		for(MBSCPerspective key:sortedStrategicMap.keySet()) {
			List<MBSCKeySuccessFactor> list = strategicMap.get(key);
			for(MBSCKeySuccessFactor ksf: list) {
				MBSCStrategicMap sm = MBSCStrategicMap.get(ksf);
				if (sm != null) {
					if (coords.get(ksf.getBSC_KeySuccessFactor_ID()) != null) {
						coords.get(ksf.getBSC_KeySuccessFactor_ID()).setBounds(sm.getRectangle());
					} else {
						coords.put(ksf.getBSC_KeySuccessFactor_ID(),sm.getRectangle());
					}
				}
			}
		}
		return result;
	}
	
	public boolean isDrawLink() {
		return drawLink;
	}

	public void setDrawLink(boolean drawLink) {
		this.drawLink = drawLink;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

}
