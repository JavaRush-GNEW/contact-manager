package ua.com.javarush.gnew.m2;

import java.io.IOException;
import ua.com.javarush.gnew.m2.cli.PhoneBookCLI;
import ua.com.javarush.gnew.m2.configuration.PhoneBookContext;


public class Main {
  public static void main(String[] args) throws IOException {
    PhoneBookContext.create();
    PhoneBookCLI.init(args);
  }
}
