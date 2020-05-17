/*
 * ExportImportDataView.java
 *
 * Created on 19 June 2003, 12:57
 */

package com.argus.financials.ui.sql;

/**
 * 
 * @author valeri chibaev
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.argus.bean.AbstractComponentModel;
import com.argus.bean.FTable;
import com.argus.bean.FTextArea;
import com.argus.bean.FTextField;
import com.argus.bean.MessageSent;
import com.argus.bean.MessageSentEvent;
import com.argus.bean.WizardContentHandler;
import com.argus.financials.api.bean.IClientView;
import com.argus.financials.api.service.UserService;
import com.argus.financials.domain.hibernate.ClientView;
import com.argus.financials.exchange.ExportData;
import com.argus.financials.swing.ICloseDialog;
import com.argus.financials.swing.SwingUtil;
import com.argus.financials.swing.table.SortedTableModel;
import com.argus.swing.SplashWindow;
import com.argus.swing.SwingUtils;
import com.argus.util.KeyValue;

public class ExportDataView
    extends com.argus.bean.BasePanel
    implements ICloseDialog
{

    private static final String WIZARD = "Wizard";

    private static final String STEP_1 = "1";

    private static final String STEP_2 = "2";

    private static final String STEP_3 = "3";

    private static final String STEP_4 = "4";

    public static final int CANCEL_OPTION = javax.swing.JOptionPane.CANCEL_OPTION;

    public static final int OK_OPTION = javax.swing.JOptionPane.OK_OPTION;

    private int result; // CANCEL_OPTION, OK_OPTION

    private Object[][] steps;

    private ExportComponentModel model;

    private WizardContentHandler wch;

    private static UserService userService;
    public static void setUserService(UserService userService) {
        ExportDataView.userService = userService;
    }

    /** Creates new form ExportDataView */
    public ExportDataView() {
        initComponents();

        tableStep1.setShowVerticalLines(false);

        steps = new Object[][] { { STEP_1, "Select Clients", jPanelStep1 },
                { STEP_2, "Select Destination", jPanelStep2 },
                { STEP_3, "Summary of Selection", jPanelStep3 },
                { STEP_4, "Summary of Export", jPanelStep4 }, };

        jLabelImage.setIcon(new javax.swing.ImageIcon(getClass().getResource(
                "/image/Export.gif")));

        model = new ExportComponentModel();
        addFormDataModel(model);
        model.addModelDataChangedListener(this);
        model.addMessageSentListener(this);

        // Add wizard content handler
        wch = new WizardContentHandler(jPanelCards);
        jPanelCards.setLayout(wch);

        for (int i = 0; i < steps.length; i++) {
            wch.addItem(new KeyValue(steps[i][1], steps[i][0]));
            wch.addLayoutComponent((java.awt.Component) steps[i][2],
                    steps[i][0]);
        }

        wch.setButtonNext(jButtonNext);
        wch.setButtonPrevious(jButtonPrevious);
        wch.setLabel(jLabelInfo);

        wch.addFormDataChangedListener(this);
        wch.registerComponent(WIZARD, this);

        jPanelSteps.add(wch.getPanel());

        addChangedListener();
        registerComponents();

        // reset to initial state
        reset();

    }

    private void addChangedListener() {

        ((FTable) tableStep1).addFormDataChangedListener(this);

        ((FTextField) textFieldStep2).addFormDataChangedListener(this);

        ((FTextArea) textAreaDetailsStep3).addFormDataChangedListener(this);

        ((FTextArea) textAreaDetailsStep4).addFormDataChangedListener(this);

    }

    private void registerComponents() {

        ((FTable) tableStep1).registerComponent(STEP_1, this);

        ((FTextField) textFieldStep2).registerComponent(STEP_2, this);

        ((FTextArea) textAreaDetailsStep3).registerComponent(STEP_3, this);

        ((FTextArea) textAreaDetailsStep4).registerComponent(STEP_4, this);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {// GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelNavigation = new javax.swing.JPanel();
        jLabelImage = new javax.swing.JLabel();
        jPanelSteps = new javax.swing.JPanel();
        jSeparatorVertical = new javax.swing.JSeparator();
        jPanelCenter = new javax.swing.JPanel();
        jPanelInfo = new javax.swing.JPanel();
        jLabelInfo = new javax.swing.JLabel();
        jPanelCards = new javax.swing.JPanel();
        jPanelStep1 = new javax.swing.JPanel();
        jScrollPaneStep1 = new javax.swing.JScrollPane();
        tableStep1 = new com.argus.bean.FTable();
        jPanelStep2 = new javax.swing.JPanel();
        textFieldStep2 = new FTextField();
        ((FTextField) textFieldStep2)
                .setFieldType(com.argus.bean.FTextField.ANY);
        jButtonStep2 = new javax.swing.JButton();
        jPanelStep3 = new javax.swing.JPanel();
        jScrollPaneDetailsStep3 = new javax.swing.JScrollPane();
        textAreaDetailsStep3 = new com.argus.bean.FTextArea();
        jPanelStep4 = new javax.swing.JPanel();
        jScrollPaneDetailsStep4 = new javax.swing.JScrollPane();
        textAreaDetailsStep4 = new com.argus.bean.FTextArea();
        jSeparatorHorizontal = new javax.swing.JSeparator();
        jPanelControls = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonReport = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButtonClose = new javax.swing.JButton();
        jButtonExport = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButtonPrevious = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));

        setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(10,
                10, 10, 10)));
        setPreferredSize(new java.awt.Dimension(600, 400));
        jPanelNavigation.setLayout(new javax.swing.BoxLayout(jPanelNavigation,
                javax.swing.BoxLayout.Y_AXIS));

        jPanelNavigation.setBorder(new javax.swing.border.EmptyBorder(
                new java.awt.Insets(1, 1, 1, 10)));
        jPanelNavigation.add(jLabelImage);

        jPanelSteps.setLayout(new javax.swing.BoxLayout(jPanelSteps,
                javax.swing.BoxLayout.Y_AXIS));

        jPanelSteps.setBorder(new javax.swing.border.EmptyBorder(
                new java.awt.Insets(10, 1, 1, 1)));
        jPanelNavigation.add(jPanelSteps);

        add(jPanelNavigation);

        jSeparatorVertical.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparatorVertical);

        jPanelCenter.setLayout(new javax.swing.BoxLayout(jPanelCenter,
                javax.swing.BoxLayout.Y_AXIS));

        jPanelCenter.setBorder(new javax.swing.border.EmptyBorder(
                new java.awt.Insets(1, 10, 10, 1)));
        jPanelInfo
                .setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabelInfo.setText("<html><h1>Info</h1><html>");
        jLabelInfo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelInfo.setFont(new java.awt.Font("Arial", 1, 24));
        jPanelInfo.add(jLabelInfo);

        jPanelCenter.add(jPanelInfo);

        jPanelCards.setLayout(new java.awt.CardLayout());

        jPanelCards.setBorder(new javax.swing.border.EmptyBorder(
                new java.awt.Insets(1, 1, 10, 1)));
        jPanelStep1.setLayout(new javax.swing.BoxLayout(jPanelStep1,
                javax.swing.BoxLayout.Y_AXIS));

        jScrollPaneStep1.setViewportView(tableStep1);

        jPanelStep1.add(jScrollPaneStep1);

        jPanelCards.add(jPanelStep1, "ExportStep1");

        jPanelStep2.setLayout(new java.awt.GridBagLayout());

        textFieldStep2.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelStep2.add(textFieldStep2, gridBagConstraints);

        jButtonStep2.setText("Browse");
        jButtonStep2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStep2ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        jPanelStep2.add(jButtonStep2, gridBagConstraints);

        jPanelCards.add(jPanelStep2, "ExportStep2");

        jPanelStep3.setLayout(new javax.swing.BoxLayout(jPanelStep3,
                javax.swing.BoxLayout.Y_AXIS));

        textAreaDetailsStep3.setEditable(false);
        textAreaDetailsStep3.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPaneDetailsStep3.setViewportView(textAreaDetailsStep3);

        jPanelStep3.add(jScrollPaneDetailsStep3);

        jPanelCards.add(jPanelStep3, "ExportStep3");

        jPanelStep4.setLayout(new javax.swing.BoxLayout(jPanelStep4,
                javax.swing.BoxLayout.Y_AXIS));

        textAreaDetailsStep4.setEditable(false);
        textAreaDetailsStep4.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPaneDetailsStep4.setViewportView(textAreaDetailsStep4);

        jPanelStep4.add(jScrollPaneDetailsStep4);

        jPanelCards.add(jPanelStep4, "ExportStep4");

        jPanelCenter.add(jPanelCards);

        jPanelCenter.add(jSeparatorHorizontal);

        jPanelControls.setLayout(new javax.swing.BoxLayout(jPanelControls,
                javax.swing.BoxLayout.X_AXIS));

        jButtonReport.setText("Report");
        jButtonReport.setDefaultCapable(false);
        jButtonReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReportActionPerformed(evt);
            }
        });

        jPanel2.add(jButtonReport);

        jPanelControls.add(jPanel2);

        jButtonClose.setText("Close");
        jButtonClose.setDefaultCapable(false);
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        jPanel3.add(jButtonClose);

        jButtonExport.setText("Export");
        jButtonExport.setDefaultCapable(false);
        jButtonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportActionPerformed(evt);
            }
        });

        jPanel3.add(jButtonExport);

        jPanelControls.add(jPanel3);

        jButtonPrevious.setText("Previous");
        jPanel4.add(jButtonPrevious);

        jButtonNext.setText("Next");
        jPanel4.add(jButtonNext);

        jPanelControls.add(jPanel4);

        jPanelCenter.add(jPanelControls);

        add(jPanelCenter);

    }// GEN-END:initComponents

    private void jButtonReportActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonReportActionPerformed
        // new Thread( new Runnable() { public void run() {
        doReport();
        // } }, "doReport" ).start();
    }// GEN-LAST:event_jButtonReportActionPerformed

    private void jButtonExportActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonExportActionPerformed
        // new Thread( new Runnable() { public void run() {
        doExport();
        // } }, "doExport" ).start();
    }// GEN-LAST:event_jButtonExportActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonCloseActionPerformed
        // result = closeDialog(this);
        doClose(null);
    }// GEN-LAST:event_jButtonCloseActionPerformed

    private void jButtonStep2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonStep2ActionPerformed
        String dir = "";
        if (getFileChooserStep2().showOpenDialog(SwingUtil.getJFrame(this)) == JFileChooser.APPROVE_OPTION)
            dir = fileChooserStep2.getSelectedFile().getPath();
        /*
         * textFieldStep2.setText( dir ); SwingUtilities.invokeLater( new
         * Runnable() { public void run() { // first
         * textFieldStep2.requestFocus();
         * 
         * SwingUtilities.invokeLater( new Runnable() { public void run() {
         * textFieldStep2.transferFocus(); // need to be syncronized with first
         * invokeLater() } } ); } } );
         */
        ((FTextField) textFieldStep2).setText(dir, true);

    }// GEN-LAST:event_jButtonStep2ActionPerformed

    public void doSave(java.awt.event.ActionEvent evt) {

    }

    public boolean isModified() {
        return model.isModified();
    }

    public static int closeDialog(ICloseDialog view) {

        int result;
        if (view.isModified()) {
            result = JOptionPane
                    .showConfirmDialog(
                            view instanceof java.awt.Component ? (java.awt.Component) view
                                    : null,
                            "Do you want to save data before closing?",
                            "Close dialog", JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                view.doSave(null);
                result = OK_OPTION;
                view.doClose(null);

            } else if (result == JOptionPane.NO_OPTION) {
                result = OK_OPTION;
                view.doClose(null);

            } else {
                result = CANCEL_OPTION;
                // keep open
            }

        } else {
            result = OK_OPTION;
            view.doClose(null);
        }

        return result;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparatorHorizontal;

    private javax.swing.JButton jButtonStep2;

    private javax.swing.JTable tableStep1;

    private javax.swing.JPanel jPanelCards;

    private javax.swing.JTextArea textAreaDetailsStep4;

    private javax.swing.JTextArea textAreaDetailsStep3;

    private javax.swing.JTextField textFieldStep2;

    private javax.swing.JPanel jPanelControls;

    private javax.swing.JPanel jPanelSteps;

    private javax.swing.JButton jButtonClose;

    private javax.swing.JLabel jLabelImage;

    private javax.swing.JPanel jPanelInfo;

    private javax.swing.JSeparator jSeparatorVertical;

    private javax.swing.JButton jButtonReport;

    private javax.swing.JScrollPane jScrollPaneStep1;

    private javax.swing.JButton jButtonNext;

    private javax.swing.JButton jButtonPrevious;

    private javax.swing.JPanel jPanelStep4;

    private javax.swing.JPanel jPanelNavigation;

    private javax.swing.JPanel jPanelStep3;

    private javax.swing.JPanel jPanelStep2;

    private javax.swing.JPanel jPanelStep1;

    private javax.swing.JPanel jPanel4;

    private javax.swing.JPanel jPanel3;

    private javax.swing.JPanel jPanelCenter;

    private javax.swing.JPanel jPanel2;

    private javax.swing.JButton jButtonExport;

    private javax.swing.JScrollPane jScrollPaneDetailsStep4;

    private javax.swing.JScrollPane jScrollPaneDetailsStep3;

    private javax.swing.JLabel jLabelInfo;

    // End of variables declaration//GEN-END:variables

    /*
     * class ExportFile extends Object {
     * 
     * private java.io.File file;
     * 
     * ExportFile( java.io.File file ) { this.file = file; }
     * 
     * public String toString() { return file.getName(); }
     * 
     * public String getCanonicalPath() throws java.io.IOException { return
     * file.getCanonicalPath(); }
     *  }
     */

    // //////////////////////////////////////////////////////////////////////////
    // EXPORT STEP 1 //
    // //////////////////////////////////////////////////////////////////////////
    private TableModelStep1 tableModelStep1;

    private TableModelStep1 getTableModelStep1(boolean refresh) {

        if (refresh)
            tableModelStep1 = null;

        if (tableModelStep1 != null)
            return tableModelStep1;

        try {
            Map<String, Object> criteria = new HashMap<String, Object>();
            List<? extends IClientView> clients = userService.findClients(criteria, 0, -1);
            int size = clients == null ? 0 : clients.size();
            Vector data = new Vector(size);
            for (IClientView c : clients) {
                java.util.Vector row = new java.util.Vector();
                row.add(Boolean.FALSE); // Selected
                row.add(c); // ClientView
                row.add(c.getOwnerShortName()); // Adviser
                if (STRATEGY < COLUMNS)
                    row.add(Boolean.FALSE); // Strategy
                data.add(row);
            }
            tableModelStep1 = new TableModelStep1(data);
        } catch (com.argus.financials.api.ServiceException e) {
            e.printStackTrace(System.err);
        }
        return tableModelStep1;
    }

    private static final int SELECTED = 0;

    private static final int CLIENT = 1;

    private static final int USER = 2;

    private static final int STRATEGY = 3;

    private static final int COLUMNS = 3; // no strategy selection

    static class TableModelStep1 extends DefaultTableModel {

        private static Vector columns = new Vector();
        static {
            columns.add("Selected");
            columns.add("ClientView");
            columns.add("Adviser");
            if (STRATEGY < COLUMNS)
                columns.add("Strategy");
        }

        public TableModelStep1(Vector data) {
            super(data, columns);
        }

        private Class[] types = new Class[] { java.lang.Boolean.class,
                java.lang.String.class, java.lang.String.class,
                java.lang.Boolean.class };

        public Class getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return (columnIndex == SELECTED) // Selected
                    || (columnIndex == STRATEGY && Boolean.TRUE
                            .equals(getValueAt(rowIndex, SELECTED)));
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

            super.setValueAt(aValue, rowIndex, columnIndex);

            switch (columnIndex) {
            case (SELECTED):
                if (STRATEGY < COLUMNS && Boolean.FALSE.equals(aValue))
                    setValueAt(Boolean.FALSE, rowIndex, STRATEGY);
                break;
            case (CLIENT):
                ;
            case (USER):
                ;
            case (STRATEGY):
                ;
            }

        }

        public boolean isSelected() {
            for (int r = 0; r < getRowCount(); r++)
                if (Boolean.TRUE.equals(getValueAt(r, SELECTED)))
                    return true;
            return false;
        }

        public Collection getSelected() {
            Collection c = new ArrayList();
            for (int r = 0; r < getRowCount(); r++)
                if (Boolean.TRUE.equals(getValueAt(r, SELECTED)))
                    c.add(getValueAt(r, CLIENT));
            return c;
        }

    }

    // //////////////////////////////////////////////////////////////////////////
    // EXPORT STEP 2 //
    // //////////////////////////////////////////////////////////////////////////
    private javax.swing.JFileChooser fileChooserStep2;

    private javax.swing.JFileChooser getFileChooserStep2() {

        if (fileChooserStep2 != null)
            return fileChooserStep2;

        fileChooserStep2 = new javax.swing.JFileChooser();
        SwingUtils.setDefaultFont(fileChooserStep2);
        fileChooserStep2
                .setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        fileChooserStep2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        return fileChooserStep2;

    }

    // //////////////////////////////////////////////////////////////////////////
    // EXPORT STEP 3 //
    // //////////////////////////////////////////////////////////////////////////
    protected void doExport() {

        final String dir = textFieldStep2.getText()
                + java.io.File.separatorChar;
        if (dir == null || dir.length() == 0)
            return;

        java.awt.Window w = SwingUtilities.windowForComponent(this);
        final SplashWindow splash = new SplashWindow(null,
                w instanceof java.awt.Frame ? (java.awt.Frame) w : null);
        splash.setStringPainted("Waiting for import to start ...");
        splash.setVisible(true);

        new Thread(new Runnable() {
            public void run() {
                new Thread(splash, "SplashWindow").start();
                splash
                        .setStringPainted("Importing Data File(s)...   Please Wait...");

                try {

                    StringBuffer sb = new StringBuffer();
                    ExportData exportData = new ExportData();
                    try {
                        sb.append("Export Summary\n");
                        sb
                                .append("--------------------------------------------------------------\n");

                        int i = 0;
                        Iterator iter = tableModelStep1.getSelected().iterator();
                        while (iter.hasNext()) {
                            ClientView c = (ClientView) iter.next();
                            String fileName = c.getSurname() + c.getFirstname() + ".xml";
                            String msg = "" + ++i + "). Exporting "
                                    + c.getShortName() + " into " + "'"
                                    + fileName + "'";
                            sb.append("\t" + msg);
                            splash.setStringPainted(msg);
                            exportData.exportFile(c.getOwnerId().intValue(), c.getId().intValue(), dir + fileName);
                            sb.append("\tOK\n");
                        }
                        sb.append("SUCCESS");
                    } catch (Exception e) {
                        sb.append("FAILED\t" + e.getMessage());
                        model.putValue(WIZARD, STEP_3);
                        e.printStackTrace(System.err);
                        return;
                    } finally {
                        ((FTextArea) textAreaDetailsStep4).setText(sb.toString(), true);
                    }
                    model.putValue(WIZARD, STEP_4);
                    model.sendNotification(new Object());
                } finally {
                    splash.close();
                }
            }
        }, "doExport").start();

    }

    // //////////////////////////////////////////////////////////////////////////
    // EXPORT STEP 4 //
    // //////////////////////////////////////////////////////////////////////////
    protected void doReport() {

        try {
            System.out.println("ExportDataView::doReport()");
            /*
             * ReportFields.generateReport( SwingUtilities.windowForComponent(
             * this ), getReportData( getPerson() ), getDefaultReport() );
             */
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

    // //////////////////////////////////////////////////////////////////////////
    // MODEL
    // //////////////////////////////////////////////////////////////////////////
    public void reset() {

        jButtonExport.setEnabled(false);

        jButtonReport.setEnabled(false);
        jButtonReport.setVisible(false);

        // reset model data
        model.reset();

        // init client's table model ( export list )
        SortedTableModel sortedTableModel = new SortedTableModel(
                getTableModelStep1(true));
        tableStep1.setModel(sortedTableModel);

        TableColumnModel tcm = tableStep1.getColumnModel();
        tcm.getColumn(SELECTED).setPreferredWidth(50);
        tcm.getColumn(SELECTED).setMinWidth(20);
        tcm.getColumn(SELECTED).setMaxWidth(50);
        tcm.getColumn(CLIENT).setPreferredWidth(200);
        tcm.getColumn(USER).setPreferredWidth(100);

        sortedTableModel.addMouseListenerToHeaderInTable(tableStep1);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // finaly show first card
                wch.show(jPanelCards, STEP_1);
            }
        });

    }

    public class ExportComponentModel extends AbstractComponentModel {

        public static final String ENABLE = "Export.Enable=true";

        public static final String DISABLE = "Export.Enable=false";

        public void reset() {
            super.reset();

            putValue(WIZARD, STEP_1);

            sendNotification(new Object());
            validate("");
            setModified(false);

        }

        public void calculate(String whoIsChanged) {
            // System.out.println( "ExportComponentModel::calculate( " +
            // whoIsChanged + " )" );

            String step = getValue(WIZARD);
            // System.out.println( "\tstep=" + step );

            if (tableModelStep1 != null) {
                int count = 0;
                StringBuffer summary = new StringBuffer();
                summary.append("Export Data File(s) into '" + textFieldStep2.getText() + "' directory\n");
                summary.append("--------------------------------------------------------------\n");
                Iterator iter = tableModelStep1.getSelected().iterator();
                while (iter.hasNext()) {
                    ClientView c = (ClientView) iter.next();
                    summary.append(++count + "). ");
                    summary.append(c.getShortName());
                    summary.append("\t");
                    summary.append("'" + c.getSurname() + c.getFirstname() + ".xml'");
                    summary.append("\n");
                }
                putValue(STEP_3, summary.toString());
            }
        }

        public boolean validate(String whoIsChanged) {
            // System.out.println( "ExportComponentModel::validate( " +
            // whoIsChanged + " )" );

            if (tableModelStep1 == null || !tableModelStep1.isSelected()) {
                putValue(WIZARD, STEP_1); // FAILED
                return true;
            }
            putValue(WIZARD, STEP_2); // SUCCESS

            if (getValue(STEP_2).length() == 0) {
                putValue(WIZARD, STEP_2); // FAILED
                return true;
            }
            putValue(WIZARD, STEP_3); // SUCCESS

            String step = getValue(WIZARD);
            // System.out.println( "\tstep=" + step );

            if (STEP_3.equals(step) || STEP_4.equals(step))
                sendMessage(whoIsChanged, MessageSent.INFO, ENABLE);
            else
                sendMessage(whoIsChanged, MessageSent.INFO, DISABLE);

            return true;

        }

    }

    // //////////////////////////////////////////////////////////////////////////
    // Override base class methods here
    // //////////////////////////////////////////////////////////////////////////
    public void messageSent(MessageSentEvent e) {

        if (ExportComponentModel.ENABLE.equals(e.getMessage()))
            jButtonExport.setEnabled(true);
        else if (ExportComponentModel.DISABLE.equals(e.getMessage()))
            jButtonExport.setEnabled(false);

    }

}
