package ua.com.javarush.gnew.m2.cli;

import static ua.com.javarush.gnew.m2.configuration.PhoneBookContext.getBean;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import ua.com.javarush.gnew.m2.cli.commands.*;

@Command(
    name = "phonebook",
    mixinStandardHelpOptions = true,
    version = "PhoneBook CLI 1.0",
    description = "CLI для управління контактами в телефонній книзі")
public class PhoneBookCLI implements CliCommand {

  @Override
  public Integer call() {
    System.out.println("Використовуйте одну з команд: user, add, search, edit, delete, list");
    return 0;
  }

  public static int init(String[] args) {
    // > -a "Name" -p 05641454532435 45646545646
    return new CommandLine(getBean(PhoneBookCLI.class))
        .addSubcommand("add", getBean(AddContact.class))
        .addSubcommand("search", getBean(SearchContact.class))
        .addSubcommand("edit", getBean(EditContact.class))
        .addSubcommand("delete", getBean(DeleteContact.class))
        .addSubcommand("list", getBean(ListContacts.class))
        .addSubcommand("user", getBean(SetUser.class))
            .addSubcommand("language", getBean(SetLocale.class))
        .execute(args);
  }
}
