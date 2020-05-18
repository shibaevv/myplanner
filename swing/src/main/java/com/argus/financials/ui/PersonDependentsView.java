/*
 * PersonDependentsView2.java
 *
 * Created on 30 November 2001, 15:57
 */

package com.argus.financials.ui;

/**
 * 
 * @author valeri chibaev
 * @author shibaevv
 * @version
 */

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.argus.financials.api.bean.PersonName;
import com.argus.financials.api.code.ObjectTypeConstant;
import com.argus.financials.code.AddressCode;
import com.argus.financials.code.CountryCode;
import com.argus.financials.code.RelationshipCode;
import com.argus.financials.etc.AddressDto;
import com.argus.financials.etc.Dependent;
import com.argus.financials.service.ClientService;
import com.argus.financials.service.PersonService;
import com.argus.financials.swing.DateInputVerifier;
import com.argus.financials.swing.IntegerInputVerifier;
import com.argus.financials.swing.SwingUtil;
import com.argus.format.Number2;
import com.argus.util.DateTimeUtils;

public class PersonDependentsView extends TableEditView {
    // new same as partner
    private PersonService person;

    private NameView name;

    private AddressView dependentAddressView;

    private static ClientService clientService;
    public static void setClientService(ClientService clientService) {
        PersonDependentsView.clientService = clientService;
    }

