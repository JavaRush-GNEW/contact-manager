package ua.com.javarush.gnew.m2;


import picocli.CommandLine;
import ua.com.javarush.gnew.m2.cli.PhoneBookCLI;
import ua.com.javarush.gnew.m2.cli.commands.*;
import ua.com.javarush.gnew.m2.service.PhoneBookInterface;
import ua.com.javarush.gnew.m2.service.SimplePhoneBook;

public class Main {
    public static void main(String[] args) {
        PhoneBookCLI.init(args);
    }
}
