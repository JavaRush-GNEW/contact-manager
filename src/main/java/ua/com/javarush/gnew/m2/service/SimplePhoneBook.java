package ua.com.javarush.gnew.m2.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.SneakyThrows;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.repository.ContactDtoRepository;

public class SimplePhoneBook implements PhoneBookInterface, ContactDtoRepository {
  @Override
  public ContactDto add(ContactDto contactDto) {
   return ContactDto.builder()
            .fullName(contactDto.getFullName())
            .phones(List.of(String.valueOf(contactDto.getPhones())))
            .emails(List.of(String.valueOf(contactDto.getEmails())))
            .build();
  }

  @SneakyThrows
  @Override
  public List<ContactDto> search(String str) {
    List<ContactDto> contacts = findAll();
    return contacts.stream()
            .filter(contact -> contact.getFullName().contains(str) ||
                    contact.getPhones().stream().anyMatch(phones -> phones.contains(str)) ||
                    contact.getEmails().stream().anyMatch(emails -> emails.contains(str)))
            .collect(Collectors.toList());
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
