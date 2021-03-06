/*
 *              Argus Software Pty Ltd License Notice
 * 
 * The contents of this file are subject to the Argus Software Pty Ltd
 * License Version 1.0 (the "License"). You may not use this file except
 * in compliance with the License. A copy of the License is available at
 * http://www.argussoftware.net/license/license_agreement.html
 * 
 * The Original Code is argus. The Initial Developer of the Original
 * Code is Argus Software Pty Ltd, All Rights Reserved.
 */

package au.com.totemsoft.swing;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Argus Software Pty Ltd</p>
 * @author Valeri CHIBAEV (mailto:apollosoft.net.au@gmail.com)
 * @author Valeri CHIBAEV (mailto:apollosoft.net.au@gmail.com)
 * @version                   $Revision: 1.2 $
 */

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.text.JTextComponent;

public class StatusBarPanel extends javax.swing.JPanel {
    
	private int offset;
	
    /** Creates new form StatusBarPanel */
    public StatusBarPanel() {
        initComponents();
        offset = getComponentCount();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelDummy = new javax.swing.JPanel();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));

        jPanelDummy.setLayout(new java.awt.GridBagLayout());

        jPanelDummy.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED), new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        //add(jPanelDummy);

    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelDummy;
    // End of variables declaration//GEN-END:variables
    
    public void addJComponent(Component jc) {
        int index = getComponentCount() - offset;
        addJComponent(jc, index);
    }
    public void addJComponent(Component jc, int index) {
        // add to panel
        if (jc instanceof JPanel) {
            add(jc, index);
            return;
        }
        
        JPanel jp = new JPanel();
        jp.setLayout(new java.awt.GridBagLayout());

        jp.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED), new BevelBorder(BevelBorder.LOWERED)));
        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jp.add(jc, gridBagConstraints);

        add(jp, index);
    }
    
    public void setText(int index, String text) {
    	JComponent jc = getJComponent(index);
    	if (jc == null) return;

    	if (text == null || text.length() == 0) text = "";
        text = " " + text.trim();
    	if (jc instanceof JTextComponent) ((JTextComponent) jc).setText(text);
    	if (jc instanceof JLabel) ((JLabel) jc).setText(text);
    }
    
    public JComponent getJComponent(int index) {
    	JPanel jp = (JPanel) getComponent(index);
        int count = jp.getComponentCount();
    	return count == 0 ? null : (JComponent) jp.getComponent(0);
    }

    /////////////////////////////////////////////////////////////////////////
    //
    /////////////////////////////////////////////////////////////////////////
    private Thread progressThread;
    long progress;
    public void startProgress(int index) {
		if (progressThread != null && progressThread.isAlive())
			return;

		progress = System.currentTimeMillis();
    	
		final JProgressBar progressBar = (JProgressBar) getJComponent(index);
		progressBar.setString("Please wait ...");
		progressBar.setStringPainted(true);
		
       	progressThread = new Thread() {
            public void run() {
                updateProgressBar(1);
                while ( !isInterrupted() ) {
                    synchronized (this) {
                        try {
                            wait( 500 );
                            updateProgressBar(1);
                        } catch(Exception e) {
                            break;
                        }
                    }
                }
            }
            private void updateProgressBar( int n ) {
            	progressBar.setValue( progressBar.getValue() + n );
                if ( progressBar.getValue() == progressBar.getMaximum() )
                	progressBar.setValue(0);
            }
    	};
    	progressThread.start();
    	
    }
    
    public void stopProgress(int index) {
		if (progressThread == null || !progressThread.isAlive())
			return;
		
		progressThread.interrupt();
		progressThread = null;
		
    	progress = (System.currentTimeMillis() - progress) / 1000;
		final JProgressBar progressBar = (JProgressBar) getJComponent(index);
		
		progressBar.setString("Completed in " + progress + " second(s)");
		progressBar.setStringPainted(true);
		progressBar.setValue(0);
		
	}

}
