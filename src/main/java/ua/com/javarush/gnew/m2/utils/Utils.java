package ua.com.javarush.gnew.m2.utils;

import java.util.List;
import ua.com.javarush.gnew.m2.dto.ContactDto;

public class Utils {
  private static final String format = "| %-10s | %-20s | %-15s | %-30s | %-45s| %n";

  public static void printContactToTableRow(ContactDto contact) {
    System.out.format(
        format,
        contact.getId(),
        contact.getFullName(),
        contact.getGithubId(),
        joining(contact.getPhones()),
        joining(contact.getEmails()));
  }

  public static void printContactList(List<ContactDto> contacts) {
    printContactHeaders();
    contacts.forEach(Utils::printContactToTableRow);
  }

  private static void printContactHeaders() {
    System.out.format(format, "ID", "FULL NAME", "GITHUB ID", "PHONE", "EMAIL");
  }

  public static String getFormattedStringFromContact(ContactDto contact) {
    return String.format(
        format,
        contact.getId(),
        contact.getFullName(),
        contact.getGithubId(),
        joining(contact.getPhones()),
        joining(contact.getEmails()));
  }

  private static String joining(List<String> str) {
    return String.join(", ", str);
  }
}
