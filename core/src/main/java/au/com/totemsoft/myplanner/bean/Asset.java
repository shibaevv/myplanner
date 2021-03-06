/*
 * FinancialAsset.java
 *
 * Created on 8 October 2001, 10:34
 */

package au.com.totemsoft.myplanner.bean;

import java.math.BigDecimal;

import au.com.totemsoft.myplanner.api.bean.ICode;
import au.com.totemsoft.myplanner.api.bean.IFPSAssignableObject;
import au.com.totemsoft.myplanner.api.code.ObjectTypeConstant;
import au.com.totemsoft.myplanner.assetallocation.AssetAllocation;
import au.com.totemsoft.myplanner.assetallocation.AssetAllocationTableRow;
import au.com.totemsoft.myplanner.assetallocation.FinancialAssetAllocation;
import au.com.totemsoft.myplanner.assetinvestment.UnitSharePrice;
import au.com.totemsoft.myplanner.bean.db.ApirPicBean;
import au.com.totemsoft.myplanner.bean.db.SharePriceDataBean;
import au.com.totemsoft.myplanner.bean.db.UnitPriceDataBean;
import au.com.totemsoft.myplanner.code.FrequencyCode;
import au.com.totemsoft.myplanner.code.FundType;
import au.com.totemsoft.myplanner.code.InvestmentType;
import au.com.totemsoft.util.DateTimeUtils;
import au.com.totemsoft.util.RateUtils;

public abstract class Asset extends Financial {

    static final long serialVersionUID = 2770681708721238798L;

    public static final Integer OBJECT_TYPE_ID = ObjectTypeConstant.ASSET;

    private String accountNumber;

    private Integer fundTypeID;

    private Integer investmentTypeID;

    private Double unitsShares;

    private BigDecimal purchaseCost;

    private BigDecimal replacementValue;

    private BigDecimal unitsSharesPrice;

    private java.util.Date priceDate;

    private BigDecimal taxDeductibleAnnualAmount;

    private Integer frequencyCodeID = FrequencyCode.YEARLY;

    private BigDecimal annualAmount;

    private BigDecimal contributionAnnualAmount;

    private BigDecimal contributionIndexation;

    private java.util.Date contributionStartDate;

    private java.util.Date contributionEndDate;

    private BigDecimal drawdownAnnualAmount;

    private BigDecimal drawdownIndexation;

    private java.util.Date drawdownStartDate;

    private java.util.Date drawdownEndDate;

    private boolean reinvest;

    /** Creates new FinancialAsset */
    protected Asset() {
        this(null);
    }

    protected Asset(Integer ownerPrimaryKeyID) {
        super(ownerPrimaryKeyID);
    }

    /**
     * Assignable methods
     */
    public void assign(IFPSAssignableObject value) throws ClassCastException {

        super.assign(value);

        if (!(this instanceof Asset))
            throw new ClassCastException("This is not a "
                    + this.getClass().getName());

        Asset a = (Asset) value;

        accountNumber = a.accountNumber;

        fundTypeID = a.fundTypeID;
        investmentTypeID = a.investmentTypeID;
        unitsShares = a.unitsShares;

        purchaseCost = a.purchaseCost;
        replacementValue = a.replacementValue;

        unitsSharesPrice = a.unitsSharesPrice;
        priceDate = a.priceDate;

        taxDeductibleAnnualAmount = a.taxDeductibleAnnualAmount;

        frequencyCodeID = a.frequencyCodeID;
        annualAmount = a.annualAmount;

        contributionAnnualAmount = a.contributionAnnualAmount;
        contributionIndexation = a.contributionIndexation;
        contributionStartDate = a.contributionStartDate;
        contributionEndDate = a.contributionEndDate;

        drawdownAnnualAmount = a.drawdownAnnualAmount;
        drawdownIndexation = a.drawdownIndexation;
        drawdownStartDate = a.drawdownStartDate;
        drawdownEndDate = a.drawdownEndDate;

        reinvest = a.reinvest;

        // has to be last (to set modified)
        setModified(true);
    }

