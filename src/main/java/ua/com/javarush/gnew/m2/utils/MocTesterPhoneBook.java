package ua.com.javarush.gnew.m2.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.repository.ContactDtoRepository;
import ua.com.javarush.gnew.m2.repository.FileContactDtoRepository;

public class MocTesterPhoneBook {

  public static void createTester() throws IOException {
    File file = new File("testerbook.st");
    ContactDtoRepository repository = new FileContactDtoRepository("tester");

    if (!file.exists()) {
      repository.save(
          new ContactDto(
              "Chris Hemsworth",
              List.of("+380671111111", "+380672222222"),
              List.of("chris.h@m.ua", "chris.h@gmail.com"),
              "ChrisHemGit"));
      repository.save(
          new ContactDto(
              "Chris Pratt",
              List.of("+380673333333", "+380674444444"),
              List.of("chris.p@m.ua", "chris.p@gmail.com"),
              "ChrisPraGit"));
      repository.save(
          new ContactDto(
              "Scarlett Johansson",
              List.of("+380675555555", "+380676666666"),
              List.of("Scarlett.j@m.ua", "Scarlett.j@gmail.com"),
              "ScarlettJohGit"));
      repository.save(
          new ContactDto(
              "Jeremy Renner",
              List.of("+380677777777", "+380678888888"),
              List.of("Jeremy.r@m.ua", "Jeremy.r@gmail.com"),
              "JeremyRenGit"));
    }
  }
}
