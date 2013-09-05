/**
 * 
 */
package org.adempiere.apps.graph;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.util.Calendar;

import org.compiere.model.MParameter;
import org.compiere.model.MParameterLine;
import org.compiere.swing.CPanel;
import org.compiere.util.Env;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author Y.Ibrayev
 *
 */
public class BSCBarChart extends CPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6627108563845229848L;
	private MParameter parameter = null;
	private int BSC_Parameter_ID = 0;
	private ChartPanel chartPanel;
	public static Dimension indicatordimension = new Dimension(800,600);
	protected DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	/** X Axis Label			*/
	protected static String		m_X_AxisLabel = "Период";
	/** Y Axis Label			*/
	protected static String		m_Y_AxisLabel = "Значение (%)";
	
	protected static String plan = "План";
	protected static String fact = "Факт";
	
	
	public BSCBarChart(MParameter _parameter) {
		super();
		setParameter(_parameter);
	}
	
	public BSCBarChart(int _BSC_Parameter_ID) {
		super();
		setBSC_Parameter_ID(_BSC_Parameter_ID);
	}
	
	public int getBSC_Parameter_ID() {
		return BSC_Parameter_ID;
	}

	public void setBSC_Parameter_ID(int bSC_Parameter_ID) {
		BSC_Parameter_ID = bSC_Parameter_ID;
		if (BSC_Parameter_ID > 0 && (parameter == null || parameter.getBSC_Parameter_ID() != BSC_Parameter_ID)) {
			setParameter(new MParameter(Env.getCtx(),BSC_Parameter_ID, null));
			viewBarChart();
		}
	}

	public MParameter getParameter() {
		return parameter;
	}

	public void setParameter(MParameter parameter) {
		this.parameter = parameter;
		if (parameter != null) {
			setBSC_Parameter_ID(parameter.getBSC_Parameter_ID());
		}
	}

	/**
	 * 
	 */
	private void viewBarChart() {
		removeAll();
        chartPanel = new ChartPanel(createChart(), //chart
									false, //boolean properties
									false, // boolean save
									false, //boolean print
									false, //boolean zoom
									true //boolean tooltips
        						   );   
        chartPanel.setPreferredSize(indicatordimension);
        add(chartPanel, BorderLayout.CENTER);
		validate();
		repaint();
		updateUI();
	}
	
	private JFreeChart createChart(){
		loadData();
		
		JFreeChart chart = ChartFactory.createBarChart3D(
				getParameter().getName(),         // chart title
				m_X_AxisLabel,               // domain axis label
				m_Y_AxisLabel,                  // range axis label
				dataset,                  // data
				PlotOrientation.VERTICAL, // orientation
				false,                     // include legend
				true,                     // tooltips?
				true                     // URLs?
		);
		return chart;
	}

	/**
	 * 
	 */
	private void loadData() {
		dataset = new DefaultCategoryDataset();
		for (int i = 0; i < getParameter().getParameterLine().size(); i++){
//			String series = m_X_AxisLabel;
			MParameterLine line = getParameter().getParameterLine().get(i);
			BigDecimal value = new BigDecimal(line.getValue());
			dataset.addValue(value.doubleValue(), fact, line.getPeriod().getName());
			dataset.addValue(1, plan, line.getPeriod().getName());
		}
	}
}
