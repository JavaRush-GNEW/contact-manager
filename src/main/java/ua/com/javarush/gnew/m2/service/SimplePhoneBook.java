package ua.com.javarush.gnew.m2.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.repository.ContactDtoRepository;
import ua.com.javarush.gnew.m2.repository.FileContactDtoRepository;

public class SimplePhoneBook implements PhoneBookInterface {

  ContactDtoRepository contactDtoRepository = new FileContactDtoRepository();

  @Override
  public ContactDto add(ContactDto contactDto) throws IOException {
    contactDtoRepository.save(contactDto);
    return contactDto;
  }

  @Override
  public List<ContactDto> search(String str) throws IOException {
    return contactDtoRepository.findByKeyword(str);
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
