package ua.com.javarush.gnew.m2.service;

import ua.com.javarush.gnew.m2.entity.Contact;
import ua.com.javarush.gnew.m2.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MocPhoneBookService implements PhoneBookInterface{

   final private List<Contact> phoneBook =new ArrayList<>(){{
        add(new Contact("12340","Chris Hemsworth",
                new String[]{"+380671111111","+380672222222"},
                new String[]{"chris.h@m.ua","chris.h@gmail.com"}));
        add(new Contact("12341","Chris Pratt",
                new String[]{"+380673333333","+380674444444"},
                new String[]{"chris.p@m.ua","chris.p@gmail.com"}));
        add(new Contact("12342","Scarlett Johansson",
                new String[]{"+380675555555","+380676666666"},
                new String[]{"Scarlett.j@m.ua","Scarlett.j@gmail.com"}));
        add(new Contact("12343","Jeremy Renner",
                new String[]{"+380677777777","+380678888888"},
                new String[]{"Jeremy.r@m.ua","Jeremy.r@gmail.com"}));
    }};




    @Override
    public void add(Contact contact) {

    }

    @Override
    public List<Contact> search(String str) {
       return phoneBook.stream()
                .filter(c-> Utils.getFormattedStringFromContact(c).contains(str))
                .collect(Collectors.toList());
    }

    @Override
    public void edit(Contact contact) {
    }

    @Override
    public void delete(String id) {
        phoneBook.stream()
                .filter(c->c.getId().equals(id))
                .findFirst()
                .ifPresent((c)->phoneBook.remove(c));
    }

    @Override
    public List<Contact> list() {
        return phoneBook;
    }

    @Override
    public Optional<Contact> getById(String id) {
       return phoneBook.stream()
               .filter(c->c.getId().equals(id))
               .findFirst();
    }
}
