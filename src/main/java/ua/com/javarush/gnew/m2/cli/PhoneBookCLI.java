package ua.com.javarush.gnew.m2.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import ua.com.javarush.gnew.m2.cli.commands.AddContact;
import ua.com.javarush.gnew.m2.cli.commands.DeleteContact;
import ua.com.javarush.gnew.m2.cli.commands.EditContact;
import ua.com.javarush.gnew.m2.cli.commands.ListContacts;
import ua.com.javarush.gnew.m2.cli.commands.SearchContact;
import ua.com.javarush.gnew.m2.cli.commands.SetUser;
import ua.com.javarush.gnew.m2.repository.ForSettingsRepository;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;
import ua.com.javarush.gnew.m2.service.SettingsService;
import ua.com.javarush.gnew.m2.service.SettingsServiceInterface;
import ua.com.javarush.gnew.m2.service.SimplePhoneBook;

import java.util.concurrent.Callable;

@Command(
        name = "phonebook",
        mixinStandardHelpOptions = true,
        version = "PhoneBook CLI 1.0",
        description = "CLI для управління контактами в телефонній книзі"
)
public class PhoneBookCLI implements CliCommand {

  private final SettingsServiceInterface settingsService;

  public PhoneBookCLI(SettingsServiceInterface settingsService) {
    this.settingsService = settingsService;
  }

  @Override
  public Integer call() {
    System.out.println("Використовуйте одну з команд: user, add, search, edit, delete, list, locale");
    return 0;
  }

  public static int init(String[] args) {

    PhoneBookInterface phoneBook = new SimplePhoneBook();
      SettingsServiceInterface settingsService = new SettingsService(new ForSettingsRepository());

    return new CommandLine(new PhoneBookCLI(settingsService))
            .addSubcommand("add", new AddContact(phoneBook))
            .addSubcommand("search", new SearchContact(phoneBook))
            .addSubcommand("edit", new EditContact(phoneBook))
            .addSubcommand("delete", new DeleteContact(phoneBook))
            .addSubcommand("list", new ListContacts(phoneBook))
            .addSubcommand("user", new SetUser())
            .addSubcommand("locale", (Callable<Integer>) () -> {
              settingsService.setLocale("uk");
              System.out.println("Locale set to Ukrainian.");
              return 0;
            })
            .execute(args);
  }
}
