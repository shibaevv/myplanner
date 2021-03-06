/*
 * TaxAnalysisData.java
 *
 * Created on 6 November 2002, 12:18
 */

package au.com.totemsoft.myplanner.report.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import au.com.totemsoft.myplanner.api.bean.IMaritalCode;
import au.com.totemsoft.myplanner.api.bean.IPerson;
import au.com.totemsoft.myplanner.api.bean.IPersonHealth;
import au.com.totemsoft.myplanner.api.code.FinancialClassID;
import au.com.totemsoft.myplanner.api.code.FinancialTypeEnum;
import au.com.totemsoft.myplanner.bean.Assumptions;
import au.com.totemsoft.myplanner.bean.IRegularType;
import au.com.totemsoft.myplanner.bean.RegularExpense;
import au.com.totemsoft.myplanner.bean.RegularIncome;
import au.com.totemsoft.myplanner.bean.TaxOffset;
import au.com.totemsoft.myplanner.code.FrequencyCode;
import au.com.totemsoft.myplanner.code.OwnerCode;
import au.com.totemsoft.myplanner.report.ReportFields;
import au.com.totemsoft.myplanner.service.ClientService;
import au.com.totemsoft.myplanner.service.PersonService;
import au.com.totemsoft.myplanner.table.model.FinancialColumnID;
import au.com.totemsoft.myplanner.table.model.FinancialTableModel2;
import au.com.totemsoft.myplanner.table.swing.ISmartTableModel;
import au.com.totemsoft.myplanner.table.swing.ISmartTableRow;
import au.com.totemsoft.myplanner.table.swing.JointTableModel;
import au.com.totemsoft.myplanner.table.swing.ProxyTableModel;
import au.com.totemsoft.myplanner.table.swing.SmartTableModel;
import au.com.totemsoft.myplanner.tax.au.ITaxConstants;
import au.com.totemsoft.myplanner.tax.au.TaxContainer;
import au.com.totemsoft.myplanner.tree.FinancialTreeFilter;
import au.com.totemsoft.myplanner.tree.FinancialTreeFilters;
import au.com.totemsoft.myplanner.tree.FinancialTreeModel;
import au.com.totemsoft.myplanner.tree.FinancialTreeStructure;
import au.com.totemsoft.util.ReferenceCode;

