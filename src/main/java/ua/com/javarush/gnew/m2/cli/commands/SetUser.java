package ua.com.javarush.gnew.m2.cli.commands;

import ua.com.javarush.gnew.m2.cli.CliCommand;
import static picocli.CommandLine.*;

@Command(name = "user",
        aliases = {"-u", "--user"},
        mixinStandardHelpOptions = true,
        description = "Обрати активного корисувача")
public  class SetUser implements CliCommand {

    @Parameters(index = "0", description = "Ім'я користувача", arity = "0..1")
    private String user;

    @Override
    public Integer call() {
        //TODO Реалізувати логіку команди user

        System.out.println("Ви обрали користувача: " + user);

        return 0;
    }
}

