package ua.com.javarush.gnew.m2.cli;

import static ua.com.javarush.gnew.m2.configuration.PhoneBookContext.getBean;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import ua.com.javarush.gnew.m2.cli.commands.*;
import ua.com.javarush.gnew.m2.service.LocalizationService;
import java.util.Locale;

@Command(
    name = "phonebook",
    mixinStandardHelpOptions = true,
    version = "PhoneBook CLI 1.0",
    description = "CLI для управління контактами в телефонній книзі")
public class PhoneBookCLI implements CliCommand {

  LocalizationService localizationService = new LocalizationService(Locale.getDefault());

    @Override
  public Integer call() {
    System.out.println(localizationService.getMessage("app.commands").replace("{0}","user, add, search, edit, delete, list, locale"));
    return 0;
  }

  public static int init(String[] args) {
    return new CommandLine(getBean(PhoneBookCLI.class))
        .addSubcommand("command.add", getBean(AddContact.class))
        .addSubcommand("command.search", getBean(SearchContact.class))
        .addSubcommand("command.edit", getBean(EditContact.class))
        .addSubcommand("command.delete", getBean(DeleteContact.class))
        .addSubcommand("command.list", getBean(ListContacts.class))
        .addSubcommand("command.user", getBean(SetUser.class))
        .addSubcommand("command.locale", getBean(SetLocale.class))
        .execute(args);
  }
}
