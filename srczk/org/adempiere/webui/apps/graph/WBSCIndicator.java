/**
 * 
 */
package org.adempiere.webui.apps.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import org.adempiere.webui.component.Panel;
import org.compiere.model.MColorSchema;
import org.compiere.model.MParameter;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.encoders.EncoderUtil;
import org.jfree.chart.encoders.ImageFormat;
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.data.Range;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.RectangleInsets;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Image;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;

/**
 * @author Y.Ibrayev
 *
 */
public class WBSCIndicator extends Panel implements EventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2317114048033697142L;
	private MParameter m_Parameter = null;
	private Menupopup popupMenu = new Menupopup();
	private Menuitem mRefresh = new Menuitem(Msg.getMsg(Env.getCtx(), "Refresh"), "/images/Refresh16.png");
	private ChartPanel chartPanel;

	public WBSCIndicator(MParameter parameter) {
		super();

		m_Parameter = parameter;

		init();
		
		mRefresh.addEventListener(Events.ON_CLICK, this);
		popupMenu.appendChild(mRefresh);

		addEventListener(Events.ON_DOUBLE_CLICK, this);
		addEventListener(Events.ON_CLICK, this);
	}	//	PerformanceIndicator

	private  JFreeChart createChart()
	{
		JFreeChart chart = null;

		//	Set Text
		StringBuffer text = new StringBuffer(m_Parameter.getName());
		text = new StringBuffer();
		text.append(m_Parameter.getName());
		
		setTooltiptext(text.toString());
		
		//
        DefaultValueDataset data = new DefaultValueDataset(m_Parameter.getValueNumber().doubleValue());
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
                 	  new Color(-13091716)
                ));
            	rangeLo = rangeHi;
            }
        }
        plot.setRange(new Range(0,rangeLo));

        plot.setDialBackgroundPaint(new Color(-13091716));
        plot.setUnits("");
        plot.setDialShape(DialShape.CHORD);//CIRCLE);
        plot.setNeedlePaint(Color.white);
        plot.setTickSize(2000);
        plot.setTickLabelFont(new Font("SansSerif", Font.BOLD, 8));
        plot.setValueFont(new Font("SansSerif", Font.BOLD, 8));
        plot.setNoDataMessageFont(new Font("SansSerif", Font.BOLD, 8));
        plot.setTickLabelPaint(Color.white);
        plot.setInsets(new RectangleInsets(1.0, 2.0, 3.0, 4.0));

        chart = new JFreeChart( m_Parameter.getName() + " " + m_Parameter.getPeriod().getName(), new Font("SansSerif", Font.BOLD, 9), plot,false);

		return chart;
	}

	private void init()
	{
		JFreeChart chart = createChart();
		chart.setBackgroundPaint(Color.WHITE);
		chart.setBorderVisible(true);
		chart.setBorderPaint(Color.LIGHT_GRAY);
		chart.setAntiAlias(true);
		BufferedImage bi = chart.createBufferedImage(200, 120, BufferedImage.TRANSLUCENT , null);
		try {
		    byte[] bytes = EncoderUtil.encode(bi, ImageFormat.PNG, true);

		    AImage image = new AImage("", bytes);
		    Image myImage = new Image();
		    myImage.setContent(image);
		    appendChild(myImage);
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

	    invalidate();
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.event.EventListener#onEvent(org.zkoss.zk.ui.event.Event)
	 */
	@Override
	public void onEvent(Event event) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 	Update Display Data
	 */
	protected void updateDisplay()
	{
		chartPanel.setChart(createChart());
	    invalidate();
	}	//	updateData
}
