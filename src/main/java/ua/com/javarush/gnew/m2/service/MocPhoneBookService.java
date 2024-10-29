package ua.com.javarush.gnew.m2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import ua.com.javarush.gnew.m2.config.PhoneBookContext;
import ua.com.javarush.gnew.m2.entity.Contact;
import ua.com.javarush.gnew.m2.entity.SimpleContact;
import ua.com.javarush.gnew.m2.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class MocPhoneBookService implements PhoneBookInterface {

    private final PhoneBookContext  context;
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    final private List<Contact> phoneBook = new ArrayList<>();
//        {{
//        add(new SimpleContact("12340", "Chris Hemsworth",
//                List.of("+380671111111", "+380672222222"),
//                List.of("chris.h@m.ua", "chris.h@gmail.com")));
//        add(new SimpleContact("12341", "Chris Pratt",
//                List.of("+380673333333", "+380674444444"),
//                List.of("chris.p@m.ua", "chris.p@gmail.com")));
//        add(new SimpleContact("12342", "Scarlett Johansson",
//                List.of("+380675555555", "+380676666666"),
//                List.of("Scarlett.j@m.ua", "Scarlett.j@gmail.com")));
//        add(new SimpleContact("12343", "Jeremy Renner",
//                List.of("+380677777777", "+380678888888"),
//                List.of("Jeremy.r@m.ua", "Jeremy.r@gmail.com")));
//    }};


    @SneakyThrows
    public MocPhoneBookService(PhoneBookContext context) {
        this.context = context;
//        saveContactsToFile(phoneBook,context.getUser()+"phonebook.book");
        phoneBook.addAll(loadContactsFromFile(context.getUser()+"phonebook.book"));


    }

    @Override
    public Contact add(String fullName, List<String> phones, List<String> emails) {
        Contact contact = new SimpleContact(fullName,phones,emails);
        phoneBook.add(contact);
        try {
            saveContactsToFile(phoneBook, context.getUser()+"phonebook.book");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contact;

    }

    @Override
    public List<Contact> search(String str) {
        return phoneBook.stream()
                .filter(c -> Utils.getFormattedStringFromContact(c).contains(str))
                .collect(Collectors.toList());
    }

    @Override
    public void edit(Contact contact) {
//    phoneBook.stream()
//                .filter(c->c.getId().equals(contact.getId()))
//                .findFirst()
//               .ifPresent(c->c=contact);
        try {
            saveContactsToFile(phoneBook, context.getUser()+"phonebook.book");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(String id) {
        phoneBook.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .ifPresent(phoneBook::remove);
        try {
            saveContactsToFile(phoneBook, context.getUser()+"phonebook.book");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Contact> list() {
        //            this.phoneBook.addAll(loadContactsFromFile(context.getUser()+"phonebook.book"));
        return phoneBook;
    }

    @Override
    public Optional<Contact> getById(String id) {
        return phoneBook.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public void saveContactsToFile(List<Contact> contacts, String filename) throws IOException {
        objectMapper.writeValue(new File(filename), contacts);
    }
    public List<SimpleContact> loadContactsFromFile(String filename) throws IOException {
        return objectMapper.readValue(new File(filename), new TypeReference<>() {
        });
    }
}
