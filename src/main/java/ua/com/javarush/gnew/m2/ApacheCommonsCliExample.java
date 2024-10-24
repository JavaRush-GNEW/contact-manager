package ua.com.javarush.gnew.m2;

import org.apache.commons.cli.*;

public class ApacheCommonsCliExample {

    /**
     * usage: ApacheCommonsCliExample
     *  -h,--help           Приклад з використанням Apache Commons Cli
     *  -hi,--hello <arg>   ім'я
     *  -V,--version        версія програми
     */

    private final String[] args;
    private Options options = new Options();

    public ApacheCommonsCliExample(String[] args) {
        this.args = args;
        initCLI();
    }
    private void initCLI(){
        Option help = new Option("h", "help", false, "допомога");
        Option version = new Option("V", "version", false, "версія програми");
        Option hello = new Option("hi", "hello", true, "ім'я");
        options.addOption(help);
        options.addOption(version);
        options.addOption(hello);
    }

    public void parse(){
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("h")) {
                formatter.printHelp("ApacheCommonsCliExample", options);
                return;
            }
            if (cmd.hasOption("V")) {
                System.out.println("ApacheCommonsCliExample 1.0");
                return;
            }
            if (cmd.hasOption("hi")) {
                String name = cmd.getOptionValue("hi");
                System.out.println("ApacheCommonsCliExample Hello " + name);

            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }


}
