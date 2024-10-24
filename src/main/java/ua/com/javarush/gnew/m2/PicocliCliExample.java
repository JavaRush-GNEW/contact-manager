package ua.com.javarush.gnew.m2;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;



@Command(name = "PicocliExample", description = "Приклад з використанням Picocli",
        mixinStandardHelpOptions = true,
        version = "PicocliExample 1.0")
public class PicocliCliExample implements Runnable{
    /**
     * Usage: PicocliExample [-hV] [-hi=<name>]
     * Приклад з використанням Picocli
     *   -h, --help                Show this help message and exit.
     *       -hi, --hello=<name>   ім'я
     *   -V, --version             Print version information and exit.
     */

    @Option(names = {"-hi", "--hello"}, description = "ім'я")
    private String name;

    @Override
    public void run() {
        if (name != null) {
            System.out.println("PicocliExample Hello " + name);
        }
    }
}
