/**
 * 
 */
package org.adempiere.apps.graph;

import groovy.swing.impl.TableLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.awt.print.Printable;

import javax.swing.JPanel;

import org.compiere.apps.ADialog;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;
import org.compiere.model.MBSCPerspective;
import org.compiere.swing.CButton;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author Y.Ibrayev
 *
 */
public class BSCViewPerspective extends CPanel implements FormPanel, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -250907119653145646L;
	
	private static CLogger log = CLogger.getCLogger (BSCViewPerspective.class);

	private int m_WindowNo = 0;
	private CPanel toolsPanel = new CPanel(new FlowLayout(FlowLayout.LEADING));
	private BSCPerspectivePanel mainPanel = null;
	private ConfirmPanel confirmPanel = new ConfirmPanel();
	private FormFrame m_frame;
	
	private CButton buttonSave = new CButton(Msg.getMsg(Env.getAD_Language(Env.getCtx()), "Save"));
	private CCheckBox boxLine = new CCheckBox(Msg.getMsg(Env.getAD_Language(Env.getCtx()), "Link"));
	private CButton buttonPrint = new CButton(Msg.getMsg(Env.getAD_Language(Env.getCtx()), "Print"));
	private CCheckBox boxDelete = new CCheckBox(Msg.getMsg(Env.getAD_Language(Env.getCtx()), "Delete"));
	

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
			dispose();
		else if (e.getSource().equals(buttonSave)) {
			if (!mainPanel.save()) {
				ADialog.info(m_WindowNo, this, Msg.getMsg(Env.getAD_Language(Env.getCtx()), "SaveError"));
			}
		} else if (e.getSource().equals(boxLine)) {
			if ((Boolean)boxLine.getValue()) {
				boxDelete.setValue(false);
			}
			mainPanel.setDrawLink((Boolean)boxLine.getValue());
			mainPanel.setDelete((Boolean)boxDelete.getValue());
		} else if (e.getSource().equals(buttonPrint)) {
			printMainPanel();
		} else if (e.getSource().equals(boxDelete)) {
			if ((Boolean)boxDelete.getValue()) {
				boxLine.setValue(false);
			}
			mainPanel.setDrawLink((Boolean)boxLine.getValue());
			mainPanel.setDelete((Boolean)boxDelete.getValue());
		}
	}

	/**
	 * 
	 */
	private class MyPrintable implements Printable {
		/* (non-Javadoc)
		 * @see java.awt.print.Printable#print(java.awt.Graphics, java.awt.print.PageFormat, int)
		 */
		@Override
		public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		      if (pageIndex > 0){
		    	  return Printable.NO_SUCH_PAGE;
		      }

		      Graphics2D g2 = (Graphics2D) graphics;
		      mainPanel.paint(g2);
		      g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
		      return Printable.PAGE_EXISTS;
		}
		
	}
	private void printMainPanel() {
		
		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setPrintable (new MyPrintable());
  	  	if (pj.printDialog() == false)
  	  		return;
  	  	try {
  	  		pj.print();
  	  	} catch (PrinterException ex) {
  	  		log.log(Level.SEVERE, getName(), ex);
  	  	}		
	}

	/* (non-Javadoc)
	 * @see org.compiere.apps.form.FormPanel#init(int, org.compiere.apps.form.FormFrame)
	 */
	@Override
	public void init(int WindowNo, FormFrame frame){
		log.fine("BSCViewPerspective");
		m_WindowNo = WindowNo;
		m_frame = frame;
		try {
			confirmPanel.addActionListener(this);
			mainPanel = new BSCPerspectivePanel();
			if (mainPanel != null) {
				initToolsPanel();
				CScrollPane scroll =  new CScrollPane(mainPanel);
				m_frame.getContentPane().add(scroll, BorderLayout.CENTER);
				m_frame.getContentPane().add(confirmPanel, BorderLayout.SOUTH);
				m_frame.getContentPane().add(toolsPanel,BorderLayout.NORTH);
				
			}
		} catch (Exception e) {
			log.log(Level.SEVERE,e.getMessage());
		}
	}

	/**
	 * 
	 */
	private void initToolsPanel() {
		if (toolsPanel == null) {
			return;
		}
		buttonSave.addActionListener(this);
		toolsPanel.add(buttonSave);
		buttonPrint.addActionListener(this);
		toolsPanel.add(buttonPrint);
		boxLine.addActionListener(this);
		toolsPanel.add(boxLine);
		boxDelete.addActionListener(this);
		toolsPanel.add(boxDelete);
	}

	/* (non-Javadoc)
	 * @see org.compiere.apps.form.FormPanel#dispose()
	 */
	@Override
	public void dispose() {
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
		removeAll();
	}
	
}
