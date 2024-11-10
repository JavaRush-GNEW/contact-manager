package ua.com.javarush.gnew.m2.cli.commands;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Parameters;

import java.io.IOException;
import ua.com.javarush.gnew.m2.cli.CliCommand;
import ua.com.javarush.gnew.m2.configuration.PhoneBookContext;
import ua.com.javarush.gnew.m2.service.SettingsServiceInterface;

@Command(
    name = "user",
    aliases = {"-u", "--user"},
    mixinStandardHelpOptions = true,
    description = "Обрати активного корисувача")
public class SetUser implements CliCommand {
  private final SettingsServiceInterface settingsService =
      PhoneBookContext.getBean(SettingsServiceInterface.class);

  @Parameters(index = "0", description = "Ім'я користувача", arity = "1")
  private String user;

  @Override
  public Integer call() {

    try {
      settingsService.setUser(user);
      System.out.println("Ви обрали користувача: " + user);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return 0;
  }
}
