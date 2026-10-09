
/**
 * Procedure class stores information about a medical procedure.
 * Course: CMSC 203
 * Platform: Eclipse IDE / Java
 */
public class Procedure
{
    private String procedureName;
    private String procedureDate;
    private String practitionerName;
    private double charges;

    /** Creates an empty procedure. */
    public Procedure()
    {
        this("", "", "", 0.0);
    }

    /** Creates a procedure with a name and date. */
    public Procedure(String procedureName, String procedureDate)
    {
        this(procedureName, procedureDate, "", 0.0);
    }

    /** Creates a procedure with all attributes. */
    public Procedure(String procedureName, String procedureDate,
                     String practitionerName, double charges)
    {
        this.procedureName = procedureName;
        this.procedureDate = procedureDate;
        this.practitionerName = practitionerName;
        this.charges = charges;
    }

    /** Returns the procedure name. */
    public String getProcedureName()
    {
        return procedureName;
    }

    /** Updates the procedure name. */
    public void setProcedureName(String procedureName)
    {
        this.procedureName = procedureName;
    }

    /** Returns the procedure date. */
    public String getProcedureDate()
    {
        return procedureDate;
    }

    /** Updates the procedure date. */
    public void setProcedureDate(String procedureDate)
    {
        this.procedureDate = procedureDate;
    }

    /** Returns the practitioner's name. */
    public String getPractitionerName()
    {
        return practitionerName;
    }

    /** Updates the practitioner's name. */
    public void setPractitionerName(String practitionerName)
    {
        this.practitionerName = practitionerName;
    }

    /** Returns the procedure charges. */
    public double getCharges()
    {
        return charges;
    }

    /** Updates the procedure charges. */
    public void setCharges(double charges)
    {
        this.charges = charges;
    }

    /** Checks whether the procedure costs at least $1000. */
    public boolean isExpensiveProcedure()
    {
        return charges >= 1000.00;
    }

    /** Applies a discount between 0 and 100 percent. */
    public void applyDiscount(double percent)
    {
        if (percent >= 0 && percent <= 100)
        {
            charges = charges * (1 - percent / 100.0);
        }
    }

    /** Returns the charge category. */
    public String getChargeCategory()
    {
        if (charges >= 1000.00)
        {
            return "High";
        }
        else if (charges >= 500.00)
        {
            return "Medium";
        }
        else
        {
            return "Low";
        }
    }

    /** Checks the practitioner name without case sensitivity. */
    public boolean isPerformedBy(String practitionerName)
    {
        return this.practitionerName != null &&
               this.practitionerName.equalsIgnoreCase(practitionerName);
    }

    /** Formats charges with a dollar sign and two decimals. */
    public String getFormattedCharge()
    {
        return String.format("$%,.2f", charges);
    }

    /** Returns all procedure information. */
    @Override
    public String toString()
    {
        return "Procedure: " + procedureName +
               "\nDate: " + procedureDate +
               "\nPractitioner: " + practitionerName +
               "\nCharge: " + getFormattedCharge() +
               "\nCategory: " + getChargeCategory();
    }
}
