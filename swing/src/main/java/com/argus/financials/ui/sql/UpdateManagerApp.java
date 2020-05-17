/*
 * UpdateManager.java
 *
 * Created on 20 March 2003, 14:15
 */

package com.argus.financials.ui.sql;

import com.argus.dao.SQLHelper;
import com.argus.financials.config.PropertySourceManager;

public class UpdateManagerApp extends javax.swing.JFrame {

    private transient static SQLHelper sqlHelper;
    public static void setSqlHelper(SQLHelper sqlHelper) {
        UpdateManagerApp.sqlHelper = sqlHelper;
    }

    /** Creates new form UpdateManager */
    public UpdateManagerApp() {
        initComponents();

        jFileChooser = getFileChooser();

    }

    private void printError(String error) {
        jTextAreaMessage.append(error + "\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {// GEN-BEGIN:initComponents
        jPanelControls = new javax.swing.JPanel();
        jToolBarFile = new javax.swing.JToolBar();
        jButtonOpenFile = new javax.swing.JButton();
        jToolBarSQL = new javax.swing.JToolBar();
        jButtonExecute = new javax.swing.JButton();
        jButtonBackup = new javax.swing.JButton();
        jSplitPane = new javax.swing.JSplitPane();
        jScrollPaneSQL = new javax.swing.JScrollPane();
        jTextAreaSQL = new javax.swing.JTextArea();
        jScrollPaneMessage = new javax.swing.JScrollPane();
        jTextAreaMessage = new javax.swing.JTextArea();

        jPanelControls.setLayout(new java.awt.FlowLayout(
                java.awt.FlowLayout.LEFT));

        jButtonOpenFile.setText("Open");
        jButtonOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenFileActionPerformed(evt);
            }
        });

        jToolBarFile.add(jButtonOpenFile);

        jPanelControls.add(jToolBarFile);

        jButtonExecute.setText("Execute");
        jButtonExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExecuteActionPerformed(evt);
            }
        });

        jToolBarSQL.add(jButtonExecute);

        jButtonBackup.setText("Backup");
        jButtonBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackupActionPerformed(evt);
            }
        });

        jToolBarSQL.add(jButtonBackup);

        jPanelControls.add(jToolBarSQL);

        getContentPane().add(jPanelControls, java.awt.BorderLayout.NORTH);

        jSplitPane.setDividerLocation(300);
        jSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jTextAreaSQL.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jScrollPaneSQL.setViewportView(jTextAreaSQL);

        jSplitPane.setLeftComponent(jScrollPaneSQL);

        jScrollPaneMessage.setPreferredSize(new java.awt.Dimension(3, 70));
        jTextAreaMessage.setEditable(false);
        jTextAreaMessage.setBackground(java.awt.Color.lightGray);
        jTextAreaMessage.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jScrollPaneMessage.setViewportView(jTextAreaMessage);

        jSplitPane.setRightComponent(jScrollPaneMessage);

        getContentPane().add(jSplitPane, java.awt.BorderLayout.CENTER);

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
                .getScreenSize();
        setSize(new java.awt.Dimension(600, 400));
        setLocation((screenSize.width - 600) / 2, (screenSize.height - 400) / 2);
    }// GEN-END:initComponents

    private void jButtonBackupActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonBackupActionPerformed
        try {
            doBackup();
        } catch (Exception e) {
            printError(e.getMessage());
        }
    }// GEN-LAST:event_jButtonBackupActionPerformed

    private void jButtonExecuteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonExecuteActionPerformed
        try {
            doExecute();
        } catch (Exception e) {
            printError(e.getMessage());
        }
    }// GEN-LAST:event_jButtonExecuteActionPerformed

    private void jButtonOpenFileActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonOpenFileActionPerformed
        try {
            doOpenFile();
        } catch (java.io.IOException e) {
            printError(e.getMessage());
        }
    }// GEN-LAST:event_jButtonOpenFileActionPerformed

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String args[]) {
        String source = args.length > 0 ? args[0] : "financialPlanner.properties";

        UpdateManagerApp um = new UpdateManagerApp();
        um.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                PropertySourceManager.getInstance().unload();
                System.exit(0);
            }
        });

        try {
            PropertySourceManager.getInstance().load(source);
            um.show();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            PropertySourceManager.getInstance().unload();
            System.exit(1);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExecute;

    private javax.swing.JToolBar jToolBarFile;

    private javax.swing.JToolBar jToolBarSQL;

    private javax.swing.JButton jButtonBackup;

    private javax.swing.JPanel jPanelControls;

    private javax.swing.JScrollPane jScrollPaneMessage;

    private javax.swing.JTextArea jTextAreaSQL;

    private javax.swing.JSplitPane jSplitPane;

    private javax.swing.JTextArea jTextAreaMessage;

    private javax.swing.JScrollPane jScrollPaneSQL;

    private javax.swing.JButton jButtonOpenFile;

    // End of variables declaration//GEN-END:variables

    private javax.swing.JFileChooser jFileChooser;

    private javax.swing.JFileChooser getFileChooser() {

        if (jFileChooser == null) {
            jFileChooser = new javax.swing.JFileChooser();
            jFileChooser.setCurrentDirectory(new java.io.File("."));
            jFileChooser
                    .setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
            jFileChooser.setFileFilter(new com.argus.io.SQLFileFilter());
            jFileChooser.setDialogType(javax.swing.JFileChooser.OPEN_DIALOG);
            jFileChooser.setDialogTitle("Open SQL file ...");
        }
        return jFileChooser;

    }

    private String getFileName() {

        if (jFileChooser.showOpenDialog(this) != javax.swing.JFileChooser.APPROVE_OPTION)
            return null;

        try {
            String fileName = jFileChooser.getSelectedFile().getCanonicalPath();
            return fileName;
        } catch (java.io.IOException e) {
            printError(e.getMessage());
            return null;
        }

    }

    private void doOpenFile() throws java.io.IOException {

        // Open *.sql file
        String fileName = getFileName();
        if (fileName == null)
            return;

        java.io.FileInputStream is = new java.io.FileInputStream(fileName);
        try {
            int b;
            StringBuffer sb = new StringBuffer();
            while ((b = is.read()) != -1)
                sb.append((char) b);
            jTextAreaSQL.setText(sb.toString());

        } finally {
            is.close();
        }

    }

    private void doExecute() throws java.sql.SQLException, java.io.IOException {

        String sqlText = jTextAreaSQL.getSelectedText();
        if (sqlText == null || sqlText.trim().length() == 0)
            sqlText = jTextAreaSQL.getText();

        java.util.Vector data = parse(sqlText);

        java.sql.Connection con = sqlHelper.getConnection();
        try {

            java.util.Iterator iter = data.iterator();
            while (iter.hasNext()) {
                String s = (String) iter.next();
                System.out.println(s);
                System.out.println();
                java.sql.Statement stmt = con.createStatement();
                int count = stmt.executeUpdate(s);
            }

            con.commit();

        } catch (java.sql.SQLException e) {
            con.rollback();
            throw e;
        }

    }

    private java.util.Vector parse(String sqlText) throws java.io.IOException {

        java.util.Vector data = new java.util.Vector();

        java.io.BufferedReader reader = new java.io.BufferedReader(
                new java.io.StringReader(sqlText));

        String line;
        String sql = "";
        while ((line = reader.readLine()) != null) {
            // check for comments
            int index = line.indexOf("--");
            if (index == 0)
                continue;
            if (index > 0)
                line = line.substring(0, index);

            sql += " " + line + " ";

            // check for end of statement
            index = sql.indexOf(";");
            if (index < 0)
                index = sql.indexOf(" GO ");
            if (index < 0)
                continue;

            data.addElement(sql.substring(0, index));
            sql = "";

        }

        return data;

    }

    private void doBackup() throws java.sql.SQLException {

        java.sql.Connection con = sqlHelper.getConnection();
        try {
            /*
             * BACKUP DATABASE MyPlanner TO DISK = 'C:\BACKUP\MyPlanner_001.dat' WITH
             * FORMAT, NAME = 'Full Backup of MyPlanner' GO
             */
            con.commit();

        } catch (java.sql.SQLException e) {
            con.rollback();
            throw e;
        }

    }

}
