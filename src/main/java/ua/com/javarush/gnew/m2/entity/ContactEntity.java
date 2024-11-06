package ua.com.javarush.gnew.m2.entity;

import java.util.List;

public interface ContactEntity {

  String getId();

  String getFullName();

  List<PhoneNumber> getPhones();

  List<Email> getEmails();

  void setFullName(String fullName);

  void setPhones(List<PhoneNumber> phones);

  void setEmails(List<Email> emails);
}
