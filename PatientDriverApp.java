
import java.util.Scanner;

/**
 * PatientDriverApp collects patient information,
 * creates three procedures, and displays medical charges.
 * Course: CMSC 203
 * Platform: Eclipse IDE / Java
 */
public class PatientDriverApp
{
    /** Reads all patient information from the keyboard. */
    public static Patient inputPatient(Scanner input)
    {
        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter middle name: ");
        String middleName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter street address: ");
        String street = input.nextLine();

        System.out.print("Enter city: ");
        String city = input.nextLine();

        System.out.print("Enter state: ");
        String state = input.nextLine();

        System.out.print("Enter zip: ");
        String zip = input.nextLine();

        System.out.print("Enter phone number (###-###-####): ");
        String phone = input.nextLine();

        System.out.print("Enter emergency contact name: ");
        String emergencyName = input.nextLine();

        System.out.print("Enter emergency contact phone (###-###-####): ");
        String emergencyPhone = input.nextLine();

        return new Patient(firstName, middleName, lastName,
                street, city, state, zip, phone,
                emergencyName, emergencyPhone);
    }

    /** Creates the first procedure with the no-arg constructor. */
    public static Procedure createProcedure1()
    {
        Procedure procedure = new Procedure();
        procedure.setProcedureName("Physical Exam");
        procedure.setProcedureDate("07/20/2026");
        procedure.setPractitionerName("Dr. Irvine");
        procedure.setCharges(250.00);
        return procedure;
    }

    /** Creates the second procedure using name and date. */
    public static Procedure createProcedure2()
    {
        Procedure procedure = new Procedure("X-ray", "07/20/2026");
        procedure.setPractitionerName("Dr. Jamison");
        procedure.setCharges(550.43);
        return procedure;
    }

    /** Creates the third procedure with all four attributes. */
    public static Procedure createProcedure3()
    {
        return new Procedure("Blood Test", "07/20/2026",
                "Dr. Smith", 1400.75);
    }

    /** Displays all patient information. */
    public static void displayPatient(Patient patient)
    {
        System.out.println();
        System.out.println("Patient Information");
        System.out.println("-------------------");
        System.out.println(patient);
    }

    /** Displays information for one procedure. */
    public static void displayProcedure(Procedure procedure)
    {
        System.out.println(procedure);
    }

    /** Displays three procedures in an aligned table. */
    public static void displayProcedureTable(
            Procedure p1, Procedure p2, Procedure p3)
    {
        System.out.println();
        System.out.printf("%-20s%-13s%-20s%-16s%s%n",
                "Procedure", "Date", "Practitioner",
                "Charge", "Category");
        System.out.println(
                "------------------------------------------------------------------------");

        Procedure[] procedures = {p1, p2, p3};

        for (Procedure p : procedures)
        {
            System.out.printf("%-20s%-13s%-20s%-16s%s%n",
                    p.getProcedureName(),
                    p.getProcedureDate(),
                    p.getPractitionerName(),
                    p.getFormattedCharge(),
                    p.getChargeCategory());
        }
    }

    /** Calculates the combined charges of all procedures. */
    public static double calculateTotalCharges(
            Procedure p1, Procedure p2, Procedure p3)
    {
        return p1.getCharges() + p2.getCharges() + p3.getCharges();
    }

    /** Calculates the average charge of three procedures. */
    public static double calculateAverageCharge(
            Procedure p1, Procedure p2, Procedure p3)
    {
        return calculateTotalCharges(p1, p2, p3) / 3.0;
    }

    /** Finds the procedure with the largest charge. */
    public static Procedure findHighestChargeProcedure(
            Procedure p1, Procedure p2, Procedure p3)
    {
        Procedure highest = p1;

        if (p2.getCharges() > highest.getCharges())
        {
            highest = p2;
        }

        if (p3.getCharges() > highest.getCharges())
        {
            highest = p3;
        }

        return highest;
    }

    /** Counts procedures with charges of at least $1000. */
    public static int countExpensiveProcedures(
            Procedure p1, Procedure p2, Procedure p3)
    {
        int count = 0;

        if (p1.isExpensiveProcedure())
        {
            count++;
        }

        if (p2.isExpensiveProcedure())
        {
            count++;
        }

        if (p3.isExpensiveProcedure())
        {
            count++;
        }

        return count;
    }

    /** Displays the financial summary for three procedures. */
    public static void displaySummary(
            Procedure p1, Procedure p2, Procedure p3)
    {
        System.out.println();
        System.out.printf("Total Charges: $%,.2f%n",
                calculateTotalCharges(p1, p2, p3));

        System.out.printf("Average Charge: $%,.2f%n",
                calculateAverageCharge(p1, p2, p3));

        Procedure highest =
                findHighestChargeProcedure(p1, p2, p3);

        System.out.println("Highest Charge Procedure: "
                + highest.getProcedureName());

        System.out.println("Number of Expensive Procedures: "
                + countExpensiveProcedures(p1, p2, p3));
    }

    /** Runs the patient application. */
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        Patient patient = inputPatient(input);

        Procedure p1 = createProcedure1();
        Procedure p2 = createProcedure2();
        Procedure p3 = createProcedure3();

        displayPatient(patient);
        displayProcedureTable(p1, p2, p3);
        displaySummary(p1, p2, p3);

        System.out.println();
        System.out.println("The program was developed by a Student: "
                + "Liya Bayu 10/09/26");

        input.close();
    }
}
