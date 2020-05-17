/*
 * QuickQuest.java
 *
 * Created on 4 December 2001, 13:34
 */

package com.argus.financials.ui.iso;

import com.argus.financials.api.ServiceException;

/**
 * 
 * @author valeri chibaev
 * 
 */

import com.argus.financials.code.InvestmentStrategyCode;
import com.argus.financials.code.InvestmentStrategyData;
import com.argus.financials.etc.GrowthRate;
import com.argus.financials.etc.Survey;
import com.argus.financials.projection.AssetAllocationView;
import com.argus.financials.swing.PercentInputVerifier;
import com.argus.financials.swing.SwingUtil;
import com.argus.format.Percent;

public class QuickQuest extends javax.swing.JPanel implements
        javax.swing.event.ChangeListener {
    private Survey _survey = null;

    private Integer investmentStrategyCodeID = null;

    private AssetAllocationView assetAllocationView;

    /** Creates new form QuickQuest */
    public QuickQuest() {
        initComponents();
        initComponents2();
    }

    private void initComponents2() {

        jLabelShowInvestmentStrategy.setCursor(java.awt.Cursor
                .getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {// GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelQuestionSummary = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanelStrategyData = new javax.swing.JPanel();
        jPanelSelectInvestmentStrategyManual = new javax.swing.JPanel();
        jComboBoxSelectInvestmentStrategyManual = new javax.swing.JComboBox(
                new InvestmentStrategyCode().getCodeDescriptions());
        jPanelRiskRating = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldRiskProfile = new javax.swing.JTextField();
        jTextFieldLTIncomeReturn = new javax.swing.JTextField();
        jTextFieldLTReturn = new javax.swing.JTextField();
        jTextFieldLTGrowthReturn = new javax.swing.JTextField();
        jTextFieldROO1Year = new javax.swing.JTextField();
        jTextFieldROO3Years = new javax.swing.JTextField();
        jTextFieldDefensiveAsset = new javax.swing.JTextField();
        jTextFieldGrowthAsset = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelShowInvestmentStrategy = new javax.swing.JLabel();
        jPanelAssetAllocation = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextFieldCash = new javax.swing.JTextField();
        jTextFieldFixedInterest = new javax.swing.JTextField();
        jTextFieldAusShares = new javax.swing.JTextField();
        jTextFieldIntShares = new javax.swing.JTextField();
        jTextFieldProperty = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabelTotal = new javax.swing.JLabel();
        jTextFieldTotal = new javax.swing.JTextField();

        setLayout(new java.awt.GridLayout(1, 2));

        jPanelQuestionSummary.setLayout(new javax.swing.BoxLayout(
                jPanelQuestionSummary, javax.swing.BoxLayout.X_AXIS));

        jPanelQuestionSummary.setBorder(new javax.swing.border.TitledBorder(
                "Risk Evaluation Questionnaire Answers"));
        jScrollPane1.setViewportView(jTable);

        jPanelQuestionSummary.add(jScrollPane1);

        add(jPanelQuestionSummary);

        jPanelStrategyData.setLayout(new javax.swing.BoxLayout(
                jPanelStrategyData, javax.swing.BoxLayout.Y_AXIS));

        jPanelSelectInvestmentStrategyManual
                .setLayout(new java.awt.GridBagLayout());

        jPanelSelectInvestmentStrategyManual
                .setBorder(new javax.swing.border.TitledBorder(
                        "Manually Select Risk Profile"));
        jComboBoxSelectInvestmentStrategyManual
                .addItemListener(new java.awt.event.ItemListener() {
                    public void itemStateChanged(java.awt.event.ItemEvent evt) {
                        jComboBoxSelectInvestmentStrategyManualItemStateChanged(evt);
                    }
                });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        jPanelSelectInvestmentStrategyManual.add(
                jComboBoxSelectInvestmentStrategyManual, gridBagConstraints);

        jPanelStrategyData.add(jPanelSelectInvestmentStrategyManual);

        jPanelRiskRating.setLayout(new java.awt.GridBagLayout());

        jPanelRiskRating.setBorder(new javax.swing.border.TitledBorder(
                "Risk Profile Results"));
        jLabel20.setText("Risk Profile Rating");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanelRiskRating.add(jLabel20, gridBagConstraints);

        jLabel21.setText("Income Return");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanelRiskRating.add(jLabel21, gridBagConstraints);

        jLabel22.setText("Total Return (Long Term Return)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 5);
        jPanelRiskRating.add(jLabel22, gridBagConstraints);

        jLabel23.setText("Capital Growth Return");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanelRiskRating.add(jLabel23, gridBagConstraints);

        jLabel25.setText("Defensive Asset %");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanelRiskRating.add(jLabel25, gridBagConstraints);

        jLabel24.setText("Growth Asset %");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanelRiskRating.add(jLabel24, gridBagConstraints);

        jTextFieldRiskProfile.setEditable(false);
        jTextFieldRiskProfile.setBackground(java.awt.Color.lightGray);
        jTextFieldRiskProfile
                .setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        jPanelRiskRating.add(jTextFieldRiskProfile, gridBagConstraints);

        jTextFieldLTIncomeReturn.setEditable(false);
        jTextFieldLTIncomeReturn.setBackground(java.awt.Color.lightGray);
        jTextFieldLTIncomeReturn
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldLTIncomeReturn
                .setPreferredSize(new java.awt.Dimension(80, 21));
        jTextFieldLTIncomeReturn.setMinimumSize(new java.awt.Dimension(70, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelRiskRating.add(jTextFieldLTIncomeReturn, gridBagConstraints);

        jTextFieldLTReturn.setEditable(false);
        jTextFieldLTReturn.setBackground(java.awt.Color.lightGray);
        jTextFieldLTReturn
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldLTReturn.setInputVerifier(PercentInputVerifier.getInstance());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanelRiskRating.add(jTextFieldLTReturn, gridBagConstraints);

        jTextFieldLTGrowthReturn.setEditable(false);
        jTextFieldLTGrowthReturn.setBackground(java.awt.Color.lightGray);
        jTextFieldLTGrowthReturn
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelRiskRating.add(jTextFieldLTGrowthReturn, gridBagConstraints);

        jTextFieldROO1Year.setEditable(false);
        jTextFieldROO1Year.setBackground(java.awt.Color.lightGray);
        jTextFieldROO1Year
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelRiskRating.add(jTextFieldROO1Year, gridBagConstraints);

        jTextFieldROO3Years.setEditable(false);
        jTextFieldROO3Years.setBackground(java.awt.Color.lightGray);
        jTextFieldROO3Years
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelRiskRating.add(jTextFieldROO3Years, gridBagConstraints);

        jTextFieldDefensiveAsset.setEditable(false);
        jTextFieldDefensiveAsset.setBackground(java.awt.Color.lightGray);
        jTextFieldDefensiveAsset
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelRiskRating.add(jTextFieldDefensiveAsset, gridBagConstraints);

        jTextFieldGrowthAsset.setEditable(false);
        jTextFieldGrowthAsset.setBackground(java.awt.Color.lightGray);
        jTextFieldGrowthAsset
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelRiskRating.add(jTextFieldGrowthAsset, gridBagConstraints);

        jLabel26.setText("Range of Outcomes - 1yr");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanelRiskRating.add(jLabel26, gridBagConstraints);

        jLabel1.setText("Range of Outcomes in 2 out of 3 Years");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanelRiskRating.add(jLabel1, gridBagConstraints);

        jLabelShowInvestmentStrategy.setText("Show");
        jLabelShowInvestmentStrategy
                .setToolTipText("Display Risk Profile Rating");
        jLabelShowInvestmentStrategy
                .setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelShowInvestmentStrategy
                .setBorder(new javax.swing.border.EtchedBorder());
        jLabelShowInvestmentStrategy
                .addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jLabelShowInvestmentStrategyMouseClicked(evt);
                    }
                });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelRiskRating.add(jLabelShowInvestmentStrategy, gridBagConstraints);

        jPanelStrategyData.add(jPanelRiskRating);

        jPanelAssetAllocation.setLayout(new java.awt.GridBagLayout());

        jPanelAssetAllocation.setBorder(new javax.swing.border.TitledBorder(
                "Preferred Asset Allocation"));
        jLabel28.setText("Cash");
        jLabel28.setPreferredSize(new java.awt.Dimension(160, 17));
        jLabel28.setMinimumSize(new java.awt.Dimension(160, 17));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanelAssetAllocation.add(jLabel28, gridBagConstraints);

        jLabel29.setText("Listed Property");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanelAssetAllocation.add(jLabel29, gridBagConstraints);

        jTextFieldCash.setEditable(false);
        jTextFieldCash.setBackground(java.awt.Color.lightGray);
        jTextFieldCash.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldCash.setPreferredSize(new java.awt.Dimension(70, 21));
        jTextFieldCash.setMinimumSize(new java.awt.Dimension(70, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelAssetAllocation.add(jTextFieldCash, gridBagConstraints);

        jTextFieldFixedInterest.setEditable(false);
        jTextFieldFixedInterest.setBackground(java.awt.Color.lightGray);
        jTextFieldFixedInterest
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelAssetAllocation.add(jTextFieldFixedInterest, gridBagConstraints);

        jTextFieldAusShares.setEditable(false);
        jTextFieldAusShares.setBackground(java.awt.Color.lightGray);
        jTextFieldAusShares
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelAssetAllocation.add(jTextFieldAusShares, gridBagConstraints);

        jTextFieldIntShares.setEditable(false);
        jTextFieldIntShares.setBackground(java.awt.Color.lightGray);
        jTextFieldIntShares
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelAssetAllocation.add(jTextFieldIntShares, gridBagConstraints);

        jTextFieldProperty.setEditable(false);
        jTextFieldProperty.setBackground(java.awt.Color.lightGray);
        jTextFieldProperty
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelAssetAllocation.add(jTextFieldProperty, gridBagConstraints);

        jLabel31.setText("Fixed Interest");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanelAssetAllocation.add(jLabel31, gridBagConstraints);

        jLabel35.setText("Australian Shares");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanelAssetAllocation.add(jLabel35, gridBagConstraints);

        jLabel36.setText("International Shares");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanelAssetAllocation.add(jLabel36, gridBagConstraints);

        jLabelTotal.setText("Total");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 5);
        jPanelAssetAllocation.add(jLabelTotal, gridBagConstraints);

        jTextFieldTotal.setEditable(false);
        jTextFieldTotal.setBackground(java.awt.Color.lightGray);
        jTextFieldTotal.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
        jPanelAssetAllocation.add(jTextFieldTotal, gridBagConstraints);

        jPanelStrategyData.add(jPanelAssetAllocation);

        add(jPanelStrategyData);

    }// GEN-END:initComponents

    private void jComboBoxSelectInvestmentStrategyManualItemStateChanged(
            java.awt.event.ItemEvent evt) {// GEN-FIRST:event_jComboBoxSelectInvestmentStrategyManualItemStateChanged
        if (evt.getSource() != jComboBoxSelectInvestmentStrategyManual)
            return;
        if (evt.getStateChange() != evt.SELECTED)
            return;

        Integer id = new InvestmentStrategyCode().getCodeID((String) evt
                .getItem());

        // update view with values for the selected strategy
        setInvestmentStrategy(id);

    }// GEN-LAST:event_jComboBoxSelectInvestmentStrategyManualItemStateChanged

    private void jLabelShowInvestmentStrategyMouseClicked(
            java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabelShowInvestmentStrategyMouseClicked
        showInvestmentStrategy(investmentStrategyCodeID);
    }// GEN-LAST:event_jLabelShowInvestmentStrategyMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jTextFieldIntShares;

    private javax.swing.JTextField jTextFieldROO3Years;

    private javax.swing.JPanel jPanelAssetAllocation;

    private javax.swing.JPanel jPanelQuestionSummary;

    private javax.swing.JLabel jLabelShowInvestmentStrategy;

    private javax.swing.JTextField jTextFieldGrowthAsset;

    private javax.swing.JTextField jTextFieldAusShares;

    private javax.swing.JScrollPane jScrollPane1;

    private javax.swing.JTextField jTextFieldROO1Year;

    private javax.swing.JTextField jTextFieldRiskProfile;

    private javax.swing.JPanel jPanelRiskRating;

    private javax.swing.JTextField jTextFieldLTReturn;

    private javax.swing.JPanel jPanelStrategyData;

    private javax.swing.JPanel jPanelSelectInvestmentStrategyManual;

    private javax.swing.JLabel jLabel36;

    private javax.swing.JLabel jLabel35;

    private javax.swing.JTextField jTextFieldCash;

    private javax.swing.JLabel jLabel31;

    private javax.swing.JTextField jTextFieldLTIncomeReturn;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabelTotal;

    private javax.swing.JLabel jLabel29;

    private javax.swing.JTextField jTextFieldTotal;

    private javax.swing.JTextField jTextFieldDefensiveAsset;

    private javax.swing.JLabel jLabel28;

    private javax.swing.JTextField jTextFieldLTGrowthReturn;

    private javax.swing.JLabel jLabel26;

    private javax.swing.JTextField jTextFieldFixedInterest;

    private javax.swing.JTextField jTextFieldProperty;

    private javax.swing.JLabel jLabel25;

    private javax.swing.JLabel jLabel24;

    private javax.swing.JLabel jLabel23;

    private javax.swing.JLabel jLabel22;

    private javax.swing.JLabel jLabel21;

    private javax.swing.JLabel jLabel20;

    private javax.swing.JComboBox jComboBoxSelectInvestmentStrategyManual;

    private javax.swing.JTable jTable;

    // End of variables declaration//GEN-END:variables

    /**
     * Viewble interface
     */
    public Integer getObjectType() {
        return null;
    }

    public void updateView(com.argus.financials.service.PersonService person)
            throws ServiceException {
        // do nothing
        throw new RuntimeException("Use setObject(Survey) instead.");
    }

    public void saveView(com.argus.financials.service.PersonService person)
            throws ServiceException {

        // check if we have a recommended investment strategy
        if (investmentStrategyCodeID != null) {
            // yes, we have a recommended investment strategy,
            // set selected investment strategy and update jComboBox (for manual
            // selection)
            jComboBoxSelectInvestmentStrategyManual
                    .setSelectedItem(new InvestmentStrategyCode()
                            .getCodeDescription(investmentStrategyCodeID));
            // update Survey
            _survey.setSelectedInvestmentStrategy(investmentStrategyCodeID);
            jComboBoxSelectInvestmentStrategyManual.updateUI();
        } else {
            // no - do we have a manual selected strategy?
            String selected_investment_strategy = (String) this.jComboBoxSelectInvestmentStrategyManual
                    .getSelectedItem();

            if (selected_investment_strategy == null) {
                // no, we don't have a manual selected strategy, update Survey
                _survey
                        .setSelectedInvestmentStrategy(InvestmentStrategyCode.VALUE_NONE);
            } else {
                // yes, update Survey with the manual selected one
                _survey
                        .setSelectedInvestmentStrategy(new InvestmentStrategyCode()
                                .getCodeID(selected_investment_strategy));
            }
        }

    }

    public void clearView() {
        SwingUtil.clear(this);
    }

    public Object getObject() {
        return null;
    }

    public void setObject(Object value) {

        if (value == null) {
            clearView();
            return;
        }

        _survey = (Survey) value;
        _survey.setTableModel(jTable);

        // update risk rating
        setInvestmentStrategy(_survey.getSelectedInvestmentStrategy());

        _survey.addChangeListener(this);

    }

    /**
     * javax.swing.event.ChangeListener interface
     */
    public void stateChanged(javax.swing.event.ChangeEvent changeEvent) {
        setInvestmentStrategy(((Survey) changeEvent.getSource())
                .getSelectedInvestmentStrategy());
    }

    /**
     * 
     */
    private void setInvestmentStrategy(Integer value) {
        // reset investmentStrategyCodeID, neccessary because if a new person
        // survey is used, which don't have a selected investment strategy, than
        // we use the old one and we can't change the strategy manual!!!
        jTextFieldRiskProfile.setText("");
        jTextFieldLTIncomeReturn.setText("");
        jTextFieldLTGrowthReturn.setText("");
        jTextFieldLTReturn.setText("");
        jTextFieldROO1Year.setText("");
        jTextFieldROO3Years.setText("");
        jTextFieldDefensiveAsset.setText("");
        jTextFieldGrowthAsset.setText("");

        jTextFieldCash.setText("");
        jTextFieldFixedInterest.setText("");
        jTextFieldProperty.setText("");
        jTextFieldAusShares.setText("");
        jTextFieldIntShares.setText("");
        jTextFieldTotal.setText("");

        investmentStrategyCodeID = value;

        if (_survey != null) {
            _survey.setSelectedInvestmentStrategy(investmentStrategyCodeID);
            investmentStrategyCodeID = _survey.getSelectedInvestmentStrategy(); // can
                                                                                // be
                                                                                // changed
                                                                                // if
                                                                                // ALL
                                                                                // questions
                                                                                // answered
        }

        // activate/deactivate jComboBox for manual investment startegy
        // selection
        jComboBoxSelectInvestmentStrategyManual.setEnabled((_survey == null)
                || (_survey != null && !_survey.isReady()));

        jLabelShowInvestmentStrategy
                .setEnabled(investmentStrategyCodeID != null);

        if (investmentStrategyCodeID == null) {
            jTextFieldRiskProfile.setText(null);
            return;
        }

        InvestmentStrategyData isd = new InvestmentStrategyData();

        String strategy = isd.getCodeDescription(investmentStrategyCodeID);
        jTextFieldRiskProfile.setText(strategy);
        if (!strategy.equals(jComboBoxSelectInvestmentStrategyManual
                .getSelectedItem()))
            jComboBoxSelectInvestmentStrategyManual.setSelectedItem(strategy);

        double[] data = null;
        double[][] data2 = null;
        Percent percent = Percent.getPercentInstance();

        GrowthRate gr = isd.getGrowthRate(investmentStrategyCodeID);

        isd.getAllocationData2(investmentStrategyCodeID);
        jTextFieldLTIncomeReturn.setText(percent.toString(gr.getIncomeRate())
                + "%");
        jTextFieldLTGrowthReturn.setText(percent.toString(gr.getGrowthRate())
                + "%");
        jTextFieldLTReturn.setText(percent.toString(gr.getRate()) + "%");

        // { {-7.4, 23.6}, {0.4, 15.8}, {8.1, 8.1} } },
        data2 = isd.getAllocationHistData2(investmentStrategyCodeID);
        jTextFieldROO1Year
                .setText("" + data2[0][0] + "%.." + data2[1][0] + "%");
        jTextFieldROO3Years.setText("" + data2[0][1] + "%.." + data2[1][1]
                + "%");
        // jTextFieldROO5Years.setText(
        // data2[0][2] == data2[1][2] ? data2[0][2] + "%.." : "" + data2[0][2] +
        // "%.." + data2[1][2] + "%" );

        jTextFieldDefensiveAsset.setText(percent
                .toString(gr.getDefensiveRate()));
        jTextFieldGrowthAsset.setText(percent.toString(100 - gr
                .getDefensiveRate()));

        data = isd.getAllocationData(investmentStrategyCodeID);

        jTextFieldCash.setText(percent.toString(data[0]));
        jTextFieldFixedInterest.setText(percent.toString(data[1]));
        jTextFieldProperty.setText(percent.toString(data[2]));
        jTextFieldAusShares.setText(percent.toString(data[3]));
        jTextFieldIntShares.setText(percent.toString(data[4]));

        // calculate total in perecent
        // total = Cash + FixedInterest + Property + Australian Shares +
        // International Shares
        // !!! data array isn't initialized to 0.0, fields can contain
        // 1.7976931348623157E308 = infinte !!!
        double total = 0.0;
        // add only if we have a percent value
        for (int i = 0; i < data.length; i++) {
            if (data[i] >= 0.0 && data[i] <= 100.0) {
                total += data[i];
            }
        }
        jTextFieldTotal.setText(percent.toString(total));

    }

    public Integer getInvestmentStrategyCodeID() {
        return investmentStrategyCodeID;
    }

    private void showInvestmentStrategy(Integer investmentStrategyCodeID) {
        if (investmentStrategyCodeID == null)
            return;

        // investmentStrategyCodeID could have changed, so create a new window
        if (assetAllocationView != null) {
            SwingUtil.setVisible(assetAllocationView, false);
        }
        assetAllocationView = new AssetAllocationView(investmentStrategyCodeID);
        SwingUtil.add2Dialog(null, assetAllocationView.getViewCaption(), true,
                assetAllocationView);
    }

}
