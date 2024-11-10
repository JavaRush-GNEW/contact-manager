package ua.com.javarush.gnew.m2.repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import ua.com.javarush.gnew.m2.dto.ContactDto;

public interface ContactDtoRepository {

  List<ContactDto> findAll();

  Optional<ContactDto> findById(long id) throws IOException;

  void deleteById(long id) throws IOException;

  void saveAll(List<ContactDto> contacts) throws IOException;

  void save(ContactDto contactDto) throws IOException;

  List<ContactDto> findByKeyword(String keyword) throws IOException;
}
