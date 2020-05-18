/*
 *              Argus Software Pty Ltd License Notice
 * 
 * The contents of this file are subject to the Argus Software Pty Ltd
 * License Version 1.0 (the "License"). 
 * You may not use this file except in compliance with the License.
 * A updated copy of the License is available at
 * http://www.argussoftware.net/license/
 * 
 * The Original Code is argus. The Initial Developer of the Original
 * Code is Argus Software Pty Ltd, All Rights Reserved.
 */

package com.argus.financials.ui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.argus.financials.api.bean.IMaritalCode;
import com.argus.financials.api.bean.IUser;
import com.argus.financials.api.bean.UserPreferences;
import com.argus.financials.api.service.UserService;
import com.argus.financials.code.AdviserTypeCode;
import com.argus.financials.config.FPSLocale;
import com.argus.financials.config.PropertySourceManager;
import com.argus.financials.config.ViewSettings;
import com.argus.financials.etc.ActionEventID;
import com.argus.financials.projection.save.Model;
import com.argus.financials.service.ClientService;
import com.argus.financials.swing.SwingUtil;
import com.argus.financials.ui.financials.FinancialView2;
import com.argus.financials.ui.help.AboutView;
import com.argus.financials.ui.help.HelpBrokerView;
import com.argus.financials.ui.help.SearchContentsView;
import com.argus.financials.ui.iso.InvRisk;
import com.argus.financials.ui.iso.InvRiskPartner;
import com.argus.financials.ui.login.UserLogin;
import com.argus.financials.ui.plan.PlanWriter;
import com.argus.financials.ui.plan.PlanWriterTemplate;
import com.argus.financials.ui.projection.AllocatedPensionViewNew;
import com.argus.financials.ui.projection.CGTView;
import com.argus.financials.ui.projection.DSSViewNew;
import com.argus.financials.ui.projection.ETPRolloverViewNew;
import com.argus.financials.ui.projection.GearingView2;
import com.argus.financials.ui.projection.InvPropertyView;
import com.argus.financials.ui.projection.MortgageView;
import com.argus.financials.ui.projection.PAYGView;
import com.argus.financials.ui.projection.SnapEntryView;
import com.argus.financials.ui.sql.ExportDataView;
import com.argus.financials.ui.sql.ImportDataView;
import com.argus.financials.ui.strategy.StrategyCreatorView2;
import com.argus.io.ErrorView;
import com.argus.io.IOUtils;
import com.argus.io.OutputView;
import com.argus.swing.SwingUtils;
import com.argus.util.DateTimeUtils;

/*
 * Created on 7/06/2006
 * 
 */

