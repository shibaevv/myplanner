/*
 * ClientView.java
 *
 * Created on 25 January 2002, 14:33
 */

package com.argus.financials.ui;

import com.argus.financials.api.InvalidCodeException;
import com.argus.financials.api.code.ObjectTypeConstant;
import com.argus.financials.config.ViewSettings;
import com.argus.financials.config.WordSettings;
import com.argus.financials.report.ReportFields;
import com.argus.financials.service.ClientService;
import com.argus.financials.service.PersonService;
import com.argus.financials.swing.SwingUtil;

public final class PartnerView extends PersonView2 {

    protected static int LAST = PersonView2.LAST;

    private static PartnerView view;

    private static ClientService clientService;
    public static void setClientService(ClientService clientService) {
        PartnerView.clientService = clientService;
    }

    /** Creates new ClientView */
    public PartnerView() {
        initComponents();
    }

    public static boolean exists() {
        return view != null;
    }

    public static PartnerView getPartnerView() {

        if (view == null)
            view = new PartnerView();

        return view;

    }

    public String getViewCaption() {
        return "ClientView Partner Details";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    private void initComponents() {
        getJTabbedPane().setTitleAt(0, "Partner Details");
    }

    /*
     * // has to be overriden in derived classes protected boolean isModified() {
     * try { PersonService partner =
     * RmiParams.getInstance().getClientPerson().getPartner( false ); return
     * partner == null ? false : partner.isModifiedRemote(); } catch (
     * com.argus.financials.service.ServiceException e ) { e.printStackTrace( System.err ); return
     * false; } }
     */

    // Variables declaration - do not modify
    // End of variables declaration

    public void display(final java.awt.event.FocusListener[] listeners, boolean inFrame) {

        if (!exists())
            getPartnerView();

        try {
            view.updateView();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // add/show view
        if (inFrame)
            // Component comp, final java.awt.event.FocusListener [] listeners,
            // String title, String iconImage, boolean center, boolean
            // resizable, boolean visible ) {
            SwingUtil.add2Frame(view, listeners, view.getViewCaption(),
                    ViewSettings.getInstance().getViewImage(
                            view.getClass().getName()), true, true, true);
        else
            ; // to JApplet

    }

    public void updateView() throws com.argus.financials.api.ServiceException {

        // will create new person if null
        PersonService person = getPerson();
        if (person == null)
            return;

        updateView(person);

    }

    public void saveView() throws com.argus.financials.api.ServiceException,
            InvalidCodeException {

        // will create new person if null
        PersonService person = getPerson();
        if (person == null)
            return;

        saveView(person);

        // save changes to database
        person.storePerson();

        // refresh data after store()
        updateView();

    }

    public void clearView() {
        super.clearView();

    }

    public Object getObject() {
        return null;
    }

    public void setObject(Object value) {

    }

    public Integer getObjectType() {
        return new Integer(ObjectTypeConstant.PERSON);
    }

    protected PersonService getPerson() throws com.argus.financials.api.ServiceException {
        return clientService.getPartner(true);
    }

    protected String getDefaultReport() {
        return WordSettings.getInstance().getClientDetailsReport();
    }

    protected ReportFields getReportData(PersonService person)
            throws java.io.IOException {

        ReportFields data = ReportFields.getInstance();
        data.initialize(clientService); // do
                                                                            // it
                                                                            // for
                                                                            // client
                                                                            // !!!

        return data;
    }

}
