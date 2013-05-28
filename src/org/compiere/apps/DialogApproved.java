/**
 * 
 */
package org.compiere.apps;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import org.compiere.swing.CDialog;

/**
 * @author V.Sokolov
 *
 */
public class DialogApproved extends CDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6932858301807052515L;
	
	/**
     * Creates new form NewJDialog
     */
    public DialogApproved(Frame parent, boolean modal, String title, Object[] options) {
        super(parent, modal);
        this.title = title;
        this.options = options;
        initComponents();
    }
    
    // Variables declaration - do not modify                     
    private JButton jButton;
    private JComboBox<Object> jComboBox;
    private JScrollPane jScrollPane;
    private JTextArea jTextArea;
    
    private ArrayList<Object> value = new ArrayList<Object>();
    private String title;
    transient protected Object[] options;
    private String Description;
    private String Item;
    
    public String getItem() {
		return Item;
	}

	public void setItem(String item) {
		Item = item;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		this.Description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<Object> getValue() {
		return value;
	}

	//                          
    private void initComponents() {

        jButton = new JButton();
        jScrollPane = new JScrollPane();
        jTextArea = new JTextArea();
        jComboBox = new JComboBox<Object>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        jButton.setText("ОК");
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonActionPerformed(evt);
                dispose();
            }
        });

        jTextArea.setColumns(20);
        jTextArea.setRows(5);
        jScrollPane.setViewportView(jTextArea);

        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<Object>(options));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jButton)
                        .addGap(0, 106, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton)
                .addContainerGap())
        );

        pack();
    }// 

    private void jButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	setDescription(jTextArea.getText());
    	setItem((String) jComboBox.getSelectedItem());
    }    

}