    /**
     * helper methods
     */
    public void clear() {
        super.clear();

        accountNumber = null;

        fundTypeID = null;
        investmentTypeID = null;
        unitsShares = null;

        purchaseCost = ZERO;
        replacementValue = ZERO;

        unitsSharesPrice = ZERO;
        priceDate = null;

        taxDeductibleAnnualAmount = ZERO;

        frequencyCodeID = FrequencyCode.YEARLY;
        annualAmount = ZERO;

        contributionAnnualAmount = ZERO;
        contributionIndexation = ZERO;
        contributionStartDate = null;
        contributionEndDate = null;

        drawdownAnnualAmount = ZERO;
        drawdownIndexation = ZERO;
        drawdownStartDate = null;
        drawdownEndDate = null;

        reinvest = false;

        // has to be last (to set modified)
        setModified(true);
    }

    /***************************************************************************
     * 
     **************************************************************************/
    // TODO: move into AssetBean ???
    public void updatePrice() {
        ICode financialCode = getFinancialCode();
        if (financialCode == null)
            return;

        UnitSharePrice unitSharePrice = null;

        // we don't know the database table
        try {
            boolean is_managed_fund = false;

            String code = financialCode.getCode();

            // try to find it by investment code
            ApirPicBean apirPicBean = new ApirPicBean();
            if (apirPicBean.findByApirPic(code)) {
                code = Integer.toString(apirPicBean.getCode());
                unitSharePrice = new UnitPriceDataBean();
                is_managed_fund = true;
            } else {
                unitSharePrice = new SharePriceDataBean();
            }

            // find unit/share in database
            if (!unitSharePrice.findUnitShareByCode(code))
                return;

            setPriceDate(unitSharePrice.getPriceDate2());
            setUnitsSharesPrice(new BigDecimal(unitSharePrice
                    .getClosePrice()));

            Double unitShares = getUnitsShares();
            setAmount(new BigDecimal(unitShares == null ? 0.
                    : unitShares.doubleValue() * unitSharePrice.getClosePrice()));

            // We do the update here, because we update the amount value in
            // the asset allocation object. - To keep consistent data!
            if (is_managed_fund) {
                this.updateAssetAllocation();
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace(System.err);
        }

    }

    /***************************************************************************
     * get/set methods
     **************************************************************************/
    @Override
    public BigDecimal getAmount() {
        BigDecimal value = super.getAmount();
        if (value == null || value.signum() == 0) {
            if (unitsShares != null && unitsShares > 0 && purchaseCost != null && purchaseCost.signum() == 1) {
                value = purchaseCost.multiply(new BigDecimal(unitsShares));
            }
        }
        return value;
    }

    public void setAmount(BigDecimal value) {
        if (equals(getAmount(), value))
            return;

        super.setAmount(value);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String value) {

        if (value != null)
            value = value.trim();

        if (equals(accountNumber, value))
            return;

        accountNumber = value;
        setModified(true);

    }

    public Double getUnitsShares() {
        return unitsShares;
    }

    public void setUnitsShares(Double value) {
        if (equals(unitsShares, value))
            return;

        unitsShares = value;
        setModified(true);
    }

    public BigDecimal getUnitsSharesPrice() {
        return unitsSharesPrice;
    }

    public void setUnitsSharesPrice(BigDecimal value) {
        if (equals(unitsSharesPrice, value))
            return;

        unitsSharesPrice = value;
        setModified(true);
    }

    public java.util.Date getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(java.util.Date value) {
        if (equals(priceDate, value))
            return;

        priceDate = value;
        setModified(true);
    }

    public Integer getFundTypeID() {
        return fundTypeID;
    }

    protected void setFundTypeID(Integer value) {
        if (equals(fundTypeID, value))
            return;

        fundTypeID = value;
        setModified(true);
    }

    public String getFundTypeDesc() {
        return new FundType().getCodeDescription(getFundTypeID());
    }

    public Integer getInvestmentTypeID() {
        return investmentTypeID;
    }

    protected void setInvestmentTypeID(Integer value) {
        if (equals(investmentTypeID, value))
            return;

        investmentTypeID = value;
        setModified(true);
    }

    public String getInvestmentTypeDesc() {
        return new InvestmentType().getCodeDescription(getInvestmentTypeID());
    }

    public BigDecimal getPurchaseCost() {
        return purchaseCost;
    }

    protected void setPurchaseCost(BigDecimal value) {
        if (equals(purchaseCost, value))
            return;

        purchaseCost = value;
        setModified(true);
    }

    public BigDecimal getReplacementValue() {
        return replacementValue;
    }

    protected void setReplacementValue(BigDecimal value) {
        if (equals(replacementValue, value))
            return;

        replacementValue = value;
        setModified(true);
    }

    public BigDecimal getContributionRegularAmount() {
        return FrequencyCode.getPeriodAmount(frequencyCodeID,
                getContributionAnnualAmount());
    }

    // read only value (stored in db)
    protected void setContributionRegularAmount(BigDecimal value) {
    }

    public BigDecimal getTaxDeductibleAnnualAmount() {
        return taxDeductibleAnnualAmount;
    }

    protected void setTaxDeductibleAnnualAmount(BigDecimal value) {
        if (equals(taxDeductibleAnnualAmount, value))
            return;
        taxDeductibleAnnualAmount = value;
        setModified(true);
    }

    public BigDecimal getTaxDeductibleRegularAmount() {
        return FrequencyCode.getPeriodAmount(frequencyCodeID,
                taxDeductibleAnnualAmount);
    }

    public Integer getFrequencyCodeID() {
        return frequencyCodeID;
    }

    protected void setFrequencyCodeID(Integer value) {
        if (equals(frequencyCodeID, value))
            return;

        frequencyCodeID = value;
        setModified(true);
    }

    public String getFrequencyCode() {
        return new FrequencyCode().getCodeDescription(frequencyCodeID);
    }

    public BigDecimal getAnnualAmount() {
        return annualAmount;
    }

    protected void setAnnualAmount(BigDecimal value) {
        if (equals(annualAmount, value))
            return;
        annualAmount = value;
        setModified(true);
    }

    protected BigDecimal getContributionAnnualAmount(int year) { // 0,1,...
        if (!DateTimeUtils.between(getContributionStartDate(),
                getContributionEndDate(), year))
            return ZERO;
        // return getContributionAnnualAmount();
        // return MoneyCalc.getIndexedValue( getContributionAnnualAmount(),
        // getContributionIndexation(), year );
        return new BigDecimal(RateUtils.getCompoundedAmount(
                getContributionAnnualAmount(), getContributionIndexation()
                        .divide(HUNDRED, BigDecimal.ROUND_HALF_UP), year));
    }

    public BigDecimal getContributionAnnualAmount() {
        return contributionAnnualAmount;
    }

    protected void setContributionAnnualAmount(BigDecimal value) {
        if (equals(contributionAnnualAmount, value))
            return;
        contributionAnnualAmount = value;
        setModified(true);
    }

    public BigDecimal getContributionIndexation() {
        return contributionIndexation;
    }

    protected void setContributionIndexation(BigDecimal value) {
        if (equals(contributionIndexation, value))
            return;
        contributionIndexation = value;
        setModified(true);
    }

    public java.util.Date getContributionStartDate() {
        return contributionStartDate;
    }

    protected void setContributionStartDate(java.util.Date value) {
        if (equals(contributionStartDate, value))
            return;
        contributionStartDate = value;
        setModified(true);
    }

    public java.util.Date getContributionEndDate() {
        return contributionEndDate;
    }

    protected void setContributionEndDate(java.util.Date value) {
        if (equals(contributionEndDate, value))
            return;
        contributionEndDate = value;
        setModified(true);
    }

    protected BigDecimal getDrawdownAnnualAmount(int year) { // 0,1,...
        if (!DateTimeUtils.between(getDrawdownStartDate(),
                getDrawdownEndDate(), year))
            return ZERO;
        // return getDrawdownAnnualAmount();
        // return MoneyCalc.getIndexedValue( getDrawdownAnnualAmount(),
        // getDrawdownIndexation(), year );
        return new BigDecimal(RateUtils.getCompoundedAmount(
                getDrawdownAnnualAmount(), getDrawdownIndexation().divide(
                        HUNDRED, BigDecimal.ROUND_HALF_UP), year));
    }

    public BigDecimal getDrawdownAnnualAmount() {
        return drawdownAnnualAmount;
    }

    protected void setDrawdownAnnualAmount(BigDecimal value) {
        if (equals(drawdownAnnualAmount, value))
            return;
        drawdownAnnualAmount = value;
        setModified(true);
    }

    public BigDecimal getDrawdownIndexation() {
        return drawdownIndexation;
    }

    protected void setDrawdownIndexation(BigDecimal value) {
        if (equals(drawdownIndexation, value))
            return;
        drawdownIndexation = value;
        setModified(true);
    }

    public java.util.Date getDrawdownStartDate() {
        return drawdownStartDate;
    }

    protected void setDrawdownStartDate(java.util.Date value) {
        if (equals(drawdownStartDate, value))
            return;
        drawdownStartDate = value;
        setModified(true);
    }

    public java.util.Date getDrawdownEndDate() {
        return drawdownEndDate;
    }

    protected void setDrawdownEndDate(java.util.Date value) {
        if (equals(drawdownEndDate, value))
            return;
        drawdownEndDate = value;
        setModified(true);
    }

    protected boolean isReinvest() {
        return reinvest;
    }

    protected void setReinvest(boolean value) {
        if (reinvest = value)
            return;
        reinvest = value;
        setModified(true);
    }

    public BigDecimal getFinancialYearAmount() {
        return getFinancialYearAmountFractional(false);
    }

    public BigDecimal getFinancialYearAmount(boolean sign) {
        return getFinancialYearAmountFractional(false, sign);
    }

    /**
     * Updates the asset allocation for managed funds ONLY!!!
     */
    private void updateAssetAllocation() {
        AssetAllocation aa = this.getAssetAllocation();

        // try to find asset allocation in (iress or) morning database tables
        FinancialAssetAllocation faa = new FinancialAssetAllocation();
        AssetAllocationTableRow faa_aatr = null;

        Integer objectTypeId = new Integer(ObjectTypeConstant.ASSET_INVESTMENT);
        // check if the current object is an investment asset
        if (objectTypeId.equals(this.getObjectTypeID())) {
            // yes, than we have a financial type and use it to find the asset
            // allocation
            faa_aatr = faa.findAssetAllocation(this);
        } else {
            // no, than we use the financial code to find the asset allocation
            faa_aatr = faa.findByFinancialCode(this.getFinancialCode());
        }
        //
        // update asset allocation
        //
        aa.setAmount((this.getAmount() == null) ? new Double(0.0) : new Double(
                this.getAmount().doubleValue()));
        aa.setInCash(new Double(faa_aatr.percent_in_cash.doubleValue()));
        aa.setInFixedInterest(new Double(faa_aatr.percent_in_fixed_interest
                .doubleValue()));
        aa.setInAustShares(new Double(faa_aatr.percent_in_aust_shares
                .doubleValue()));
        aa.setInIntnlShares(new Double(faa_aatr.percent_in_intnl_shares
                .doubleValue()));
        aa
                .setInProperty(new Double(faa_aatr.percent_in_property
                        .doubleValue()));
        aa.setInOther(new Double(faa_aatr.percent_in_other.doubleValue()));
    }
}
