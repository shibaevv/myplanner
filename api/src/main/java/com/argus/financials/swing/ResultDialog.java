/*
 * ResultDialog.java
 *
 * Created on 14 October 2002, 10:50
 */

package com.argus.financials.swing;

import com.argus.swing.SwingUtils;

/**
 * 
 * @author valeri chibaev
 */

public class ResultDialog extends javax.swing.JDialog {

    public static final int CANCEL_OPTION = javax.swing.JOptionPane.CANCEL_OPTION;

    public static final int OK_OPTION = javax.swing.JOptionPane.OK_OPTION;

    public static final String CANCEL = "Cancel";

    public static final String OK = "OK";

    private int result; // CANCEL_OPTION, OK_OPTION

    /** Creates new form ResultDialog */
    public ResultDialog(java.awt.Component comp) {
        this(comp, null, true);
    }

    /** Creates new form ResultDialog */
    public ResultDialog(java.awt.Component comp, java.awt.Frame parent) {
        this(comp, parent, true);
    }

    /** Creates new form ResultDialog */
    public ResultDialog(java.awt.Component comp, java.awt.Frame parent,
            boolean modal) {
        super(parent == null ? SwingUtil.getSharedOwnerFrame() : parent, modal);

        initComponents();

        jButtonOK.setActionCommand(OK);
        jButtonCancel.setActionCommand(CANCEL);

        getContentPane().add(comp, java.awt.BorderLayout.CENTER);

        int h = (int) comp.getPreferredSize().getHeight()
                + (int) jPanelControls.getHeight();
        int w = (int) comp.getPreferredSize().getWidth();
        if (h > SwingUtils.SCREEN_HEIGHT * 4 / 5)
            h = SwingUtils.SCREEN_HEIGHT * 4 / 5;
        if (w > SwingUtils.SCREEN_WIDTH)
            w = SwingUtils.SCREEN_WIDTH;
        ((javax.swing.JComponent) comp)
                .setPreferredSize(new java.awt.Dimension(w, h));

        pack();

        // position in center
        setLocation((SwingUtils.SCREEN_WIDTH - w) / 2,
                (SwingUtils.SCREEN_HEIGHT - h) / 2);

        setResizable(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {// GEN-BEGIN:initComponents
        jPanelControls = new javax.swing.JPanel();
        jButtonOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jButtonOK.setText("OK");
        jButtonOK.setDefaultCapable(false);
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        jPanelControls.add(jButtonOK);

        jButtonCancel.setText("Cancel");
        jButtonCancel.setDefaultCapable(false);
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jPanelControls.add(jButtonCancel);

        getContentPane().add(jPanelControls, java.awt.BorderLayout.SOUTH);

    }// GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {// GEN-FIRST:event_formComponentShown
        result = CANCEL_OPTION;
    }// GEN-LAST:event_formComponentShown

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonCancelActionPerformed
        setVisible(false);
        result = CANCEL_OPTION; // by default
    }// GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonOKActionPerformed
        setVisible(false);
        result = OK_OPTION;
    }// GEN-LAST:event_jButtonOKActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_closeDialog
        setVisible(false);
        result = CANCEL_OPTION; // by default
        // dispose();
    }// GEN-LAST:event_closeDialog

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;

    private javax.swing.JPanel jPanelControls;

    private javax.swing.JButton jButtonOK;

    // End of variables declaration//GEN-END:variables

    public int getResult() {
        return result;
    }

    public void addActionListener(java.awt.event.ActionListener al) {
        jButtonOK.addActionListener(al);
        jButtonCancel.addActionListener(al);
    }

}
