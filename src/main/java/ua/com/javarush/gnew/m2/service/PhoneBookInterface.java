package ua.com.javarush.gnew.m2.service;

import ua.com.javarush.gnew.m2.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface PhoneBookInterface {

    Contact add(String fullName, List<String> phones, List<String> emails);
    List<Contact> search(String str);
    void edit(Contact contact);
    void delete(String id);
    List<Contact> list ();
    Optional<Contact> getById(String id);
}
