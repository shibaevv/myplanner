/*
 * CapitalGainsTax.java
 *
 * Created on 19 December 2001, 18:28
 */

package au.com.totemsoft.myplanner.swing.projection;

/**
 * 
 * @author Valeri CHIBAEV (mailto:apollosoft.net.au@gmail.com)
 * @author Valeri CHIBAEV (mailto:apollosoft.net.au@gmail.com)
 */

import java.awt.event.FocusListener;

import au.com.totemsoft.myplanner.code.ModelType;
import au.com.totemsoft.myplanner.config.ViewSettings;
import au.com.totemsoft.myplanner.etc.ActionEventID;
import au.com.totemsoft.myplanner.projection.save.Model;
import au.com.totemsoft.swing.SwingUtil;

public class CGTView extends javax.swing.JPanel implements ActionEventID,
        javax.swing.event.ChangeListener {

    private static CGTView view;

    public Integer getDefaultType() {
        return ModelType.CGT_CALC;
    }

    public String getDefaultTitle() {
        return "New Capital Gains Tax Calculator";
    }

    /** Creates new form CapitalGainsTax */
    public CGTView() {
        initComponents();
    }

    public static boolean exists() {
        return view != null;
    }

    public static CGTView getInstance() {
        if (view == null)
            view = new CGTView();
        return view;
    }

    /**
     * javax.swing.event.ChangeListener interface
     */
    public void stateChanged(javax.swing.event.ChangeEvent changeEvent) {
        // updateNonEditable();
        // updateComponents();
        // updateChart();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {// GEN-BEGIN:initComponents
        bgCalculateTax = new javax.swing.ButtonGroup();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanelPurchaseDetails = new javax.swing.JPanel();
        jPanelPurchaseParcels = new javax.swing.JPanel();
        jScrollPanePurchaseParcels = new javax.swing.JScrollPane();
        jTablePurchaseParcels = new javax.swing.JTable();
        jPanelPurchaseParcelDetails = new javax.swing.JPanel();
        jPanelAssetPurcaseDetails = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField17 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jPanelAssetCostDetails = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jPanelSaleDetails = new javax.swing.JPanel();
        jPanelSaleParcels = new javax.swing.JPanel();
        jScrollPaneSaleParcels = new javax.swing.JScrollPane();
        jTableSaleParcels = new javax.swing.JTable();
        jPanelSaleParcelDetails = new javax.swing.JPanel();
        jPanelAssetSaleDetails = new javax.swing.JPanel();
        jTextField23 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jPanelParcelDetails = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jPanelCalculateTax = new javax.swing.JPanel();
        jRadioButtonCalculateTaxYES = new javax.swing.JRadioButton();
        jRadioButtonCalculateTaxNO = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanelCapitalGainDetails = new javax.swing.JPanel();
        jPanelSummaryPosition = new javax.swing.JPanel();
        jScrollPaneSummaryPosition = new javax.swing.JScrollPane();
        jTableSummaryPosition = new javax.swing.JTable();
        jPanelSummuryPositionDetails = new javax.swing.JPanel();
        jPanelCostBaseInfo = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jPanelGainLossCalcs = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jPanelControls = new javax.swing.JPanel();
        jPanelCloseSave = new javax.swing.JPanel();
        jButtonClose = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jPanelPreviousNext = new javax.swing.JPanel();
        jButtonPrevious = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        setPreferredSize(new java.awt.Dimension(650, 500));
        jTabbedPane.setPreferredSize(new java.awt.Dimension(10, 1500));
        jPanelPurchaseDetails.setLayout(new javax.swing.BoxLayout(
                jPanelPurchaseDetails, javax.swing.BoxLayout.Y_AXIS));

        jPanelPurchaseParcels.setLayout(new javax.swing.BoxLayout(
                jPanelPurchaseParcels, javax.swing.BoxLayout.X_AXIS));

        jPanelPurchaseParcels.setBorder(new javax.swing.border.TitledBorder(
                "Purchase Parcels"));
        jPanelPurchaseParcels
                .setPreferredSize(new java.awt.Dimension(463, 125));
        jScrollPanePurchaseParcels.setPreferredSize(new java.awt.Dimension(453,
                100));
        jScrollPanePurchaseParcels.setViewportView(jTablePurchaseParcels);

        jPanelPurchaseParcels.add(jScrollPanePurchaseParcels);

        jPanelPurchaseDetails.add(jPanelPurchaseParcels);

        jPanelPurchaseParcelDetails.setLayout(new javax.swing.BoxLayout(
                jPanelPurchaseParcelDetails, javax.swing.BoxLayout.X_AXIS));

        jPanelAssetPurcaseDetails.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;

        jPanelAssetPurcaseDetails
                .setBorder(new javax.swing.border.TitledBorder(
                        "Asset Purchase Details"));
        jPanelAssetPurcaseDetails.setPreferredSize(new java.awt.Dimension(345,
                125));
        jLabel1.setText("Date of Acquisition");
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetPurcaseDetails.add(jLabel1, gridBagConstraints1);

        jLabel2.setText("Asset Type");
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetPurcaseDetails.add(jLabel2, gridBagConstraints1);

        jLabel3.setText("Asset Name");
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetPurcaseDetails.add(jLabel3, gridBagConstraints1);

        jLabel5.setText("Purchase Share/Unit Price");
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 3;
        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetPurcaseDetails.add(jLabel5, gridBagConstraints1);

        jLabel4.setText("Shares/Units Purchased");
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 4;
        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetPurcaseDetails.add(jLabel4, gridBagConstraints1);

        jTextField14.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetPurcaseDetails.add(jTextField14, gridBagConstraints1);

        jComboBox1.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelAssetPurcaseDetails.add(jComboBox1, gridBagConstraints1);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelAssetPurcaseDetails.add(jTextField17, gridBagConstraints1);

        jTextField12.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 3;
        gridBagConstraints1.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetPurcaseDetails.add(jTextField12, gridBagConstraints1);

        jTextField15.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 4;
        gridBagConstraints1.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetPurcaseDetails.add(jTextField15, gridBagConstraints1);

        jPanelPurchaseParcelDetails.add(jPanelAssetPurcaseDetails);

        jPanelAssetCostDetails.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;

        jPanelAssetCostDetails.setBorder(new javax.swing.border.TitledBorder(
                "Asset Cost Details"));
        jLabel6.setText("Cost of Acquisition");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetCostDetails.add(jLabel6, gridBagConstraints2);

        jLabel9.setText("Costs of Ownership");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetCostDetails.add(jLabel9, gridBagConstraints2);

        jLabel7.setText("Costs to Increase Value");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetCostDetails.add(jLabel7, gridBagConstraints2);

        jLabel8.setText("Costs to Preserve Title");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 3;
        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetCostDetails.add(jLabel8, gridBagConstraints2);

        jLabel14.setText("Total Cost");
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 4;
        gridBagConstraints2.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetCostDetails.add(jLabel14, gridBagConstraints2);

        jTextField10.setEditable(false);
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 4;
        gridBagConstraints2.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelAssetCostDetails.add(jTextField10, gridBagConstraints2);

        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 3;
        gridBagConstraints2.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelAssetCostDetails.add(jTextField11, gridBagConstraints2);

        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelAssetCostDetails.add(jTextField16, gridBagConstraints2);

        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelAssetCostDetails.add(jTextField19, gridBagConstraints2);

        jTextField18.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelAssetCostDetails.add(jTextField18, gridBagConstraints2);

        jPanelPurchaseParcelDetails.add(jPanelAssetCostDetails);

        jPanelPurchaseDetails.add(jPanelPurchaseParcelDetails);

        jTabbedPane.addTab("Purchase Details", jPanelPurchaseDetails);

        jPanelSaleDetails.setLayout(new javax.swing.BoxLayout(
                jPanelSaleDetails, javax.swing.BoxLayout.Y_AXIS));

        jPanelSaleParcels.setLayout(new javax.swing.BoxLayout(
                jPanelSaleParcels, javax.swing.BoxLayout.X_AXIS));

        jPanelSaleParcels.setBorder(new javax.swing.border.TitledBorder(
                "Sale Parcels"));
        jScrollPaneSaleParcels
                .setPreferredSize(new java.awt.Dimension(453, 100));
        jScrollPaneSaleParcels.setViewportView(jTableSaleParcels);

        jPanelSaleParcels.add(jScrollPaneSaleParcels);

        jPanelSaleDetails.add(jPanelSaleParcels);

        jPanelSaleParcelDetails.setLayout(new javax.swing.BoxLayout(
                jPanelSaleParcelDetails, javax.swing.BoxLayout.X_AXIS));

        jPanelAssetSaleDetails.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;

        jPanelAssetSaleDetails.setBorder(new javax.swing.border.TitledBorder(
                "Asset Sale Details"));
        jTextField23.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 5;
        gridBagConstraints3.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetSaleDetails.add(jTextField23, gridBagConstraints3);

        jLabel18.setText("Costs of CGT Event");
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 5;
        gridBagConstraints3.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetSaleDetails.add(jLabel18, gridBagConstraints3);

        jLabel17.setText("Sale Price");
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 4;
        gridBagConstraints3.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetSaleDetails.add(jLabel17, gridBagConstraints3);

        jTextField22.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 4;
        gridBagConstraints3.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetSaleDetails.add(jTextField22, gridBagConstraints3);

        jTextField21.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 3;
        gridBagConstraints3.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetSaleDetails.add(jTextField21, gridBagConstraints3);

        jTextField20.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetSaleDetails.add(jTextField20, gridBagConstraints3);

        jLabel15.setText("Date of Sale");
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetSaleDetails.add(jLabel15, gridBagConstraints3);

        jLabel16.setText("Units/Shares Sold");
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 3;
        gridBagConstraints3.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetSaleDetails.add(jLabel16, gridBagConstraints3);

        jLabel19.setText("Asset Type");
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetSaleDetails.add(jLabel19, gridBagConstraints3);

        jLabel20.setText("Asset Name");
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAssetSaleDetails.add(jLabel20, gridBagConstraints3);

        jComboBox2.setPreferredSize(new java.awt.Dimension(130, 20));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelAssetSaleDetails.add(jComboBox2, gridBagConstraints3);

        jComboBox3.setPreferredSize(new java.awt.Dimension(130, 20));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelAssetSaleDetails.add(jComboBox3, gridBagConstraints3);

        jPanelSaleParcelDetails.add(jPanelAssetSaleDetails);

        jPanelParcelDetails.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints4;

        jPanelParcelDetails.setBorder(new javax.swing.border.TitledBorder(
                "Parcel Details"));
        jLabel21.setText("Results Displayed");
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.WEST;
        jPanelParcelDetails.add(jLabel21, gridBagConstraints4);

        jComboBox4.setPreferredSize(new java.awt.Dimension(130, 20));
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        jPanelParcelDetails.add(jComboBox4, gridBagConstraints4);

        jLabel26.setText("Parcel Usage");
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.WEST;
        jPanelParcelDetails.add(jLabel26, gridBagConstraints4);

        jComboBox5.setPreferredSize(new java.awt.Dimension(130, 20));
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        jPanelParcelDetails.add(jComboBox5, gridBagConstraints4);

        jPanelCalculateTax.setLayout(new javax.swing.BoxLayout(
                jPanelCalculateTax, javax.swing.BoxLayout.X_AXIS));

        jRadioButtonCalculateTaxYES.setText("Yes");
        bgCalculateTax.add(jRadioButtonCalculateTaxYES);
        jPanelCalculateTax.add(jRadioButtonCalculateTaxYES);

        jRadioButtonCalculateTaxNO.setText("No");
        bgCalculateTax.add(jRadioButtonCalculateTaxNO);
        jPanelCalculateTax.add(jRadioButtonCalculateTaxNO);

        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 2;
        gridBagConstraints4.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.WEST;
        jPanelParcelDetails.add(jPanelCalculateTax, gridBagConstraints4);

        jLabel10.setText("Calculate Tax");
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 2;
        gridBagConstraints4.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.WEST;
        jPanelParcelDetails.add(jLabel10, gridBagConstraints4);

        jLabel11.setText("Tax Rate");
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 3;
        gridBagConstraints4.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.WEST;
        jPanelParcelDetails.add(jLabel11, gridBagConstraints4);

        jTextField1.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 3;
        gridBagConstraints4.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.WEST;
        jPanelParcelDetails.add(jTextField1, gridBagConstraints4);

        jPanelSaleParcelDetails.add(jPanelParcelDetails);

        jPanelSaleDetails.add(jPanelSaleParcelDetails);

        jTabbedPane.addTab("Sale Details", jPanelSaleDetails);

        jPanelCapitalGainDetails.setLayout(new javax.swing.BoxLayout(
                jPanelCapitalGainDetails, javax.swing.BoxLayout.Y_AXIS));

        jPanelSummaryPosition.setLayout(new javax.swing.BoxLayout(
                jPanelSummaryPosition, javax.swing.BoxLayout.X_AXIS));

        jPanelSummaryPosition.setBorder(new javax.swing.border.TitledBorder(
                "Summary Position"));
        jScrollPaneSummaryPosition.setPreferredSize(new java.awt.Dimension(453,
                100));
        jScrollPaneSummaryPosition.setViewportView(jTableSummaryPosition);

        jPanelSummaryPosition.add(jScrollPaneSummaryPosition);

        jPanelCapitalGainDetails.add(jPanelSummaryPosition);

        jPanelSummuryPositionDetails.setLayout(new javax.swing.BoxLayout(
                jPanelSummuryPositionDetails, javax.swing.BoxLayout.X_AXIS));

        jPanelCostBaseInfo.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints5;

        jPanelCostBaseInfo.setBorder(new javax.swing.border.TitledBorder(
                "Cost Base Information"));
        jLabel12.setText("Discount Cost Base");
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints5.anchor = java.awt.GridBagConstraints.WEST;
        jPanelCostBaseInfo.add(jLabel12, gridBagConstraints5);

        jLabel13.setText("Indexed Cost Base");
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 1;
        gridBagConstraints5.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints5.anchor = java.awt.GridBagConstraints.WEST;
        jPanelCostBaseInfo.add(jLabel13, gridBagConstraints5);

        jLabel27.setText("Reduced Cost Base");
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 2;
        gridBagConstraints5.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints5.anchor = java.awt.GridBagConstraints.WEST;
        jPanelCostBaseInfo.add(jLabel27, gridBagConstraints5);

        jTextField2.setEditable(false);
        jTextField2.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelCostBaseInfo.add(jTextField2, gridBagConstraints5);

        jTextField3.setEditable(false);
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 1;
        gridBagConstraints5.gridy = 1;
        gridBagConstraints5.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelCostBaseInfo.add(jTextField3, gridBagConstraints5);

        jTextField4.setEditable(false);
        gridBagConstraints5 = new java.awt.GridBagConstraints();
        gridBagConstraints5.gridx = 1;
        gridBagConstraints5.gridy = 2;
        gridBagConstraints5.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelCostBaseInfo.add(jTextField4, gridBagConstraints5);

        jPanelSummuryPositionDetails.add(jPanelCostBaseInfo);

        jPanelGainLossCalcs.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints6;

        jPanelGainLossCalcs.setBorder(new javax.swing.border.TitledBorder(
                "Gain/Loss Calculations"));
        jLabel25.setText("Tax Payable");
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 3;
        gridBagConstraints6.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints6.anchor = java.awt.GridBagConstraints.WEST;
        jPanelGainLossCalcs.add(jLabel25, gridBagConstraints6);

        jTextField27.setEditable(false);
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 1;
        gridBagConstraints6.gridy = 3;
        gridBagConstraints6.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelGainLossCalcs.add(jTextField27, gridBagConstraints6);

        jLabel24.setText("Recommendation");
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 2;
        gridBagConstraints6.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints6.anchor = java.awt.GridBagConstraints.WEST;
        jPanelGainLossCalcs.add(jLabel24, gridBagConstraints6);

        jTextField26.setEditable(false);
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 1;
        gridBagConstraints6.gridy = 2;
        gridBagConstraints6.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelGainLossCalcs.add(jTextField26, gridBagConstraints6);

        jLabel23.setText("Indexation Result");
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 1;
        gridBagConstraints6.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints6.anchor = java.awt.GridBagConstraints.WEST;
        jPanelGainLossCalcs.add(jLabel23, gridBagConstraints6);

        jTextField25.setEditable(false);
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 1;
        gridBagConstraints6.gridy = 1;
        gridBagConstraints6.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelGainLossCalcs.add(jTextField25, gridBagConstraints6);

        jLabel22.setText("Discount Result");
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 0;
        gridBagConstraints6.insets = new java.awt.Insets(0, 0, 0, 5);
        gridBagConstraints6.anchor = java.awt.GridBagConstraints.WEST;
        jPanelGainLossCalcs.add(jLabel22, gridBagConstraints6);

        jTextField24.setEditable(false);
        jTextField24.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints6 = new java.awt.GridBagConstraints();
        gridBagConstraints6.gridx = 1;
        gridBagConstraints6.gridy = 0;
        gridBagConstraints6.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelGainLossCalcs.add(jTextField24, gridBagConstraints6);

        jPanelSummuryPositionDetails.add(jPanelGainLossCalcs);

        jPanelCapitalGainDetails.add(jPanelSummuryPositionDetails);

        jTabbedPane.addTab("Capital Gains", jPanelCapitalGainDetails);

        add(jTabbedPane);

        jPanelControls.setLayout(new javax.swing.BoxLayout(jPanelControls,
                javax.swing.BoxLayout.X_AXIS));

        jButtonClose.setText("Close");
        jPanelCloseSave.add(jButtonClose);

        jButtonSave.setText("Save");
        jPanelCloseSave.add(jButtonSave);

        jPanelControls.add(jPanelCloseSave);

        jButtonPrevious.setText("< Previous");
        jPanelPreviousNext.add(jButtonPrevious);

        jButtonNext.setText("Next >");
        jPanelPreviousNext.add(jButtonNext);

        jPanelControls.add(jPanelPreviousNext);

        add(jPanelControls);

    }// GEN-END:initComponents

    public static CGTView display(final Model model, FocusListener[] listeners) {

        boolean exists = CGTView.exists();

        CGTView view = CGTView.getInstance();
        if (!exists)
            SwingUtil.add2Frame(view, listeners,
                model == null ? view.getDefaultTitle() : model.getTitle(),
                ViewSettings.getInstance().getViewImage(
                view.getClass().getName()), true, true, false);

//        try { view.updateView( model ); }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        SwingUtil.setVisible(view, true);

        return view;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgCalculateTax;

    private javax.swing.JTabbedPane jTabbedPane;

    private javax.swing.JPanel jPanelPurchaseDetails;

    private javax.swing.JPanel jPanelPurchaseParcels;

    private javax.swing.JScrollPane jScrollPanePurchaseParcels;

    private javax.swing.JTable jTablePurchaseParcels;

    private javax.swing.JPanel jPanelPurchaseParcelDetails;

    private javax.swing.JPanel jPanelAssetPurcaseDetails;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JLabel jLabel5;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JTextField jTextField14;

    private javax.swing.JComboBox jComboBox1;

    private javax.swing.JTextField jTextField17;

    private javax.swing.JTextField jTextField12;

    private javax.swing.JTextField jTextField15;

    private javax.swing.JPanel jPanelAssetCostDetails;

    private javax.swing.JLabel jLabel6;

    private javax.swing.JLabel jLabel9;

    private javax.swing.JLabel jLabel7;

    private javax.swing.JLabel jLabel8;

    private javax.swing.JLabel jLabel14;

    private javax.swing.JTextField jTextField10;

    private javax.swing.JTextField jTextField11;

    private javax.swing.JTextField jTextField16;

    private javax.swing.JTextField jTextField19;

    private javax.swing.JTextField jTextField18;

    private javax.swing.JPanel jPanelSaleDetails;

    private javax.swing.JPanel jPanelSaleParcels;

    private javax.swing.JScrollPane jScrollPaneSaleParcels;

    private javax.swing.JTable jTableSaleParcels;

    private javax.swing.JPanel jPanelSaleParcelDetails;

    private javax.swing.JPanel jPanelAssetSaleDetails;

    private javax.swing.JTextField jTextField23;

    private javax.swing.JLabel jLabel18;

    private javax.swing.JLabel jLabel17;

    private javax.swing.JTextField jTextField22;

    private javax.swing.JTextField jTextField21;

    private javax.swing.JTextField jTextField20;

    private javax.swing.JLabel jLabel15;

    private javax.swing.JLabel jLabel16;

    private javax.swing.JLabel jLabel19;

    private javax.swing.JLabel jLabel20;

    private javax.swing.JComboBox jComboBox2;

    private javax.swing.JComboBox jComboBox3;

    private javax.swing.JPanel jPanelParcelDetails;

    private javax.swing.JLabel jLabel21;

    private javax.swing.JComboBox jComboBox4;

    private javax.swing.JLabel jLabel26;

    private javax.swing.JComboBox jComboBox5;

    private javax.swing.JPanel jPanelCalculateTax;

    private javax.swing.JRadioButton jRadioButtonCalculateTaxYES;

    private javax.swing.JRadioButton jRadioButtonCalculateTaxNO;

    private javax.swing.JLabel jLabel10;

    private javax.swing.JLabel jLabel11;

    private javax.swing.JTextField jTextField1;

    private javax.swing.JPanel jPanelCapitalGainDetails;

    private javax.swing.JPanel jPanelSummaryPosition;

    private javax.swing.JScrollPane jScrollPaneSummaryPosition;

    private javax.swing.JTable jTableSummaryPosition;

    private javax.swing.JPanel jPanelSummuryPositionDetails;

    private javax.swing.JPanel jPanelCostBaseInfo;

    private javax.swing.JLabel jLabel12;

    private javax.swing.JLabel jLabel13;

    private javax.swing.JLabel jLabel27;

    private javax.swing.JTextField jTextField2;

    private javax.swing.JTextField jTextField3;

    private javax.swing.JTextField jTextField4;

    private javax.swing.JPanel jPanelGainLossCalcs;

    private javax.swing.JLabel jLabel25;

    private javax.swing.JTextField jTextField27;

    private javax.swing.JLabel jLabel24;

    private javax.swing.JTextField jTextField26;

    private javax.swing.JLabel jLabel23;

    private javax.swing.JTextField jTextField25;

    private javax.swing.JLabel jLabel22;

    private javax.swing.JTextField jTextField24;

    private javax.swing.JPanel jPanelControls;

    private javax.swing.JPanel jPanelCloseSave;

    private javax.swing.JButton jButtonClose;

    private javax.swing.JButton jButtonSave;

    private javax.swing.JPanel jPanelPreviousNext;

    private javax.swing.JButton jButtonPrevious;

    private javax.swing.JButton jButtonNext;
    // End of variables declaration//GEN-END:variables

}