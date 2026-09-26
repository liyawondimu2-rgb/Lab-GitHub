import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Class: CMSC203
 * Instructor: Gary Thai
 * Description: Project 1 Grade Calculator. Reads grading configuration and
 * student scores from files, calculates weighted averages, validates Y/N
 * keyboard input, and writes a grade report.
 * Due: 09/25/2026
 * Platform/compiler: Eclipse / Java
 *
 * Integrity pledge: I pledge that I have completed the programming assignment independently. I have not copied the code from a student or any source.
 * Print your Name here: Liya Bayu
 */

public class GradeCalculator
{
    public static void main(String[] args)
    {
        final String CONFIG_FILE = "gradeconfig.txt";
        final String INPUT_FILE = "grades_input.txt";
        final String OUTPUT_FILE = "grades_report.txt";

        String courseName = "";
        int categoryCount = 0;
        boolean useDefaultConfig = false;
        boolean configValid = true;
        int weightTotal = 0;

        System.out.println("========================================");
        System.out.println("   CMSC203 Project 1 - Grade Calculator");
        System.out.println("========================================");
        System.out.println("Loading configuration from " + CONFIG_FILE + " ...");

        // First pass: validate the configuration file and total weight.
        try
        {
            Scanner configCheck = new Scanner(new File(CONFIG_FILE));

            if (configCheck.hasNextLine())
            {
                courseName = configCheck.nextLine().trim();
            }
            else
            {
                configValid = false;
            }

            if (configValid && configCheck.hasNextLine())
            {
                String countText = configCheck.nextLine().trim();
                try
                {
                    categoryCount = Integer.parseInt(countText);
                    if (categoryCount < 1)
                    {
                        configValid = false;
                    }
                }
                catch (NumberFormatException e)
                {
                    configValid = false;
                }
            }
            else
            {
                configValid = false;
            }

            int configIndex = 1;
            while (configValid && configIndex <= categoryCount)
            {
                if (!configCheck.hasNextLine())
                {
                    configValid = false;
                }
                else
                {
                    String categoryLine = configCheck.nextLine().trim();
                    Scanner lineScanner = new Scanner(categoryLine);

                    if (!lineScanner.hasNext())
                    {
                        configValid = false;
                    }
                    else
                    {
                        lineScanner.next();

                        if (!lineScanner.hasNextInt())
                        {
                            configValid = false;
                        }
                        else
                        {
                            int weight = lineScanner.nextInt();

                            if (weight < 0 || weight > 100)
                            {
                                configValid = false;
                            }
                            else
                            {
                                weightTotal += weight;
                            }
                        }
                    }

                    lineScanner.close();
                }

                configIndex++;
            }

            configCheck.close();

            if (weightTotal != 100)
            {
                configValid = false;
            }
        }
        catch (FileNotFoundException e)
        {
            configValid = false;
        }

        if (!configValid)
        {
            useDefaultConfig = true;
            courseName = "CMSC203 Computer Science I";
            categoryCount = 3;
            System.out.println("Configuration missing or invalid.");
            System.out.println("Using default configuration.");
        }
        else
        {
            System.out.println("Configuration loaded successfully.");
        }

        System.out.println("Using input file: " + INPUT_FILE);
        System.out.println("Using output file: " + OUTPUT_FILE);
        System.out.println("Reading student scores...");

        Scanner gradesFile;

        try
        {
            gradesFile = new Scanner(new File(INPUT_FILE));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error: " + INPUT_FILE + " is missing or cannot be read.");
            System.out.println("Program ended.");
            return;
        }

        if (!gradesFile.hasNextLine())
        {
            System.out.println("Error: Student first name is missing.");
            gradesFile.close();
            return;
        }
        String firstName = gradesFile.nextLine().trim();

        if (!gradesFile.hasNextLine())
        {
            System.out.println("Error: Student last name is missing.");
            gradesFile.close();
            return;
        }
        String lastName = gradesFile.nextLine().trim();

        System.out.println("Student: " + firstName + " " + lastName);
        System.out.println("Course: " + courseName);
        System.out.println("Category Results:");

        // If the configuration is valid, reopen it so categories can be
        // processed without using arrays or ArrayLists.
        Scanner configFile = null;

        if (!useDefaultConfig)
        {
            try
            {
                configFile = new Scanner(new File(CONFIG_FILE));
                configFile.nextLine();
                configFile.nextLine();
            }
            catch (FileNotFoundException e)
            {
                useDefaultConfig = true;
                courseName = "CMSC203 Computer Science I";
                categoryCount = 3;
            }
        }

        String reportBody = "";
        double overallAverage = 0.0;

        int categoryIndex = 1;
        while (categoryIndex <= categoryCount)
        {
            String expectedCategory = "";
            int categoryWeight = 0;

            if (useDefaultConfig)
            {
                if (categoryIndex == 1)
                {
                    expectedCategory = "Projects";
                    categoryWeight = 40;
                }
                else if (categoryIndex == 2)
                {
                    expectedCategory = "Quizzes";
                    categoryWeight = 30;
                }
                else
                {
                    expectedCategory = "Exams";
                    categoryWeight = 30;
                }
            }
            else
            {
                if (configFile != null && configFile.hasNextLine())
                {
                    Scanner configLine = new Scanner(configFile.nextLine());
                    expectedCategory = configLine.next();
                    categoryWeight = configLine.nextInt();
                    configLine.close();
                }
            }

            if (!gradesFile.hasNextLine())
            {
                System.out.println("Error: Missing category data for " + expectedCategory + ".");
                break;
            }

            String inputCategory = gradesFile.nextLine().trim();

            if (!gradesFile.hasNextLine())
            {
                System.out.println("Error: Missing score count for " + inputCategory + ".");
                break;
            }

            int numberOfScores = 0;
            boolean scoreCountValid = true;

            try
            {
                numberOfScores = Integer.parseInt(gradesFile.nextLine().trim());
                if (numberOfScores <= 0)
                {
                    scoreCountValid = false;
                }
            }
            catch (NumberFormatException e)
            {
                scoreCountValid = false;
            }

            if (!gradesFile.hasNextLine())
            {
                System.out.println("Error: Missing scores for " + inputCategory + ".");
                break;
            }

            String scoresLine = gradesFile.nextLine();
            Scanner scoreScanner = new Scanner(scoresLine);
            double scoreSum = 0.0;
            int validScoreCount = 0;
            int scoreIndex = 1;

            if (scoreCountValid)
            {
                while (scoreIndex <= numberOfScores)
                {
                    if (scoreScanner.hasNextDouble())
                    {
                        double score = scoreScanner.nextDouble();

                        if (score >= 0.0 && score <= 100.0)
                        {
                            scoreSum += score;
                            validScoreCount++;
                        }
                        else
                        {
                            System.out.println("Warning: Score " + score
                                    + " is outside 0-100 and was ignored.");
                        }
                    }
                    else if (scoreScanner.hasNext())
                    {
                        String badValue = scoreScanner.next();
                        System.out.println("Warning: Invalid score \"" + badValue
                                + "\" was ignored.");
                    }
                    else
                    {
                        break;
                    }

                    scoreIndex++;
                }
            }
            else
            {
                System.out.println("Error: Invalid number of scores for "
                        + inputCategory + ".");
            }

            scoreScanner.close();

            if (!inputCategory.equals(expectedCategory))
            {
                System.out.println("Error: Expected category " + expectedCategory
                        + " but found " + inputCategory + ". Category skipped.");
            }
            else if (validScoreCount == 0)
            {
                System.out.println("Error: No valid scores for " + inputCategory
                        + ". Category skipped.");
            }
            else
            {
                double categoryAverage = scoreSum / validScoreCount;
                overallAverage += categoryAverage * categoryWeight / 100.0;

                String categoryResult = String.format(
                        "  %s (%d%%): average = %.2f",
                        expectedCategory, categoryWeight, categoryAverage);

                System.out.println(categoryResult);
                reportBody += categoryResult + System.lineSeparator();
            }

            categoryIndex++;
        }

        if (configFile != null)
        {
            configFile.close();
        }
        gradesFile.close();

        Scanner keyboard = new Scanner(System.in);
        String plusMinusChoice = "";

        while (!plusMinusChoice.equalsIgnoreCase("Y")
                && !plusMinusChoice.equalsIgnoreCase("N"))
        {
            System.out.print("Apply +/- grading? (Y/N): ");
            plusMinusChoice = keyboard.nextLine().trim();

            if (!plusMinusChoice.equalsIgnoreCase("Y")
                    && !plusMinusChoice.equalsIgnoreCase("N"))
            {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }

        String baseLetterGrade;

        if (overallAverage >= 90.0)
        {
            baseLetterGrade = "A";
        }
        else if (overallAverage >= 80.0)
        {
            baseLetterGrade = "B";
        }
        else if (overallAverage >= 70.0)
        {
            baseLetterGrade = "C";
        }
        else if (overallAverage >= 60.0)
        {
            baseLetterGrade = "D";
        }
        else
        {
            baseLetterGrade = "F";
        }

        String finalLetterGrade = baseLetterGrade;

        // +/- cutoffs: within each passing 10-point band,
        // 8.00-9.99 = plus, 0.00-1.99 = minus, otherwise no sign.
        if (plusMinusChoice.equalsIgnoreCase("Y") && !baseLetterGrade.equals("F"))
        {
            double positionInBand = overallAverage % 10.0;

            if (baseLetterGrade.equals("A"))
            {
                if (overallAverage >= 98.0)
                {
                    finalLetterGrade = "A+";
                }
                else if (overallAverage < 92.0)
                {
                    finalLetterGrade = "A-";
                }
            }
            else if (positionInBand >= 8.0)
            {
                finalLetterGrade = baseLetterGrade + "+";
            }
            else if (positionInBand < 2.0)
            {
                finalLetterGrade = baseLetterGrade + "-";
            }
        }

        System.out.printf("Overall numeric average: %.2f%n", overallAverage);
        System.out.println("Base letter grade: " + baseLetterGrade);
        System.out.println("Final letter grade: " + finalLetterGrade);

        try
        {
            PrintWriter report = new PrintWriter(OUTPUT_FILE);

            report.println("Course: " + courseName);
            report.println("Student: " + firstName + " " + lastName);
            report.println("Category Results:");
            report.print(reportBody);
            report.printf("Overall numeric average: %.2f%n", overallAverage);
            report.println("Final letter grade: " + finalLetterGrade);
            report.println("Default configuration used: "
                    + (useDefaultConfig ? "Yes" : "No"));

            report.close();

            System.out.println("Summary written to " + OUTPUT_FILE);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error: Could not create " + OUTPUT_FILE + ".");
        }

        keyboard.close();

        System.out.println("Program complete. Goodbye!");
    }
}
