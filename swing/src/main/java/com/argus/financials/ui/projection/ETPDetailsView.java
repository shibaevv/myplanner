/*
 * ETPRolloverView.java
 *
 * Created on 29 November 2001, 11:47
 */

package com.argus.financials.ui.projection;

/**
 * 
 * @author shibaevv
 * @version
 */

import java.math.BigDecimal;

import com.argus.financials.api.ETPConstants;
import com.argus.financials.etc.ActionEventID;
import com.argus.financials.projection.AllocatedPensionCalcNew;
import com.argus.financials.projection.MoneyCalc;
import com.argus.financials.swing.CurrencyInputVerifier;
import com.argus.financials.swing.SwingUtil;
import com.argus.format.Currency;

public class ETPDetailsView extends javax.swing.JPanel implements
        javax.swing.event.ChangeListener, ActionEventID {

    private int result = -1;

    protected AllocatedPensionCalcNew apCalc;

    private java.awt.event.FocusListener[] listeners;

    /** Creates new form apCalc */
    public ETPDetailsView() {
        this(new AllocatedPensionCalcNew());

    }

    public ETPDetailsView(MoneyCalc calc) {
        apCalc = (AllocatedPensionCalcNew) calc;

        initComponents();
        initComponents2();
    }

    private void initComponents2() {
        apCalc.addChangeListener(this);
        jTextFieldTotalAmountTotal.setText(Currency.getCurrencyInstance()
                .toString(apCalc.getTotalETPAmount()));
        updateETPComponents();
        if (apCalc.getPre() != null
                && !new BigDecimal(0).equals(apCalc.getPre())) {
            jTextFieldPre071983Total.setText(Currency.getCurrencyInstance()
                    .toString(apCalc.getPre()));
        } else {
            jTextFieldPre071983Total.setText(Currency.getCurrencyInstance()
                    .toString(apCalc.getPre071983Total()));

        }

        if (apCalc.getPostTaxed() != null
                && !new BigDecimal(0).equals(apCalc.getPostTaxed())) {
            jTextFieldPost061983TaxedTotal.setText(Currency
                    .getCurrencyInstance().toString(apCalc.getPostTaxed()));
        } else {
            jTextFieldPost061983TaxedTotal.setText(Currency
                    .getCurrencyInstance().toString(
                            apCalc.getPost061983TaxedTotal()));
        }

    }

    /**
     * javax.swing.event.ChangeListener interface
     */
    public void stateChanged(javax.swing.event.ChangeEvent changeEvent) {
        updateNonEditable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {// GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelETPAllocation = new javax.swing.JPanel();
        jLabelPreJuly1983 = new javax.swing.JLabel();
        jLabelPostJune1983Taxed = new javax.swing.JLabel();
        jLabelPostJune1983Untaxed = new javax.swing.JLabel();
        jLabelUndeducted = new javax.swing.JLabel();
        jLabelCGTExempt = new javax.swing.JLabel();
        jLabelExcess = new javax.swing.JLabel();
        jLabelConcessional = new javax.swing.JLabel();
        jLabelInvalidity = new javax.swing.JLabel();
        jLabelTotalAmount = new javax.swing.JLabel();
        jTextFieldPost061983UntaxedTotal = new javax.swing.JTextField();
        jTextFieldUndeductedTotal = new javax.swing.JTextField();
        jTextFieldCGTExemptTotal = new javax.swing.JTextField();
        jTextFieldExcessTotal = new javax.swing.JTextField();
        jTextFieldConcessionalTotal = new javax.swing.JTextField();
        jTextFieldInvalidityTotal = new javax.swing.JTextField();
        jTextFieldPre071983Total = new javax.swing.JTextField();
        jTextFieldPost061983TaxedTotal = new javax.swing.JTextField();
        jTextFieldTotalAmountTotal = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButtonOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(10,
                10, 10, 10)));
        jPanelETPAllocation.setLayout(new java.awt.GridBagLayout());

        jPanelETPAllocation.setBorder(new javax.swing.border.TitledBorder(
                "Adjust ETP"));
        jLabelPreJuly1983.setText("Pre 1 July 1983");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelETPAllocation.add(jLabelPreJuly1983, gridBagConstraints);

        jLabelPostJune1983Taxed.setText("Post 30 June 1983 - (Taxed)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelETPAllocation.add(jLabelPostJune1983Taxed, gridBagConstraints);

        jLabelPostJune1983Untaxed
                .setText("                             - (Untaxed)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelETPAllocation.add(jLabelPostJune1983Untaxed, gridBagConstraints);

        jLabelUndeducted.setText("Undeducted");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelETPAllocation.add(jLabelUndeducted, gridBagConstraints);

        jLabelCGTExempt.setText("CGT Exempt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelETPAllocation.add(jLabelCGTExempt, gridBagConstraints);

        jLabelExcess.setText("Excess");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelETPAllocation.add(jLabelExcess, gridBagConstraints);

        jLabelConcessional.setText("Concessional");
        jLabelConcessional.setPreferredSize(new java.awt.Dimension(140, 17));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelETPAllocation.add(jLabelConcessional, gridBagConstraints);

        jLabelInvalidity.setText("Invalidity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelETPAllocation.add(jLabelInvalidity, gridBagConstraints);

        jLabelTotalAmount.setText("Total Amount");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelETPAllocation.add(jLabelTotalAmount, gridBagConstraints);

        jTextFieldPost061983UntaxedTotal
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldPost061983UntaxedTotal.setInputVerifier(CurrencyInputVerifier
                .getInstance());
        jTextFieldPost061983UntaxedTotal
                .setNextFocusableComponent(jTextFieldUndeductedTotal);
        jTextFieldPost061983UntaxedTotal
                .addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        jTextFieldPost061983UntaxedTotalFocusLost(evt);
                    }
                });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelETPAllocation.add(jTextFieldPost061983UntaxedTotal,
                gridBagConstraints);

        jTextFieldUndeductedTotal
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldUndeductedTotal.setInputVerifier(CurrencyInputVerifier
                .getInstance());
        jTextFieldUndeductedTotal
                .setNextFocusableComponent(jTextFieldCGTExemptTotal);
        jTextFieldUndeductedTotal
                .addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        jTextFieldUndeductedTotalFocusLost(evt);
                    }
                });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelETPAllocation.add(jTextFieldUndeductedTotal, gridBagConstraints);

        jTextFieldCGTExemptTotal
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldCGTExemptTotal.setInputVerifier(CurrencyInputVerifier
                .getInstance());
        jTextFieldCGTExemptTotal
                .setNextFocusableComponent(jTextFieldExcessTotal);
        jTextFieldCGTExemptTotal
                .addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        jTextFieldCGTExemptTotalFocusLost(evt);
                    }
                });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelETPAllocation.add(jTextFieldCGTExemptTotal, gridBagConstraints);

        jTextFieldExcessTotal
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldExcessTotal.setInputVerifier(CurrencyInputVerifier
                .getInstance());
        jTextFieldExcessTotal
                .setNextFocusableComponent(jTextFieldConcessionalTotal);
        jTextFieldExcessTotal
                .addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        jTextFieldExcessTotalFocusLost(evt);
                    }
                });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelETPAllocation.add(jTextFieldExcessTotal, gridBagConstraints);

        jTextFieldConcessionalTotal
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldConcessionalTotal.setPreferredSize(new java.awt.Dimension(90,
                20));
        jTextFieldConcessionalTotal.setInputVerifier(CurrencyInputVerifier
                .getInstance());
        jTextFieldConcessionalTotal
                .setNextFocusableComponent(jTextFieldInvalidityTotal);
        jTextFieldConcessionalTotal
                .addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        jTextFieldConcessionalTotalFocusLost(evt);
                    }
                });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelETPAllocation
                .add(jTextFieldConcessionalTotal, gridBagConstraints);

        jTextFieldInvalidityTotal
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldInvalidityTotal.setInputVerifier(CurrencyInputVerifier
                .getInstance());
        jTextFieldInvalidityTotal
                .addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        jTextFieldInvalidityTotalFocusLost(evt);
                    }
                });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelETPAllocation.add(jTextFieldInvalidityTotal, gridBagConstraints);

        jTextFieldPre071983Total.setEditable(false);
        jTextFieldPre071983Total
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldPre071983Total
                .setPreferredSize(new java.awt.Dimension(90, 21));
        jTextFieldPre071983Total.setMinimumSize(new java.awt.Dimension(90, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelETPAllocation.add(jTextFieldPre071983Total, gridBagConstraints);

        jTextFieldPost061983TaxedTotal.setEditable(false);
        jTextFieldPost061983TaxedTotal
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelETPAllocation.add(jTextFieldPost061983TaxedTotal,
                gridBagConstraints);

        jTextFieldTotalAmountTotal.setEditable(false);
        jTextFieldTotalAmountTotal
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelETPAllocation.add(jTextFieldTotalAmountTotal, gridBagConstraints);

        add(jPanelETPAllocation);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButtonOK.setText("OK");
        jButtonOK.setDefaultCapable(false);
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 10);
        jPanel1.add(jButtonOK, gridBagConstraints);

        jButtonCancel.setText("Cancel");
        jButtonCancel.setDefaultCapable(false);
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel1.add(jButtonCancel, gridBagConstraints);

        add(jPanel1);

    }// GEN-END:initComponents

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonOKActionPerformed
        // Add your handling code here:
        SwingUtil.setVisible(this, false);
        result = ETPConstants.OK_OPTION;

    }// GEN-LAST:event_jButtonOKActionPerformed

    private void jTextFieldPost061983UntaxedTotalFocusLost(
            java.awt.event.FocusEvent evt) {// GEN-FIRST:event_jTextFieldPost061983UntaxedTotalFocusLost
        // Add your handling code here:
        String previousValue = Currency.getCurrencyInstance().toString(
                apCalc.getPost061983UntaxedTotal());

        BigDecimal d = null;
        String value = jTextFieldPost061983UntaxedTotal.getText().trim();
        if (value != null && value.length() > 0)
            d = Currency.getCurrencyInstance().getBigDecimalValue(value);

        jTextFieldPost061983UntaxedTotal
                .setText(jTextFieldPost061983UntaxedTotal.getText().trim());
        apCalc.setPost061983UntaxedTotal(d);

        if (!apCalc.validateETPComponentInput(d)) {
            jTextFieldPost061983UntaxedTotal.setText(previousValue);
            apCalc.setPost061983UntaxedTotal(Currency.getCurrencyInstance()
                    .getBigDecimalValue(previousValue));
            jTextFieldPost061983UntaxedTotal.requestFocus();
        }

    }// GEN-LAST:event_jTextFieldPost061983UntaxedTotalFocusLost

    private void jTextFieldInvalidityTotalFocusLost(
            java.awt.event.FocusEvent evt) {// GEN-FIRST:event_jTextFieldInvalidityTotalFocusLost
        // Add your handling code here:
        String previousValue = Currency.getCurrencyInstance().toString(
                apCalc.getInvalidityTotal());

        BigDecimal d = null;
        String value = jTextFieldInvalidityTotal.getText().trim();
        if (value != null && value.length() > 0)
            d = Currency.getCurrencyInstance().getBigDecimalValue(value);

        jTextFieldInvalidityTotal.setText(value);
        apCalc.setInvalidityTotal(d);

        if (!apCalc.validateETPComponentInput(d)) {
            jTextFieldInvalidityTotal.setText(previousValue);
            apCalc.setInvalidityTotal(Currency.getCurrencyInstance()
                    .getBigDecimalValue(previousValue));
            jTextFieldInvalidityTotal.requestFocus();
        }

    }// GEN-LAST:event_jTextFieldInvalidityTotalFocusLost

    private void jTextFieldConcessionalTotalFocusLost(
            java.awt.event.FocusEvent evt) {// GEN-FIRST:event_jTextFieldConcessionalTotalFocusLost
        // Add your handling code here:
        String previousValue = Currency.getCurrencyInstance().toString(
                apCalc.getConcessionalTotal());

        BigDecimal d = null;
        String value = jTextFieldConcessionalTotal.getText().trim();
        if (value != null && value.length() > 0)
            d = Currency.getCurrencyInstance().getBigDecimalValue(value);

        jTextFieldConcessionalTotal.setText(value);
        apCalc.setConcessionalTotal(d);

        if (!apCalc.validateETPComponentInput(d)) {
            jTextFieldConcessionalTotal.setText(previousValue);
            apCalc.setConcessionalTotal(Currency.getCurrencyInstance()
                    .getBigDecimalValue(previousValue));
            jTextFieldConcessionalTotal.requestFocus();

        }

    }// GEN-LAST:event_jTextFieldConcessionalTotalFocusLost

    private void jTextFieldExcessTotalFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_jTextFieldExcessTotalFocusLost
        // Add your handling code here:
        String previousValue = Currency.getCurrencyInstance().toString(
                apCalc.getExcessTotal());

        BigDecimal d = null;
        String value = jTextFieldExcessTotal.getText().trim();
        if (value != null && value.length() > 0)
            d = Currency.getCurrencyInstance().getBigDecimalValue(value);

        jTextFieldExcessTotal.setText(value);
        apCalc.setExcessTotal(d);
        if (!apCalc.validateETPComponentInput(d)) {
            jTextFieldExcessTotal.setText(previousValue);
            apCalc.setExcessTotal(Currency.getCurrencyInstance()
                    .getBigDecimalValue(previousValue));
            jTextFieldExcessTotal.requestFocus();
        }

    }// GEN-LAST:event_jTextFieldExcessTotalFocusLost

    private void jTextFieldCGTExemptTotalFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_jTextFieldCGTExemptTotalFocusLost
        // Add your handling code here:
        String previousValue = Currency.getCurrencyInstance().toString(
                apCalc.getCGTExemptTotal());

        BigDecimal d = null;
        String value = jTextFieldCGTExemptTotal.getText().trim();
        if (value != null && value.length() > 0)
            d = Currency.getCurrencyInstance().getBigDecimalValue(value);

        jTextFieldCGTExemptTotal.setText(value);
        apCalc.setCGTExemptTotal(d);

        if (!apCalc.validateETPComponentInput(d)) {
            jTextFieldCGTExemptTotal.setText(previousValue);
            apCalc.setCGTExemptTotal(Currency.getCurrencyInstance()
                    .getBigDecimalValue(previousValue));
            jTextFieldCGTExemptTotal.requestFocus();
        }

    }// GEN-LAST:event_jTextFieldCGTExemptTotalFocusLost

    private void jTextFieldUndeductedTotalFocusLost(
            java.awt.event.FocusEvent evt) {// GEN-FIRST:event_jTextFieldUndeductedTotalFocusLost
        // Add your handling code here:
        String previousValue = Currency.getCurrencyInstance().toString(
                apCalc.getUndeductedTotal());

        BigDecimal d = null;
        String value = jTextFieldUndeductedTotal.getText().trim();
        if (value != null && value.length() > 0)
            d = Currency.getCurrencyInstance().getBigDecimalValue(value);

        jTextFieldUndeductedTotal.setText(value);
        apCalc.setUndeductedTotal(d);

        if (!apCalc.validateETPComponentInput(d)) {
            jTextFieldUndeductedTotal.setText(previousValue);
            apCalc.setUndeductedTotal(Currency.getCurrencyInstance()
                    .getBigDecimalValue(previousValue));
            jTextFieldUndeductedTotal.requestFocus();
        }

    }// GEN-LAST:event_jTextFieldUndeductedTotalFocusLost

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonCancelActionPerformed
        // Add your handling code here:
        SwingUtil.setVisible(this, false);
        result = ETPConstants.CANCEL_OPTION;

    }// GEN-LAST:event_jButtonCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelExcess;

    private javax.swing.JButton jButtonCancel;

    private javax.swing.JTextField jTextFieldExcessTotal;

    private javax.swing.JTextField jTextFieldUndeductedTotal;

    private javax.swing.JTextField jTextFieldPost061983UntaxedTotal;

    private javax.swing.JLabel jLabelConcessional;

    private javax.swing.JTextField jTextFieldConcessionalTotal;

    private javax.swing.JLabel jLabelInvalidity;

    private javax.swing.JButton jButtonOK;

    private javax.swing.JTextField jTextFieldPre071983Total;

    private javax.swing.JLabel jLabelPostJune1983Untaxed;

    private javax.swing.JLabel jLabelUndeducted;

    private javax.swing.JTextField jTextFieldCGTExemptTotal;

    private javax.swing.JLabel jLabelPostJune1983Taxed;

    private javax.swing.JTextField jTextFieldInvalidityTotal;

    private javax.swing.JPanel jPanel1;

    private javax.swing.JTextField jTextFieldTotalAmountTotal;

    private javax.swing.JLabel jLabelCGTExempt;

    private javax.swing.JTextField jTextFieldPost061983TaxedTotal;

    private javax.swing.JLabel jLabelTotalAmount;

    private javax.swing.JPanel jPanelETPAllocation;

    private javax.swing.JLabel jLabelPreJuly1983;

    // End of variables declaration//GEN-END:variables

    public int getResult() {
        return result;
    }

    public void updateETPComponents() {

        BigDecimal d = null;

        d = apCalc.getPost061983UntaxedTotal();
        jTextFieldPost061983UntaxedTotal.setText(Currency.getCurrencyInstance()
                .toString(d));

        d = apCalc.getUndeductedTotal();
        jTextFieldUndeductedTotal.setText(Currency.getCurrencyInstance()
                .toString(d));

        d = apCalc.getCGTExemptTotal();
        jTextFieldCGTExemptTotal.setText(Currency.getCurrencyInstance()
                .toString(d));

        d = apCalc.getExcessTotal();
        jTextFieldExcessTotal.setText(Currency.getCurrencyInstance()
                .toString(d));

        d = apCalc.getConcessionalTotal();
        jTextFieldConcessionalTotal.setText(Currency.getCurrencyInstance()
                .toString(d));

        d = apCalc.getInvalidityTotal();
        jTextFieldInvalidityTotal.setText(Currency.getCurrencyInstance()
                .toString(d));
    }

    public void updateNonEditable() {
        BigDecimal d = null;

        d = apCalc.getPre071983Total();
        jTextFieldPre071983Total.setText(Currency.getCurrencyInstance()
                .toString(d));

        d = apCalc.getPost061983TaxedTotal();
        jTextFieldPost061983TaxedTotal.setText(Currency.getCurrencyInstance()
                .toString(d));

    }

}
