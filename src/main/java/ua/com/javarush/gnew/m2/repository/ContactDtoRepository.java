package ua.com.javarush.gnew.m2.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import ua.com.javarush.gnew.m2.dto.ContactDto;

public interface ContactDtoRepository {
  ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
  @SneakyThrows
  default List<ContactDto> findAll() throws IOException {
    File file = new File("demo.st");
    if (!file.exists()) {
      return new ArrayList<>();
    }

    return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, ContactDto.class));
  }

  @SneakyThrows
  default Optional<ContactDto> findById(long id) throws IOException {
    return findAll().stream().filter(contact -> contact.getId() == id).findFirst();
  }

  default void deleteById(long id) throws IOException {
    Optional<ContactDto> contactToDelete = findById(id);
    if (contactToDelete.isPresent()) {
      List<ContactDto> contacts = findAll();
      contacts.remove(contactToDelete.get());
      saveAll(contacts);
    } else {
      System.out.println("Контакт с id " + id + " не найден.");
    }
  }

  @SneakyThrows
  default void saveAll(List<ContactDto> contacts) throws IOException {
    objectMapper.writeValue(new File("demo.st"),contacts);
  };

  default void save(ContactDto contactDto) throws IOException {
    List<ContactDto> contacts = findAll();
    contacts.add(contactDto);
    saveAll(contacts);
  }

  default List<ContactDto> findByKeyword(String keyword) throws IOException {
    List<ContactDto> contacts = findAll();
    return contacts.stream()
            .filter(contact -> contact.getFullName().contains(keyword) ||
                    contact.getPhones().stream().anyMatch(phones -> phones.contains(keyword)) ||
                    contact.getEmails().stream().anyMatch(emails -> emails.contains(keyword)))
            .collect(Collectors.toList());
  }
}
