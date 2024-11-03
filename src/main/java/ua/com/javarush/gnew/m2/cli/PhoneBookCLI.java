package ua.com.javarush.gnew.m2.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import ua.com.javarush.gnew.m2.cli.commands.*;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;
import ua.com.javarush.gnew.m2.service.SimplePhoneBook;

@Command(name = "phonebook", mixinStandardHelpOptions = true, version = "PhoneBook CLI 1.0",
        description = "CLI для управління контактами в телефонній книзі")
public class PhoneBookCLI implements CliCommand {

    @Override
    public Integer call() {
        System.out.println("Використовуйте одну з команд: user, add, search, edit, delete, list");
        return 0;
    }

    public static int  init(String[] args){

        PhoneBookInterface phoneBook = new SimplePhoneBook();

       return new CommandLine(new PhoneBookCLI())
                .addSubcommand("add", new AddContact(phoneBook))
                .addSubcommand("search", new SearchContact(phoneBook))
                .addSubcommand("edit", new EditContact(phoneBook))
                .addSubcommand("delete", new DeleteContact(phoneBook))
                .addSubcommand("list", new ListContacts(phoneBook))
                .addSubcommand("user", new SetUser())
                .execute(args);
    }
}
