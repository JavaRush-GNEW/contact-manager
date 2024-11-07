package ua.com.javarush.gnew.m2.configuration;

import java.util.HashMap;
import java.util.Map;
import ua.com.javarush.gnew.m2.cli.PhoneBookCLI;
import ua.com.javarush.gnew.m2.cli.commands.*;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;
import ua.com.javarush.gnew.m2.service.SimplePhoneBook;

public class PhoneBookContext {
  private static Map<Class, Object> context = new HashMap<>();

  public static void create() {
    PhoneBookInterface phoneBook = new SimplePhoneBook();

    addBean(PhoneBookCLI.class, new PhoneBookCLI());
    addBean(AddContact.class, new AddContact(phoneBook));
    addBean(DeleteContact.class, new DeleteContact(phoneBook));
    addBean(EditContact.class, new EditContact(phoneBook));
    addBean(EditContactMenu.class, new EditContactMenu(phoneBook));
    addBean(ListContacts.class, new ListContacts(phoneBook));
    addBean(SearchContact.class, new SearchContact(phoneBook));
    addBean(SetUser.class, new SetUser());
    addBean(PhoneBookInterface.class, phoneBook);
  }

  private static void addBean(Class clazz, Object object) {
    context.put(clazz, object);
  }

  public static <T> T getBean(Class<T> clazz) {
    return clazz.cast(context.get(clazz));
  }
}
