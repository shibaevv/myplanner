/*
 * SmartTableModel.java
 *
 * Created on 29 November 2002, 13:34
 */

package au.com.totemsoft.myplanner.table.swing;

/**
 * 
 * @author Valeri CHIBAEV (mailto:apollosoft.net.au@gmail.com)
 */

public interface ISmartTableModel extends javax.swing.table.TableModel {

    /**
     * Returns the value for the row at rowIndex. Parameters: rowIndex - the row
     * whose value is to be queried Returns: the value Object at the specified
     * row
     */
    public ISmartTableRow getRowAt(int rowIndex);

    /**
     * Sets the value in the row at rowIndex to aValue. Parameters: aValue - the
     * new value rowIndex - the row whose value is to be changed
     */
    public void setRowAt(ISmartTableRow aValue, int rowIndex);

    /**
     * Delete null/empty rows
     */
    public void pack();

}
