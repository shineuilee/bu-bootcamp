import java.io.*;
import java.util.ArrayList;

public class GradeAnalyzer {

    static int invalidLinesSkipped = 0;

    public static void main(String[] args) {

        // Step 1: read scores from file
        ArrayList<Integer> scores = readScores("scores.txt");

        // Step 2: calculate statistics
        double average = calculateAverage(scores);

        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;

        for (int score : scores) {
            if (score > highest) {
                highest = score;
            }

            if (score < lowest) {
                lowest = score;
            }
        }

        // Step 3: write and print report
        writeReport(scores, average, highest, lowest, "report.txt");
    }


    // Returns a list of valid scores read from the file
    public static ArrayList<Integer> readScores(String filename) {

        ArrayList<Integer> scores = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = reader.readLine()) != null) {

                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                try {
                    int score = Integer.parseInt(line);

                    if (score >= 0 && score <= 100) {
                        scores.add(score);
                    } else {
                        System.out.println("Warning: invalid score skipped: " + line);
                        invalidLinesSkipped++;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Warning: invalid line skipped: " + line);
                    invalidLinesSkipped++;
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        return scores;
    }


    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {

        if (scores.isEmpty()) {
            return 0.0;
        }

        double total = 0;

        for (int score : scores) {
            total += score;
        }

        return total / scores.size();
    }


    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
                                   double avg, int high, int low,
                                   String outputFile) {

        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;

        for (int score : scores) {

            if (score >= 90) {
                countA++;
            } else if (score >= 80) {
                countB++;
            } else if (score >= 70) {
                countC++;
            } else if (score >= 60) {
                countD++;
            } else {
                countF++;
            }
        }

        String report = "=== Grade Analysis Report ===\n";
        report += "Total scores processed: " + scores.size() + "\n";
        report += "Invalid lines skipped: " + invalidLinesSkipped + "\n\n";

        if (scores.isEmpty()) {
            report += "No valid scores found.\n";
        } else {
            report += String.format("Average score: %.2f%n", avg);
            report += "Highest score: " + high + "\n";
            report += "Lowest score: " + low + "\n";
        }

        report += "\nGrade distribution:\n";
        report += "A (90-100): " + countA + "\n";
        report += "B (80-89): " + countB + "\n";
        report += "C (70-79): " + countC + "\n";
        report += "D (60-69): " + countD + "\n";
        report += "F (below 60): " + countF + "\n";

        System.out.print(report);

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(outputFile))) {

            writer.write(report);

        } catch (IOException e) {
            System.out.println("Error writing report.");
        }
    }
}