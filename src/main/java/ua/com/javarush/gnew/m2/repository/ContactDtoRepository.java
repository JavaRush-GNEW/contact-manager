package ua.com.javarush.gnew.m2.repository;

import ua.com.javarush.gnew.m2.dto.ContactDto;

import java.util.List;
import java.util.Optional;

public interface ContactDtoRepository {
    List<ContactDto> findAll();

    Optional<ContactDto> findById(long id);

    void deleteById(long id);

    void saveAll(List<ContactDto> contacts);

    void save(ContactDto contactDto);

}
