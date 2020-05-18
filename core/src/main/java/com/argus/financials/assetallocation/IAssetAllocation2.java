/*
 * InterfaceAssetAllocationView2.java
 *
 * Created on 10 March 2003, 13:19
 */

package com.argus.financials.assetallocation;

/**
 * 
 * @author valeri chibaev
 */

public interface IAssetAllocation2 extends IAssetAllocation {

    public void setTotalInCash(double value);

    public void setTotalInFixedInterest(double value);

    public void setTotalInAustShares(double value);

    public void setTotalInIntnlShares(double value);

    public void setTotalInProperty(double value);

    public void setTotalInOther(double value);

    public void setTotalTotal(double value);

}
