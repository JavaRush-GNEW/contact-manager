package ua.com.javarush.gnew.m2.cli.commands;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Parameters;

import ua.com.javarush.gnew.m2.cli.CliCommand;

@Command(
    name = "user",
    aliases = {"-u", "--user"},
    mixinStandardHelpOptions = true,
    description = "Обрати активного корисувача")
public class SetUser implements CliCommand {
  @Parameters(index = "0", description = "Ім'я користувача", arity = "1")
  private String user;

  @Override
  public Integer call() {
    // TODO Реалізувати логіку команди user

    System.out.println("Ви обрали користувача: " + user);

    return 0;
  }
}
