package ua.com.javarush.gnew.m2.cli.commands;

import static picocli.CommandLine.Command;

import ua.com.javarush.gnew.m2.cli.CliCommand;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;
import ua.com.javarush.gnew.m2.utils.Utils;

@Command(
    name = "list",
    aliases = {"-ls", "--list"},
    description = "Виводить усі контакти",
    mixinStandardHelpOptions = true)
public class ListContacts implements CliCommand {
  private final PhoneBookInterface phoneBookInterface;

  public ListContacts(PhoneBookInterface phoneBookInterface) {
    this.phoneBookInterface = phoneBookInterface;
  }

  @Override
  public Integer call() {
    Utils.printContactList(phoneBookInterface.list());
    return 0;
  }
}
