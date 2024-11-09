package ua.com.javarush.gnew.m2.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import ua.com.javarush.gnew.m2.cli.PhoneBookCLI;
import ua.com.javarush.gnew.m2.cli.commands.*;
import ua.com.javarush.gnew.m2.repository.ContactDtoRepository;
import ua.com.javarush.gnew.m2.repository.FileContactDtoRepository;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;
import ua.com.javarush.gnew.m2.service.SimplePhoneBook;
import ua.com.javarush.gnew.m2.utils.MocTesterPhoneBook;

public class PhoneBookContext {
  private static Map<Class, Object> context = new HashMap<>();

  public static void create() {

    ContactDtoRepository contactDtoRepository= new FileContactDtoRepository("tester");

    addBean(ContactDtoRepository.class, contactDtoRepository);
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


      try {
          MocTesterPhoneBook.createTester();
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
  }

  private static void addBean(Class clazz, Object object) {
    context.put(clazz, object);
  }

  public static <T> T getBean(Class<T> clazz) {
    return clazz.cast(context.get(clazz));
  }
}
