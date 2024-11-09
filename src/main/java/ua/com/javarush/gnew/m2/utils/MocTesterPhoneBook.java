package ua.com.javarush.gnew.m2.utils;

import ua.com.javarush.gnew.m2.configuration.PhoneBookContext;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.repository.ContactDtoRepository;
import ua.com.javarush.gnew.m2.repository.FileContactDtoRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MocTesterPhoneBook {

  public static void createTester() throws IOException {
    File file = new File("testerbook.st");
    if (!file.exists()) {
      ContactDtoRepository repository = new FileContactDtoRepository("tester");
      repository.save(
          ContactDto.builder()
              .fullName("Chris Hemsworth")
              .phones(List.of("+380671111111", "+380672222222"))
              .emails(List.of("chris.h@m.ua", "chris.h@gmail.com"))
              .build());
      repository.save(
              ContactDto.builder()
                      .fullName("Chris Pratt")
                      .phones(List.of("+380673333333", "+380674444444"))
                      .emails(List.of("chris.p@m.ua", "chris.p@gmail.com"))
                      .build());
      repository.save(
              ContactDto.builder()
                      .fullName("Scarlett Johansson")
                      .phones(List.of("+380675555555", "+380676666666"))
                      .emails(List.of("Scarlett.j@m.ua", "Scarlett.j@gmail.com"))
                      .build());
      repository.save(
              ContactDto.builder()
                      .fullName("Jeremy Renner")
                      .phones(List.of("+380677777777", "+380678888888"))
                      .emails(List.of("Jeremy.r@m.ua", "Jeremy.r@gmail.com"))
                      .build());
    }
  }
}
