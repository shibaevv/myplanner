/*
 * StrategyModel.java
 *
 * Created on 10 April 2002, 08:27
 */

package au.com.totemsoft.myplanner.strategy;

import au.com.totemsoft.myplanner.assetallocation.AssetAllocation;
import au.com.totemsoft.myplanner.bean.AbstractBase;
import au.com.totemsoft.myplanner.bean.NegativeAmountException;
import au.com.totemsoft.myplanner.projection.save.Model;
import au.com.totemsoft.util.ReferenceCode;

public class StrategyModel extends AbstractBase {

    static final long serialVersionUID = 855443041940320086L;

    public static final String NEW_MODEL = "New Model ...";

    // model associated with this strategy model
    // saved to StrategyModel table as ModelTypeID/ModelData
    private Model model;

    private ReferenceCode refCode;

    private java.math.BigDecimal goalAmount;

    private java.math.BigDecimal totalInitialAmount;

    private java.math.BigDecimal totalContributionAmount;

    // asset allocation
    private Integer assetAllocationID;

    private AssetAllocation assetAllocation;

    /** Creates new StrategyModel */
    public StrategyModel(ReferenceCode refCode) {
        this.refCode = refCode;
    }

    public StrategyModel(Model model) {
        this.model = model;
    }

    public String toString() {
        return getTitle();
    }

    public String toHTMLString() {
        return "<html>"
                + "<b>"
                + getTitle()
                + "</b><br>"
                +
                // "<b>Total amount: </b>" + curr.toString( getTotalAmount() ) +
                // "<br>" +
                "<b>Total initial amount: </b>"
                + curr.toString(getTotalInitialAmount()) + "<br>"
                + "<b>Total contribution amount: </b>"
                + curr.toString(getTotalContributionAmount()) + "<br>"
                + "</html>";
    }

    /*
     * 
     */
    public ReferenceCode getReferenceCode() {
        if (refCode == null) {
            refCode = new ReferenceCode();
            refCode.setDescription(getTitle());
        }
        return refCode;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model value) {
        if (equals(model, value))
            return;

        model = value;

        setModified(true);
    }

    /*
     * 
     */
    public Integer getModelPrimaryKeyID() {
        return getModel() == null ? null : getModel().getId();
    }

    public String getModelTitle() {
        return getModel() == null ? null : getModel().getTitle();
    }

    public Integer getTypeID() {
        return getModel() == null ? null : getModel().getTypeID();
    }

    /*
     * 
     */
    public String getTitle() {
        if (refCode == null || refCode.getDescription() == null)
            return getModelTitle();
        return refCode.getDescription();
    }

    public void setTitle(String value) {
        if (refCode == null)
            refCode = new ReferenceCode();

        if (equals(refCode.getDescription(), value))
            return;

        refCode.setDescription(value);
        setModified(true);
    }

    public java.math.BigDecimal getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(java.math.BigDecimal value)
            throws NegativeAmountException {
        if (equals(goalAmount, value))
            return;

        goalAmount = value;
        setModified(true);
    }

    public java.math.BigDecimal getTotalInitialAmount() {
        return totalInitialAmount;
        // return new BigDecimal( getCalc().getInitialValue());
    }

    public void setTotalInitialAmount(java.math.BigDecimal value) {
        if (equals(totalInitialAmount, value))
            return;

        totalInitialAmount = value;
        setModified(true);
    }

    public java.math.BigDecimal getTotalContributionAmount() {
        return totalContributionAmount;
        // return new BigDecimal( getCalc().getRegularContribution() );
    }

    public void setTotalContributionAmount(java.math.BigDecimal value) {
        if (equals(totalContributionAmount, value))
            return;

        totalContributionAmount = value;
        setModified(true);
    }

    public AssetAllocation getAssetAllocation() {
        /*
         * if( this.assetAllocation == null ) { this.assetAllocation = new
         * AssetAllocation(); }
         */
        return this.assetAllocation;
    }

    public void setAssetAllocation(AssetAllocation value) {
        this.assetAllocation = value;
    }

    public Integer getAssetAllocationID() {
        return (assetAllocation == null) ? assetAllocationID : assetAllocation
                .getAssetAllocationID();
    }

    public void setAssetAllocationID(Integer value) {
        assetAllocationID = value;
        getAssetAllocation().setAssetAllocationID(value);
    }
}