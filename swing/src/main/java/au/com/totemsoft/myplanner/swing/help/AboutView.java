/*
 * AboutView.java
 *
 * Created on 27 June 2002, 10:16
 */

package au.com.totemsoft.myplanner.swing.help;

/**
 * @author Valeri CHIBAEV (mailto:apollosoft.net.au@gmail.com)
 */

import java.util.Properties;

import javax.swing.ImageIcon;

import au.com.totemsoft.myplanner.config.ViewSettings;
import au.com.totemsoft.myplanner.swing.AbstractPanel;
import au.com.totemsoft.myplanner.swing.FinancialPlannerApp;
import au.com.totemsoft.myplanner.swing.IMenuCommand;
import au.com.totemsoft.swing.SwingUtil;

public class AboutView extends AbstractPanel {

    private static final String UNKNOWN = "???";

    public static final String APPLICATION_VERSION = "APPLICATION_VERSION";

    public static final String DATABASE_VERSION = "DATABASE_VERSION";

    public static final String DATABASE_SERVER_VERSION = "DATABASE_SERVER_VERSION";

    private static AboutView view;

    /** Creates new form AboutView */
    private AboutView() {
        initComponents();

        jLabelImage.setIcon(new ImageIcon(getClass().getResource(
                "/image/art.gif")));

        setProperty(DATABASE_SERVER_VERSION, utilityService.getDBServerVersion());
        setProperty(DATABASE_VERSION, utilityService.getDBVersion());
        setProperty(APPLICATION_VERSION, FinancialPlannerApp.APP_VERSION);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {// GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelDetails = new javax.swing.JPanel();
        jLabelApplicationVersion = new javax.swing.JLabel();
        applicationVersion = new javax.swing.JLabel();
        jLabelDatabaseVersion = new javax.swing.JLabel();
        databaseVersion = new javax.swing.JLabel();
        jLabelServer = new javax.swing.JLabel();
        server = new javax.swing.JLabel();
        jLabelDBServerVersion = new javax.swing.JLabel();
        jScrollPaneDBServerVersion = new javax.swing.JScrollPane();
        dbServerVersion = new javax.swing.JTextPane();
        jLabelImage = new javax.swing.JLabel();
        jPanelControls = new javax.swing.JPanel();
        jButtonOK = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        setPreferredSize(new java.awt.Dimension(500, 250));
        jPanelDetails.setLayout(new java.awt.GridBagLayout());

        jPanelDetails.setBorder(new javax.swing.border.EmptyBorder(
                new java.awt.Insets(10, 10, 10, 10)));
        jLabelApplicationVersion.setText("Application Version:");
        jLabelApplicationVersion.setPreferredSize(new java.awt.Dimension(150,
                19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelDetails.add(jLabelApplicationVersion, gridBagConstraints);

        applicationVersion.setText("X.XX.XX");
        applicationVersion.setPreferredSize(new java.awt.Dimension(0, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanelDetails.add(applicationVersion, gridBagConstraints);

        jLabelDatabaseVersion.setText("Database Version:");
        jLabelDatabaseVersion.setPreferredSize(new java.awt.Dimension(150, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelDetails.add(jLabelDatabaseVersion, gridBagConstraints);

        databaseVersion.setText("XXX.XX.XX");
        databaseVersion.setPreferredSize(new java.awt.Dimension(0, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.weightx = 1.0;
        jPanelDetails.add(databaseVersion, gridBagConstraints);

        jLabelServer.setText("Server:");
        jLabelServer.setPreferredSize(new java.awt.Dimension(150, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelDetails.add(jLabelServer, gridBagConstraints);

        server.setText("XXXXXXXXXXXXXXXXX");
        server.setToolTipText("");
        server.setPreferredSize(new java.awt.Dimension(0, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanelDetails.add(server, gridBagConstraints);

        jLabelDBServerVersion.setText("Database Server Version:");
        jLabelDBServerVersion.setPreferredSize(new java.awt.Dimension(150, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelDetails.add(jLabelDBServerVersion, gridBagConstraints);

        jScrollPaneDBServerVersion.setPreferredSize(new java.awt.Dimension(3,
                70));
        dbServerVersion.setEditable(false);
        jScrollPaneDBServerVersion.setViewportView(dbServerVersion);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelDetails.add(jScrollPaneDBServerVersion, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelDetails.add(jLabelImage, gridBagConstraints);

        add(jPanelDetails);

        jPanelControls.setLayout(new java.awt.FlowLayout(
                java.awt.FlowLayout.CENTER, 5, 15));

        jButtonOK.setText("OK");
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        jPanelControls.add(jButtonOK);

        add(jPanelControls);

    }// GEN-END:initComponents

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonOKActionPerformed
        SwingUtil.setVisible(this, false);
    }// GEN-LAST:event_jButtonOKActionPerformed

    public static AboutView display(java.awt.event.FocusListener[] listeners) {

        if (view == null) {
            view = new AboutView();
            SwingUtil.add2Frame(view, listeners,
                    IMenuCommand.ABOUT.getSecond().toString(),
                    ViewSettings.getInstance().getViewImage(view.getClass().getName()),
                    true,
                    true,
                    false);
        }

        // view.updateView();
        SwingUtil.setVisible(view, true);

        return view;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel databaseVersion;

    private javax.swing.JLabel jLabelDatabaseVersion;

    private javax.swing.JTextPane dbServerVersion;

    private javax.swing.JLabel jLabelServer;

    private javax.swing.JPanel jPanelControls;

    private javax.swing.JLabel jLabelImage;

    private javax.swing.JButton jButtonOK;

    private javax.swing.JPanel jPanelDetails;

    private javax.swing.JLabel jLabelApplicationVersion;

    private javax.swing.JLabel server;

    private javax.swing.JScrollPane jScrollPaneDBServerVersion;

    private javax.swing.JLabel applicationVersion;

    private javax.swing.JLabel jLabelDBServerVersion;

    // End of variables declaration//GEN-END:variables

    public void updateView(Properties properties) {
        applicationVersion.setText(properties.getProperty(APPLICATION_VERSION, UNKNOWN));
        databaseVersion.setText(properties.getProperty(DATABASE_VERSION, UNKNOWN));
        dbServerVersion.setText(properties.getProperty(DATABASE_SERVER_VERSION, UNKNOWN));
    }

    public void setProperty(String key, String value) {
        if (APPLICATION_VERSION.equals(key))
            applicationVersion.setText(value);
        else if (DATABASE_VERSION.equals(key))
            databaseVersion.setText(value);
        else if (DATABASE_SERVER_VERSION.equals(key))
            dbServerVersion.setText(value);
    }
    
}
