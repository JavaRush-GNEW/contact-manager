package ua.com.javarush.gnew.m2.cli.commands;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Option;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import ua.com.javarush.gnew.m2.cli.CliCommand;
import ua.com.javarush.gnew.m2.configuration.PhoneBookContext;
import ua.com.javarush.gnew.m2.repository.GroupContactsRepository;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;
import ua.com.javarush.gnew.m2.utils.Utils;

@Command(
    name = "group",
    aliases = {"-gr", "--group"},
    description = "Керування групами контактів ",
    mixinStandardHelpOptions = true)
public class GroupContact implements CliCommand {
  private final GroupContactsRepository groupContactsRepository =
      PhoneBookContext.getBean(GroupContactsRepository.class);
  private final PhoneBookInterface phoneBookInterface =
      PhoneBookContext.getBean(PhoneBookInterface.class);

  @Option(
      names = {"-c", "--create"},
      description = "Створити групу контактів",
      required = false)
  private String newGroup;

  @Option(
      names = {"-d", "--delete"},
      description = "Номер телефону",
      required = false)
  private String delGroup;

  @Option(
      names = {"-a", "--add"},
      description = "Додати перелік контактів до групи",
      required = false,
      arity = "2..10")
  private List<String> addArgs = new ArrayList<>();

  @Option(
      names = {"-rm", "--remove"},
      description = "Додати перелік контактів до групи",
      required = false,
      arity = "2..10")
  private List<String> delArgs = new ArrayList<>();

  @Override
  public Integer call() {
    try {

      if (newGroup != null) {
        groupContactsRepository.create(newGroup);
        System.out.println("Cворено нову групу " + newGroup);
      } else if (delGroup != null) {
        groupContactsRepository.delete(delGroup);
        System.out.println("Видалено групу " + delGroup);
      } else if (addArgs.size() >= 2) {
        String group = addArgs.get(0);
        addArgs.remove(0);
        addArgs.stream()
            .forEach(id -> groupContactsRepository.addContact(group, Integer.parseInt(id)));
        System.out.println("Контакти додано до групи " + group);
      } else if (delArgs.size() >= 2) {
        String group = delArgs.get(0);
        delArgs.remove(0);
        delArgs.stream()
            .forEach(id -> groupContactsRepository.deleteContact(group, Integer.parseInt(id)));
        System.out.println("Контакти видалено з групи " + group);
      } else {
        groupContactsRepository.getGroups().stream()
            .forEach(
                g -> {
                  System.out.println();
                  System.out.println("### " + g + "###");
                  //                  System.out.println();
                  Utils.printContactList(
                      groupContactsRepository.getContactsId(g).stream()
                          .map(
                              id -> {
                                try {
                                  return phoneBookInterface.getById(id).get();
                                } catch (IOException e) {
                                  throw new RuntimeException(e);
                                }
                              })
                          .filter(Objects::nonNull)
                          .collect(Collectors.toList()));
                });
      }

    } catch (Exception e) {
      System.out.println("Помилка, невірний формат: ");

      throw new RuntimeException(e);
    }
    return 0;
  }
}
