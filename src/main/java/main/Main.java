package main;

import turingmachine.TuringFactory;
import turingmachine.TuringMachine;

public class Main {
    public static void main(String[] args) {
        TuringFactory factory = new TuringFactory();
        TuringMachine tm = factory.tmFromFilePath("./tm/addition.txt");
        System.out.println(tm.accepts("01#1#11"));
    }
}
