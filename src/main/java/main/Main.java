package main;

import exceptions.InvalidDescriptionException;
import turingmachine.TuringFactory;
import turingmachine.TuringMachine;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            TuringFactory factory = new TuringFactory();
            TuringMachine tm = factory.tmFromFilePath("./tms/example.txt");
            System.out.println(tm.accepts("aaa"));
        } catch (IOException e) {
            System.out.println("Could not read the file.");
        } catch (InvalidDescriptionException e) {
            System.out.println("Description file was not in the correct format.");
        }

    }
}
