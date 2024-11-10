package ua.com.javarush.gnew.m2.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import ua.com.javarush.gnew.m2.dto.ContactDto;

public interface PhoneBookInterface {

  ContactDto add(ContactDto contactDto) throws IOException;

  List<ContactDto> search(String str) throws IOException;

  void edit(ContactDto contact) throws IOException;

  void delete(long id) throws IOException;

  List<ContactDto> list() throws IOException;

  Optional<ContactDto> getById(long id) throws IOException;
}
