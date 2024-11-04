package ua.com.javarush.gnew.m2.Repository;

import ua.com.javarush.gnew.m2.dto.ContactDto;

import java.io.IOException;

public interface ContactDtoRepository {
    void save(ContactDto contact) throws IOException;
    ContactDto load() throws IOException;
    ContactDto findById(long id) throws IOException;

}
