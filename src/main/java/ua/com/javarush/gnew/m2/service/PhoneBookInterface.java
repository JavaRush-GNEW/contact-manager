package ua.com.javarush.gnew.m2.service;

import java.util.List;
import java.util.Optional;
import ua.com.javarush.gnew.m2.dto.ContactDto;

public interface PhoneBookInterface {

  ContactDto add(ContactDto contactDto);

  List<ContactDto> search(String str);

  void edit(ContactDto contact);

  void delete(long id);

  List<ContactDto> list();

  Optional<ContactDto> getById(long id);
}
