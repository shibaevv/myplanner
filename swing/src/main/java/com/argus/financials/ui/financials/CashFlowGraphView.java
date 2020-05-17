/*
 * CashFlowGraphView.java
 *
 * Created on 18 April 2002, 12:35
 */

package com.argus.financials.ui.financials;

/**
 * 
 * @version
 */

import com.klg.jclass.chart.JCChart;
import com.klg.jclass.chart.beans.SimpleChart;

public class CashFlowGraphView extends com.argus.financials.chart.GraphView {

    /** Creates new StrategyResultView */
    public CashFlowGraphView() {
        super();

        initComponents();

    }

    private void initComponents() {

    }

    // End of variables declaration

    /**
     * 
     */
    public void setChartType(int chartType) {
        if (chart instanceof SimpleChart)
            ((SimpleChart) chart).setChartType(chartType);
        else
            super.setChartType(chartType);
    }

    protected JCChart createChart() {

        JCChart _chart = super.createChart();

        setTitleAxisX("");

        return _chart;

    }

}
