package ua.com.javarush.gnew.m2.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ua.com.javarush.gnew.m2.dto.ContactDto;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ContactDtoRepository {
    ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

     default List<ContactDto> loadAll() throws IOException {
        return objectMapper.readValue(new File("demo.st"),objectMapper.getTypeFactory().constructCollectionType(List.class, ContactDto.class));
    }

     default ContactDto findById(long id) throws IOException {
        List<ContactDto> contacts = loadAll();
        return contacts.stream().filter(contact -> contact.getId() == id).findFirst().orElse(null);
    }

     default void saveAll(List<ContactDto> contacts) throws IOException {
        objectMapper.writeValue(new File("demo.st"),contacts);
    }


     default void save(ContactDto contact) throws IOException {
        List<ContactDto> contacts = loadAll();
        contacts.add(contact);
        saveAll(contacts);
    }

}
