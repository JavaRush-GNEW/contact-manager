package ua.com.javarush.gnew.m2.cli.commands;

import static picocli.CommandLine.Command;

import ua.com.javarush.gnew.m2.cli.CliCommand;
import ua.com.javarush.gnew.m2.configuration.PhoneBookContext;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;
import ua.com.javarush.gnew.m2.utils.Utils;

import java.io.IOException;

@Command(
    name = "list",
    aliases = {"-ls", "--list"},
    description = "Виводить усі контакти",
    mixinStandardHelpOptions = true)
public class ListContacts implements CliCommand {
  private final PhoneBookInterface phoneBookInterface =
      PhoneBookContext.getBean(PhoneBookInterface.class);

  @Override
  public Integer call() throws IOException {
    Utils.printContactList(phoneBookInterface.list());
    return 0;
  }
}
