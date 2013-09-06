/**
 * 
 */
package org.adempiere.apps.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * @author Y.Ibrayev
 *
 */
public class BSCThermometer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 214267677220361355L;
	private double value = 0;
	public static Dimension dimension = new Dimension(100,20);
	
	public BSCThermometer(double value) {
		super();
		setSize(dimension);
		setPreferredSize(dimension);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		setValue(value);
	}
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
    @Override
	protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	int width = getWidth() / 3;
    	int height = getHeight();
    	Color[] c = {Color.RED, Color.YELLOW, Color.GREEN};
    	int x = 0;
    	int y = 0;
    	for(int i = 0; i < 3 ; i++) {
        	g.setColor(c[i]);
        	g.fillRect(x, y, width, height);
        	x += width;
    	}
    	
    	drawMarker(g);
    }

	/**
	 * @param g
	 */
	private void drawMarker(Graphics g) {
    	int width = getWidth() / 3;
    	int height = getHeight();
    	int x = Math.round((float)((2 * width) * getValue()));
    	
    	int[] xp = new int[4];
    	int[] yp = new int[4];
    	xp[0] = x;
    	yp[0] = 0;
    	xp[1] = x+3;
    	yp[1] = getHeight() / 2;
    	xp[2] = x;
    	yp[2] = getHeight();
    	xp[3] = x-3;
    	yp[3] = getHeight() / 2;

    	if(x + 3 > getWidth()) {
    		for(int i = 0; i < 4; i++) {
    			xp[i] -=3;
    		}
    	}
    	if (x -3 < 0) {
    		for(int i = 0; i < 4; i++) {
    			xp[i] +=3;
    		}
    	}
    	g.setColor(Color.BLACK);
    	g.fillPolygon(xp,yp,4);
	}
	
}