    /** Creates new PersonDependentsView2 */
    public PersonDependentsView() {

        // "Full Name", "DOB", "Age", "Relationship", "Allowance Amount"
        COLUMN_NAMES = Dependent.COLUMN_NAMES;

        name = new NameView(false);
        dependentAddressView = new AddressView(AddressCode.RESIDENTIAL);

        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    private void initComponents() {
        jPanelPersonDetails = new javax.swing.JPanel();
        jLabelDateOfBirth = new javax.swing.JLabel();
        jTextFieldDateOfBirth = new com.argus.bean.FDateChooser();
        jLabelSupportToAge = new javax.swing.JLabel();
        jTextFieldSupportToAge = new javax.swing.JTextField();
        jLabelAge = new javax.swing.JLabel();
        jTextFieldAge = new javax.swing.JTextField();
        jLabelRelationship = new javax.swing.JLabel();
        jComboBoxRelationship = new javax.swing.JComboBox(
                new RelationshipCode().getCodeDescriptions());
        jLabelDOBCountry = new javax.swing.JLabel();
        jComboBoxDobCountry = new javax.swing.JComboBox(new CountryCode()
                .getCodeDescriptions());

        jButtonAddPartners = new javax.swing.JButton();

        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
                new Object[][] {}, COLUMN_NAMES) {
            boolean[] canEdit = new boolean[COLUMN_NAMES.length];

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        jTable.setModel(model);

        jPanelDetails.setLayout(new java.awt.GridLayout(1, 2));
        // jPanelDetails.setPreferredSize(new java.awt.Dimension(10, 300));

        jPanelPersonDetails.setBorder(new javax.swing.border.TitledBorder(
                "Dependent Details"));

        jPanelPersonDetails.setLayout(new java.awt.GridBagLayout());

        int gridy = 0;
        java.awt.GridBagConstraints gridBagConstraints1;

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = gridy++;
        gridBagConstraints1.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.weightx = 1.0;
        jPanelPersonDetails.add(name, gridBagConstraints1);

        jLabelDateOfBirth.setText("DOB");
        jLabelDateOfBirth.setHorizontalAlignment(jLabelDateOfBirth.LEADING);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = gridy;
        gridBagConstraints1.insets = new java.awt.Insets(0, 10, 0, 10);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        jPanelPersonDetails.add(jLabelDateOfBirth, gridBagConstraints1);

        jTextFieldDateOfBirth.setNextFocusableComponent(jTextFieldSupportToAge);
        jTextFieldDateOfBirth.setPreferredSize(new java.awt.Dimension(50, 21));
        jTextFieldDateOfBirth.setInputVerifier(new DateInputVerifier());
        jTextFieldDateOfBirth
                .addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        jTextFieldDateOfBirthFocusLost(evt);
                    }
                });
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = gridy;
        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 0, 10);
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.weightx = 1.0;
        // gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        jPanelPersonDetails.add(jTextFieldDateOfBirth, gridBagConstraints1);

        jLabelAge.setText("Age");
        jLabelAge.setPreferredSize(new java.awt.Dimension(10, 21));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = gridy;
        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 0, 10);
        // gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        jPanelPersonDetails.add(jLabelAge, gridBagConstraints1);

        jTextFieldAge.setEditable(false);
        jTextFieldAge.setBackground(java.awt.Color.lightGray);
        jTextFieldAge.setHorizontalAlignment(javax.swing.JTextField.LEADING);
        jTextFieldAge.setPreferredSize(new java.awt.Dimension(20, 20));
        jTextFieldAge.setMinimumSize(new java.awt.Dimension(20, 20));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 3;
        gridBagConstraints1.gridy = gridy++;
        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 0, 10);
        // gridBagConstraints1.gridwidth =
        // java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        // gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        jPanelPersonDetails.add(jTextFieldAge, gridBagConstraints1);

        jLabelSupportToAge.setText("Support to Age");
        jLabelSupportToAge.setHorizontalAlignment(jLabelSupportToAge.LEADING);
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = gridy;
        gridBagConstraints1.insets = new java.awt.Insets(0, 10, 0, 10);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        jPanelPersonDetails.add(jLabelSupportToAge, gridBagConstraints1);

        jTextFieldSupportToAge
                .setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldSupportToAge.setPreferredSize(new java.awt.Dimension(10, 21));
        jTextFieldSupportToAge.setInputVerifier(IntegerInputVerifier
                .getInstance());
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = gridy++;
        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 0, 10);
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        // gridBagConstraints1.weightx = 1.0;
        // gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        jPanelPersonDetails.add(jTextFieldSupportToAge, gridBagConstraints1);

        jLabelDOBCountry.setText("Country of Birth");
        jLabelDOBCountry.setPreferredSize(new java.awt.Dimension(100, 16));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = gridy;
        gridBagConstraints1.insets = new java.awt.Insets(0, 10, 0, 10);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        jPanelPersonDetails.add(jLabelDOBCountry, gridBagConstraints1);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = gridy++;
        gridBagConstraints1.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelPersonDetails.add(jComboBoxDobCountry, gridBagConstraints1);

        jLabelRelationship.setText("Relationship to ClientView");
        jLabelRelationship.setPreferredSize(new java.awt.Dimension(100, 16));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = gridy;
        gridBagConstraints1.insets = new java.awt.Insets(0, 10, 0, 10);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        jPanelPersonDetails.add(jLabelRelationship, gridBagConstraints1);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = gridy++;
        gridBagConstraints1.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.weightx = 1.0;
        gridBagConstraints1.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelPersonDetails.add(jComboBoxRelationship, gridBagConstraints1);

        jPanelDetails.add(jPanelPersonDetails);

        dependentAddressView.setBorder(new javax.swing.border.TitledBorder(
                "Dependent Address"));
        jPanelDetails.add(dependentAddressView);
        jPanelDetails.setVisible(false);

        tpgControls.setTitle("Dependents");
        //jButtonAddPartners.setText("Add Dependents");
        //jButtonAddPartners.setToolTipText("Add Partner's Dependents");
        tpgControls.add(new AbstractAction("Add Dependents") {
            public void actionPerformed(ActionEvent evt) {
                jButtonAddPartnersActionPerformed(evt);
            }
        });

    }

    private void jTextFieldDateOfBirthFocusLost(java.awt.event.FocusEvent evt) {
        Double age = DateTimeUtils.getAgeDouble(DateTimeUtils
                .getDate(jTextFieldDateOfBirth.getText()));
        jTextFieldAge.setText(age == null ? null : "" + age.intValue());
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel jPanelPersonDetails;
    private javax.swing.JLabel jLabelDateOfBirth;
    private javax.swing.JLabel jLabelAge;
    private javax.swing.JLabel jLabelSupportToAge;
    private javax.swing.JTextField jTextFieldAge;
    private com.argus.bean.FDateChooser jTextFieldDateOfBirth;
    private javax.swing.JTextField jTextFieldSupportToAge;
    private javax.swing.JLabel jLabelRelationship;
    private javax.swing.JComboBox jComboBoxRelationship;
    private javax.swing.JLabel jLabelDOBCountry;
    private javax.swing.JComboBox jComboBoxDobCountry;
    private javax.swing.JButton jButtonAddPartners;
    // End of variables declaration

    protected Object[][] getRowData() {

        if (details == null)
            return new Object[][] {};

        try {
            Set set = new TreeSet(new Comparator() {
                public int compare(Object o1, Object o2) {
                    Map.Entry e1 = (Map.Entry) o1;
                    Map.Entry e2 = (Map.Entry) o2;

                    Dependent d1 = (Dependent) e1.getValue();
                    Dependent d2 = (Dependent) e2.getValue();
                    return ((String) d1.getData()[1]).compareTo((String) d2
                            .getData()[1]);
                }
            });

            set.addAll(details.entrySet());

            AddressDto clientAddress = clientService.getResidentialAddress();

            int i = 0;
            // allocate matrix
            Object[][] rowData = new Object[details.size()][];
            for (Iterator iter = set.iterator(); iter.hasNext();) {

                Map.Entry entry = (Map.Entry) iter.next();
                Dependent d = (Dependent) entry.getValue();

                if (d == null)
                    continue;

                AddressDto dependentAddress = d.getAddress();
                // same as client
                if (clientAddress.getId().equals(
                        dependentAddress.getParentAddressId()))
                    clientAddress.addChangeListener(dependentAddress);

                rowData[i++] = d.getData();

            }

            if (i == rowData.length)
                return rowData;

            Object[][] rowData2 = new Object[i][];
            System.arraycopy(rowData, 0, rowData2, 0, i);
            return rowData2;

        } catch (Exception e) {
            e.printStackTrace(System.err);
            return new Object[][] {};
        }

    }

    /**
     * Viewable interface
     */
    public Integer getObjectType() {
        return new Integer(ObjectTypeConstant.PERSON);
    }

    public void updateView(PersonService person) throws com.argus.financials.api.ServiceException {
        // new: same as partner
        this.person = person;

        details = this.person.getDependents();

        super.updateView(this.person);

        if (jTable.getRowCount() == 0) {
            clearView();
        } else {
            jTable.changeSelection(0, 1, false, false);
            display(getSelectedObject());
        }

    }

    public void saveView(PersonService person) throws com.argus.financials.api.ServiceException {

        person.setDependents((TreeMap) details);

    }

    public void clearView() {
        super.clearView();

        SwingUtil.clear(jPanelDetails);

        name.clearView();
        dependentAddressView.clearView();
        /*
         * jTextFieldDateOfBirth.setText( null );
         * jComboBoxDobCountry.setSelectedItem( null );
         * jComboBoxRelationship.setSelectedItem( null );
         */

    }

    /**
     * 
     */
    protected void add() {
        // super.add();

        clearView(); // details view

        jPanelDetails.setVisible(true);

        Dependent dependent = new Dependent();
        Integer objID = new Integer(--newObjectID);

        if (details == null)
            details = new TreeMap();
        details.put(objID, dependent);

        Object[] rowData = dependent.getData();
        rowData[COLUMN_OBJECT_ID] = objID;
        ((DefaultTableModel) jTable.getModel()).addRow(rowData);
        jTable.changeSelection(jTable.getRowCount() - 1, 1, false, false);

        detailsEnabled(true); // has to be first
        SwingUtil.setEnabled(jTextFieldAge, false);
        dependentAddressView.setDefaultCountry();

    }

    protected void display(Object obj) throws com.argus.financials.api.ServiceException {

        SwingUtil.setEnabled(jTextFieldAge, false);

        if (obj == null) {
            clearView();
            return;
        }

        Dependent d = (Dependent) obj;

        name.setObject(d.getName());

        // address
        AddressDto dependentAddress = d.getAddress();
        dependentAddressView.setObject(dependentAddress);
        dependentAddress.addChangeListener(dependentAddressView); // add new
                                                                    // selected
        dependentAddressView.setEnabled(false);

        java.util.Date dob = d.getDateOfBirth();
        if (dob == null)
            jTextFieldDateOfBirth.setText(null);
        else
            jTextFieldDateOfBirth.setText(DateTimeUtils.asString(dob, null));

        Number age = d.getName().getAge();
        jTextFieldAge.setText(age == null ? null : "" + age.intValue());

        Integer supportToAge = d.getSupportToAge();
        jTextFieldSupportToAge.setText(supportToAge == null ? null
                : supportToAge.toString());

        Integer id = d.getDobCountryID();
        jComboBoxDobCountry.setSelectedItem(new CountryCode()
                .getCodeDescription(id));

        id = d.getRelationshipCodeID();
        jComboBoxRelationship.setSelectedItem(new RelationshipCode()
                .getCodeDescription(id));

        jPanelDetails.setVisible(true);
    }

    protected void update(Object obj) {

        if (obj == null) {
            // add();
            return;
        }

        DefaultTableModel tm = (DefaultTableModel) jTable.getModel();

        try {
            save(obj);

            int id = jTable.getSelectedRow();
            Object[] rowData = ((Dependent) obj).getData();
            for (int i = 0; i < rowData.length; i++)
                if (i == COLUMN_OBJECT_ID) {
                    if (rowData[i] != null)
                        tm.setValueAt(rowData[i], id, i);
                } else {
                    tm.setValueAt(rowData[i], id, i);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void remove(Integer objID) {
        if (objID == null)
            return;

        Object dependent = details.remove(objID);
        if (objID.intValue() > 0)
            details.put(objID, null); // replace object with null value

        // remove table row
        int selectedRow = jTable.getSelectedRow();
        if (jTable.getRowCount() >= 1)
            jTable.changeSelection(jTable.getRowCount() - 1, 1, false, false);
        else
            clearView();
        ((DefaultTableModel) jTable.getModel()).removeRow(selectedRow);

        if (dependent != null)
            Dependent.getBeneficiaries().getCodes().remove(dependent);

    }

    private void save(Object obj) throws com.argus.financials.api.ServiceException {
        Number2 number = Number2.getNumberInstance();

        Dependent d = (Dependent) obj;

        // name
        name.saveView(null);
        d.setName((PersonName) name.getObject());

        // dependentAddressView
        dependentAddressView.saveView(null);
        d.setAddress((AddressDto) dependentAddressView.getObject());

        java.sql.Date dob = DateTimeUtils.getSqlDate(jTextFieldDateOfBirth
                .getText());
        d.setDateOfBirth(dob);

        String supportToAge = jTextFieldSupportToAge.getText();
        // double age = number.doubleValue( supportToAge );
        // d.setSupportToAge( age < 0 ? null : new Integer( (int)age) );
        d
                .setSupportToAge((supportToAge == null || supportToAge.length() == 0) ? null
                        : new Integer(supportToAge));
        String s = (String) jComboBoxDobCountry.getSelectedItem();
        d.setDobCountryID(new CountryCode().getCodeID(s));

        s = (String) jComboBoxRelationship.getSelectedItem();
        d.setRelationshipCodeID(new RelationshipCode().getCodeID(s));

    }

    // BEGIN: "Add Partner's Dependents" panel
    private void jButtonAddPartnersActionPerformed(
            java.awt.event.ActionEvent evt) {

        if (JOptionPane.showConfirmDialog(this,
                "Do you really want to add partner's dependents?",
                "Copy Partner's Dependents", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            doSameAsPartnerCopy();

    }

    private void doSameAsPartnerCopy() {
        // add partner's dependents
        try {
            // get partner
            PersonService pp = null;

            try {
                // client view => we need to get the partner
                pp = ((ClientService) person).getPartner(false);
            } catch (java.lang.ClassCastException e) {
                // partner view => we need to get the client
                pp = (PersonService) (clientService);
            }

            // get partner's dependents
            if (pp != null) {
                TreeMap tm = pp.getDependents();

                Collection co = tm.values();
                Iterator it = co.iterator();

                while (it.hasNext()) {
                    Integer objID = new Integer(--newObjectID);
                    Dependent dep = (Dependent) it.next();
                    Dependent new_dep = new Dependent();

                    new_dep.assign(dep);
                    new_dep.setId(null);
                    new_dep.setOwnerId(person.getId());

                    details.put(objID, new_dep);

                    Object[] rowData = new_dep.getData();
                    rowData[COLUMN_OBJECT_ID] = objID;
                    ((DefaultTableModel) jTable.getModel()).addRow(rowData);
                }
                ((DefaultTableModel) jTable.getModel()).fireTableDataChanged();
            } // end if
        } catch (com.argus.financials.api.ServiceException e) {
            e.printStackTrace(System.err);
        } catch (java.lang.NullPointerException e) {
            e.printStackTrace(System.err);
        }
    }

    private void doSameAsPartnerDelete() {
        if (person != null) {
            try {
                person.setDependents(new TreeMap());
                updateView(person);
            } catch (com.argus.financials.api.ServiceException e) {
                e.printStackTrace(System.err);
            }
        }
    }
    // END: "Add Partner's Dependents" panel
}
