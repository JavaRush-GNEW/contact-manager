package ua.com.javarush.gnew.m2.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import ua.com.javarush.gnew.m2.cli.PhoneBookCLI;
import ua.com.javarush.gnew.m2.cli.commands.*;
import ua.com.javarush.gnew.m2.repository.*;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;
import ua.com.javarush.gnew.m2.service.SettingsService;
import ua.com.javarush.gnew.m2.service.SettingsServiceInterface;
import ua.com.javarush.gnew.m2.service.SimplePhoneBook;
import ua.com.javarush.gnew.m2.utils.MocTesterPhoneBook;

public class PhoneBookContext {
  private static Map<Class, Object> context = new HashMap<>();

  public static void create() throws IOException {

    SettingsRepository settingsRepository = new FileSettingsRepository();
    Optional<String> optionalUser =
        Optional.ofNullable(settingsRepository.loadSingleSetting("user"));
    addBean(SettingsRepository.class, settingsRepository);

    ContactDtoRepository contactDtoRepository =
        new FileContactDtoRepository(optionalUser.orElse("tester"));
    addBean(ContactDtoRepository.class, contactDtoRepository);

    GroupContactsRepository groupContactsRepository =
        new FileGroupContactsRepository(optionalUser.orElse("tester"));
    addBean(GroupContactsRepository.class, groupContactsRepository);

    PhoneBookInterface phoneBook = new SimplePhoneBook();
    addBean(PhoneBookInterface.class, phoneBook);

    SettingsServiceInterface serviceInterface = new SettingsService();
    addBean(SettingsServiceInterface.class, serviceInterface);

    addBean(PhoneBookCLI.class, new PhoneBookCLI());
    addBean(AddContact.class, new AddContact());
    addBean(DeleteContact.class, new DeleteContact());
    addBean(EditContact.class, new EditContact());
    addBean(EditContactMenu.class, new EditContactMenu());
    addBean(ListContacts.class, new ListContacts());
    addBean(SearchContact.class, new SearchContact());
    addBean(SetLocale.class, new SetLocale());
    addBean(SetUser.class, new SetUser());
    addBean(GroupContact.class, new GroupContact());

    try {
      MocTesterPhoneBook.createTester();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void addBean(Class clazz, Object object) {
    context.put(clazz, object);
  }

  public static <T> T getBean(Class<T> clazz) {
    return clazz.cast(context.get(clazz));
  }
}