public class FinancialPlannerActionMap
    extends ActionMap
    implements ActionEventID, IMenuCommand
{

    private Frame app;
    private FocusListener[] focusListeners;
    
    private static JDialog outputView;

    private static JDialog errorView;

    private static ClientService clientService;
    private static UserPreferences userPreferences;
    private static UserService userService;
    public static void setClientService(ClientService clientService) {
        FinancialPlannerActionMap.clientService = clientService;
    }
    public static void setUserPreferences(UserPreferences userPreferences) {
        FinancialPlannerActionMap.userPreferences = userPreferences;
    }
    public static void setUserService(UserService userService) {
        FinancialPlannerActionMap.userService = userService;
    }

    FinancialPlannerActionMap(Frame app, FocusListener focusListener, String config)
        throws IOException
    {
        this.app = app;
        this.focusListeners = new FocusListener[] {focusListener};

        // set System.out, System.err
        //setOutputView();
        //setErrorView();

        PropertySourceManager.getInstance().load(config);

        // make System.out, System.err visible
        if (outputView != null)
            outputView.setVisible(false);
        if (errorView != null)
            errorView.setVisible(true);

        initActions();
        
    }

    private void initActions() {
        
        ////////////////////////////////////////////////////////////////////////////
        //          Common actions
        ////////////////////////////////////////////////////////////////////////////
        put(SAVE, new AbstractAction(SAVE.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
        put(PRINT, new AbstractAction(PRINT.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
        put(EXPORT, new AbstractAction(EXPORT.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                displayExportDataView();
            }
        });
        put(IMPORT, new AbstractAction(IMPORT.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                displayImportDataView();
            }
        });
        put(EXIT, new AbstractAction(EXIT.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                logout();
                System.exit(0);
            }
        });

        put(CUT, new AbstractAction(CUT.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
        put(COPY, new AbstractAction(COPY.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
        put(PASTE, new AbstractAction(PASTE.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
    
        put(ADD, new AbstractAction(ADD.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                fireActionEvent(DATA_ADD);
            }
        });
        put(REMOVE, new AbstractAction(REMOVE.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                fireActionEvent(DATA_REMOVE);
            }
        });
        put(UPDATE, new AbstractAction(UPDATE.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                fireActionEvent(DATA_UPDATE);
            }
        });
    
        put(STANDARD, new AbstractAction(STANDARD.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
        put(CUSTOMISE, new AbstractAction(CUSTOMISE.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
    
//        put(SYSTEM_OUT, new AbstractAction(SYSTEM_OUT.getSecond().toString()) {
//            public void actionPerformed(ActionEvent evt) {
//                if (outputView != null)
//                    outputView.setVisible(true);
//            }
//        });    
//        put(SYSTEM_ERR, new AbstractAction(SYSTEM_ERR.getSecond().toString()) {
//            public void actionPerformed(ActionEvent evt) {
//                if (errorView != null)
//                    errorView.setVisible(true);
//            }
//        });
    
        // look-and-feel
        UIManager.LookAndFeelInfo[] info = UIManager.getInstalledLookAndFeels();
        for (int i = 0; i < info.length; i++) {
            String lookAndFeelName = info[i].getName();
            String lookAndFeelClassName = info[i].getClassName();
            AbstractAction action = new AbstractAction(lookAndFeelName) {
                public void actionPerformed(ActionEvent evt) {
                    String lookAndFeel = (String) getValue(Action.ACTION_COMMAND_KEY);
                    SwingUtil.setLookAndFeel(lookAndFeel);
                    SwingUtilities.updateComponentTreeUI(app);
                }
            };
            action.putValue(Action.ACTION_COMMAND_KEY, lookAndFeelClassName);
            put(lookAndFeelName, action);
        }
    
        put(SOFTWARE_UPDATES, new AbstractAction(SOFTWARE_UPDATES.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                displayWWW("http://www.argussoftware.net");
            }
        });
    
        put(DOWNLOAD_UPDATE, new AbstractAction(DOWNLOAD_UPDATE.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                displayWWW(FPSLocale.getInstance().getUpdateDownloadURL() + FinancialPlannerApp.APP_VERSION);
            }
        });
        put(PLAN_TRACKER, new AbstractAction(PLAN_TRACKER.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                displayPlanTracker();
            }
        });
        put(CLIENT_DETAILS, new AbstractAction(CLIENT_DETAILS.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                displayClient(clientService.getId());
            }
        });

        put(PARTNER_DETAILS, new AbstractAction(PARTNER_DETAILS.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                PartnerView.getPartnerView().display(focusListeners, true);
            }
        });
    
        put(FINANCIALS, new AbstractAction(FINANCIALS.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                FinancialView2.display(clientService, focusListeners);
            }
        });
    
        put(RISK_EVALUATION_CLIENT, new AbstractAction(RISK_EVALUATION_CLIENT.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                InvRisk.display(focusListeners);
            }
        });
    
        put(RISK_EVALUATION_PARTNER, new AbstractAction(RISK_EVALUATION_PARTNER.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                InvRiskPartner.displayPartner(focusListeners);
            }
        });
    
        put(STRATEGY_DESINER, new AbstractAction(STRATEGY_DESINER.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                StrategyCreatorView2.display(focusListeners);
            }
        });
    
        put(INVESTMENT_GURU, new AbstractAction(INVESTMENT_GURU.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
        put(MANUAL_SELECTION, new AbstractAction(MANUAL_SELECTION.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
    
        put(NO_ADVICE, new AbstractAction(NO_ADVICE.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
        put(LIMITED_ADVICE, new AbstractAction(LIMITED_ADVICE.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
        put(FULL_ADVICE, new AbstractAction(FULL_ADVICE.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
    
        put(PLAN_WIZARD, new AbstractAction(PLAN_WIZARD.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                PlanWriter.display(focusListeners);
            }
        });
        put(PLAN_TEMPLATE_WIZARD, new AbstractAction(PLAN_TEMPLATE_WIZARD.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                PlanWriterTemplate.display(focusListeners);
            }
        });
    
        put(SEARCH_CLIENT, new AbstractAction(SEARCH_CLIENT.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                displayClientList();
            }
        });
        put(ADD_CLIENT, new AbstractAction(ADD_CLIENT.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                createNewClient();
            }
        });
        put(CRM, new AbstractAction(CRM.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
    
        put(CURRENT_POSITION_CALC, new AbstractAction(NEW.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                Object source = evt.getSource();
                SnapEntryView.display(source instanceof Model ? (Model) source : null, focusListeners);
            }
        });
    
        put(INSURANCE_NEEDS_CALC, new AbstractAction(NEW.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
        put(PREMIUM_CALC, new AbstractAction(NEW.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
    
        put(INVESTMENT_GEARING_CALC, new AbstractAction(NEW.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                Object source = evt.getSource();
                GearingView2.display(source instanceof Model ? (Model) source : null, focusListeners);
            }
        });
    
        put(PROJECTED_WEALTH_CALC, new AbstractAction(NEW.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
        put(INVESTMENT_PROPERTIES_CALC, new AbstractAction(NEW.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                Object source = evt.getSource();
                InvPropertyView.display(source instanceof Model ? (Model) source : null, focusListeners);
            }
        });
    
        put(LOAN_AND_MORTGAGE_CALC, new AbstractAction(NEW.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                Object source = evt.getSource();
                MortgageView.display(source instanceof Model ? (Model) source : null, focusListeners);
            }
        });
    
        put(ALOCATED_PENSION_CALC, new AbstractAction(NEW.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                Object source = evt.getSource();
                AllocatedPensionViewNew view = AllocatedPensionViewNew.display(
                    source instanceof Model ? (Model) source : null, focusListeners);
                view.setClientNameFocus();
            }
        });
    
        put(ETP_ROLLOVER_CALC, new AbstractAction(NEW.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                Object source = evt.getSource();
                ETPRolloverViewNew view = ETPRolloverViewNew.display(
                    source instanceof Model ? (Model) source : null, focusListeners);
                view.setClientNameFocus();
            }
        });
    
        put(SUPERANNUATION_RBL_CALC, new AbstractAction(NEW.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
        put(RETIREMENT_CALC, new AbstractAction(NEW.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
        put(RETIREMENT_HOME_CALC, new AbstractAction(NEW.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {

            }
        });
    
        put(PAYG_CALC, new AbstractAction(NEW.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                Object source = evt.getSource();
                PAYGView.display(source instanceof Model ? (Model) source : null, focusListeners);
            }
        });
    
        put(CGT_CALC, new AbstractAction(NEW.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                Object source = evt.getSource();
                CGTView.display(source instanceof Model ? (Model) source : null, focusListeners);
            }
        });
    
        put(CENTRELINK_CALC, new AbstractAction(NEW.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                Object source = evt.getSource();
                DSSViewNew.display(source instanceof Model ? (Model) source : null, focusListeners);
            }
        });
    
        put(SWAP_ASSETS, new AbstractAction(SWAP_ASSETS.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                swapAssets();
            }
        });
    
        put(RECOVER_ASSETS, new AbstractAction(RECOVER_ASSETS.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                recoverAssets();
            }
        });
    
        put(REMOVE_ASSETS, new AbstractAction(REMOVE_ASSETS.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                deleteAssets();
            }
        });
    
        put(FEEDBACK, new AbstractAction(FEEDBACK.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                displayFeedback("support@argussoftware.net");
            }
        });
    
        // Tools
    
        // Help MenuItems
        put(CONTENTS_INDEX, new AbstractAction(CONTENTS_INDEX.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                displayContentsIndex();
            }
        });
    
        put(SEARCH_CONTENTS, new AbstractAction(SEARCH_CONTENTS.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                // displaySearchContents();
            }
        });
    
        put(ABOUT, new AbstractAction(ABOUT.getSecond().toString()) {
            public void actionPerformed(ActionEvent evt) {
                displayAbout();
            }
        });

    }
    
    private void setOutputView() throws IOException {
        if (outputView != null)
            return;

        String fileName = FPSLocale.getInstance().getLogDir() + File.separator
                + IOUtils.getUserName()
                + "_" + IOUtils.getHostName() + "_"
                + DateTimeUtils.getCurentDateTime() + "_out" + ".log";
        System.out.println("OutputView=" + fileName);
        
        OutputView view = new OutputView(fileName, true);

        outputView = new JDialog(app, "View System Output", false);
        outputView.getContentPane().add(view);
        outputView.pack();

        // position
        int w = (int) SwingUtils.DIM_SCREEN.getWidth();
        int h = (int) SwingUtils.DIM_SCREEN.getHeight();
        outputView.setLocation(w - (int) outputView.getWidth(), h
                - (int) outputView.getHeight());

        System.setOut(view.getPrintStream());

    }

    private void setErrorView() throws IOException {
        if (errorView != null)
            return;

        String fileName = FPSLocale.getInstance().getLogDir() + File.separator
                + IOUtils.getUserName()
                + "_" + IOUtils.getHostName() +
                // "_" + DateTime.getCurentDateTime() +
                // "_err" +
                ".log";
        System.out.println("ErrorView=" + fileName);

        ErrorView  view = new ErrorView(fileName, true);

        errorView = new JDialog(app, "View System Log File", false);
        errorView.getContentPane().add(view);
        errorView.pack();

        // position
        int h = (int) SwingUtils.DIM_SCREEN.getHeight();
        errorView.setLocation(0, h - (int) errorView.getHeight());

        System.setErr(view.getPrintStream());

    }

    /**
     * events
     */
    private static int eventID;
    private void fireActionEvent(Integer actionID) {

        javax.swing.Action action = (javax.swing.Action) get(actionID);
        if (action == null)
            return;

        action.actionPerformed(new ActionEvent(this, ++eventID, getClass().getName()));

    }

    private void displayPlanTracker() {
        // SwingUtilities.invokeLater( new Runnable() { public void run() {
        PlanTracker view = PlanTracker.getInstance();

        SwingUtil.add2Frame(view, focusListeners, view.getViewCaption(),
                ViewSettings.getInstance().getViewImage(
                        view.getClass().getName()), true, false, true);

        try {
            view.updateView(null);
        } catch (com.argus.financials.api.ServiceException e) {
            e.printStackTrace(System.err);
            return;
        }

    }

    private void logout() {
        UserLogin.getInstance().logout();
    }

    /**
     * user and client methods
     */
    private void displayClientList() {

        if (ClientSearch.display(app).getResult() == ClientSearch.OK_OPTION)
        {
            // HACK code -- update user in mainframe
            FinancialPlannerApp app = FinancialPlannerApp.getInstance();
            if (app != null) {
                app.updateClient();
                app.updateModels();
            }
        }

    }

    private void createNewClient() {
        
        if (JOptionPane
                .showConfirmDialog(
                        null,
                        "You are about to create a new client in your database, do you want to proceed ?",
                        "Add ClientView", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE) != JOptionPane.OK_OPTION)
            return;

        try {
            // new client will be created
            Integer newClientID = userService.persist(null).intValue();

            // close all visible forms (visible = false)
            SwingUtil.closeAll();

            displayClient(newClientID);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

    private static ExportDataView exportDataView;

    private void displayExportDataView() {

        if (exportDataView == null) {
            exportDataView = new ExportDataView();

            // Component comp, final java.awt.event.FocusListener [] listeners,
            // String title, String iconImage, boolean center, boolean
            // resizable, boolean visible ) {
            SwingUtil.add2Frame(exportDataView, focusListeners,
                    "Export Data", ViewSettings.getInstance().getViewImage(
                            exportDataView.getClass().getName()), true, true,
                    true);
        } else {
            exportDataView.reset();
            SwingUtil.setVisible(exportDataView, true);
        }

    }

    private static ImportDataView importDataView;

    private void displayImportDataView() {

        if (importDataView == null) {
            importDataView = new ImportDataView();

            // Component comp, final java.awt.event.FocusListener [] listeners,
            // String title, String iconImage, boolean center, boolean
            // resizable, boolean visible ) {
            SwingUtil.add2Frame(importDataView, focusListeners,
                    "Import Data", ViewSettings.getInstance().getViewImage(
                            importDataView.getClass().getName()), true, true,
                    true);
        } else {
            importDataView.reset();
            SwingUtil.setVisible(importDataView, true);
        }

    }

    public void displayClient(final Integer clientPersonID) {
        // SwingUtilities.invokeLater( new Runnable() { public void run() {
        ClientView.getClientView().display(clientPersonID,
                focusListeners, true);
        // } } );
    }

    private static com.argus.financials.ui.sql.AssetSwapView assetSwapView;

    private void swapAssets() {

        if (assetSwapView == null) {
            assetSwapView = new com.argus.financials.ui.sql.AssetSwapView();

            // Component comp, final java.awt.event.FocusListener [] listeners,
            // String title, String iconImage, boolean center, boolean
            // resizable, boolean visible ) {
            SwingUtil.add2Frame(assetSwapView, focusListeners,
                    "Swap Assets", ViewSettings.getInstance().getViewImage(
                            assetSwapView.getClass().getName()), true, true,
                    true);
        } else {
            assetSwapView.reset();
            SwingUtil.setVisible(assetSwapView, true);
        }

    }

    private static com.argus.financials.ui.sql.AssetRecoverView assetRecoverView;

    private void recoverAssets() {

        if (assetRecoverView == null) {
            assetRecoverView = new com.argus.financials.ui.sql.AssetRecoverView();

            // Component comp, final java.awt.event.FocusListener [] listeners,
            // String title, String iconImage, boolean center, boolean
            // resizable, boolean visible ) {
            SwingUtil.add2Frame(assetRecoverView, focusListeners,
                    "Recover Assets", ViewSettings.getInstance().getViewImage(
                            assetRecoverView.getClass().getName()), true, true,
                    true);
        } else {
            assetRecoverView.reset();
            SwingUtil.setVisible(assetRecoverView, true);
        }

    }

    private static com.argus.financials.ui.sql.AssetDeleteView assetDeleteView;

    private void deleteAssets() {

        if (assetDeleteView == null) {
            assetDeleteView = new com.argus.financials.ui.sql.AssetDeleteView();

            // Component comp, final java.awt.event.FocusListener [] listeners,
            // String title, String iconImage, boolean center, boolean
            // resizable, boolean visible ) {
            SwingUtil.add2Frame(assetDeleteView, focusListeners,
                    "Delete Assets", ViewSettings.getInstance().getViewImage(
                            assetDeleteView.getClass().getName()), true, true,
                    true);
        } else {
            assetDeleteView.reset();
            SwingUtil.setVisible(assetDeleteView, true);
        }

    }

    /***************************************************************************
     * HELP MENU ITEMS
     **************************************************************************/

    private void displayContentsIndex() {
        HelpBrokerView.display();
    }

    private void displaySearchContents() {
        SearchContentsView.display(focusListeners);
    }

    public void displayAbout() {
        AboutView.display(focusListeners);
    }

    // TODO: read from *.properties file
    // it is on system path usualy (Netscp.exe)
    private static String BROWSER = "iexplore.exe"; 
    public void displayWWW(String url) {
        String cmd = "start \"WWW\" /B " + BROWSER + " " + url;
        try {
            Process process = com.argus.io.DOSRunner.getInstance().run(cmd,
                    "/c");
        } catch (java.io.IOException e) {
            System.err.println(e.getMessage());
        }

    }

    private static String OUTLOOK = "outlook.exe";
    public void displayFeedback(String to) {
        String cmd = "start \"outlook\" /B \"" + OUTLOOK
                + "\" /c ipm.note /m \"" + to + "\"";
        try {
            Process process = com.argus.io.DOSRunner.getInstance().run(cmd,
                    "/c");
        } catch (java.io.IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public void updateAccessability() {

        try {
            IUser user = userPreferences.getUser();
            Integer userTypeId = user == null ? null : user.getTypeId();

            boolean adminPerson = AdviserTypeCode.isAdminPerson(userTypeId);
            //boolean supportPerson = AdviserTypeCode.isSupportPerson(userTypeId);

            IMaritalCode marital = clientService != null ? clientService.getPersonName().getMarital() : null;

            boolean isClient = clientService != null;
            boolean isSingle = isClient && IMaritalCode.isSingle(marital == null ? null : marital.getId());

            get(IMenuCommand.EXPORT).setEnabled(isClient);
            get(IMenuCommand.IMPORT).setEnabled(true);

            get(IMenuCommand.CLIENT_DETAILS).setEnabled(isClient);
            get(IMenuCommand.RISK_EVALUATION_CLIENT).setEnabled(isClient);
            get(IMenuCommand.FINANCIALS).setEnabled(isClient);

            get(IMenuCommand.PARTNER_DETAILS).setEnabled(isClient && !isSingle);
            get(IMenuCommand.RISK_EVALUATION_PARTNER).setEnabled(isClient && !isSingle);

            get(IMenuCommand.STRATEGY_DESINER).setEnabled(isClient);

            get(IMenuCommand.PLAN_WIZARD).setEnabled(isClient);
            get(IMenuCommand.PLAN_TEMPLATE_WIZARD).setEnabled(adminPerson || isClient);

        } catch (com.argus.financials.api.ServiceException e) {
            e.printStackTrace(System.err);
        }

    }

    /***************************************************************************
     * helper methods
     **************************************************************************/
    private boolean equals(Object value1, Object value2) {
        return (value1 == null && value2 == null)
                || (value1 != null && value1.equals(value2))
                || (value2 != null && value2.equals(value1));
    }

}
