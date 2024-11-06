package ua.com.javarush.gnew.m2.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import ua.com.javarush.gnew.m2.dto.ContactDto;

public class SimplePhoneBook implements PhoneBookInterface {
  @Override
  public ContactDto add(ContactDto contactDto) {
    return contactDto;
  }

  @Override
  public List<ContactDto> search(String str) {
    return Collections.emptyList();
  }

  @Override
  public void edit(ContactDto contact) {
    // TODO: implement this method
  }

  @Override
  public void delete(long id) {
    // TODO: implement this method

  }

  @Override
  public List<ContactDto> list() {
    return Collections.emptyList();
  }

  @Override
  public Optional<ContactDto> getById(long id) {
    return Optional.empty();
  }
}
