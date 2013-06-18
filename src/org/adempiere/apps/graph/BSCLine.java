/**
 * 
 */
package org.adempiere.apps.graph;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * @author Y.Ibrayev
 *
 */
public class BSCLine extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3451396548198450863L;
	
	private boolean isEnd = false;

	public BSCLine() {
		super();
		setFocusable(false);
		setPreferredSize(BSCIndicator.paneldimension);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int x1 = getSize().width / 2;
		int x2 = getSize().width / 2;
		int y1 = 0;
		int y2 = (!isEnd() ? getSize().height : getSize().height /2 );
		
		g.drawLine(x1, y1, x2, y2);
		
		x1 = getSize().width / 2;
		x2 = getSize().width;
		y1 = getSize().height / 2;
		y2 = getSize().height / 2;
		
		g.drawLine(x1, y1, x2, y2);
		
		x1 = x2 - 10;
		y1 = y2 - 10;
		g.drawLine(x1, y1, x2, y2);
		
		x1 = x2 - 10;
		y1 = y2 + 10;
		g.drawLine(x1, y1, x2, y2);
	}
	
	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

}
