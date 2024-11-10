package ua.com.javarush.gnew.m2.cli.commands;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Parameters;

import ua.com.javarush.gnew.m2.cli.CliCommand;
import ua.com.javarush.gnew.m2.configuration.PhoneBookContext;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;
import ua.com.javarush.gnew.m2.utils.Utils;

import java.io.IOException;

@Command(
    name = "search",
    aliases = {"-s", "--search"},
    mixinStandardHelpOptions = true,
    description = "Шукає контакт за ім'ям")
public class SearchContact implements CliCommand {

  private final PhoneBookInterface phoneBookInterface =
      PhoneBookContext.getBean(PhoneBookInterface.class);

  @Parameters(index = "0", description = "Фраза для пошуку", arity = "0..1")
  private String name;

  @Override
  public Integer call() throws IOException {
    Utils.printContactList(phoneBookInterface.search(name));
    return 0;
  }
}
