/*
 * HelpBrokerView.java
 *
 * Created on August 16, 2002, 12:47 PM
 */

package au.com.totemsoft.myplanner.swing.help;

/**
 * 
 * @author Valeri CHIBAEV (mailto:apollosoft.net.au@gmail.com)
 * @version
 */

import java.awt.Component;

import au.com.totemsoft.myplanner.config.FPSLocale;

public class HelpBrokerView {

    private static final String DEFAULT_HELPSET = "help/FinancialPlanner.hs";

//    private static HelpBroker mainHB;

    /** Creates new HelpBrokerView */
    private HelpBrokerView() {
    }

    public static void initHelpMenu(Component mItem) {

        // Needs to be declared somewhere in the property file
        String helpsetName = FPSLocale.getInstance().getHelpsetName();

        if (helpsetName == null || helpsetName.trim().length() == 0) {
            helpsetName = DEFAULT_HELPSET;
            FPSLocale.getInstance().setHelpsetName(helpsetName);
        }

        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            if (loader == null)
                return;

//            HelpSet mainHS = new HelpSet(loader, HelpSet.findHelpSet(loader, helpsetName));
//            mainHB = mainHS.createHelpBroker();
//            mainHB.enableHelp(mItem, "top", mainHS);

        } catch (Exception e) {
            e.printStackTrace(System.err);
            return;

        } catch (ExceptionInInitializerError e) {
            e.printStackTrace(System.err);
            return;

        }

    }

    public static void display() {
//        mainHB.setDisplayed(true);
    }

}
