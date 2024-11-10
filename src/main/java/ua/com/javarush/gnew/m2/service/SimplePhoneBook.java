package ua.com.javarush.gnew.m2.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import ua.com.javarush.gnew.m2.configuration.PhoneBookContext;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.repository.ContactDtoRepository;

public class SimplePhoneBook implements PhoneBookInterface {

  ContactDtoRepository contactDtoRepository = PhoneBookContext.getBean(ContactDtoRepository.class);

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
  public void delete(long id) throws IOException {
    contactDtoRepository.deleteById(id);
  }

  @Override
  public List<ContactDto> list() throws IOException {
    return contactDtoRepository.findAll();
  }

  @Override
  public Optional<ContactDto> getById(long id) throws IOException {
    return contactDtoRepository.findById(id);
  }
}
