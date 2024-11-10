package ua.com.javarush.gnew.m2.cli.commands;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import lombok.Data;
import ua.com.javarush.gnew.m2.cli.CliCommand;
import ua.com.javarush.gnew.m2.configuration.PhoneBookContext;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;
import ua.com.javarush.gnew.m2.utils.Utils;

@Data
@Command(name = "--edit-menu", description = "Редагує існуючий контакт за ім'ям")
public class EditContactMenu implements CliCommand {
  private final PhoneBookInterface phoneBookInterface =
      PhoneBookContext.getBean(PhoneBookInterface.class);

  private ContactDto contact;

  Scanner scanner = new Scanner(System.in);

  @Parameters(index = "0", description = "ID контакта", arity = "1")
  private String choice;

  @Override
  public Integer call() throws IOException {

    switch (choice) {
      case "1":
        System.out.print("1. Введiть нове iм'я: ");
        String change = scanner.nextLine();
        contact.setFullName(change);
        update(contact);
        break;
      case "2":
        System.out.print("2. Введiть телефони: ");
        String stringPhones = scanner.nextLine();
        List<String> phones = List.of(stringPhones.split(" "));
        contact.setPhones(phones);
        update(contact);
        break;
      case "3":
        System.out.print("2. Введiть електронну пошту: ");
        String stringEmails = scanner.nextLine();
        List<String> emails = List.of(stringEmails.split(" "));
        contact.setEmails(emails);
        update(contact);
        break;

      default:
        System.out.println("Неправильний вибір. Спробуйте ще раз.");
    }

    return 0;
  }

  private void update(ContactDto contact) throws IOException {
    phoneBookInterface.edit(contact);
    System.out.println("Контакт оновлено: ");
    Utils.printContactList(List.of(contact));
  }
}
