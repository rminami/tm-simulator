import exceptions.InvalidDescriptionException;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import factory.TuringFactory;
import turingmachine.TuringMachine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Command(name = "turing machine simulator", mixinStandardHelpOptions = true, version = "1.0")
public class Main implements Runnable {
    @Option(names = { "-s", "--source" }, description = "Turing machine source file.",
            paramLabel = "<source_file>", required = true)
    private File sourceFile;

    @Option(names = { "-i", "--input" }, description = "Input values for Turing machine",
            paramLabel = "<input_file>", required = true)
    private File inputFile;

    @Option(names = { "-c", "--count-steps" }, description = "Step count mode. " +
            "Counts number of steps until completion.")
    boolean stepFlag;

    public void run() {
        TuringFactory factory = new TuringFactory();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            TuringMachine tm = factory.tmFromFile(sourceFile);
            String line;
            while ((line = reader.readLine()) != null) {
                printResult(tm, line);
            }
        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        } catch (InvalidDescriptionException e) {
            System.out.println("Source file was in the wrong format.");
        }
    }

    public void printResult(TuringMachine tm, String line) {
        if (line.length() == 0)
            return;
        System.out.println("Input:\t" + line);
        if (!stepFlag) {
            System.out.println("Accept?\t" + (tm.accepts(line) ? "Yes" : "No") + "\n");
        } else {
            int stepCount = tm.getStepCount(line);
            System.out.println("Accept?\t" + (stepCount > 0 ? "Yes" : "No"));
            System.out.println("Steps:\t" + (stepCount > 0 ? stepCount : "n/a") + "\n");
        }
    }

    public static void main(String[] args) {
        CommandLine.run(new Main(), System.out, args);
    }
}
