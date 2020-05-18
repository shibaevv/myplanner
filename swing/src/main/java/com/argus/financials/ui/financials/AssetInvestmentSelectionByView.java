/*
 * AssetInvestmentSelectionView.java
 *
 * Created on 18 July 2002, 14:30
 */

package com.argus.financials.ui.financials;

import java.util.Vector;

import com.argus.financials.assetinvestment.AvailableInvestmentsSearch;
import com.argus.financials.assetinvestment.AvailableInvestmentsTableModel;
import com.argus.financials.assetinvestment.AvailableInvestmentsTableRow;
import com.argus.financials.swing.SwingUtil;

/**
 * 
 * @author shibaevv
 */
public class AssetInvestmentSelectionByView extends javax.swing.JPanel {

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {// GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroupSelectBy = new javax.swing.ButtonGroup();
        jPanelSelectBy = new javax.swing.JPanel();
        jRadioButtonDescription = new javax.swing.JRadioButton();
        jTextFieldDescription = new javax.swing.JTextField();
        jButtonDescriptionSearch = new javax.swing.JButton();
        jRadioButtonInvestmentCode = new javax.swing.JRadioButton();
        jTextFieldInvestmentCode = new javax.swing.JTextField();
        jButtonInvestmentCodeSearch = new javax.swing.JButton();
        jPanelAvailableInvestments = new javax.swing.JPanel();
        jScrollPaneAvailableInvestments = new javax.swing.JScrollPane();
        jTableAvailableInvestments = new javax.swing.JTable();
        // BEGIN: User Code
        // allow only one row to be selected
        jTableAvailableInvestments
                .setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        // END: User Code
        jPanelButtons = new javax.swing.JPanel();
        jButtonOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        setPreferredSize(new java.awt.Dimension(540, 400));
        setMinimumSize(new java.awt.Dimension(540, 400));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanelSelectBy.setLayout(new java.awt.GridBagLayout());

        jPanelSelectBy.setBorder(new javax.swing.border.TitledBorder(
                "Select by"));
        jRadioButtonDescription.setSelected(true);
        jRadioButtonDescription.setText("Description");
        buttonGroupSelectBy.add(jRadioButtonDescription);
        jRadioButtonDescription
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jRadioButtonDescriptionActionPerformed(evt);
                    }
                });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelSelectBy.add(jRadioButtonDescription, gridBagConstraints);

        jTextFieldDescription
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jTextFieldDescriptionActionPerformed(evt);
                    }
                });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanelSelectBy.add(jTextFieldDescription, gridBagConstraints);

        jButtonDescriptionSearch.setText("Search");
        jButtonDescriptionSearch
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButtonDescriptionSearchActionPerformed(evt);
                    }
                });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelSelectBy.add(jButtonDescriptionSearch, gridBagConstraints);

        jRadioButtonInvestmentCode.setText("Investment Code");
        buttonGroupSelectBy.add(jRadioButtonInvestmentCode);
        jRadioButtonInvestmentCode
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jRadioButtonInvestmentCodeActionPerformed(evt);
                    }
                });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelSelectBy.add(jRadioButtonInvestmentCode, gridBagConstraints);

        jTextFieldInvestmentCode
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jTextFieldInvestmentCodeActionPerformed(evt);
                    }
                });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanelSelectBy.add(jTextFieldInvestmentCode, gridBagConstraints);

        jButtonInvestmentCodeSearch.setText("Search");
        jButtonInvestmentCodeSearch
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButtonInvestmentCodeSearchActionPerformed(evt);
                    }
                });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelSelectBy.add(jButtonInvestmentCodeSearch, gridBagConstraints);

        add(jPanelSelectBy);

        jPanelAvailableInvestments.setLayout(new javax.swing.BoxLayout(
                jPanelAvailableInvestments, javax.swing.BoxLayout.X_AXIS));

        jPanelAvailableInvestments
                .setBorder(new javax.swing.border.TitledBorder(
                        "Available Investments"));
        jTableAvailableInvestments
                .setModel(new com.argus.financials.assetinvestment.AvailableInvestmentsTableModel());
        jTableAvailableInvestments
                .addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jTableAvailableInvestmentsMouseClicked(evt);
                    }
                });

        jScrollPaneAvailableInvestments
                .setViewportView(jTableAvailableInvestments);

        jPanelAvailableInvestments.add(jScrollPaneAvailableInvestments);

        add(jPanelAvailableInvestments);

        jButtonOK.setText("OK");
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        jPanelButtons.add(jButtonOK);

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jPanelButtons.add(jButtonCancel);

        add(jPanelButtons);

    }// GEN-END:initComponents

    private void jTableAvailableInvestmentsMouseClicked(
            java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jTableAvailableInvestmentsMouseClicked
        if (evt.getClickCount() == 2) {
            setVisible(false);
            result = OK_OPTION;
        }
    }// GEN-LAST:event_jTableAvailableInvestmentsMouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {// GEN-FIRST:event_formComponentShown
        result = CANCEL_OPTION;
    }// GEN-LAST:event_formComponentShown

    private void jButtonInvestmentCodeSearchActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonInvestmentCodeSearchActionPerformed
        // Add your handling code here:
        searchByInvestmentCode();
    }// GEN-LAST:event_jButtonInvestmentCodeSearchActionPerformed

    private void jButtonDescriptionSearchActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonDescriptionSearchActionPerformed
        // Add your handling code here:
        searchByDescription();
    }// GEN-LAST:event_jButtonDescriptionSearchActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonCancelActionPerformed
        // Add your handling code here:
        setVisible(false);
        result = CANCEL_OPTION; // by default
    }// GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonOKActionPerformed
        // Add your handling code here:
        setVisible(false);
        result = OK_OPTION;
    }// GEN-LAST:event_jButtonOKActionPerformed

    private void jTextFieldInvestmentCodeActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldInvestmentCodeActionPerformed
        // Add your handling code here:
    }// GEN-LAST:event_jTextFieldInvestmentCodeActionPerformed

    private void jRadioButtonInvestmentCodeActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButtonInvestmentCodeActionPerformed
        // Add your handling code here:
        changeSearch(INVESTMENT_CODE_SEARCH_ID);
    }// GEN-LAST:event_jRadioButtonInvestmentCodeActionPerformed

    private void jRadioButtonDescriptionActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButtonDescriptionActionPerformed
        // Add your handling code here:
        changeSearch(DESCRIPTION_SEARCH_ID);
    }// GEN-LAST:event_jRadioButtonDescriptionActionPerformed

    private void jTextFieldDescriptionActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldDescriptionActionPerformed
        // Add your handling code here:
    }// GEN-LAST:event_jTextFieldDescriptionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;

    private javax.swing.JButton jButtonDescriptionSearch;

    private javax.swing.JTable jTableAvailableInvestments;

    private javax.swing.JPanel jPanelAvailableInvestments;

    private javax.swing.JTextField jTextFieldInvestmentCode;

    private javax.swing.ButtonGroup buttonGroupSelectBy;

    private javax.swing.JPanel jPanelButtons;

    private javax.swing.JRadioButton jRadioButtonDescription;

    private javax.swing.JPanel jPanelSelectBy;

    private javax.swing.JButton jButtonOK;

    private javax.swing.JScrollPane jScrollPaneAvailableInvestments;

    private javax.swing.JButton jButtonInvestmentCodeSearch;

    private javax.swing.JRadioButton jRadioButtonInvestmentCode;

    private javax.swing.JTextField jTextFieldDescription;

    // End of variables declaration//GEN-END:variables

    private static AssetInvestmentSelectionByView view;

    private int result; // CANCEL_OPTION, OK_OPTION

    public static final int CANCEL_OPTION = javax.swing.JOptionPane.CANCEL_OPTION;

    public static final int OK_OPTION = javax.swing.JOptionPane.OK_OPTION;

    private static final int DESCRIPTION_SEARCH_ID = 1; // search by description

    private static final int INVESTMENT_CODE_SEARCH_ID = 2; // search by
                                                            // investment code

    private int current_search_type_id = 0; // the current search type

    private AvailableInvestmentsSearch _aiai_search = null; // the description
                                                            // and investment
                                                            // code search
                                                            // object

    private Vector searchResultTableRows = null; // contains all
                                                    // assets/investments which
                                                    // where found by the last
                                                    // search

    private int selectedRowNumber = -1; // the selected row of the available
                                        // assets/investment table

    /** Creates new form AssetInvestmentSelectionView */
    private AssetInvestmentSelectionByView() {
        super();
        initComponents();

        // register listener for selection of a row
        javax.swing.ListSelectionModel rowSM = this.jTableAvailableInvestments
                .getSelectionModel();
        rowSM
                .addListSelectionListener(new javax.swing.event.ListSelectionListener() {
                    public void valueChanged(
                            javax.swing.event.ListSelectionEvent e) {
                        // Ignore extra messages.
                        if (e.getValueIsAdjusting())
                            return;

                        javax.swing.ListSelectionModel lsm = (javax.swing.ListSelectionModel) e
                                .getSource();
                        if (lsm.isSelectionEmpty()) {
                            selectedRowNumber = -1;
                            // System.out.println("No rows are selected.");
                        } else {
                            selectedRowNumber = lsm.getMinSelectionIndex();
                            // System.out.println("Row " + selectedRowNumber + "
                            // is now selected.");
                        }
                    }
                });

        // create search object
        _aiai_search = new AvailableInvestmentsSearch();

        // set search to description search
        changeSearch(DESCRIPTION_SEARCH_ID);
    }

    public static AssetInvestmentSelectionByView getInstance() {
        if (view == null)
            view = new AssetInvestmentSelectionByView();
        else
            ; // view.initComponents2();

        view.result = CANCEL_OPTION;

        return view;
    }

    /**
     * Returns the caption of the view as a String.
     * 
     * @return the caption for the view
     */
    public String getViewCaption() {
        return "Investment Selection";
    }

    /**
     * Checks if a view exits (view is != null).
     * 
     * @return true if the view exists, else false
     */
    public static boolean exists() {
        return view != null;
    }

    /**
     * Returns the number of the selected option button (OK or Cancel)
     * 
     * CANCEL_OPTION: returns javax.swing.JOptionPane.CANCEL_OPTION OK_OPTION:
     * returns javax.swing.JOptionPane.OK_OPTION
     * 
     * @return the integer value of the selected button
     */
    public int getResult() {
        return result;
    }

    /**
     * Change the visible status of the view.
     * 
     * @param visible -
     *            true or false
     */
    public void setVisible(boolean visible) {
        // SwingUtils.setVisible( this, b );
        SwingUtil
                .add2Dialog(null, getViewCaption(), true, this, false, visible);
    }

    /**
     * @param search_type -
     *            type of the search
     */
    private void changeSearch(int search_type) {
        switch (search_type) {
        case DESCRIPTION_SEARCH_ID:
            // set current search type
            current_search_type_id = DESCRIPTION_SEARCH_ID;
            // enable description search input fields,
            // disable investment code search input fields
            changeDescriptionSearchEditableStatus(true);
            changeInvestmentCodeSearchEditableStatus(false);
            break;
        case INVESTMENT_CODE_SEARCH_ID:
            // set current search type
            current_search_type_id = INVESTMENT_CODE_SEARCH_ID;
            // enable investment code search input fields
            // disable description search input fields
            changeDescriptionSearchEditableStatus(false);
            changeInvestmentCodeSearchEditableStatus(true);
            break;
        default:
            // set current search type
            current_search_type_id = DESCRIPTION_SEARCH_ID;
            // enable description search input fields,
            // disable investment code search input fields
            changeDescriptionSearchEditableStatus(true);
            changeInvestmentCodeSearchEditableStatus(false);
            break;
        }
    }

    /**
     * Enables or disables all input fields for the investment code search.
     * 
     * @param enable -
     *            true or false
     */
    private void changeInvestmentCodeSearchEditableStatus(boolean enable) {
        jRadioButtonInvestmentCode.setSelected(enable);
        SwingUtil.setEnabled(jTextFieldInvestmentCode, enable);
        SwingUtil.setEnabled(jButtonInvestmentCodeSearch, enable);
    }

    /*
     * Enables or disables all input fields for the description search.
     * 
     * @param enable - true or false
     */
    private void changeDescriptionSearchEditableStatus(boolean enable) {
        jRadioButtonDescription.setSelected(enable);
        SwingUtil.setEnabled(jTextFieldDescription, enable);
        SwingUtil.setEnabled(jButtonDescriptionSearch, enable);
    }

    /**
     * Search in database tables "iress-asset" and "apir-pic" for all rows whose
     * description contains at least one of the keywords as part of any word in
     * the description column. The result Vector is sorted by the column
     * description.
     */
    private void searchByDescription() {
        String keywords = jTextFieldDescription.getText();

        // do we have some keywords?
        if (keywords != null && keywords.length() > 0) {
            searchResultTableRows = _aiai_search.searchByDescription(keywords);

            // update available investments table
            this.updateTableAvailableInvestments(searchResultTableRows);
        }
    }

    /**
     * Search in database tables "iress-asset" and "apir-pic" for all rows whose
     * investment code contains at least one of the keywords as part of any word
     * in the description column. The result Vector is sorted by the column
     * investment code.
     */
    private void searchByInvestmentCode() {
        String keywords = jTextFieldInvestmentCode.getText();

        // do we have some keywords?
        if (keywords != null && keywords.length() > 0) {
            searchResultTableRows = _aiai_search
                    .searchByInvestmentCode(keywords);

            // update available investments table
            this.updateTableAvailableInvestments(searchResultTableRows);
        }
    }

    /**
     * Updates the available asset/investment table's content (on screen too).
     * The methode needs a Vector of "AvailableInvestmentsTableRow" objects,
     * because the "AvailableInvestmentsTableModel" can only display this kind
     * of objects.
     * 
     * @param table_rows -
     *            a Vector which contains a rows of available assets/investments
     * @see com.argus.financials.assetinvestment.AvailableInvestmentsTableModel
     * @see com.argus.financials.assetinvestment.AvailableInvestmentsTableRow
     */
    private void updateTableAvailableInvestments(Vector table_rows) {
        jTableAvailableInvestments.setModel(new AvailableInvestmentsTableModel(
                table_rows));
        jScrollPaneAvailableInvestments
                .setViewportView(jTableAvailableInvestments);
    }

    /**
     * Get method for selectedRowNumber.
     * 
     * @return the number of the selected row in the "AvailableInvestmentsTable"
     */
    public int getSelectedRowNumber() {
        return this.selectedRowNumber;
    }

    /**
     * Get method for searchResultTableRows.
     * 
     * @return all assets/investments which where found by the last search
     * @see com.argus.financials.assetinvestment.AvailableInvestmentsTableRow
     */
    public Vector getSearchResultTableRows() {
        return this.searchResultTableRows;
    }

    /**
     * Get method for selected row.
     * 
     * @return returns the selected row in the "AvailableInvestmentsTable"
     */
    public AvailableInvestmentsTableRow getSelectedRow() {
        AvailableInvestmentsTableRow row = null;

        if (this.selectedRowNumber >= 0) {
            row = (AvailableInvestmentsTableRow) this.searchResultTableRows
                    .elementAt(this.selectedRowNumber);
        }

        return row;
    }

    /**
     * Search in database tables "iress-asset" and "apir-pic" for all rows whose
     * description contains at least one of the keywords as part of any word in
     * the description column. The result Vector is sorted by the column
     * description.
     * 
     * This methode is needed to populate the result Vector before displaying it
     * in a table. Normally this methode would be implemented as another
     * constructor!
     */
    public void searchByDescription(String keywords) {
        // update the search type and view
        changeSearch(DESCRIPTION_SEARCH_ID);
        jTextFieldDescription.setText(keywords);
        // update the textfield, if not, we will see only the right part of the
        // text
        jTextFieldDescription.updateUI();

        // do we have some keywords?
        if (keywords != null && keywords.length() > 0) {
            searchResultTableRows = _aiai_search.searchByDescription(keywords);

            // update available investments table
            this.updateTableAvailableInvestments(searchResultTableRows);
        }
    }

    /**
     * Search in database tables "iress-asset" and "apir-pic" for all rows whose
     * investment code contains at least one of the keywords as part of any word
     * in the description column. The result Vector is sorted by the column
     * investment code.
     * 
     * This methode is needed to populate the result Vector before displaying it
     * in a table. Normally this methode would be implemented as another
     * constructor!
     */
    public void searchByInvestmentCode(String keywords) {
        // update the search type and view
        changeSearch(INVESTMENT_CODE_SEARCH_ID);
        jTextFieldInvestmentCode.setText(keywords);
        // update the textfield, if not, we will see only the right part of the
        // text
        jTextFieldInvestmentCode.updateUI();

        // do we have some keywords?
        if (keywords != null && keywords.length() > 0) {
            searchResultTableRows = _aiai_search
                    .searchByInvestmentCode(keywords);

            // update available investments table
            this.updateTableAvailableInvestments(searchResultTableRows);
        }
    }

}