public class TaxAnalysisData extends au.com.totemsoft.myplanner.bean.AbstractBase
        implements au.com.totemsoft.myplanner.report.Reportable,
        javax.swing.event.ChangeListener {
    protected static final au.com.totemsoft.math.Money _money;
    static {
        _money = new au.com.totemsoft.math.Money();
        _money.setMaximumFractionDigits(0);
        _money.setMinimumFractionDigits(0);
    }

    private static final int[] taxRules = IRegularType.RULES[IRegularType.iTAXANALYSIS];

    private static final String[] taxTypes = { ITaxConstants.O_TAX_WITHHELD, //
            ITaxConstants.O_LOW_INCOME, // 
            // TaxConstants.O_IMPUTATION_CREDIT, // IMPUTATION_CREDIT_REBATE
            ITaxConstants.O_LUMP_SUM_ETP, // 
            ITaxConstants.O_OTHER, // 
            ITaxConstants.O_SUPER, // PENSION_REBATE
    };

    public static final int NAME = 0;

    public static final int CLIENT = 1;

    public static final int PARTNER = 2;

    public static final int TOTAL = 3;

    private static Vector columnNames;

    private static Vector columnClasses;
    static {
        columnNames = new Vector();
        columnNames.add(NAME, "");
        columnNames.add(CLIENT, "ClientView");
        columnNames.add(PARTNER, "Partner");
        columnNames.add(TOTAL, "Total");

        columnClasses = new Vector();
        columnClasses.add(NAME, java.lang.String.class);
        columnClasses.add(CLIENT, java.math.BigDecimal.class);
        columnClasses.add(PARTNER, java.math.BigDecimal.class);
        columnClasses.add(TOTAL, java.math.BigDecimal.class);

    }

    private String prefix;

    public String getReportFieldsPrefix() {
        return prefix;
    }

    private Map financials;

    private Assumptions assumptions;

    private FinancialTreeModel model;

    private boolean married;

    private TaxContainer tcClient = new TaxContainer();

    private TaxContainer tcPartner = new TaxContainer();

    transient private ISmartTableModel summaryTableModel;

    transient private ISmartTableModel taxTableModel;

    transient private ISmartTableModel taxSummaryTableModel;

    transient private DetailsTableModel incomeTableModel;

    transient private DetailsTableModel expenseTableModel;

    transient private DetailsTableModel offsetTableModel;

    /**
     * Creates a new instance of TaxAnalysisData
     * 
     * TaxAnalysisViev <-- TaxAnalysisData <-- Assumptions --> AssumptionView
     * 
     */
    public TaxAnalysisData(Map _financials, Assumptions assumptions,
            String prefix) {

        this.financials = _financials;
        this.prefix = prefix == null ? ReportFields.CURRENT_PREFIX : prefix;

        this.assumptions = assumptions;
        this.assumptions.addChangeListener(this);

    }

    public void clear() {
        super.clear();

        summaryTableModel = null;
        taxTableModel = null;
        taxSummaryTableModel = null;

        incomeTableModel = null;
        expenseTableModel = null;
        offsetTableModel = null;

    }

    public Map getFinancials() {
        return financials;
    }

    public Assumptions getAssumptions() {
        return assumptions;
    }

    private PersonService person;

    public void update() throws au.com.totemsoft.myplanner.api.ServiceException {
        assumptions.disableNotify();
        try {
            assumptions.update(person);
        } finally {
            assumptions.enableNotify();
        }
    }

    public void update(PersonService _person, Map _financials)
            throws au.com.totemsoft.myplanner.api.ServiceException {
        this.person = _person;
        this.financials = _financials;
        update();
    }

    /***************************************************************************
     * javax.swing.event.ChangeListener interface
     **************************************************************************/
    public void stateChanged(javax.swing.event.ChangeEvent changeEvent) {

        if (changeEvent != null && changeEvent.getSource() != assumptions)
            ; // throw ???

        try {
            updateTreeModel();
        } catch (au.com.totemsoft.myplanner.api.ServiceException e) {
            e.printStackTrace(System.err);
            return;
        }
        notifyChangeListeners();

    }

    private void updateTreeModel() throws au.com.totemsoft.myplanner.api.ServiceException {

        clear();

        // //////////////////////////////////////////////////////////////////////
        // initialize tax containers
        // //////////////////////////////////////////////////////////////////////
        IPerson personName = person.getPersonName();
        IPersonHealth personHealth = personName == null ? null : personName.getPersonHealth();
        TreeMap dependants = person.getDependents();

        married = !IMaritalCode.isSingle(personName.getMarital().getId());
        Number age = personName.getAge();
        boolean hospitalCover = personHealth == null ? false : personHealth.isHospitalCover();
        tcClient.add(TaxContainer.PERSONAL, ITaxConstants.P_HOSPITAL_COVER, hospitalCover ? 1. : 0.); // 0. = false, 1. = true
        tcClient.add(TaxContainer.PERSONAL, ITaxConstants.P_MARITAL_STATUS, married ? 1. : 0.); // 0. = false, 1. = true
        tcClient.add(TaxContainer.PERSONAL, ITaxConstants.P_DEPENDANTS, dependants == null ? 0. : dependants.size());
        tcClient.add(TaxContainer.PERSONAL, ITaxConstants.P_AGE, age == null ? 0. : age.doubleValue());

        if (married && person instanceof ClientService) {
            PersonService clientPartner = ((ClientService) person).getPartner(false);
            IPerson clientPartnerName = clientPartner.getPersonName();
            IPersonHealth clientPartnerHealth = clientPartnerName == null ? null : clientPartnerName.getPersonHealth();
            age = clientPartner.getPersonName().getAge();
            hospitalCover = clientPartnerHealth == null ? false : clientPartnerHealth.isHospitalCover();
            dependants = clientPartner.getDependents();
            tcPartner.add(TaxContainer.PERSONAL, ITaxConstants.P_HOSPITAL_COVER, hospitalCover ? 1. : 0.); // 0. = false, 1. = true
            tcPartner.add(TaxContainer.PERSONAL, ITaxConstants.P_MARITAL_STATUS, married ? 1. : 0.); // 0. = false, 1. = true
            tcPartner.add(TaxContainer.PERSONAL, ITaxConstants.P_DEPENDANTS, dependants == null ? 0. : dependants.size());
            tcPartner.add(TaxContainer.PERSONAL, ITaxConstants.P_AGE, age == null ? 0. : age.doubleValue());
        }

        // clear non-person data only
        tcClient.reset();
        tcPartner.reset();

        // financials = Financials.deepCopy( map, false );

        refreshModel();

        // refresh these tables
        getIncomeTableModel();
        getExpenseTableModel();

        // add original data
        getOffsetTableModel();
        // reset (will be regenerated with extra data from tax container)
        offsetTableModel = null;

        // add spouse income into tax containers
        double clientIncomeAmount = incomeTableModel.getTotal(CLIENT);
        double partnerIncomeAmount = incomeTableModel.getTotal(PARTNER);
        double clientExpenseAmount = expenseTableModel.getTotal(CLIENT);
        double partnerExpenseAmount = expenseTableModel.getTotal(PARTNER);

        assumptions.setClientIncome(clientIncomeAmount);
        tcClient.add(TaxContainer.PERSONAL, ITaxConstants.P_SPOUSE_INCOME,
                partnerIncomeAmount - partnerExpenseAmount);
        if (married) {
            assumptions.setPartnerIncome(partnerIncomeAmount);
            tcPartner.add(TaxContainer.PERSONAL, ITaxConstants.P_SPOUSE_INCOME,
                    clientIncomeAmount - clientExpenseAmount);
        }

        // do calculate
        tcClient.calculate();
        tcPartner.calculate();

        // add generated tax offsets (tax containers)
        // Get calculated low income rebate and add it to as new Offset
        // financial
        OffsetTableModel tm = (OffsetTableModel) getOffsetTableModel();
        tm.getFinancialTreeModel().setDataGenerator(model.getDataGenerator());
        tm.build();

    }

    private void refreshModel() {

        if (model != null)
            return;

        model = new FinancialTreeModel();

        model.setRules(taxRules);

        FinancialTreeStructure fts = new FinancialTreeStructure();
        model.setStructure(fts);
        fts.add(FinancialTreeStructure.FINANCIAL_CLASS);
        fts.add(FinancialTreeStructure.FINANCIAL_TYPE);

        // model.setDataGenerator( null ); // ???
        model.setDataGenerator(new TaxOffsetDataGenerator());
        model.setFinancials(financials);

    }

    // //////////////////////////////////////////////////////////////////////////
    //
    // //////////////////////////////////////////////////////////////////////////
    public ISmartTableModel getSummaryTableModel() {
        if (summaryTableModel == null)
            summaryTableModel = new SummaryTableModel();
        return summaryTableModel;
    }

    public ISmartTableModel getTaxTableModel() {
        if (taxTableModel == null)
            taxTableModel = new TaxTableModel();
        return taxTableModel;
    }

    public ISmartTableModel getTaxSummaryTableModel() {
        if (taxSummaryTableModel == null)
            taxSummaryTableModel = new TaxSummaryTableModel();
        return taxSummaryTableModel;
    }

    public DetailsTableModel getIncomeTableModel() {
        if (incomeTableModel == null)
            incomeTableModel = new IncomeTableModel();
        return incomeTableModel;
    }

    public DetailsTableModel getExpenseTableModel() {
        if (expenseTableModel == null)
            expenseTableModel = new ExpenseTableModel();
        return expenseTableModel;
    }

    public DetailsTableModel getOffsetTableModel() {
        if (offsetTableModel == null)
            offsetTableModel = new OffsetTableModel();
        return offsetTableModel;
    }

    public ISmartTableModel getDetailsTableModel() {
        return new JointTableModel(new ISmartTableModel[] {
                getIncomeTableModel(), getExpenseTableModel(),
                getOffsetTableModel(), });
    }

    // //////////////////////////////////////////////////////////////////////////
    //
    // //////////////////////////////////////////////////////////////////////////
    private static final int[] summaryRowTypes = new int[] {
            ISmartTableRow.HEADER1,
            // ISmartTableRow.HEADER2, // does not show totals, because footer
            // exists
            ISmartTableRow.FOOTER2, ISmartTableRow.FOOTER1 };

    class SummaryTableModel extends JointTableModel {

        private double clientLowIncomeRebate;

        private double partnerLowIncomeRebate;

        SummaryTableModel() {

            super(new ISmartTableModel[] {
                    new ProxyTableModel(getIncomeTableModel(), null,
                            summaryRowTypes),
                    new ProxyTableModel(getExpenseTableModel(), null,
                            summaryRowTypes),
                    getTaxTableModel(),
                    new ProxyTableModel(getOffsetTableModel(), null,
                            summaryRowTypes), getTaxSummaryTableModel(), });

        }

        public int getRowCount() {
            return super.getRowCount();
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return super.getValueAt(rowIndex, columnIndex);
        }

        public void setValueAt(Object obj, int rowIndex, int columnIndex) {
            super.setValueAt(obj, rowIndex, columnIndex);
        }

        public ISmartTableRow getRowAt(int rowIndex) {
            return super.getRowAt(rowIndex);
        }

        public void setRowAt(ISmartTableRow aValue, int rowIndex) {
            super.setRowAt(aValue, rowIndex);
        }

    }

    class TaxSummaryTableModel extends SmartTableModel {

        static final int TAX_PAYABLE = 0;

        static final int NET_INCOME = 1;

        private Vector data;

        TaxSummaryTableModel() {
            super(null, columnNames, columnClasses);

            initData();
            setData(data);
        }

        private void initData() {
            // refresh these tables
            getIncomeTableModel();
            // getExpenseTableModel();
            // getOffsetTableModel();
            getTaxTableModel();

            data = new Vector();

            ISmartTableRow row = new AbstractSmartTableRow(
                    ISmartTableRow.HEADER1) {
                private double clientAmount = tcClient
                        .getResult(ITaxConstants.TAX_PAYABLE);

                private double partnerAmount = married ? tcPartner
                        .getResult(ITaxConstants.TAX_PAYABLE) : 0.;

                public Object getValueAt(int columnIndex) {
                    switch (columnIndex) {
                    case NAME:
                        return "ESTIMATED TAX PAYABLE";
                    case CLIENT:
                        return _money.setValue(clientAmount);
                    case PARTNER:
                        return _money.setValue(partnerAmount);
                    case TOTAL:
                        return _money.setValue(clientAmount + partnerAmount);
                    default:
                        return null;
                    }
                }
            };
            data.add(TAX_PAYABLE, row);

            row = new AbstractSmartTableRow(ISmartTableRow.HEADER1) {
                private double clientTotalIncomeAmount = incomeTableModel
                        .getTotal(CLIENT);

                // tcClient.getResult( TaxConstants.I_TOTAL_INCOME );
                private double partnerTotalIncomeAmount = incomeTableModel
                        .getTotal(PARTNER);

                // tcPartner.getResult( TaxConstants.I_TOTAL_INCOME );

                private double clientAmount = clientTotalIncomeAmount
                        - tcClient.getResult(ITaxConstants.TAX_PAYABLE);

                private double partnerAmount = married ? partnerTotalIncomeAmount
                        - tcPartner.getResult(ITaxConstants.TAX_PAYABLE)
                        : 0.;

                public Object getValueAt(int columnIndex) {
                    switch (columnIndex) {
                    case NAME:
                        return "NET INCOME";
                    case CLIENT:
                        return _money.setValue(clientAmount);
                    case PARTNER:
                        return _money.setValue(partnerAmount);
                    case TOTAL:
                        return _money.setValue(clientAmount + partnerAmount);
                    default:
                        return null;
                    }
                }
            };
            data.add(NET_INCOME, row);

        }

    }

    // //////////////////////////////////////////////////////////////////////////
    //
    // //////////////////////////////////////////////////////////////////////////
    public class TaxOffsetDataGenerator implements
            au.com.totemsoft.myplanner.tree.DataGenerator {

        public void addGeneratedData(FinancialTreeModel model,
                Map source, final int[] rules, int year) {

            if (source == null)
                return;

            Map taxOffsets = (Map) source
                    .get(FinancialClassID.TAX_OFFSET);
            if (taxOffsets == null) {
                taxOffsets = new HashMap();
                source.put(FinancialClassID.TAX_OFFSET, taxOffsets);
            } else {
                // Financials.removeGeneratedData( taxOffsets ); // has to be
                // removed by now
            }

            // Get calculated low income rebate and add it to as new Offset
            // financial
            for (int i = 0; i < taxTypes.length; i++) {
                String taxType = taxTypes[i];
                Integer financialTypeID = TaxOffset.getFinancialTypeID(taxType);
                if (financialTypeID == null)
                    continue;

                double clientAmount = tcClient.getResult(taxType);
                double partnerAmount = married ? tcPartner.getResult(taxType)
                        : 0.;

                if (clientAmount != 0.) {
                    TaxOffset taxOffsetClient = createTaxOffset(
                            OwnerCode.CLIENT, financialTypeID.intValue(),
                            clientAmount);
                    taxOffsets.put(taxOffsetClient.getId(),
                            taxOffsetClient);
                    // if ( model != null ) model.addFinancial( taxOffsetClient
                    // );
                }

                if (partnerAmount != 0.) {
                    TaxOffset taxOffsetPartner = createTaxOffset(
                            OwnerCode.PARTNER, financialTypeID.intValue(),
                            partnerAmount);
                    taxOffsets.put(taxOffsetPartner.getId(),
                            taxOffsetPartner);
                    // if ( model != null ) model.addFinancial( taxOffsetPartner
                    // );
                }

            }

        }

        public void addGeneratedData(FinancialTreeModel model,
                Collection source, final int[] rules, int year) {

            // addGeneratedData( financials, rules, year );

            if (source == null)
                return;

            // Get calculated low income rebate and add it to as new Offset
            // financial
            for (int i = 0; i < taxTypes.length; i++) {
                String taxType = taxTypes[i];
                Integer financialTypeID = TaxOffset.getFinancialTypeID(taxType);
                if (financialTypeID == null)
                    continue;

                double clientAmount = tcClient.getResult(taxType);
                double partnerAmount = married ? tcPartner.getResult(taxType)
                        : 0.;

                if (clientAmount != 0.) {
                    TaxOffset taxOffsetClient = createTaxOffset(
                            OwnerCode.CLIENT, financialTypeID.intValue(),
                            clientAmount);
                    source.add(taxOffsetClient);
                    // if ( model != null ) model.addFinancial( taxOffsetClient
                    // );
                }

                if (partnerAmount != 0.) {
                    TaxOffset taxOffsetPartner = createTaxOffset(
                            OwnerCode.PARTNER, financialTypeID.intValue(),
                            partnerAmount);
                    source.add(taxOffsetPartner);
                    // if ( model != null ) model.addFinancial( taxOffsetPartner
                    // );
                }

            }

        }

        private TaxOffset createTaxOffset(Integer owner, int financialTypeID,
                double amount) {
            TaxOffset taxOffset = new TaxOffset();
            taxOffset.setGenerated(true);
            taxOffset.setOwnerCodeID(owner);
            taxOffset.setFinancialTypeId(financialTypeID);
            taxOffset.setRegularAmount(new java.math.BigDecimal(amount));
            taxOffset.setFrequencyCodeID(FrequencyCode.YEARLY);
            return taxOffset;
        }

    }

    // //////////////////////////////////////////////////////////////////////////
    //
    // //////////////////////////////////////////////////////////////////////////
    class TaxTableModel extends SmartTableModel {

        static final int HEADER = 0;

        static final int TAXABLE_INCOME = 1;

        static final int TAX_ON_TAXABLE_INCOME = 2;

        static final int MEDICARE_LEVY = 3;

        static final int MEDICARE_LEVY_SURCHARGE = 4;

        static final int FOOTER = 5;

        private Vector data;

        TaxTableModel() {
            super(null, columnNames, columnClasses);

            initData();
            setData(data);
        }

        private void initData() {
            data = new Vector();

            ISmartTableRow row = new AbstractSmartTableRow(
                    ISmartTableRow.HEADER1) {
                public Object getValueAt(int columnIndex) {
                    switch (columnIndex) {
                    case NAME:
                        return "TAX CALCULATIONS";
                    case CLIENT:
                    case PARTNER:
                    case TOTAL:
                    default:
                        return null;
                    }
                }
            };
            data.add(HEADER, row);

            row = new AbstractSmartTableRow(ISmartTableRow.FOOTER2) {
                private double clientAmount = tcClient
                        .getResult(ITaxConstants.TAXABLE_INCOME);

                private double partnerAmount = married ? tcPartner
                        .getResult(ITaxConstants.TAXABLE_INCOME) : 0.;

                public Object getValueAt(int columnIndex) {
                    switch (columnIndex) {
                    case NAME:
                        return "Taxable Income";
                    case CLIENT:
                        return _money.setValue(clientAmount);
                    case PARTNER:
                        return _money.setValue(partnerAmount);
                    case TOTAL:
                        return _money.setValue(clientAmount + partnerAmount);
                    default:
                        return null;
                    }
                }
            };
            data.add(TAXABLE_INCOME, row);

            row = new AbstractSmartTableRow(ISmartTableRow.FOOTER2) {
                private double clientAmount = tcClient
                        .getResult(ITaxConstants.TAX_ON_TAXABLE_INCOME);

                private double partnerAmount = married ? tcPartner
                        .getResult(ITaxConstants.TAX_ON_TAXABLE_INCOME) : 0.;

                public Object getValueAt(int columnIndex) {
                    switch (columnIndex) {
                    case NAME:
                        return "Tax on taxable Income";
                    case CLIENT:
                        return _money.setValue(clientAmount);
                    case PARTNER:
                        return _money.setValue(partnerAmount);
                    case TOTAL:
                        return _money.setValue(clientAmount + partnerAmount);
                    default:
                        return null;
                    }
                }
            };
            data.add(TAX_ON_TAXABLE_INCOME, row);

            row = new AbstractSmartTableRow(ISmartTableRow.FOOTER2) {
                private double clientAmount = tcClient
                        .getResult(ITaxConstants.ML);

                private double partnerAmount = married ? tcPartner
                        .getResult(ITaxConstants.ML) : 0.;

                public Object getValueAt(int columnIndex) {
                    switch (columnIndex) {
                    case NAME:
                        return "Medicare levy";
                    case CLIENT:
                        return _money.setValue(clientAmount);
                    case PARTNER:
                        return _money.setValue(partnerAmount);
                    case TOTAL:
                        return _money.setValue(clientAmount + partnerAmount);
                    default:
                        return null;
                    }
                }
            };
            data.add(MEDICARE_LEVY, row);

            row = new AbstractSmartTableRow(ISmartTableRow.FOOTER2) {
                private double clientAmount = tcClient
                        .getResult(ITaxConstants.MLS);

                private double partnerAmount = married ? tcPartner
                        .getResult(ITaxConstants.MLS) : 0.;

                public Object getValueAt(int columnIndex) {
                    switch (columnIndex) {
                    case NAME:
                        return "Medicare levy surcharge";
                    case CLIENT:
                        return _money.setValue(clientAmount);
                    case PARTNER:
                        return _money.setValue(partnerAmount);
                    case TOTAL:
                        return _money.setValue(clientAmount + partnerAmount);
                    default:
                        return null;
                    }
                }
            };
            data.add(MEDICARE_LEVY_SURCHARGE, row);

            // empty row
            row = new AbstractSmartTableRow(ISmartTableRow.FOOTER1) {
                public Object getValueAt(int columnIndex) {
                    switch (columnIndex) {
                    case NAME:
                    case CLIENT:
                    case PARTNER:
                    case TOTAL:
                    default:
                        return null;
                    }
                }
            };
            data.add(FOOTER, row);

        }

    }

    public class IncomeTableModel extends DetailsTableModel {

        IncomeTableModel() {
            // all taxable and non-taxable items
            super(FinancialClassID.RC_REGULAR_INCOME, "INCOME"); // will call initTaxContainers()

            initTaxContainers(TaxContainer.INCOME, RegularIncome.getRegularTaxType(FinancialTypeEnum.INCOME_SALARY));
            initTaxContainers(TaxContainer.INCOME, RegularIncome.getRegularTaxType(FinancialTypeEnum.INCOME_SOCIAL_SECURITY));
            initTaxContainers(TaxContainer.INCOME, RegularIncome.getRegularTaxType(FinancialTypeEnum.INCOME_RETIREMENT));
            initTaxContainers(TaxContainer.INCOME, RegularIncome.getRegularTaxType(FinancialTypeEnum.INCOME_INVESTMENT));
            initTaxContainers(TaxContainer.INCOME, RegularIncome.getRegularTaxType(FinancialTypeEnum.INCOME_OTHER));

            // do not add tax free income into tax container
            // initTaxContainers( TaxContainer.INCOME,
            // RegularIncome.getRegularTaxType( INCOME_OTHER_TAX_FREE ),
            // INCOME_OTHER_TAX_FREE );

        }

    }

    public class ExpenseTableModel extends DetailsTableModel {
        ExpenseTableModel() {
            super(FinancialClassID.RC_REGULAR_EXPENSE, "DEDUCTIONS"); // will call initTaxContainers()
            initTaxContainers(TaxContainer.EXPENSE, RegularExpense.getRegularTaxType(FinancialTypeEnum.EXPENSE_SAVING_INVESTMENT));
            initTaxContainers(TaxContainer.EXPENSE, RegularExpense.getRegularTaxType(FinancialTypeEnum.EXPENSE_GENERAL));
            initTaxContainers(TaxContainer.EXPENSE, RegularExpense.getRegularTaxType(FinancialTypeEnum.EXPENSE_EDUCATION));
            initTaxContainers(TaxContainer.EXPENSE, RegularExpense.getRegularTaxType(FinancialTypeEnum.EXPENSE_OTHER));
        }
    }

    public class OffsetTableModel extends DetailsTableModel {

        OffsetTableModel() {
            super(FinancialClassID.RC_TAX_OFFSET, "TAX OFFSETS"); // will call initTaxContainers()
            initTaxContainers(TaxContainer.OFFSET, TaxOffset.getRegularTaxType(FinancialTypeEnum.TAXOFFSET_IMPUTATION_CREDIT));
            initTaxContainers(TaxContainer.OFFSET, TaxOffset.getRegularTaxType(FinancialTypeEnum.TAXOFFSET_LUMP_SUM));
            initTaxContainers(TaxContainer.OFFSET, TaxOffset.getRegularTaxType(FinancialTypeEnum.TAXOFFSET_SUPER));
            initTaxContainers(TaxContainer.OFFSET, TaxOffset.getRegularTaxType(FinancialTypeEnum.TAXOFFSET_LOW_INCOME));
            initTaxContainers(TaxContainer.OFFSET, ITaxConstants.O_OTHER, FinancialTypeEnum.TAXOFFSET_OTHERS);
        }

    }

    public class DetailsTableModel extends FinancialTableModel2 {

        private String tableName;

        // used in initTaxContainers(...)
        protected FinancialTreeFilter filterTaxContainer;

        DetailsTableModel(ReferenceCode finClass, String tableName) {

            super();

            this.tableName = tableName;

            refreshModel();

            FinancialTreeFilters filters = model.getFilters();
            filters.clear();

            // income, expense or taxoffset
            filterTaxContainer = new FinancialTreeFilter(
                    FinancialTreeStructure.FINANCIAL_CLASS);
            filterTaxContainer.add(finClass);

            // only taxable/deductible items include
            filterTaxContainer = new FinancialTreeFilter(filterTaxContainer,
                    FinancialTreeStructure.TAXABLE);
            if (FinancialClassID.RC_REGULAR_EXPENSE.equals(finClass))
                filterTaxContainer.add(Boolean.TRUE);
            else
                filterTaxContainer.add(null);
            filters.add(filterTaxContainer);

            setFinancialTreeModel(model.buildCopy());

            // only taxable/deductible items include
            filterTaxContainer = new FinancialTreeFilter(
                    FinancialTreeStructure.FINANCIAL_CLASS);
            filterTaxContainer.add(finClass);

            // filterTaxContainer.clear(); // TAXABLE
            filterTaxContainer = new FinancialTreeFilter(filterTaxContainer,
                    FinancialTreeStructure.TAXABLE);
            if (FinancialClassID.RC_TAX_OFFSET.equals(finClass))
                filterTaxContainer.add(null);
            else
                filterTaxContainer.add(Boolean.TRUE);

            // for the financial type specified in initTaxContainers(...)
            filterTaxContainer = new FinancialTreeFilter(filterTaxContainer,
                    FinancialTreeStructure.FINANCIAL_TYPE);
            // filterTaxContainer.add( financialTypeID );

        }

        protected void initTaxContainers(String type, String taxType, Integer financialTypeID) {
            filterTaxContainer.clear(); // remove old financialTypeID
            filterTaxContainer.add(financialTypeID);
            initTaxContainers(type, taxType);
        }

        protected void initTaxContainers(String type, String taxType, Integer[] financialTypeID) {
            filterTaxContainer.clear(); // remove old financialTypeID
            for (int i = 0; i < financialTypeID.length; i++)
                filterTaxContainer.add(financialTypeID[i]);
            initTaxContainers(type, taxType);
        }

        protected void initTaxContainers(String type, String taxType, FinancialTypeEnum[] financialTypes) {
            filterTaxContainer.clear(); // remove old financialTypeID
            for (int i = 0; i < financialTypes.length; i++)
                filterTaxContainer.add(financialTypes[i].getId());
            initTaxContainers(type, taxType);
        }

        protected void initTaxContainers(String type, String taxType) {

            java.math.BigDecimal amount = (java.math.BigDecimal) getValue(
                    FinancialColumnID.REAL_AMOUNT_CLIENT, filterTaxContainer);
            double clientAmount = amount == null ? 0. : amount.doubleValue();
            if (clientAmount < 0.)
                clientAmount = -clientAmount;

            amount = (java.math.BigDecimal) getValue(
                    FinancialColumnID.REAL_AMOUNT_PARTNER, filterTaxContainer);
            double partnerAmount = amount == null ? 0. : amount.doubleValue();
            if (partnerAmount < 0.)
                partnerAmount = -partnerAmount;

            if (clientAmount != 0.)
                tcClient.add(type, taxType, clientAmount);
            if (partnerAmount != 0.)
                tcPartner.add(type, taxType, partnerAmount);

        }

        public int getColumnCount() {
            return columnNames.size();
        }

        public String getColumnName(int columnIndex) {
            return (String) columnNames.get(columnIndex);
        }

        public Class getColumnClass(int columnIndex) {
            return (Class) columnClasses.get(columnIndex);
        }

        public Object getValueAt(int rowIndex, int columnIndex) {

            ISmartTableRow r = getRowAt(rowIndex);
            int rowType = r.getRowType();

            if (rowType == r.HEADER1)
                return columnIndex == NAME ? tableName : null;

            if (rowType == r.HEADER2 || rowType == r.HEADER3
                    || rowType == r.HEADER4 || rowType == r.HEADER5)
                return columnIndex == NAME ? super.getValueAt(rowIndex,
                        FinancialColumnID.NAME) : null;

            switch (columnIndex) {
            case NAME:
                return rowType == r.FOOTER1 ? "Total" : super.getValueAt(
                        rowIndex, FinancialColumnID.NAME);
            case CLIENT:
                return _money.setValue((java.math.BigDecimal) super.getValueAt(
                        rowIndex, FinancialColumnID.REAL_AMOUNT_CLIENT));
            case PARTNER:
                return _money.setValue((java.math.BigDecimal) super.getValueAt(
                        rowIndex, FinancialColumnID.REAL_AMOUNT_PARTNER));
            case TOTAL:
                return _money.setValue((java.math.BigDecimal) super.getValueAt(
                        rowIndex, FinancialColumnID.REAL_AMOUNT));
            default:
                return null;
            }

        }

        public double getTotal(int columnIndex) {

            int rowIndex = getRowCount() - 1;
            if (rowIndex < 0)
                return 0.;

            switch (columnIndex) {
            case NAME:
                return 0.;
            case CLIENT:
                return ((au.com.totemsoft.math.Numeric) getValueAt(rowIndex,
                        columnIndex)).doubleValue();
            case PARTNER:
                return ((au.com.totemsoft.math.Numeric) getValueAt(rowIndex,
                        columnIndex)).doubleValue();
            case TOTAL:
                return ((au.com.totemsoft.math.Numeric) getValueAt(rowIndex,
                        columnIndex)).doubleValue();
            default:
                return 0.;
            }

        }

    }

    /***************************************************************************
     * au.com.totemsoft.activex.Reportable interface
     **************************************************************************/
    public void initializeReportData(
            au.com.totemsoft.myplanner.report.ReportFields reportFields)
            throws java.io.IOException {
        initializeReportData(reportFields, clientService);
    }

    public void initializeReportData(
            au.com.totemsoft.myplanner.report.ReportFields reportFields,
            au.com.totemsoft.myplanner.service.PersonService person)
            throws java.io.IOException {

        if (person != null)
            reportFields.initialize(person);

        reportFields.setValue(prefix + reportFields.TaxAnalysis_IncomeTable,
                getIncomeTableModel());
        reportFields.setValue(prefix + reportFields.TaxAnalysis_ExpenseTable,
                getExpenseTableModel());
        reportFields.setValue(prefix + reportFields.TaxAnalysis_OffsetTable,
                getOffsetTableModel());
        reportFields.setValue(prefix + reportFields.TaxAnalysis_DetailsTable,
                getDetailsTableModel());

        reportFields.setValue(prefix + reportFields.TaxAnalysis_SummaryTable,
                getSummaryTableModel());

        reportFields.setValue(prefix + reportFields.TaxAnalysis_ClientIncome,
                _money.toString(assumptions.getClientIncome()));
        reportFields.setValue(prefix + reportFields.TaxAnalysis_PartnerIncome,
                _money.toString(assumptions.getPartnerIncome()));

    }

}
