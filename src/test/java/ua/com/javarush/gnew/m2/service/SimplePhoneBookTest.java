package ua.com.javarush.gnew.m2.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.javarush.gnew.m2.configuration.PhoneBookContext;
import ua.com.javarush.gnew.m2.dto.ContactDto;
import ua.com.javarush.gnew.m2.repository.ContactDtoRepository;

@ExtendWith(MockitoExtension.class)
class SimplePhoneBookTest {
  @Mock ContactDtoRepository contactDtoRepository;

  PhoneBookInterface phoneBook;
  ContactDto contact =
      new ContactDto(
          "Chris Hemsworth",
          List.of("+380671111111", "+380672222222"),
          List.of("chris.h@m.ua", "chris.h@gmail.com"),
          "ChrisHemGit");

  @BeforeEach
  void beforeEach() {
    try (MockedStatic<PhoneBookContext> phone = Mockito.mockStatic(PhoneBookContext.class)) {
      phone
          .when(
              () -> {
                PhoneBookContext.getBean(ContactDtoRepository.class);
              })
          .thenReturn(contactDtoRepository);
      phoneBook = new SimplePhoneBook();
    }
  }

  @Test
  void add() throws IOException {
    phoneBook.add(contact);
    Mockito.verify(contactDtoRepository, Mockito.times(1)).save(contact);
  }

  @Test
  void search() throws IOException {

    phoneBook.search("Hello");
    Mockito.verify(contactDtoRepository, Mockito.times(1)).findByKeyword("Hello");
  }

  @Test
  void edit() throws IOException {

    phoneBook.edit(contact);
    Mockito.verify(contactDtoRepository, Mockito.times(1)).save(contact);
  }

  @Test
  void delete() throws IOException {

    phoneBook.delete(1);
    Mockito.verify(contactDtoRepository, Mockito.times(1)).deleteById(1);
  }

  @Test
  void list() throws IOException {

    List<ContactDto> mockList =
        new ArrayList<>() {
          {
            add(contact);
            add(contact);
          }
        };

    Mockito.doReturn(mockList).when(contactDtoRepository).findAll();
    List<ContactDto> contacts = phoneBook.list();
    assertEquals(mockList, contacts);
  }

  @Test
  void getById() throws IOException {

    Mockito.doReturn(Optional.ofNullable(contact)).when(contactDtoRepository).findById(1);

    Optional<ContactDto> contactDto = phoneBook.getById(1);

    assertFalse(contactDto.isEmpty());

    assertEquals(contact, contactDto.get());
  }
}
