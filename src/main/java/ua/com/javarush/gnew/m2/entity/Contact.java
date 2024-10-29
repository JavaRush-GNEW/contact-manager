package ua.com.javarush.gnew.m2.entity;

import java.util.List;

public interface Contact {

    String getId();

    String getFullName();

    List<String> getPhones();

    List<String> getEmails();

    void setId(String id);

    void setFullName(String fullName);

    void setPhones(List<String> phones);

    void setEmails(List<String> emails);
}
