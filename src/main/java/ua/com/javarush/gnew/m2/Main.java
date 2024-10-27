package ua.com.javarush.gnew.m2;

import picocli.CommandLine;
import ua.com.javarush.gnew.m2.cli.PhoneBookCLI;
import ua.com.javarush.gnew.m2.service.MocPhoneBookService;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello dRaider-concept-cli!");

        /**
         * PhoneBook Picocli Cli Example
         */
        PhoneBookInterface phoneBook = new MocPhoneBookService();

        int exitCode = new CommandLine(new PhoneBookCLI())
                .addSubcommand("add", new PhoneBookCLI.AddContact(phoneBook))
                .addSubcommand("search", new PhoneBookCLI.SearchContact(phoneBook))
                .addSubcommand("edit", new PhoneBookCLI.EditContact(phoneBook))
                .addSubcommand("delete", new PhoneBookCLI.DeleteContact(phoneBook))
                .addSubcommand("list", new PhoneBookCLI.ListContacts(phoneBook))
                .execute(args);
        System.exit(exitCode);

    }
}
