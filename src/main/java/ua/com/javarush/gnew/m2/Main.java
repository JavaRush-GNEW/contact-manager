package ua.com.javarush.gnew.m2;

import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello dRaider-branch!");
        /**
        * Apache Commons Cli Example
        */
        System.out.println("Apache Commons Cli Example");
        new ApacheCommonsCliExample(args).parse();


        /**
         * Picocli Cli Example
         */
        System.out.println("Picocli Cli Example");
        int exitCode = new CommandLine(new PicocliCliExample()).execute(args);


    }
}
