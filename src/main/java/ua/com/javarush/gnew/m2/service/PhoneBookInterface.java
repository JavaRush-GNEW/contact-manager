package ua.com.javarush.gnew.m2.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import ua.com.javarush.gnew.m2.dto.ContactDto;

public interface PhoneBookInterface {

  ContactDto add(ContactDto contactDto) throws IOException;

  List<ContactDto> search(String str) throws IOException;

  void edit(ContactDto contact);

  void delete(long id);

  List<ContactDto> list();

  Optional<ContactDto> getById(long id);
}
