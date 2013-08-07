/**
 * 
 */
package org.compiere.model;

import java.awt.Rectangle;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.Env;

import com.sun.enterprise.web.MemoryStrategyBuilder;

/**
 * @author Y.Ibrayev
 *
 */
public class MBSCStrategicMap extends X_BSC_StrategicMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6826580559759393478L;
	private static CLogger log = CLogger.getCLogger(MBSCStrategicMap.class);
	private static HashMap<Integer, Rectangle> coords = new HashMap<Integer, Rectangle>();
	private static List<MBSCStrategicMap> strategicMap = new ArrayList<MBSCStrategicMap>();
	private Rectangle rectangle = null;
	
	/**
	 * @param ctx
	 * @param BSC_StrategicMap_ID
	 * @param trxName
	 */
	public MBSCStrategicMap(Properties ctx, int BSC_StrategicMap_ID,
			String trxName) {
		super(ctx, BSC_StrategicMap_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBSCStrategicMap(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 */
	public MBSCStrategicMap(Properties ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	public static HashMap<Integer, Rectangle> getCoords() {
		if(coords.size() <= 0) {
			loadCoords();
		}
		return coords;
	}

	public Rectangle getRectangle() {
		if (rectangle == null) {
			rectangle = new Rectangle(getXPosition(), getYPosition(), getWidth(), getHeight());
		}
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		setXPosition(rectangle.x);
		setYPosition(rectangle.y);
		setWidth(rectangle.width);
		setHeight(rectangle.height);
		this.rectangle = rectangle;
	}
	
	@Override
	public void setXPosition (int XPosition) {
		super.setXPosition(XPosition);
		getRectangle().x = XPosition;
	}
	
	@Override
	public void setYPosition (int YPosition) {
		super.setYPosition(YPosition);
		getRectangle().y = YPosition;
	}

	@Override
	public void setWidth (int Width) {
		super.setWidth(Width);
		getRectangle().width = Width;
	}
	
	@Override
	public void setHeight (int Height) {
		super.setHeight(Height);
		getRectangle().height = Height;
	}
	
	public static HashMap<Integer, Rectangle> loadCoords() {
		coords.clear();
		for(MBSCStrategicMap sm: MBSCStrategicMap.getStrategicMap()) {
			coords.put(sm.getBSC_KeySuccessFactor_ID(), sm.getRectangle());
		}
		return coords;
	}
	
	public static List<MBSCStrategicMap> getStrategicMap() {
		if (strategicMap.size() <= 0) {
			loadStrategicMap();
		}
		return strategicMap;
	}

	/**
	 * 
	 */
	public static List<MBSCStrategicMap> loadStrategicMap() {
		strategicMap.clear();
		String WhereClause = "AD_Client_ID = "+Env.getAD_Client_ID(Env.getCtx());
		strategicMap = new Query(Env.getCtx(), MBSCStrategicMap.Table_Name, WhereClause,null)
		                  .list();
		if (strategicMap == null) {
			strategicMap = new ArrayList<MBSCStrategicMap>();
		}
		return strategicMap;
	}
	
	public static MBSCStrategicMap get(MBSCKeySuccessFactor ksf) {
		MBSCStrategicMap result = null;
		if(getStrategicMap().size() > 0 && ksf != null) {
			for(MBSCStrategicMap item: getStrategicMap()) {
				if (item.getBSC_KeySuccessFactor_ID() == ksf.getBSC_KeySuccessFactor_ID()) {
					result = item;
				}
			}
		}
		return result;
	}
}
