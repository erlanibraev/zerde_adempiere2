/**
 * 
 */
package org.adempiere.apps.graph;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * @author Y.Ibrayev
 *
 */
public class BSCPointPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1239947744732997028L;
	private Color pointColor = null;
	
	public Color getPointColor() {
		return pointColor;
	}

	public void setPointColor(Color pointColor) {
		this.pointColor = pointColor;
	}

	BSCPointPanel(Color pointColor) {
		super();
		setPointColor(pointColor);
	}

    @Override
	protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.setColor(getPointColor());
    	g.fillOval(0, 0, getWidth(), getHeight());
//    	g.setColor(Color.BLACK);
//    	g.drawOval(0, 0, getWidth(), getHeight());
    }
}
