/**
 * 
 */
package org.adempiere.apps.graph;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import org.compiere.model.MBSCCard;
import org.compiere.model.MColorSchema;
import org.compiere.model.MParameter;
import org.compiere.swing.CMenuItem;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.data.Range;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.RectangleInsets;

/**
 * @author Y.Ibrayev
 *
 */
public class BSCCardIndicator extends JPanel implements MouseListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8962910459260853329L;

	private static double s_width100 = 150;
	private static Color colorOK = Color.magenta;
	private static Color colorNotOK = Color.lightGray;
	public static Dimension indicatordimension = new Dimension(170,120);
	public static Dimension paneldimension = new Dimension(180, 150);
	private ChartPanel chartPanel;
	private JPopupMenu popupMenu = new JPopupMenu();
	private CMenuItem mRefresh = new CMenuItem(Msg.getMsg(Env.getCtx(), "Refresh"), Env.getImageIcon("Refresh16.gif"));
	private MParameter parameter = null;
	private double m_line = 0;
	private MBSCCard card = null;
	private int BSC_Card_ID = 0;
	
	BSCCardIndicator(MBSCCard aCard) {
		super();
		setCard(aCard);
		init();
	}
	
	BSCCardIndicator(int aBSC_card_ID) {
		super();
		setBSC_Card_ID(aBSC_card_ID);
		init();
	}
	
	private void init() {
		setName(getCard().getName());
        chartPanel = new ChartPanel(createChart(), //chart
									false, //boolean properties
									false, // boolean save
									false, //boolean print
									false, //boolean zoom
									true //boolean tooltips
        						   );   
        chartPanel.setPreferredSize(indicatordimension);
		
        chartPanel.addChartMouseListener( new org.jfree.chart.ChartMouseListener() 
        {
            public void chartMouseClicked(org.jfree.chart.ChartMouseEvent e) 
            {
                //plot p = (MeterPlot) e.getSource();
               MouseEvent me = e.getTrigger();
                if (SwingUtilities.isLeftMouseButton(me) && me.getClickCount() > 1)
                        fireActionPerformed(me);
                if (SwingUtilities.isRightMouseButton(me))
                        popupMenu.show((Component)me.getSource(), me.getX(), me.getY());             
                }            
                public void chartMouseMoved(org.jfree.chart.ChartMouseEvent e) 
                {

                }
            });

        this.add(chartPanel, BorderLayout.CENTER);
    	this.setMinimumSize(paneldimension);
    	this.setMaximumSize(paneldimension);
    	invalidate();
		
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		setOpaque(true);
		mRefresh.addActionListener(this);
		popupMenu.add(mRefresh);
		addMouseListener(this);
		
		setPreferredSize(paneldimension);
	}
	
	public MBSCCard getCard() {
		return card;
	}
	
	public void setCard(MBSCCard card) {
		BSC_Card_ID = (card == null ? 0 : card.getBSC_Card_ID());
		this.card = card;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public int getBSC_Card_ID() {
		return BSC_Card_ID;
	}

	public void setBSC_Card_ID(int bSC_Card_ID) {
		if (bSC_Card_ID > 0 && getCard() == null) {
			card = new MBSCCard(Env.getCtx(),bSC_Card_ID,null);
		}
		BSC_Card_ID = bSC_Card_ID;
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
	
	private  JFreeChart createChart(){
		JFreeChart chart = null;

		m_line = s_width100;
		double var = (getCard().getValueNumber() != null ? getCard().getValueNumber().doubleValue() : 0)  ;// TODO
		DefaultValueDataset data = new DefaultValueDataset(var);
		MeterPlot plot = new MeterPlot(data);
		
		MColorSchema colorSchema = new MColorSchema(Env.getCtx(),100,null); //TODO !!!!!!!!!!
		
        int rangeLo = 0; int rangeHi=0;
        for (int i=1; i<=4; i++){
            switch (i) {
             case 1: rangeHi = colorSchema.getMark1Percent(); break;
             case 2: rangeHi = colorSchema.getMark2Percent(); break;
             case 3: rangeHi = colorSchema.getMark3Percent(); break;
             case 4: rangeHi = colorSchema.getMark4Percent(); break;
            }
            if (rangeHi==9999)
            	rangeHi = (int) Math.floor(rangeLo*1.5);
            if (rangeLo < rangeHi) {
            	plot.addInterval(new MeterInterval("Normal", //label
                 	  new Range(rangeLo, rangeHi), //range
                 	  colorSchema.getColor(rangeHi),
                 	  new BasicStroke(7.0f),
                 	  //Color.lightGray
                 	  new Color(-13091716)
                 	  //Color.gray 
                ));
            	rangeLo = rangeHi;
            }
        }
        plot.setRange(new Range(0,rangeLo));
        plot.setDialBackgroundPaint(new Color(-13091716));//Color.GRAY);
        plot.setUnits(getCard().getName());
        plot.setDialShape(DialShape.CHORD);//CIRCLE);        
        //plot.setDialBackgroundPaint(new GradientPaint(0, 0, m_goal.getColor(), 0, 1000, Color.black));
        plot.setNeedlePaint(Color.white);  
        plot.setTickSize(2000);
        plot.setTickLabelFont(new Font("SansSerif", Font.BOLD, 12));
        plot.setTickLabelPaint(Color.white);
        plot.setInsets(new RectangleInsets(1.0, 2.0, 3.0, 4.0)); 
        
        String message = getCard().getName();
        if (getCard().getC_BPartner_ID() > 0) {
        	message += "\n" + getCard().getC_BPartner().getName();
        }
        chart = new JFreeChart( message, new Font("SansSerif", Font.BOLD, 15), plot,false);
        
		return chart;
	}
	
    protected void fireActionPerformed(MouseEvent event){
        // Guaranteed to return a non-null array
    	ActionListener[] listeners = getActionListeners();
        ActionEvent e = null;
        // Process the listeners first to last
        for (int i = 0; i < listeners.length; i++){
        	//	Lazily create the event:
        	if (e == null) 
        		e = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
        			"pi", event.getWhen(), event.getModifiers());
        	listeners[i].actionPerformed(e);
        }
    }	//	fireActionPerformed
	
    public ActionListener[] getActionListeners(){
        return (ActionListener[])(listenerList.getListeners(ActionListener.class));
    }	//	getActionListeners

}
