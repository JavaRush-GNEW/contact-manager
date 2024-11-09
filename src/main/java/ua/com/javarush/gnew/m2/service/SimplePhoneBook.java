package ua.com.javarush.gnew.m2.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import ua.com.javarush.gnew.m2.configuration.PhoneBookContext;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.repository.ContactDtoRepository;
import ua.com.javarush.gnew.m2.repository.FileContactDtoRepository;

public class SimplePhoneBook implements PhoneBookInterface {
  ContactDtoRepository contactDtoRepository = PhoneBookContext.getBean(ContactDtoRepository.class);
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
    try {
      return contactDtoRepository.findAll();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Optional<ContactDto> getById(long id) {
    return Optional.empty();
  }
}
