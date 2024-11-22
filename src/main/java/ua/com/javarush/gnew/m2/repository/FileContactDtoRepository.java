package ua.com.javarush.gnew.m2.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import ua.com.javarush.gnew.m2.dto.ContactDto;

public class FileContactDtoRepository implements ContactDtoRepository {
  ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

  private final String user;

  public FileContactDtoRepository(String user) {
    this.user = user;
  }

  @Override
  public List<ContactDto> findAll() {
    List<ContactDto> result;
    File file = new File(user + "book.st");
    try {
      result =
          objectMapper.readValue(
              file,
              objectMapper.getTypeFactory().constructCollectionType(List.class, ContactDto.class));
    } catch (IOException e) {
      result = new ArrayList<>();
    }
    return result;
  }

  @Override
  public Optional<ContactDto> findById(long id) {
    return findAll().stream().filter(contact -> contact.getId() == id).findFirst();
  }

  @Override
  public void deleteById(long id) throws IOException {
    List<ContactDto> contacts = findAll();
    contacts.removeIf(contact -> contact.getId() == id);
    saveAll(contacts);
  }

  @Override
  public void saveAll(List<ContactDto> contacts) throws IOException {
    objectMapper.writeValue(new File(user + "book.st"), contacts);
  }

  @Override
  public ContactDto save(ContactDto contactDto) throws IOException {
    List<ContactDto> contacts = findAll();
    Optional<ContactDto> optional =
        contacts.stream().filter(c -> c.getId() == contactDto.getId()).findFirst();
    if (optional.isEmpty()) {
      contactDto.setId((long) (Math.random() * 10000));
      contacts.add(contactDto);
    } else {
      contacts.set(contacts.indexOf(optional.get()), contactDto);
    }
    saveAll(contacts);
    return contactDto;
  }

  @Override
  public List<ContactDto> findByKeyword(String keyword) {
    List<ContactDto> contacts = findAll();
    return contacts.stream()
        .filter(
            contact ->
                contact.getFullName().contains(keyword)
                    || contact.getPhones().stream().anyMatch(phones -> phones.contains(keyword))
                    || contact.getEmails().stream().anyMatch(emails -> emails.contains(keyword))
                    || (contact.getGithubId() != null && contact.getGithubId().contains(keyword)))
        .collect(Collectors.toList());
  }
}
