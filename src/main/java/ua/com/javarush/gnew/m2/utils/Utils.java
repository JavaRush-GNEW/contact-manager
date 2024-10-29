package ua.com.javarush.gnew.m2.utils;

import ua.com.javarush.gnew.m2.entity.Contact;

import java.util.List;

public class Utils {
   private static final String format = "| %-10s | %-20s | %-30s | %-45s| %n";

    public static void printContactToTableRow(Contact contact) {
        System.out.format(format, contact.getId(),
                contact.getFullName(),
                joining(contact.getPhones()),
                joining(contact.getEmails()));
    }
    public static void printContactList(List<Contact> contacts){
        printContactHeaders();
        contacts.forEach(Utils::printContactToTableRow);
    }
    private static void printContactHeaders() {
        System.out.format(format,
                "ID",
                "FULL NAME",
               "PHONE",
                "EMAIL");
    }

    public static String getFormattedStringFromContact(Contact contact) {
       return String.format(format, contact.getId(),
                contact.getFullName(),
                joining(contact.getPhones()),
                joining(contact.getEmails()));
    }



    private static String joining(List<String> str){
      return String.join(", ", str);
    }
}
