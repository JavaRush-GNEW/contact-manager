package ua.com.javarush.gnew.m2.repository;

import java.util.List;

public interface GroupContactsRepository {
  String create(String group);

  String delete(String group);

  boolean addContact(String group, long id);

  boolean deleteContact(String group, long id);

  List<String> getGroups();

  List<Long> getContactsId(String group);
}
