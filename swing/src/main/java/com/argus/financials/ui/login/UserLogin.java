/*
 * UserLogin.java
 *
 * Created on 19 July 2001, 13:04
 */

package com.argus.financials.ui.login;

import com.argus.financials.code.ReferenceDataLoader;
import com.argus.financials.config.FPSLocale;
import com.argus.financials.config.PropertySourceManager;
import com.argus.financials.swing.SwingUtil;
import com.argus.financials.ui.AbstractPanel;
import com.argus.io.IOUtils;

public final class UserLogin
    extends AbstractPanel
    implements com.argus.financials.swing.IDefaultButton
{

    private static UserLogin view;

    private String lastError;

    /** Creates new form UserLogin */
    private UserLogin(boolean allowDisplayOnDesktop, boolean displayOnDesktop) {
        initComponents();
        initComponents2(allowDisplayOnDesktop, displayOnDesktop);
    }

    public static UserLogin getInstance() {
        return view;
    }

    public static UserLogin newInstance(boolean allowDisplayOnDesktop, boolean displayOnDesktop) {
        if (view == null)
            view = new UserLogin(allowDisplayOnDesktop, displayOnDesktop);
        return view;
    }

    public static boolean exists() {
        return view != null;
    }

    public void setActionListener(java.awt.event.ActionListener al) {
        // to notify main form
        jButtonOK.addActionListener(al);
        jButtonCancel.addActionListener(al);

        jButtonRegister.addActionListener(al);
    }

    private void initComponents2(boolean allowDisplayOnDesktop, boolean displayOnDesktop) {

        FPSLocale l = FPSLocale.getInstance();

        jCheckBoxDisplayOnDesktop.setSelected(displayOnDesktop);
        jCheckBoxDisplayOnDesktop.setVisible(allowDisplayOnDesktop);

        jLabelServerName.setVisible(false);
        jTextFieldServerName.setVisible(false);

        setUserName(l.getLastUserName());

        jPasswordFieldConfirm.setVisible(false);
        jLabelConfirm.setVisible(false);

        jButtonChangePassword.setVisible(false);

        jPanelNewUser.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    private void initComponents() {// GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelNewUser = new javax.swing.JPanel();
        jLabelNewUserInfo = new javax.swing.JLabel();
        jPanelNewUserControls = new javax.swing.JPanel();
        jButtonRegister = new javax.swing.JButton();
        jPanelExistingUser = new javax.swing.JPanel();
        jPasswordField = new javax.swing.JPasswordField();
        jTextFieldUserID = new javax.swing.JTextField();
        jPasswordFieldConfirm = new javax.swing.JPasswordField();
        jTextFieldServerName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelConfirm = new javax.swing.JLabel();
        jLabelServerName = new javax.swing.JLabel();
        jCheckBoxDisplayOnDesktop = new javax.swing.JCheckBox();
        jPanelControls = new javax.swing.JPanel();
        jButtonOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jButtonChangePassword = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }

            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }

            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanelNewUser.setLayout(new javax.swing.BoxLayout(jPanelNewUser,
                javax.swing.BoxLayout.X_AXIS));

        jPanelNewUser
                .setBorder(new javax.swing.border.TitledBorder("New User"));
        jLabelNewUserInfo
                .setText("<html>\n<body>\n\n<font size=3><p align=\"LEFT\">\n<b>In order to use this product,<br>have access to technical support,<br>you must register.</b>\n</p></font>\n\n</body>\n</html>");
        jLabelNewUserInfo
                .setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNewUserInfo.setAlignmentX(1.0F);
        jPanelNewUser.add(jLabelNewUserInfo);

        jPanelNewUserControls.setLayout(new javax.swing.BoxLayout(
                jPanelNewUserControls, javax.swing.BoxLayout.X_AXIS));

        jButtonRegister.setText("Register");
        jButtonRegister.setDefaultCapable(false);
        jPanelNewUserControls.add(jButtonRegister);

        jPanelNewUser.add(jPanelNewUserControls);

        add(jPanelNewUser);

        jPanelExistingUser.setLayout(new java.awt.GridBagLayout());

        jPanelExistingUser.setBorder(new javax.swing.border.TitledBorder(
                "Existing User"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelExistingUser.add(jPasswordField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelExistingUser.add(jTextFieldUserID, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelExistingUser.add(jPasswordFieldConfirm, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        jPanelExistingUser.add(jTextFieldServerName, gridBagConstraints);

        jLabel1.setText("User ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanelExistingUser.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanelExistingUser.add(jLabel2, gridBagConstraints);

        jLabelConfirm.setText("Confirm");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanelExistingUser.add(jLabelConfirm, gridBagConstraints);

        jLabelServerName.setText("Server");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        jPanelExistingUser.add(jLabelServerName, gridBagConstraints);

        jCheckBoxDisplayOnDesktop.setText("Display On Desktop");
        jCheckBoxDisplayOnDesktop
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jCheckBoxDisplayOnDesktopActionPerformed(evt);
                    }
                });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        jPanelExistingUser.add(jCheckBoxDisplayOnDesktop, gridBagConstraints);

        add(jPanelExistingUser);

        jButtonOK.setText("OK");
        jButtonOK.setSelected(true);
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        jPanelControls.add(jButtonOK);

        jButtonCancel.setText("Cancel");
        jButtonCancel.setDefaultCapable(false);
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        jPanelControls.add(jButtonCancel);

        jButtonChangePassword.setText("Change password");
        jButtonChangePassword.setDefaultCapable(false);
        jButtonChangePassword
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButtonChangePasswordActionPerformed(evt);
                    }
                });

        jPanelControls.add(jButtonChangePassword);

        add(jPanelControls);

    }// GEN-END:initComponents

    private void jCheckBoxDisplayOnDesktopActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jCheckBoxDisplayOnDesktopActionPerformed
        System.setProperty("DisplayOnDesktop", jCheckBoxDisplayOnDesktop
                .isSelected() ? Boolean.TRUE.toString() : Boolean.FALSE
                .toString());
    }// GEN-LAST:event_jCheckBoxDisplayOnDesktopActionPerformed

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {// GEN-FIRST:event_formAncestorAdded
        if (evt.getComponent().getTopLevelAncestor() instanceof javax.swing.JApplet) {
            jLabelServerName.setVisible(false);
            jTextFieldServerName.setVisible(false);
        }
    }// GEN-LAST:event_formAncestorAdded

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonOKActionPerformed

        // reset password fields
        jPasswordField.setText("");
        jPasswordFieldConfirm.setText("");

        // hide these ..
        jPasswordFieldConfirm.setVisible(false);
        jLabelConfirm.setVisible(false);

    }// GEN-LAST:event_jButtonOKActionPerformed

    private void jButtonChangePasswordActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonChangePasswordActionPerformed

        // reset password fields
        jPasswordField.setText("");
        jPasswordFieldConfirm.setText("");

        jPasswordFieldConfirm.setVisible(true);
        jLabelConfirm.setVisible(true);

    }// GEN-LAST:event_jButtonChangePasswordActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelNewUserControls;

    private javax.swing.JButton jButtonCancel;

    private javax.swing.JLabel jLabelConfirm;

    private javax.swing.JButton jButtonChangePassword;

    private javax.swing.JPanel jPanelControls;

    private javax.swing.JLabel jLabelServerName;

    private javax.swing.JPanel jPanelExistingUser;

    private javax.swing.JPasswordField jPasswordField;

    private javax.swing.JTextField jTextFieldUserID;

    private javax.swing.JButton jButtonOK;

    private javax.swing.JButton jButtonRegister;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JTextField jTextFieldServerName;

    private javax.swing.JPasswordField jPasswordFieldConfirm;

    private javax.swing.JLabel jLabelNewUserInfo;

    private javax.swing.JPanel jPanelNewUser;

    private javax.swing.JCheckBox jCheckBoxDisplayOnDesktop;

    // End of variables declaration//GEN-END:variables

    public void setVisible(boolean value) {
        com.argus.financials.swing.SwingUtil.setVisible(this, value);
    }

    public javax.swing.JButton getDefaultButton() {
        return jButtonOK;
    }

    public String getUserName() {
        return jTextFieldUserID.getText();
    }

    public void setUserName(String value) {
        jTextFieldUserID.setText(value);
        if (jTextFieldUserID.getText().length() > 0)
            jPasswordField.requestFocus();
    }

    public String getUserPassword() {
        return new String(jPasswordField.getPassword());
    }

    public String getLastError() {
        return lastError;
    }

    /**
     * do login/logout on rmi server
     */
    public boolean login() {
        try
        {
            userService.login(getUserName(), getUserPassword());
            String msg = "<<<<<<<<<< LOGIN for user: "
                + IOUtils.getUserName() + ", "
                + new java.util.Date();
            System.out.println(msg);
            System.err.println(msg);
            // pre-load ALL reference codes
            new ReferenceDataLoader();
            return true;
        }
        catch (Exception e)
        {
            lastError = e.getMessage();
            return false;
        }
    }

    public void logout() {
        SwingUtil.closeAll();

        userService.logout();

        String msg = "LOGOUT for user: "
                + IOUtils.getUserName() + ", "
                + new java.util.Date() + " >>>>>>>>>>";
        System.out.println(msg);
        System.err.println(msg);

        // unload/store local configuration
        PropertySourceManager.getInstance().unload();

        // unreference
        view = null;
    }

}