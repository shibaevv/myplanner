/*
 * UpdateManager.java
 *
 * Created on 20 March 2003, 14:15
 */

package com.argus.financials.ui.sql;

/**
 * 
 * @author valeri chibaev
 */

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.argus.financials.code.AdviserTypeCode;
import com.argus.financials.config.PropertySourceManager;
import com.argus.financials.config.ViewSettings;
import com.argus.financials.etc.Contact;
import com.argus.financials.etc.PersonName;
import com.argus.financials.exchange.ExportData;
import com.argus.financials.service.ServiceLocator;
import com.argus.financials.service.UserService;
import com.argus.financials.swing.SwingUtil;
import com.argus.financials.ui.CheckBoxList;

public class ExportImportManagerApp extends javax.swing.JPanel {

    public static final String DEVELOPER = "Developer";

    public static final String USER = "User";

    private javax.swing.JFileChooser jFileChooser;

    private CheckBoxList sqlList;

    private java.awt.CardLayout cardLayout;

    /** Creates new form UpdateManager */
    public ExportImportManagerApp(String cardName) {
        initComponents();

        jFileChooser = getFileChooser();

        sqlList = new CheckBoxList();
        // sqlList.setSelectionMode(
        // javax.swing.ListSelectionModel.SINGLE_SELECTION );
        sqlList
                .setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        // sqlList.setDragAndDropEnabled( true );
        jScrollPaneSQL.setViewportView(sqlList);

        addMouseListener(jTextAreaMessage, jPopupMenu);

        // //////////////////////////////////////////////////////////////////////
        jTree.setModel(new UserClientsTreeModel());

        javax.swing.JCheckBox cb = new javax.swing.JCheckBox();
        // cb.setHorizontalTextPosition( SwingConstants.LEADING );
        cb.setBackground(jTree.getBackground());
        cb.setIcon(new javax.swing.ImageIcon(getClass().getResource(
                "/image/Empty16.gif")));
        cb.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource(
                "/image/ComposeMail16.gif")));
        cb.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource(
                "/image/ComposeMail16.gif")));

        DefaultTreeCellRenderer renderer = new CheckBoxTreeCellRenderer(cb);
        // renderer.setLeafIcon( selectedIcon );
        jTree.setCellRenderer(renderer);

        DefaultTreeCellEditor editor = new CheckBoxTreeCellEditor(jTree,
                renderer, cb);
        jTree.setCellEditor(editor);

        jTree.setRootVisible(false);

        // //////////////////////////////////////////////////////////////////////
        cardLayout = (java.awt.CardLayout) getLayout();

        cardLayout.show(this, cardName);

    }

    private void printError(String error) {
        jTextAreaMessage.append(error + "\n");
    }

    private void addMouseListener(java.awt.Component comp,
            final javax.swing.JPopupMenu popupMenu) {
        comp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                showPopup(e);
            }

            public void mouseReleased(java.awt.event.MouseEvent e) {
                showPopup(e);
            }

            private void showPopup(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {// GEN-BEGIN:initComponents
        jPopupMenu = new javax.swing.JPopupMenu();
        jMenuItemClear = new javax.swing.JMenuItem();
        jPanelDeveloper = new javax.swing.JPanel();
        jPanelControls = new javax.swing.JPanel();
        jToolBarFile = new javax.swing.JToolBar();
        jButtonOpenFile = new javax.swing.JButton();
        jButtonSaveFile = new javax.swing.JButton();
        jToolBarSQL = new javax.swing.JToolBar();
        jButtonExport = new javax.swing.JButton();
        jButtonTransform = new javax.swing.JButton();
        jCheckBoxSelectAll = new javax.swing.JCheckBox();
        jButtonExecute = new javax.swing.JButton();
        jTabbedPane = new javax.swing.JTabbedPane();
        jScrollPaneXML = new javax.swing.JScrollPane();
        jTextAreaXML = new javax.swing.JTextArea();
        jScrollPaneSQL = new javax.swing.JScrollPane();
        jScrollPaneMessage = new javax.swing.JScrollPane();
        jTextAreaMessage = new javax.swing.JTextArea();
        jPanelUser = new javax.swing.JPanel();
        jScrollPaneTree = new javax.swing.JScrollPane();
        jTree = new javax.swing.JTree();
        jScrollPaneDetails = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanelUserControls = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jMenuItemClear.setText("Clear");
        jMenuItemClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClearActionPerformed(evt);
            }
        });

        jPopupMenu.add(jMenuItemClear);

        setLayout(new java.awt.CardLayout());

        setPreferredSize(new java.awt.Dimension(600, 370));
        jPanelDeveloper.setLayout(new java.awt.BorderLayout());

        jPanelControls.setLayout(new java.awt.FlowLayout(
                java.awt.FlowLayout.LEFT));

        jButtonOpenFile.setText("Open");
        jButtonOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenFileActionPerformed(evt);
            }
        });

        jToolBarFile.add(jButtonOpenFile);

        jButtonSaveFile.setText("Save");
        jButtonSaveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveFileActionPerformed(evt);
            }
        });

        jToolBarFile.add(jButtonSaveFile);

        jPanelControls.add(jToolBarFile);

        jButtonExport.setToolTipText("Export Client");
        jButtonExport.setText("Export");
        jButtonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportActionPerformed(evt);
            }
        });

        jToolBarSQL.add(jButtonExport);

        jButtonTransform.setToolTipText("Transform Exported Data");
        jButtonTransform.setText("Transform");
        jButtonTransform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTransformActionPerformed(evt);
            }
        });

        jToolBarSQL.add(jButtonTransform);

        jCheckBoxSelectAll.setToolTipText("SAelect All");
        jCheckBoxSelectAll.setText("All");
        jCheckBoxSelectAll.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxSelectAllItemStateChanged(evt);
            }
        });

        jToolBarSQL.add(jCheckBoxSelectAll);

        jButtonExecute.setToolTipText("Execute selected sql");
        jButtonExecute.setText("Execute");
        jButtonExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExecuteActionPerformed(evt);
            }
        });

        jToolBarSQL.add(jButtonExecute);

        jPanelControls.add(jToolBarSQL);

        jPanelDeveloper.add(jPanelControls, java.awt.BorderLayout.NORTH);

        jTextAreaXML.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jScrollPaneXML.setViewportView(jTextAreaXML);

        jTabbedPane.addTab("XML", jScrollPaneXML);

        jTabbedPane.addTab("SQL", jScrollPaneSQL);

        jPanelDeveloper.add(jTabbedPane, java.awt.BorderLayout.CENTER);

        jScrollPaneMessage.setPreferredSize(new java.awt.Dimension(3, 100));
        jTextAreaMessage.setEditable(false);
        jTextAreaMessage.setBackground(java.awt.Color.lightGray);
        jTextAreaMessage.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jScrollPaneMessage.setViewportView(jTextAreaMessage);

        jPanelDeveloper.add(jScrollPaneMessage, java.awt.BorderLayout.SOUTH);

        add(jPanelDeveloper, "Developer");

        jPanelUser.setLayout(new java.awt.BorderLayout());

        jScrollPaneTree.setPreferredSize(new java.awt.Dimension(200, 10));
        jTree.setEditable(true);
        jScrollPaneTree.setViewportView(jTree);

        jPanelUser.add(jScrollPaneTree, java.awt.BorderLayout.WEST);

        jScrollPaneDetails.setViewportView(jTextPane1);

        jPanelUser.add(jScrollPaneDetails, java.awt.BorderLayout.CENTER);

        jButton1.setText("Export");
        jPanelUserControls.add(jButton1);

        jButton2.setText("Import");
        jPanelUserControls.add(jButton2);

        jPanelUser.add(jPanelUserControls, java.awt.BorderLayout.SOUTH);

        add(jPanelUser, "User");

    }// GEN-END:initComponents

    private void jCheckBoxSelectAllItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_jCheckBoxSelectAllItemStateChanged
        sqlList.selectAll(evt.getStateChange() == evt.SELECTED);
    }// GEN-LAST:event_jCheckBoxSelectAllItemStateChanged

    private void jMenuItemClearActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItemClearActionPerformed
        jTextAreaMessage.setText("");
    }// GEN-LAST:event_jMenuItemClearActionPerformed

    private void jButtonExecuteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonExecuteActionPerformed
        try {
            doExecute();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            printError(e.getMessage());
        }
    }// GEN-LAST:event_jButtonExecuteActionPerformed

    private void jButtonTransformActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonTransformActionPerformed
        try {
            doTransform();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            printError(e.getMessage());
        }
    }// GEN-LAST:event_jButtonTransformActionPerformed

    private void jButtonSaveFileActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonSaveFileActionPerformed
        try {
            doSaveFile();
        } catch (java.io.IOException e) {
            e.printStackTrace(System.err);
            printError(e.getMessage());
        }
    }// GEN-LAST:event_jButtonSaveFileActionPerformed

    private void jButtonExportActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonExportActionPerformed
        try {
            doExport();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            printError(e.getMessage());
        }
    }// GEN-LAST:event_jButtonExportActionPerformed

    private void jButtonOpenFileActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonOpenFileActionPerformed
        try {
            doOpenFile();
        } catch (java.io.IOException e) {
            e.printStackTrace(System.err);
            printError(e.getMessage());
        }
    }// GEN-LAST:event_jButtonOpenFileActionPerformed

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String args[]) {

        String source = args.length > 0 ? args[0] : "financialPlanner.properties";

        // USER card can be used ONLY as part of another application with login
        // (valid UserService required)
        ExportImportManagerApp view = new ExportImportManagerApp(DEVELOPER);
        java.awt.Window w = (java.awt.Window) SwingUtil.add2Frame(view,
                (java.awt.event.FocusListener[]) null, "Export/Import Manager",
                ViewSettings.getInstance().getViewImage(
                        view.getClass().getName()), true, true, false);

        w.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                PropertySourceManager.getInstance().unload();
                System.exit(0);
            }
        });

        try {
            PropertySourceManager.getInstance().load(source);
            w.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            PropertySourceManager.getInstance().unload();
            System.exit(1);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPaneTree;

    private javax.swing.JButton jButtonExecute;

    private javax.swing.JToolBar jToolBarFile;

    private javax.swing.JPanel jPanelUser;

    private javax.swing.JScrollPane jScrollPaneXML;

    private javax.swing.JCheckBox jCheckBoxSelectAll;

    private javax.swing.JScrollPane jScrollPaneDetails;

    private javax.swing.JToolBar jToolBarSQL;

    private javax.swing.JPanel jPanelControls;

    private javax.swing.JPanel jPanelDeveloper;

    private javax.swing.JScrollPane jScrollPaneMessage;

    private javax.swing.JTabbedPane jTabbedPane;

    private javax.swing.JButton jButton2;

    private javax.swing.JButton jButton1;

    private javax.swing.JPopupMenu jPopupMenu;

    private javax.swing.JButton jButtonTransform;

    private javax.swing.JTextArea jTextAreaMessage;

    private javax.swing.JScrollPane jScrollPaneSQL;

    private javax.swing.JPanel jPanelUserControls;

    private javax.swing.JMenuItem jMenuItemClear;

    private javax.swing.JTextArea jTextAreaXML;

    private javax.swing.JTree jTree;

    private javax.swing.JButton jButtonExport;

    private javax.swing.JButton jButtonOpenFile;

    private javax.swing.JButton jButtonSaveFile;

    private javax.swing.JTextPane jTextPane1;

    // End of variables declaration//GEN-END:variables

    private javax.swing.JFileChooser getFileChooser() {

        if (jFileChooser == null) {
            jFileChooser = new javax.swing.JFileChooser();
            jFileChooser.setCurrentDirectory(new java.io.File("."));
            jFileChooser
                    .setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
            jFileChooser.setFileFilter(new com.argus.io.XMLFileFilter());
            jFileChooser.setDialogType(javax.swing.JFileChooser.OPEN_DIALOG);
            jFileChooser.setDialogTitle("Open XML file ...");
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

        // Open *.xml file
        String fileName = getFileName();
        if (fileName == null)
            return;

        if (exportData == null)
            exportData = new ExportData(
                    com.argus.financials.service.ServiceLocator.getInstance()
                            .getUserPersonID(),
                    com.argus.financials.service.ServiceLocator.getInstance()
                            .getClientPersonID());

        exportData.open(fileName);

        jTextAreaXML.setText(exportData.getExportData().toString());

    }

    private void doSaveFile() throws java.io.IOException {

        // Save *.xml file
        String fileName = getFileName();
        if (fileName == null)
            return;

        exportData.save(fileName);

    }

    private ExportData exportData;

    private void doExport() throws java.sql.SQLException, java.io.IOException {

        Integer userPersonID =
        // new Integer(31345); // SUPERVISOR
        new Integer(11815); // scot
        // com.argus.server.RmiParams.getInstance().getUserPersonID();
        Integer clientPersonID =
        // new Integer(32811); // Jim Ediot
        new Integer(68767); // Export Client
        // com.argus.server.RmiParams.getInstance().getClientPersonID();

        exportData = new ExportData(userPersonID, clientPersonID);

        exportData.execute();

        jTextAreaXML.setText(exportData.getExportData().toString());

    }

    private void doTransform() throws java.io.IOException {

        if (exportData == null)
            return;

        String xsl = null; // "com/argus/financials/xml/XMLtoSQLsp.xsl";

        sqlList.removeAllElements();
        java.util.Collection c = exportData.transformXML2SQL(xsl);
        java.util.Iterator iter = c.iterator();
        while (iter.hasNext())
            sqlList.addItem(iter.next());

    }

    private void doExecute() throws java.sql.SQLException {

        java.util.Collection sqlCollection = sqlList.getCheckedValues();
        if (sqlCollection == null || sqlCollection.size() == 0)
            sqlCollection = sqlList.getListData();

        String[] sql = (String[]) sqlCollection.toArray(new String[0]);
        // ( String [] ) sqlList.getSelectedValues();

        exportData.execute(sql);

    }

    private void test(String s) {
        com.argus.financials.strategy.StrategyGroup sg = new com.argus.financials.strategy.StrategyGroup();
        sg.setSerializedStrategyGroupData(s.getBytes());
        try {
            sg.getStrategyGroupData();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    // //////////////////////////////////////////////////////////////////////////
    //
    // //////////////////////////////////////////////////////////////////////////
    private static final javax.swing.border.Border noFocusBorder = new javax.swing.border.EmptyBorder(
            1, 1, 1, 1);

    class CheckBoxTreeCellRenderer extends DefaultTreeCellRenderer {

        private javax.swing.JCheckBox cb;

        CheckBoxTreeCellRenderer(javax.swing.JCheckBox cb) {
            super();
            this.cb = cb;
        }

        public java.awt.Component getTreeCellRendererComponent(
                javax.swing.JTree jTree, Object value, boolean selected,
                boolean expanded, boolean leaf, int row, boolean hasFocus) {
            UserClientsTreeModel.CheckBoxTreeNode node = (UserClientsTreeModel.CheckBoxTreeNode) value;
            cb.setText(value.toString());
            cb.setSelected(node.isSelected());

            cb
                    .setBackground(selected ? Color.lightGray : jTree
                            .getBackground());
            cb.setForeground(selected ? jTree.getForeground() : jTree
                    .getForeground());
            cb.setFocusPainted(false);
            cb.setBorderPainted(true);
            cb
                    .setBorder(selected ? UIManager
                            .getBorder("List.focusCellHighlightBorder")
                            : noFocusBorder);

            return cb;
        }

    }

    class CheckBoxTreeCellEditor extends DefaultTreeCellEditor {

        private javax.swing.JCheckBox cb;

        CheckBoxTreeCellEditor(JTree tree, DefaultTreeCellRenderer render,
                javax.swing.JCheckBox cb) {
            super(tree, render);
            this.cb = cb;
        }

        public Component getTreeCellEditorComponent(JTree tree, Object value,
                boolean isSelected, boolean expanded, boolean leaf, int row) {
            UserClientsTreeModel.CheckBoxTreeNode node = (UserClientsTreeModel.CheckBoxTreeNode) value;
            System.out.println("CheckBoxTreeCellEditor: " + value
                    + ", node.isSelected=" + node.isSelected()
                    + ", isSelected=" + isSelected + ", expanded=" + expanded
                    + ", leaf=" + leaf + ", row=" + row);
            node.setSelected(!node.isSelected());
            return cb;
        }
        /*
         * public void
         * addCellEditorListener(javax.swing.event.CellEditorListener
         * cellEditorListener) { }
         * 
         * public void cancelCellEditing() { }
         * 
         * public Object getCellEditorValue() { }
         * 
         * public java.awt.Component
         * getTreeCellEditorComponent(javax.swing.JTree jTree, Object obj,
         * boolean param, boolean param3, boolean param4, int param5) { }
         * 
         * public boolean isCellEditable(java.util.EventObject eventObject) { }
         * 
         * public void
         * removeCellEditorListener(javax.swing.event.CellEditorListener
         * cellEditorListener) { }
         * 
         * public boolean shouldSelectCell(java.util.EventObject eventObject) { }
         * 
         * public boolean stopCellEditing() { }
         */
    }

    class UserClientsTreeModel extends javax.swing.tree.DefaultTreeModel
    // , javax.swing.table.TableModelListener
    {
        private DefaultMutableTreeNode root;

        UserClientsTreeModel() {
            super(new DefaultMutableTreeNode("Loading..."));

            root = new CheckBoxTreeNode("Advisers");

            UserService userPerson = ServiceLocator.getInstance()
                    .getUserService();
            try {
                if (userPerson != null)
                    init(userPerson);
            } catch (com.argus.financials.service.ServiceException e) {
                e.printStackTrace(System.err);
            }

            setRoot(root);

        }

        private void init(UserService userPerson)
                throws com.argus.financials.service.ServiceException {

            HashMap selectionCriteria = new HashMap();
            if (AdviserTypeCode.isSupportPerson(userPerson
                    .getAdviserTypeCodeID()))
                selectionCriteria.put(UserService.ALL_USERS_CLIENTS,
                        Boolean.TRUE);
            else
                selectionCriteria.put(UserService.ADVISORID, userPerson
                        .getPrimaryKey());

            List<Contact> clients = userPerson.findClients(selectionCriteria);
            int size = clients == null ? 0 : clients.size();

            Map users = new TreeMap();

            for (int i = 0; i < size; i++) {
                Contact c = (Contact) clients.get(i);
                if (c == null || c.getName() == null
                        || c.getName().getFullName() == null)
                    continue;

                PersonName pn = c.getOwnerName();
                if (pn == null)
                    continue;

                DefaultMutableTreeNode user = (DefaultMutableTreeNode) users
                        .get(c.getOwnerName().getFullName());
                if (user == null) {
                    user = new CheckBoxTreeNode(c.getOwnerName());
                    users.put(c.getOwnerName().getFullName(), user);
                    root.add(user);
                }

                user.add(new CheckBoxTreeNode(c));

            }

        }

        public void addTreeModelListener(
                javax.swing.event.TreeModelListener treeModelListener) {
            super.addTreeModelListener(treeModelListener);
        }

        public void removeTreeModelListener(
                javax.swing.event.TreeModelListener treeModelListener) {
            super.removeTreeModelListener(treeModelListener);
        }

        public void valueForPathChanged(javax.swing.tree.TreePath treePath,
                Object obj) {
            super.valueForPathChanged(treePath, obj);
        }

        public Object getChild(Object obj, int param) {
            return super.getChild(obj, param);
        }

        public int getChildCount(Object obj) {
            return super.getChildCount(obj);
        }

        public int getIndexOfChild(Object obj, Object obj1) {
            return super.getIndexOfChild(obj, obj1);
        }

        public Object getRoot() {
            return super.getRoot();
        }

        public boolean isLeaf(Object obj) {
            return super.isLeaf(obj);
        }

        class CheckBoxTreeNode extends DefaultMutableTreeNode {

            private boolean selected = false;

            public CheckBoxTreeNode(Object userObject) {
                super(userObject);
            }

            public boolean isSelected() {
                return selected;
            }

            public void setSelected(boolean value) {
                selected = value;
            }

        }

    }

}
