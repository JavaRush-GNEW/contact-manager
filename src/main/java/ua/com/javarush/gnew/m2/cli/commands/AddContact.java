package ua.com.javarush.gnew.m2.cli.commands;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Option;

import java.util.ArrayList;
import java.util.List;
import ua.com.javarush.gnew.m2.cli.CliCommand;
import ua.com.javarush.gnew.m2.configuration.PhoneBookContext;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;
import ua.com.javarush.gnew.m2.utils.Utils;

@Command(
    name = "add",
    aliases = {"-a", "--add"},
    description = "Додає новий контакт до телефонної книги",
    mixinStandardHelpOptions = true)
public class AddContact implements CliCommand {
  private final PhoneBookInterface phoneBookInterface =
      PhoneBookContext.getBean(PhoneBookInterface.class);

  @Option(
      names = {"-n", "--name"},
      description = "Ім'я контакту",
      required = true)
  private String name;

  @Option(
      names = {"-p", "--phone"},
      description = "Номер телефону",
      arity = "0..3")
  private List<String> phones = new ArrayList<>();

  @Option(
      names = {"-e", "--email"},
      description = "Електронна пошта",
      arity = "0..3")
  private List<String> emails = new ArrayList<>();

  @Option(
      names = {"-g", "--github"},
      description = "GitHub ID",
      defaultValue = "")
  private String githubId;

  @Override
  public Integer call() {
    try {
      ContactDto newContactDto = new ContactDto(name, phones, emails, githubId);
      ContactDto savedContactDto = phoneBookInterface.add(newContactDto);
      System.out.println("Контакт додано: ");
      Utils.printContactList(List.of(savedContactDto));

    } catch (Exception e) {
      System.out.println("Помилка, невірний формат: " + name);

      throw new RuntimeException(e);
    }
    return 0;
  }
}
