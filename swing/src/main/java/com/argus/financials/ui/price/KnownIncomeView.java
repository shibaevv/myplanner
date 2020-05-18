/*
 * KnownIncomeView.java
 *
 * Created on 6 August 2003, 11:01
 */

package com.argus.financials.ui.price;

import com.argus.financials.model.IncomeTableModel;
import com.argus.financials.ui.BaseFinancialsView;
import com.argus.util.RateUtils;

public class KnownIncomeView extends BaseFinancialsView {
    
    public static void main( String [] args ) {
        main( args, new KnownIncomeView() );
    }

    
    private IncomeTableModel income;
    
    
    /** Creates new form KnownIncomeView */
    public KnownIncomeView() {
        initComponents();

        //
        income = new IncomeTableModel();
        tblIncome.setModel( income );
        _addMouseListener( jScrollPane );
        _addMouseListener( tblIncome );
/*
        tblIncome.addKeyListener( new java.awt.event.KeyAdapter() {
            public void keyReleased( java.awt.event.KeyEvent evt ) {
                System.out.println( "" + evt.getKeyChar() + " " + evt.getKeyCode() );
            }
        } );
*/
        
        // add listeners
        assetPrice.addFocusListener( this );
        years.addFocusListener( this );
        interestRc.addFocusListener( this );
        dividends.addFocusListener( this );
        price.addFocusListener( this );
        
        setToolTipText("Known income calculator");

        // refresh/init
        execute();
        
    }
    
    private void _addMouseListener( java.awt.Component component ) {
        component.addMouseListener(
            new java.awt.event.MouseAdapter() {
                public void mousePressed( java.awt.event.MouseEvent e ) {
                    showPopup(e);
                }

                public void mouseReleased( java.awt.event.MouseEvent e ) {
                    showPopup(e);
                }

                private void showPopup( java.awt.event.MouseEvent e ) {
                    if ( e.isPopupTrigger() )
                        pmTable.show( e.getComponent(), e.getX(), e.getY() );
                }
            }
        );
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        pmTable = new javax.swing.JPopupMenu();
        miAdd = new javax.swing.JMenuItem();
        miRemove = new javax.swing.JMenuItem();
        jLabelInterestRate2 = new javax.swing.JLabel();
        dividends = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel221 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        jLabelInterestRate = new javax.swing.JLabel();
        interestRc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        assetPrice = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        years = new javax.swing.JTextField();
        jScrollPane = new javax.swing.JScrollPane();
        tblIncome = new javax.swing.JTable();

        miAdd.setText("Add");
        miAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAddActionPerformed(evt);
            }
        });

        pmTable.add(miAdd);
        miRemove.setText("Remove");
        miRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRemoveActionPerformed(evt);
            }
        });

        pmTable.add(miRemove);

        setLayout(new java.awt.GridBagLayout());

        jLabelInterestRate2.setText("Present Value of Dividends");
        jLabelInterestRate2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        add(jLabelInterestRate2, gridBagConstraints);

        dividends.setToolTipText("Present Value of Dividends");
        dividends.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        dividends.setInputVerifier(nif);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(dividends, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        add(jSeparator2, gridBagConstraints);

        jLabel221.setText("Price");
        jLabel221.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        add(jLabel221, gridBagConstraints);

        price.setToolTipText("Forward or future price");
        price.setEditable(false);
        price.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        price.setInputVerifier(nif);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(price, gridBagConstraints);

        jLabelInterestRate.setText("Interest Rate, %");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        add(jLabelInterestRate, gridBagConstraints);

        interestRc.setToolTipText("Risk-free rate of interest per annum, expressed with continuous compounding");
        interestRc.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        interestRc.setInputVerifier(nif);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(interestRc, gridBagConstraints);

        jLabel2.setText("Asset Price");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        add(jLabel2, gridBagConstraints);

        assetPrice.setToolTipText("Price of the asset underlying the forvard or futures contract today");
        assetPrice.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        assetPrice.setInputVerifier(nif);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(assetPrice, gridBagConstraints);

        jLabel21.setText("Contract Life (years)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        add(jLabel21, gridBagConstraints);

        years.setToolTipText("Life of a forward or future contract");
        years.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        years.setInputVerifier(nif);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(years, gridBagConstraints);

        jScrollPane.setToolTipText("Right click to Add/Remove Row");
        tblIncome.setToolTipText("Right click to Add/Remove Row");
        jScrollPane.setViewportView(tblIncome);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane, gridBagConstraints);

    }//GEN-END:initComponents

    private void miRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRemoveActionPerformed
        int row = tblIncome.getSelectedRow();
        if ( row >= 0 )
            income.removeRow( row );
        if ( tblIncome.getRowCount() >= 1 )
            tblIncome.changeSelection( tblIncome.getRowCount() - 1 , 1, false, false );
    }//GEN-LAST:event_miRemoveActionPerformed

    private void miAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAddActionPerformed
        income.addRow();
        tblIncome.changeSelection( tblIncome.getRowCount() - 1 , 1, false, false );
    }//GEN-LAST:event_miAddActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField years;
    private javax.swing.JPopupMenu pmTable;
    private javax.swing.JMenuItem miRemove;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JTextField assetPrice;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTextField interestRc;
    private javax.swing.JTable tblIncome;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel221;
    private javax.swing.JMenuItem miAdd;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField dividends;
    private javax.swing.JLabel jLabelInterestRate;
    private javax.swing.JTextField price;
    private javax.swing.JLabel jLabelInterestRate2;
    // End of variables declaration//GEN-END:variables
    
    /**
     *
     */
    protected void execute() {
        
        try {
            double S0 = nif.doubleValue( assetPrice.getText() );
            double T = nif.doubleValue( years.getText() );
            double Rc = nif.doubleValue( interestRc.getText() ) / 100.;
            double I = nif.doubleValue( dividends.getText() );
            
            double f0 = RateUtils.getPrice( S0, I, Rc, T ); // 3.6
            
            price.setText( nif.getNumberFormatter().toString( f0 ) );
            
        } catch ( NumberFormatException e ) { 
//System.err.println( e.getMessage() ); 
            price.setText( "" );
        }
        
    }
    
}
