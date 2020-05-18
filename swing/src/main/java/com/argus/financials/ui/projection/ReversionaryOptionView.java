/*
 * ReversionaryOptionView.java
 *
 * Created on 5 September 2002, 15:54
 */

package com.argus.financials.ui.projection;

import java.math.BigDecimal;

import javax.swing.JComboBox;

import com.argus.financials.api.ETPConstants;
import com.argus.financials.code.APRelationshipCode;
import com.argus.financials.code.SexCode;
import com.argus.financials.projection.AllocatedPensionCalcNew;
import com.argus.financials.projection.MoneyCalc;
import com.argus.financials.projection.data.LifeExpectancy;
import com.argus.financials.swing.SwingUtil;
import com.argus.format.Number2;
import com.argus.util.DateTimeUtils;
import com.argus.util.ReferenceCode;

public class ReversionaryOptionView extends javax.swing.JPanel implements
        javax.swing.event.ChangeListener {

    private int result = -1;

    protected AllocatedPensionCalcNew apCalc;

    private java.awt.event.FocusListener[] listeners;

    private boolean isClient;

    /** Creates new form ReversionaryOptionView */
    public ReversionaryOptionView(AllocatedPensionCalcNew calc) {
        apCalc = calc;
        initComponents();
        initComponents2();
    }

    /** Creates new form apCalc */
    public ReversionaryOptionView() {
        this(new AllocatedPensionCalcNew());

    }

    private void initComponents2() {
        apCalc.addChangeListener(this);
        updateEditable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {// GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxSpouse = new javax.swing.JComboBox(new APRelationshipCode()
                .getCodes().toArray());
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldDOB = new com.argus.bean.FDateChooser();
        jComboBoxSex = new JComboBox(new SexCode().getCodeDescriptions());
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldAge = new javax.swing.JTextField();
        jTextFieldLifeExpectancy = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButtonOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(10,
                10, 10, 10)));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.EmptyBorder(new java.awt.Insets(10, 10,
                        10, 10)), new javax.swing.border.TitledBorder("")));
        jLabel1.setText("Relationship");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 17));
        jLabel1.setMinimumSize(new java.awt.Dimension(150, 17));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel4.setText("Date of Birth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Sex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jLabel5, gridBagConstraints);

        jComboBoxSpouse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxSpouseItemStateChanged(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jComboBoxSpouse, gridBagConstraints);

        jTextFieldName.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldNameFocusLost(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jTextFieldName, gridBagConstraints);

        jTextFieldDOB
                .addFormDataChangedListener(new com.argus.bean.FormDataChangedListener() {
                    public void formDataChanged(
                            com.argus.bean.FormDataChangedEvent evt) {
                        jTextFieldDOBFormDataChanged(evt);
                    }
                });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jTextFieldDOB, gridBagConstraints);

        jComboBoxSex.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxSexItemStateChanged(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jComboBoxSex, gridBagConstraints);

        jLabel6.setText("Age #2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel7.setText("Life Expectancy");
        jLabel7.setPreferredSize(new java.awt.Dimension(150, 17));
        jLabel7.setMinimumSize(new java.awt.Dimension(150, 17));
        jLabel7.setMaximumSize(new java.awt.Dimension(190, 17));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jLabel7, gridBagConstraints);

        jTextFieldAge.setEditable(false);
        jTextFieldAge.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldAge.setPreferredSize(new java.awt.Dimension(130, 21));
        jTextFieldAge.setMinimumSize(new java.awt.Dimension(130, 26));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jTextFieldAge, gridBagConstraints);

        jTextFieldLifeExpectancy.setEditable(false);
        jTextFieldLifeExpectancy
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jTextFieldLifeExpectancy, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        jPanel1.add(jPanel5, gridBagConstraints);

        add(jPanel1);

        jPanel3.setBorder(new javax.swing.border.EtchedBorder());
        jPanel3.setPreferredSize(new java.awt.Dimension(61, 40));
        jPanel3.setMaximumSize(new java.awt.Dimension(300, 40));
        jButtonOK.setText("OK");
        jButtonOK.setPreferredSize(new java.awt.Dimension(81, 27));
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        jPanel3.add(jButtonOK);

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jPanel3.add(jButtonCancel);

        add(jPanel3);

    }// GEN-END:initComponents

    private void jTextFieldDOBFormDataChanged(
            com.argus.bean.FormDataChangedEvent evt) {// GEN-FIRST:event_jTextFieldDOBFormDataChanged
        // Add your handling code here:
        if (isClient)
            apCalc.setPartnerDOB(getDOB());
        else
            apCalc.setDateOfBirth(getDOB());
        updateNonEditable();
    }// GEN-LAST:event_jTextFieldDOBFormDataChanged

    private void jTextFieldNameFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_jTextFieldNameFocusLost
        // Add your handling code here:
        if (isClient)
            apCalc.setPartnerName(getName());
        else
            apCalc.setClientName(getName());
    }// GEN-LAST:event_jTextFieldNameFocusLost

    private void jComboBoxSpouseItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_jComboBoxSpouseItemStateChanged
        // Add your handling code here:
        apCalc.setRelationshipID(getRelationshipID());

    }// GEN-LAST:event_jComboBoxSpouseItemStateChanged

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonCancelActionPerformed
        // Add your handling code here:
        result = ETPConstants.CANCEL_OPTION;
        SwingUtil.setVisible(this, false);
    }// GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonOKActionPerformed
        // Add your handling code here:
        SwingUtil.setVisible(this, false);
        result = ETPConstants.OK_OPTION;
    }// GEN-LAST:event_jButtonOKActionPerformed

    private void jComboBoxSexItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_jComboBoxSexItemStateChanged
        // Add your handling code here:
        if (isClient)
            apCalc.setPartnerSexCodeID(getSexCodeID());
        else
            apCalc.setSexCodeID(getSexCodeID());
        updateNonEditable();
    }// GEN-LAST:event_jComboBoxSexItemStateChanged

    private void jTextFieldDOBActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldDOBActionPerformed
        // Add your handling code here:
    }// GEN-LAST:event_jTextFieldDOBActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;

    private javax.swing.JComboBox jComboBoxSex;

    private javax.swing.JTextField jTextFieldLifeExpectancy;

    private javax.swing.JTextField jTextFieldName;

    private javax.swing.JButton jButtonOK;

    private com.argus.bean.FDateChooser jTextFieldDOB;

    private javax.swing.JLabel jLabel7;

    private javax.swing.JTextField jTextFieldAge;

    private javax.swing.JLabel jLabel6;

    private javax.swing.JLabel jLabel5;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JPanel jPanel5;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JPanel jPanel3;

    private javax.swing.JPanel jPanel1;

    private javax.swing.JComboBox jComboBoxSpouse;

    // End of variables declaration//GEN-END:variables

    public void updateNonEditable() {
        String s = (String) jComboBoxSex.getSelectedItem();
        int age = apCalc.getAge(getDOB());
        setAge(new BigDecimal(age));

        if (s == null || s.length() == 0
                || age == (int) MoneyCalc.UNKNOWN_VALUE)
            return;
        setLifeExpectancy(new BigDecimal(LifeExpectancy.getValue(age,
                getSexCodeID())));
    }

    /*
     * private void updateAPCalcData() {
     * 
     * apCalc.setPartnerDateOfBirth( getDOB() ); apCalc.setPartnerSexCodeID(
     * getSexCodeID() ); }
     */
    public Integer getRelationshipID() {
        return ((ReferenceCode) jComboBoxSpouse.getSelectedItem())
                .getCodeId();
    }

    public void setRelationshipID(Integer value) {
        jComboBoxSpouse.setSelectedItem(new APRelationshipCode().getCode(apCalc
                .getRelationshipID()));

    }

    public java.util.Date getDOB() {
        return DateTimeUtils.getDate(jTextFieldDOB.getText());
    }

    public void setDOB(java.util.Date value) {
        jTextFieldDOB.setText(DateTimeUtils.asString(value));
    }

    public Integer getSexCodeID() {
        return new SexCode().getCodeID((String) jComboBoxSex.getSelectedItem());
    }

    public void setSexCodeID(Integer value) {
        jComboBoxSex.setSelectedItem(new SexCode().getCodeDescription(value));
    }

    public String getName() {
        return jTextFieldName.getText() == null ? null : jTextFieldName
                .getText().trim();
    }

    public void setName(String value) {
        jTextFieldName.setText(value == null ? null : value.trim());
    }

    public void setAge(BigDecimal value) {
        jTextFieldAge.setText("" + value);
    }

    public void setLifeExpectancy(BigDecimal value) {
        jTextFieldLifeExpectancy.setText(Number2.getNumberInstance().toString(
                value));
    }

    private void updateEditable() {
        setRelationshipID(apCalc.getRelationshipID());

        if (apCalc.getIsClient() == null || apCalc.getIsClient().booleanValue()) {
            isClient = true;
            setName(apCalc.getPartnerName());
            setDOB(apCalc.getPartnerDOB());
            setSexCodeID(apCalc.getPartnerSexCodeID());
        } else {
            isClient = false;
            setName(apCalc.getClientName());
            setDOB(apCalc.getDateOfBirth());
            setSexCodeID(apCalc.getSexCodeID());
        }

    }

    /**
     * javax.swing.event.ChangeListener interface
     */
    public void stateChanged(javax.swing.event.ChangeEvent changeEvent) {
        updateNonEditable();
    }

    public int getResult() {
        return result;
    }
}
