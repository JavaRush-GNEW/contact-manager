package ua.com.javarush.gnew.m2.repository;

import java.util.List;

public interface GroupContactsRepository {
  String create(String group);

  String delete(String group);

  String addContact(String group, long id);

  String deleteContact(String group, long id);

  List<String> getGroups();

  List<String> getContactsId(String group);
}
