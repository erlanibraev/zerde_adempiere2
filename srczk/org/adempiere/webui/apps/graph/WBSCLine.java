/**
 * 
 */
package org.adempiere.webui.apps.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import org.adempiere.webui.component.Panel;
import org.compiere.util.CLogger;
import org.zkoss.image.AImage;
import org.zkoss.zul.Image;

/**
 * @author Y.Ibrayev
 *
 */
public class WBSCLine extends Panel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6030230652508454136L;
	private static CLogger log = CLogger.getCLogger (WPAPanel.class);
	private static Dimension s_size = new Dimension (200, 120);
	private boolean isEnd = false;
	private Rectangle m_bounds  = new Rectangle(0, 0, s_size.width, s_size.height);;
	private BufferedImage bi = new BufferedImage (s_size.width, s_size.height, BufferedImage.TYPE_INT_ARGB);
	
	public WBSCLine() {
		super();
		
		paint();
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(bi, "png", os);
			AImage image = new AImage("BSCLine.png", os.toByteArray());
		    Image myImage = new Image();
		    myImage.setContent(image);
		    appendChild(myImage);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		invalidate();
	}
	
	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}
	
	protected void paint() {
		Graphics2D g = bi.createGraphics();
		
		g.setBackground(new Color(200,200,200));
		g.setColor(new Color(100,100,100));
		
		int x1 = m_bounds.width / 2;
		int x2 = m_bounds.width / 2;
		int y1 = 0;
		int y2 = (!isEnd() ? m_bounds.height : m_bounds.height /2 );
		
		g.drawLine(x1, y1, x2, y2);
		
		x1 = m_bounds.width / 2;
		x2 = m_bounds.width;
		y1 = m_bounds.height / 2;
		y2 = m_bounds.height / 2;
		
		g.drawLine(x1, y1, x2, y2);
		
		x1 = x2 - 10;
		y1 = y2 - 10;
		g.drawLine(x1, y1, x2, y2);
		
		x1 = x2 - 10;
		y1 = y2 + 10;
		g.drawLine(x1, y1, x2, y2);
	}
	
	
}
