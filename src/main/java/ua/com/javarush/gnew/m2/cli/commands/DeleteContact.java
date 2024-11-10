package ua.com.javarush.gnew.m2.cli.commands;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.List;

import lombok.SneakyThrows;
import ua.com.javarush.gnew.m2.cli.CliCommand;
import ua.com.javarush.gnew.m2.configuration.PhoneBookContext;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;
import ua.com.javarush.gnew.m2.utils.Utils;

@Command(
    name = "--delete",
    aliases = {"-d", "--delete"},
    description = "Видаляє контакт за ID",
    mixinStandardHelpOptions = true)
public class DeleteContact implements CliCommand {

  private final PhoneBookInterface phoneBookInterface =
      PhoneBookContext.getBean(PhoneBookInterface.class);

  @Parameters(index = "0", description = "ID контакта", arity = "1..*")
  private List<Long> listId;


  @Override
  public Integer call() throws IOException {
    listId.forEach(id -> {
      try {
        phoneBookInterface.delete(id);
      } catch (IOException e) {
        System.err.println("Ошибка при удалении контакта с ID " + id + ": " + e.getMessage());
      }
    });
    System.out.println("Контакт(и) видалено:");
    Utils.printContactList(phoneBookInterface.list());
    return 0;
  }
}
